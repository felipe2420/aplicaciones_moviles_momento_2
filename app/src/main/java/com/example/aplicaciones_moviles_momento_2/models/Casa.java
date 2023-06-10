package com.example.aplicaciones_moviles_momento_2.models;

public class Casa extends Inmueble {
    private int nrohabitaciones;

    public Casa(Inmueble base, int nrohabitaciones) {
        super(base.getCodigo(), base.getDireccion(), base.getTelefono());
        this.nrohabitaciones = nrohabitaciones;
    }

    public void repararJardin() {

    }
}
