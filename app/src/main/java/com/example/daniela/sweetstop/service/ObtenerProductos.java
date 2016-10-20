package com.example.daniela.sweetstop.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.example.daniela.sweetstop.adapter.PedidoAdapter;
import com.example.daniela.sweetstop.model.Catalogo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes.CONNECTION_TIMEOUT;
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes.READ_TIMEOUT;
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_PRODUCTO;

/**
 * Created by gonzalopro on 10/20/16.
 */

public class ObtenerProductos extends AsyncTask<Void,Void,Void> {

    private Context context;
    private RecyclerView recyclerView;
    private ListView listViewOrder;
    private ProgressDialog progressDialog;
    private List<Catalogo> catalogos;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder resultCatalogo;

    public ObtenerProductos(Context context, RecyclerView recyclerView, ListView listView) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.listViewOrder = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Un momento por favor", "Obteniendo las Productos...",true);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            url = new URL(_URL_PRODUCTO);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int response_code = httpURLConnection.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {

                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                resultCatalogo = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    resultCatalogo.append(line);
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
        catalogos = new ArrayList<>();
        try {
            JSONObject group_info = new JSONObject(String.valueOf(resultCatalogo));

            JSONArray jsonArray = group_info.getJSONArray("productos_info");
            for (int i = 0; i < jsonArray.length() ; i++) {

                JSONObject jsonGroup = jsonArray.getJSONObject(i);
                catalogos.add(i, new Catalogo(jsonGroup.getString("idProducto"),jsonGroup.getString("nombre"),jsonGroup.getString("descripcion"),jsonGroup.getString("imagen"),jsonGroup.getString("precio")));

                recyclerView.setAdapter(new PedidoAdapter(context, catalogos, listViewOrder));
                progressDialog.dismiss();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
