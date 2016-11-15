package com.example.daniela.sweetstop.utilitarios;

import android.os.Binder;

import com.example.daniela.sweetstop.model.EstadoPedido;

/**
 * Created by gonzalopro on 11/15/16.
 */

public class ObjectWrapperForBinder extends Binder {
    private final EstadoPedido estadoPedido;

    public ObjectWrapperForBinder(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }
}
