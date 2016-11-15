package com.example.daniela.sweetstop.model;

/**
 * Created by gonzalopro on 11/14/16.
 */

public class EstadoPedido {

    private String idPedido;
    private String codigo;
    private String fecha;
    private String estadoIdEstado;

    public EstadoPedido(String idPedido, String codigo, String fecha, String estadoIdEstado) {
        this.idPedido = idPedido;
        this.codigo = codigo;
        this.fecha = fecha;
        this.estadoIdEstado = estadoIdEstado;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstadoIdEstado() {
        return estadoIdEstado;
    }
}
