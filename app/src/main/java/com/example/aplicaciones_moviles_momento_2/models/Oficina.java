package com.example.aplicaciones_moviles_momento_2.models;

public class Oficina extends Inmueble{
    private boolean salaAsamblea;

    public Oficina(Inmueble base, boolean salaAsamblea) {
        super(base.getCodigo(), base.getDireccion(), base.getTelefono());
        this.salaAsamblea = salaAsamblea;
    }

    public boolean isSalaAsamblea() {
        return salaAsamblea;
    }

    public void instalarInternet() {

    }
}
