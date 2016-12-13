package com.example.daniela.sweetstop.model;

/**
 * Created by gonzalopro on 12/11/16.
 */

public class Publicidad {

    private String description;
    private String imagePath;

    public Publicidad(String imagePath, String description) {
        this.imagePath = imagePath;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
