package com.example.daniela.sweetstop.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.daniela.sweetstop.SwAndroid;
import com.example.daniela.sweetstop.adapter.CatalogoAdapter;
import com.example.daniela.sweetstop.model.Catalogo;
import com.example.daniela.sweetstop.model.Pedido;

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
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_PEDIDO;

/**
 * Created by gonzalopro on 10/26/16.
 */

public class EnviarPedido extends AsyncTask<Void,Void,Void> {

    private Context context;
    private ProgressDialog progressDialog;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder result;
    private int idUsuario;
    private List<Pedido> pedidos;
    //private String jsonSendPedido;

    public EnviarPedido(Context context, int idUsuario, List<Pedido> pedidos) {
        this.context = context;
        this.idUsuario = idUsuario;
        this.pedidos = pedidos;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Un momento por favor", "Enviando Pedido...",true);
    }

    @Override
    protected Void doInBackground(Void... params) {

        //JSONObject jsonObject = new JSONObject();

        try {
            url = new URL(_URL_PEDIDO);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        try {

           /* jsonObject.put("idUsuario",idUsuario);
            jsonObject.put("idProducto",pedidos);
            jsonSendPedido = jsonObject.toString();*/

            /*for (Pedido sendPedido: pedidos) {
                //builder.appendQueryParameter("items[]",sendPedido.getIdProducto());
                jsonObject.put("idProducto",sendPedido.getIdProducto());
                jsonObject.put("cantidad",sendPedido.getCantidad());


            }*/


            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            //httpURLConnection.setFixedLengthStreamingMode(jsonSendPedido.getBytes().length);

            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("idUsuario", String.valueOf(idUsuario));
            //builder.appendQueryParameter("items[]", pedidos.toString());

            Log.d("Enviar Pedido", pedidos.toString());

            for (Pedido sendPedido: pedidos) {

                builder.appendQueryParameter("items[]", sendPedido.getIdProducto());
                builder.appendQueryParameter("cantidad[]", sendPedido.getCantidad());
            }

            String request = builder.build().getEncodedQuery();

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(request);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            //httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
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
                    System.out.println("result: " + result);
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
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        ((SwAndroid) context.getApplicationContext()).clearPedido();
        
        /*try {
            JSONObject group_info = new JSONObject(String.valueOf(result));
            JSONArray jsonArray = group_info.getJSONArray("code");
            for (int i = 0; i < jsonArray.length() ; i++) {

                //int response = jsonArray.getInt(i);
                //JSONObject jsonCode = jsonArray.getJSONObject(i);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
}
