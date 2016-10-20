package com.example.daniela.sweetstop;

import android.app.Application;

/**
 * Created by gonzalopro on 10/18/16.
 */

public class SwAndroid extends Application {

    String id_cliente;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }
}
