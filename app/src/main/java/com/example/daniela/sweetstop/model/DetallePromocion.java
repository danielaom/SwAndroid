package com.example.daniela.sweetstop.model;

/**
 * Created by gonzalopro on 11/30/16.
 */

public class DetallePromocion {

    private String nombre;
    private String descripcion;
    private String imagen;

    public DetallePromocion(String nombre, String descripcion, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }
}
