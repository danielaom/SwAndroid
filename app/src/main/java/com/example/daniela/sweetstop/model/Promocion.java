package com.example.daniela.sweetstop.model;

/**
 * Created by gonzalopro on 11/30/16.
 */

public class Promocion {

    private String idPromocion;
    private String nombre;
    private String descripcion;
    private String precio;
    private String fechaInicio;
    private String fechaFin;
    private String imagen;

    public Promocion(String idPromocion, String nombre, String descripcion, String precio, String fechaInicio, String fechaFin, String imagen) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.imagen = imagen;
    }

    public String getIdPromocion() {
        return idPromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public String getImagen() {
        return imagen;
    }
}
