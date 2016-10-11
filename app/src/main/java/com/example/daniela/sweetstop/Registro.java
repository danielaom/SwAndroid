package com.example.daniela.sweetstop;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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

public class Registro extends AppCompatActivity {

    private ImageView imageViewAtras;
    private Button buttonRegistrar;
    private EditText nombre, app, apm, ci, cellphone, email, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registro);

        imageViewAtras = (ImageView) findViewById(R.id.imageView_atras);
        buttonRegistrar = (Button) findViewById(R.id.buttonRegistrar);

        nombre = (EditText) findViewById(R.id.editTextNombre);
        app = (EditText) findViewById(R.id.editTextApp);
        apm = (EditText) findViewById(R.id.editTextApm);
        ci = (EditText) findViewById(R.id.editTextci);
        cellphone = (EditText) findViewById(R.id.editTextphone);
        email = (EditText) findViewById(R.id.editTextemail);
        address = (EditText) findViewById(R.id.editTextaddress);

    }

    @Override
    protected void onStart() {
        super.onStart();
        imageViewAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = nombre.getText().toString();
                String appaterno = app.getText().toString();
                String apmmaterno = apm.getText().toString();
                String c_i = ci.getText().toString();
                String cell = cellphone.getText().toString();
                String mail = email.getText().toString();
                String add = address.getText().toString();

                new RegistroTask(nom,appaterno,apmmaterno,c_i,cell,mail,add, Registro.this).execute();
            }
        });


    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class RegistroTask extends AsyncTask<Void, Void, Void> {

        private final String mNombre;
        private final String mPaterno;
        private final String mMaterno;
        private final String mCarnet;
        private final String mCelular;
        private final String mCorreo;
        private final String mDireccion;
        private URL url;
        private HttpURLConnection httpURLConnection;
        private StringBuilder resultado;
        private Context context;

        RegistroTask(String mNombre, String mPaterno, String mMaterno, String mCarnet, String mCelular, String mCorreo, String mDireccion, Context paramContext) {
            this.mNombre = mNombre;
            this.mPaterno = mPaterno;
            this.mMaterno = mMaterno;
            this.mCarnet = mCarnet;
            this.mCelular = mCelular;
            this.mCorreo = mCorreo;
            this.mDireccion = mDireccion;

            context = paramContext;
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                url = new URL(VariablesConstantes._URL_REGISTRO);
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
                        .appendQueryParameter("nombre", mNombre)
                        .appendQueryParameter("ap_paterno", mPaterno)
                        .appendQueryParameter("ap_materno", mMaterno)
                        .appendQueryParameter("ci", mCarnet)
                        .appendQueryParameter("correo", mCorreo)
                        .appendQueryParameter("telefono", mCelular)
                        .appendQueryParameter("direccion", mDireccion);


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

                    resultado = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        resultado.append(line);
                    }

                } else {


                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void success) {

            Log.d("Response", "value: " + resultado);
            try {
                JSONObject group_info = new JSONObject(String.valueOf(resultado));

                JSONArray codigo_respuesta = group_info.getJSONArray("codigo");

                for (int i = 0; i < codigo_respuesta.length() ; i++) {
                    int response = codigo_respuesta.getInt(i);

                    switch (response) {
                        case 0:
                            // NO tiene acceso
                            Toast.makeText(context, "Usuario sin Acceso", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(context, "Usuario Registrado exitosamente", Toast.LENGTH_SHORT).show();
                            // Iniciar nueva actividad
                            //context.startActivity(new Intent(context, MainActivity.class));
                            break;

                    }

                    Log.d("Response", "value: " + response);
                    //names.add(i, jsonGroup.getString("nombre"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            /*if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }*/
        }

        @Override
        protected void onCancelled() {

        }
    }
}
