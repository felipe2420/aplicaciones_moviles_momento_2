package com.example.aplicaciones_moviles_momento_2.datasource;

import com.example.aplicaciones_moviles_momento_2.models.Inmueble;

import java.util.ArrayList;

public class InmueblesDataSource {
    public static ArrayList<Inmueble> data = new ArrayList<>();

    public static Inmueble selectedProperty;

    public static boolean didImprovedProperty;
    public static int currentRentCode;
    public static int currentSettled;

    public static void resetSelection() {
        selectedProperty = null;
        didImprovedProperty = false;
        currentRentCode = 0;
        currentSettled = 0;
    }
}
