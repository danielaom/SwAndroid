package com.example.daniela.sweetstop.service;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.daniela.sweetstop.EstadoMesaActivity;
import com.example.daniela.sweetstop.adapter.EstadoMesaAdapter;
import com.example.daniela.sweetstop.model.EstadoMesa;

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
import java.util.ArrayList;
import java.util.List;

import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes.CONNECTION_TIMEOUT;
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes.READ_TIMEOUT;
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_ESTADO_MESA;

/**
 * Created by gonzalopro on 10/30/16.
 */

public class ObtenerEstadosMesas extends AsyncTask<Void,Void,Void>  {

    private String idMesa;
    private ProgressDialog progressDialog;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder resultEstadoMesa;
    private EstadoMesaActivity estadoMesaActivity;
    private List<EstadoMesa> estadoMesas;
    private RecyclerView recyclerView;

    public ObtenerEstadosMesas(String idMesa, EstadoMesaActivity estadoMesaActivity, RecyclerView recyclerView) {
        this.idMesa = idMesa;
        this.estadoMesaActivity = estadoMesaActivity;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(estadoMesaActivity, "Un momento por favor", "Obteniendo Reservas...",true);
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            url = new URL(_URL_ESTADO_MESA);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("idMesa", idMesa);

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

                resultEstadoMesa = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    resultEstadoMesa.append(line);
                    //System.out.println("result: " +resultEstadoMesa);
                }


            } else {
                Log.d("Async", "error async");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void avoid) {
        super.onPostExecute(avoid);
        progressDialog.dismiss();
        estadoMesas = new ArrayList<>();
        try {
            JSONObject group_info = new JSONObject(String.valueOf(resultEstadoMesa));

            JSONArray jsonArray = group_info.getJSONArray("estado_mesas_info");
            for (int i = 0; i < jsonArray.length() ; i++) {

                JSONObject jsonGroup = jsonArray.getJSONObject(i);
                estadoMesas.add(i, new EstadoMesa(jsonGroup.getString("fechaInicio"),jsonGroup.getString("fechaFin")));

                recyclerView.setAdapter(new EstadoMesaAdapter(estadoMesas));
                progressDialog.dismiss();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
