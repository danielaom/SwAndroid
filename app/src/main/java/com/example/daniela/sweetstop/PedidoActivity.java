package com.example.daniela.sweetstop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.daniela.sweetstop.service.ObtenerProductos;


/**
 * Created by gonzalopro on 10/20/16.
 */

public class PedidoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPedidoProducto;
    private GridLayoutManager gridLayoutManager;
    private ListView listViewPedido;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        listViewPedido = (ListView) findViewById(R.id.list);
        recyclerViewPedidoProducto = (RecyclerView) findViewById(R.id.rv_pedido_producto);
        assert recyclerViewPedidoProducto != null;
        recyclerViewPedidoProducto.hasFixedSize();
        gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerViewPedidoProducto.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new ObtenerProductos(PedidoActivity.this, recyclerViewPedidoProducto, listViewPedido).execute();
    }
}
