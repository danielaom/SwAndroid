package com.example.daniela.sweetstop;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daniela.sweetstop.adapter.PedidoAdapter;
import com.example.daniela.sweetstop.model.Pedido;
import com.example.daniela.sweetstop.service.EnviarPedido;
import com.example.daniela.sweetstop.service.ObtenerProductos;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gonzalopro on 10/20/16.
 */

public class PedidoActivity extends AppCompatActivity {

    public static final String TAG = PedidoActivity.class.getSimpleName();

    private RecyclerView recyclerViewPedidoProducto;
    private GridLayoutManager gridLayoutManager;
    private ListView listViewPedido;
    private Button buttonEnviar, buttonCancelar;

    private List<Pedido> pedidos;
    private int idUsuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        listViewPedido = (ListView) findViewById(R.id.list);
        recyclerViewPedidoProducto = (RecyclerView) findViewById(R.id.rv_pedido_producto);
        buttonEnviar = (Button) findViewById(R.id.btn_pedido_enviar);
        buttonCancelar = (Button) findViewById(R.id.btn_pedido_clear);

        assert recyclerViewPedidoProducto != null;
        recyclerViewPedidoProducto.hasFixedSize();
        gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewPedidoProducto.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new ObtenerProductos(PedidoActivity.this, recyclerViewPedidoProducto, listViewPedido).execute();

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (((SwAndroid) getApplicationContext()).getPedidos().size() != 0) {
                    pedidos = ((SwAndroid) getApplicationContext()).getPedidos();
                    Double total = 0.00;
                    for (Pedido pedido: pedidos) {
                        total = total + ( (Double.parseDouble(pedido.getPrecio())) * (Double.parseDouble(pedido.getCantidad())) );
                    }

                    new AlertDialog.Builder(PedidoActivity.this)
                            .setTitle("Confirmar pedido")
                            .setMessage("El total del pedido es: "+ total + " Bs." +"  \n¿Esta seguro de envar el pedido?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    idUsuario = ((SwAndroid) getApplicationContext()).getId_cliente();

                                    Log.i(TAG, "Usuario ID: " + idUsuario);
                                    new EnviarPedido(PedidoActivity.this, idUsuario, pedidos).execute();
                                    listViewPedido.setAdapter(null);
                                }
                            })
                            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                } else {
                    Toast.makeText(PedidoActivity.this, "Carrito de Pedido Vacion", Toast.LENGTH_SHORT).show();
                }
                /*for (Pedido pedido: pedidos) {
                    Log.i(TAG,"Pedidos ID: " + pedido.getIdProducto());
                    Log.i(TAG,"Pedidos CA: " + pedido.getCantidad());
                }*/
            }
        });

        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SwAndroid) getApplicationContext()).clearPedido();
                onBackPressed();
            }
        });
    }
}
