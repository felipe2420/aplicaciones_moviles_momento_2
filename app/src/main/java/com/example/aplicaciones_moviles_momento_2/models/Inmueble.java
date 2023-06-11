package com.example.aplicaciones_moviles_momento_2.models;

public class Inmueble {
    private int codigo;
    private String direccion;
    private String telefono;

    public Inmueble(int codigo, String direccion, String telefono) {
        this.codigo = codigo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public void radicar(int codigo) {

    }

    public void arrendar(int codigo) {

    }

    public int getCodigo() {
        return codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}
