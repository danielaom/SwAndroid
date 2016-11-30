package com.example.daniela.sweetstop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daniela.sweetstop.service.EnviarReservaPromocion;
import com.example.daniela.sweetstop.service.ObtenerDetallePromocion;

/**
 * Created by gonzalopro on 11/30/16.
 */

public class DetallePromocionActivity extends AppCompatActivity {

    private String idPromocion;
    private String precio;
    private int id;
    private String idUsuario;

    private ListView listView;
    private EditText editTextCantidad;
    private Button buttonReservar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_promocion);

        listView = (ListView) findViewById(R.id.lv_detail_promotions);
        editTextCantidad = (EditText) findViewById(R.id.et_detail_promotion);
        buttonReservar = (Button) findViewById(R.id.btn_detail_promotion);
        idPromocion = getIntent().getStringExtra("idPromocion");
        precio = getIntent().getStringExtra("precio");
        id = ((SwAndroid) getApplicationContext()).getId_cliente();
        idUsuario = String.valueOf(id);
        Log.d("DP", "id send: " + idUsuario);
        new ObtenerDetallePromocion(this,listView,idPromocion).execute();

    }

    @Override
    protected void onStart() {
        super.onStart();

        buttonReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextCantidad.getText().toString().isEmpty()) {

                    new EnviarReservaPromocion(DetallePromocionActivity.this,idPromocion,idUsuario,editTextCantidad.getText().toString(),precio).execute();

                } else {
                    Toast.makeText(DetallePromocionActivity.this, "El campo cantidad esta vacia", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
