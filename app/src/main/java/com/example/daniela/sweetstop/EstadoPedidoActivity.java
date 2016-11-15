package com.example.daniela.sweetstop;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.daniela.sweetstop.model.EstadoPedido;
import com.example.daniela.sweetstop.utilitarios.ObjectWrapperForBinder;

/**
 * Created by gonzalopro on 11/15/16.
 */

public class EstadoPedidoActivity extends AppCompatActivity {

    private EstadoPedido estadoPedido;
    private TextView textViewCodigo;
    private TextView textViewFecha;
    private LinearLayout linearLayoutButton;
    private ImageView imageViewBack, imageViewFinalizar,imageViewCancelar;
    private final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(450,300);

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_pedido);

        textViewCodigo = (TextView) findViewById(R.id.tv_activity_estado_pedido_codigo);
        textViewFecha = (TextView) findViewById(R.id.tv_activity_estado_pedido_fecha);
        linearLayoutButton = (LinearLayout) findViewById(R.id.ll_activity_estado_pedido);

        assert ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("object_estado_pedido")) != null;
        estadoPedido = ((ObjectWrapperForBinder) getIntent().getExtras().getBinder("object_estado_pedido")).getEstadoPedido();
        Log.d("Object Receive", "datos: " + estadoPedido.getIdPedido());

        textViewCodigo.setText(estadoPedido.getCodigo());
        textViewFecha.setText(estadoPedido.getFecha());

        imageViewBack = new ImageView(getApplicationContext());
        imageViewBack.setLayoutParams(layoutParams);
        imageViewBack.setBackgroundResource(R.drawable.back);

        imageViewFinalizar = new ImageView(getApplicationContext());
        imageViewFinalizar.setLayoutParams(layoutParams);
        imageViewFinalizar.setBackgroundResource(R.drawable.finalizar);

        imageViewCancelar = new ImageView(getApplicationContext());
        imageViewCancelar.setLayoutParams(layoutParams);
        imageViewCancelar.setBackgroundResource(R.drawable.cancelar_pedido);
    }

    @Override
    protected void onStart() {
        super.onStart();

        linearLayoutButton.removeAllViews();
        switch (estadoPedido.getEstadoIdEstado()) {
            case "6":
                linearLayoutButton.addView(imageViewFinalizar);
                break;
            case "7":
                linearLayoutButton.addView(imageViewBack);
                linearLayoutButton.addView(imageViewCancelar);
                break;
            default:
                linearLayoutButton.addView(imageViewBack);
        }

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imageViewFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageViewCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
