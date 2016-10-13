package com.example.daniela.sweetstop.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.daniela.sweetstop.adapter.CategoryAdapter;
import com.example.daniela.sweetstop.model.Categoria;
import com.example.daniela.sweetstop.utilitarios.VariablesConstantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_CATEGORIA;

/**
 * Created by gonzalopro on 10/12/16.
 */

public class ObtenerCategorias extends AsyncTask<Void, Void, Void> {

    private Context context;
    private ListView listViewCategoria;
    private ProgressDialog progressDialog;
    private List<Categoria> categorias;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder resultCategorias;

    public ObtenerCategorias (Context context, ListView listViewCategoria) {
        this.context = context;
        this.listViewCategoria = listViewCategoria;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Un momento por favor", "Obteniendo las categorias...",true);
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            url = new URL(_URL_CATEGORIA);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int response_code = httpURLConnection.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {

                InputStream input = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                resultCategorias = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    resultCategorias.append(line);
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

        categorias = new ArrayList<>();
        try {
            JSONObject group_info = new JSONObject(String.valueOf(resultCategorias));

            JSONArray jsonArray = group_info.getJSONArray("categorias_info");

            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject jsonGroup = jsonArray.getJSONObject(i);
                categorias.add(i, new Categoria(jsonGroup.getString("idCategoria"),jsonGroup.getString("nombre")));

                listViewCategoria.setAdapter(new CategoryAdapter(context, categorias));
                progressDialog.dismiss();
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
