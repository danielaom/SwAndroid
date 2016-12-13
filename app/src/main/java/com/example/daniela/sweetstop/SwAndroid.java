package com.example.daniela.sweetstop;

import android.app.Application;

import com.example.daniela.sweetstop.model.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonzalopro on 10/18/16.
 */

public class SwAndroid extends Application {

    int id_cliente;
    List<Pedido> pedidos = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void clearPedido() {
        this.pedidos.clear();
    }


}
