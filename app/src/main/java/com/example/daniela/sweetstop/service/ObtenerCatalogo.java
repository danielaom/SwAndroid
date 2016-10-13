package com.example.daniela.sweetstop.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daniela.sweetstop.adapter.CatalogoAdapter;
import com.example.daniela.sweetstop.model.Catalogo;

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
import static com.example.daniela.sweetstop.utilitarios.VariablesConstantes._URL_CATALOGO;

/**
 * Created by gonzalopro on 10/13/16.
 */

public class ObtenerCatalogo extends AsyncTask <Object,Void,Void> {

    public static final String TAG = ObtenerCatalogo.class.getSimpleName();

    private Context context;
    private ListView listViewCatalogo;
    private ProgressDialog progressDialog;
    private List<Catalogo> catalogos;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private StringBuilder resultCatalogo;
    private String idCategoria;

    public ObtenerCatalogo(Context context, ListView listViewCatalogo) {
        this.context = context;
        this.listViewCatalogo = listViewCatalogo;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Un momento por favor", "Obteniendo el catalogo...",true);
    }

    @Override
    protected Void doInBackground(Object... params) {

        idCategoria = (String) params[0];
        Log.d(TAG,"id: " + idCategoria);
        try {
            url = new URL(_URL_CATALOGO);
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
                    .appendQueryParameter("idCategoria", idCategoria);

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

                resultCatalogo = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    resultCatalogo.append(line);
                    System.out.println("result: " +resultCatalogo);
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

                listViewCatalogo.setAdapter(new CatalogoAdapter(context, catalogos));
                progressDialog.dismiss();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
