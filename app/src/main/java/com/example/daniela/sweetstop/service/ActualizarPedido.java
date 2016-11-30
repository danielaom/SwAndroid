package com.example.daniela.sweetstop.service;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.daniela.sweetstop.EstadoPedidoActivity;
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

import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_ACTUALIZAR_ESTADO;

/**
 * Created by gonzalopro on 11/30/16.
 */

public class ActualizarPedido extends AsyncTask <Void,Void,Void>{

    private String idPedido;
    private String idState;
    private EstadoPedidoActivity estadoPedidoActivity;
    private Context context;

    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder result;

    public ActualizarPedido(Context context, String idPedido, String idState, EstadoPedidoActivity estadoPedidoActivity) {
        this.idPedido = idPedido;
        this.idState = idState;
        this.estadoPedidoActivity = estadoPedidoActivity;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            url = new URL(_URL_ACTUALIZAR_ESTADO);
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
                    .appendQueryParameter("idPedido", idPedido)
                    .appendQueryParameter("estadoIdEstado", idState);

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
        Log.d("ap","valor: " + result);
        try {
            JSONObject group_info = new JSONObject(String.valueOf(result));

            JSONArray codigo_respuesta = group_info.getJSONArray("codigo");

            for (int i = 0; i < codigo_respuesta.length() ; i++) {
                int response = codigo_respuesta.getInt(i);

                switch (response) {
                    case 0:
                        Toast.makeText(context, "Error al actualizar el pedido", Toast.LENGTH_SHORT).show();

                        break;
                    case 1:
                        Toast.makeText(context, "Pedido Actualizado exitosamente", Toast.LENGTH_SHORT).show();
                        estadoPedidoActivity.onBackPressed();
                        break;

                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
