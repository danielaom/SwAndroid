package com.example.daniela.sweetstop.service;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.daniela.sweetstop.adapter.EstadoPedidoAdapter;
import com.example.daniela.sweetstop.model.EstadoPedido;

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
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_ESTADO_PEDIDO;

/**
 * Created by gonzalopro on 11/15/16.
 */

public class ObtenerEstadosPedidos extends AsyncTask<Void,Void,Void> {

    private Context context;
    private RecyclerView recyclerView;
    private int idUsuario;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<EstadoPedido> estadoPedidos;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder resultStateOrder;

    public ObtenerEstadosPedidos(Context context, RecyclerView recyclerView, int idUsuario, SwipeRefreshLayout mSwipeRefreshLayout) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.idUsuario = idUsuario;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            url = new URL(_URL_ESTADO_PEDIDO);
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
                    .appendQueryParameter("usuarioIdUsuario", String.valueOf(idUsuario));

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

                resultStateOrder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    resultStateOrder.append(line);
                    System.out.println("result: " +resultStateOrder);
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
        Log.d("ESTADO Ps", "valores: " + resultStateOrder);
        estadoPedidos = new ArrayList<>();
        try {
            JSONObject group_info = new JSONObject(String.valueOf(resultStateOrder));

            JSONArray jsonArray = group_info.getJSONArray("usuario_info");
            for (int i = 0; i < jsonArray.length() ; i++) {

                JSONObject jsonGroup = jsonArray.getJSONObject(i);
                estadoPedidos.add(i, new EstadoPedido(jsonGroup.getString("idPedido"),jsonGroup.getString("codigo"),jsonGroup.getString("fecha"),jsonGroup.getString("estadoIdEstado")));

                recyclerView.setAdapter(new EstadoPedidoAdapter(context, estadoPedidos));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mSwipeRefreshLayout.setRefreshing(false);
    }

}
