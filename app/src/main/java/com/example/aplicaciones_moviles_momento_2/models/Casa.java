package com.example.aplicaciones_moviles_momento_2.models;

import com.example.aplicaciones_moviles_momento_2.datasource.InmueblesDataSource;

public class Casa extends Inmueble {
    private final int nrohabitaciones;

    private boolean didRepairGarden = false;

    public Casa(Inmueble base, int nrohabitaciones) {
        super(base.getCodigo(), base.getDireccion(), base.getTelefono());
        this.nrohabitaciones = nrohabitaciones;
    }

    public int getNrohabitaciones() {
        return nrohabitaciones;
    }

    public void repararJardin() {
        InmueblesDataSource.didImprovedProperty = true;
    }
}
