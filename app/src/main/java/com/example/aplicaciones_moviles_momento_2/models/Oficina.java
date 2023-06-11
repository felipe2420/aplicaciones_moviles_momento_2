package com.example.aplicaciones_moviles_momento_2.models;

import com.example.aplicaciones_moviles_momento_2.datasource.InmueblesDataSource;

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
        InmueblesDataSource.didImprovedProperty = true;
    }
}
