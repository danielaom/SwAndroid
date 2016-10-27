package com.example.daniela.sweetstop.model;

/**
 * Created by gonzalopro on 10/26/16.
 */

public class Pedido {

    private String idProducto;
    private String cantidad;
    private Catalogo catalogo;

    public Pedido(String idProducto, String cantidad, Catalogo catalogo) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.catalogo = catalogo;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }
}
