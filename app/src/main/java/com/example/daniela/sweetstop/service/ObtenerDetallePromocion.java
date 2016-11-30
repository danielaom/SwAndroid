package com.example.daniela.sweetstop.service;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.daniela.sweetstop.adapter.DetallePromocionAdapter;
import com.example.daniela.sweetstop.model.DetallePromocion;

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
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_PROMOCION_DETALLE;

/**
 * Created by gonzalopro on 11/30/16.
 */

public class ObtenerDetallePromocion extends AsyncTask<Void,Void,Void> {

    private ListView listView;
    private Context context;
    private List<DetallePromocion> detallePromocions;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder resultDetallePromocion;
    private String idPromocion;

    public ObtenerDetallePromocion(Context context, ListView listView, String idPromocion) {
        this.context = context;
        this.listView = listView;
        this.idPromocion = idPromocion;
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            url = new URL(_URL_PROMOCION_DETALLE);
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
                    .appendQueryParameter("idPromocion", idPromocion);

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

                resultDetallePromocion = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    resultDetallePromocion.append(line);
                    System.out.println("result: " +resultDetallePromocion);
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
        detallePromocions = new ArrayList<>();

        try {
            JSONObject group_info = new JSONObject(String.valueOf(resultDetallePromocion));

            JSONArray jsonArray = group_info.getJSONArray("productos_info");
            for (int i = 0; i < jsonArray.length() ; i++) {

                JSONObject jsonGroup = jsonArray.getJSONObject(i);
                detallePromocions.add(i, new DetallePromocion(jsonGroup.getString("nombre"),jsonGroup.getString("descripcion"),jsonGroup.getString("imagen")));

                listView.setAdapter(new DetallePromocionAdapter(context,detallePromocions));


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
