package com.example.aplicaciones_moviles_momento_2.models;

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
        String message = "El jardín ya fue reparado";
        if (!didRepairGarden) {
            didRepairGarden = true;
            message = "Jardín reparado con exito";
        }
    }
}
