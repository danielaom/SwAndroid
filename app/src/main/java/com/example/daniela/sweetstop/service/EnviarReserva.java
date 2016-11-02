package com.example.daniela.sweetstop.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.daniela.sweetstop.MainActivity;
import com.example.daniela.sweetstop.SwAndroid;
import com.example.daniela.sweetstop.utilitarios.VariablesConstantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_RESERVA;

/**
 * Created by gonzalopro on 11/1/16.
 */

public class EnviarReserva extends AsyncTask<Void,Void,Void> {

    private Context context;
    private ProgressDialog progressDialog;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder result;
    private int idUsuario;
    private String idMesa;
    private String inicio;
    private String fin;


    public EnviarReserva(Context context, int idUsuario, String idMesa, String inicio, String fin) {
        this.context = context;
        this.idUsuario = idUsuario;
        this.idMesa = idMesa;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Un momento por favor", "Enviando reserva...",true);
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            url = new URL(_URL_RESERVA);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(VariablesConstantes.READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(VariablesConstantes.CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("idUsuario", String.valueOf(idUsuario))
                    .appendQueryParameter("idMesa", idMesa)
                    .appendQueryParameter("inicio", inicio)
                    .appendQueryParameter("fin", fin);

            String request = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(request);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            httpURLConnection.connect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int response_code = httpURLConnection.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                result = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }

            } else {
                progressDialog.dismiss();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Log.d("EnviarReserva", "" + result);
        try {
            JSONObject group_info = new JSONObject(String.valueOf(result));

            JSONArray codigo_respuesta = group_info.getJSONArray("code");

            for (int i = 0; i < codigo_respuesta.length() ; i++) {
                int response = codigo_respuesta.getInt(i);

                switch (response) {
                    case 0:
                        Toast.makeText(context, "Usuario sin Acceso", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, "Reserva Registrar exitosamente", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        break;

                }

                Log.d("Response", "value: " + response);
                //names.add(i, jsonGroup.getString("nombre"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
