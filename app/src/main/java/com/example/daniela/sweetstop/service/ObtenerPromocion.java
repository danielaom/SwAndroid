package com.example.daniela.sweetstop.service;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.daniela.sweetstop.adapter.PromocionesAdapter;
import com.example.daniela.sweetstop.model.Promocion;

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
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_PROMOCION;

/**
 * Created by gonzalopro on 11/30/16.
 */

public class ObtenerPromocion extends AsyncTask<Void,Void,Void> {

    private List<Promocion> promocions;
    private Context context;
    private RecyclerView recyclerView;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder resultPromocion;

    public ObtenerPromocion(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            url = new URL(_URL_PROMOCION);
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
                resultPromocion = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    resultPromocion.append(line);
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
        promocions = new ArrayList<>();
        try {
            JSONObject group_info = new JSONObject(String.valueOf(resultPromocion));

            JSONArray jsonArray = group_info.getJSONArray("pro_info");

            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject jsonGroup = jsonArray.getJSONObject(i);
                promocions.add(i, new Promocion(jsonGroup.getString("idPromocion"),jsonGroup.getString("nombre"),jsonGroup.getString("descripcion"),jsonGroup.getString("precio"),jsonGroup.getString("fechaInicio"),jsonGroup.getString("fechaFin"),jsonGroup.getString("imagen")));

                recyclerView.setAdapter(new PromocionesAdapter(context, promocions));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
