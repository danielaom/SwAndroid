package com.example.daniela.sweetstop.model;

/**
 * Created by gonzalopro on 11/29/16.
 */

public class EstadoMesa {

    private String fechaInicio;
    private String fechaFin;

    public EstadoMesa(String fechaInicio, String fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }
}
