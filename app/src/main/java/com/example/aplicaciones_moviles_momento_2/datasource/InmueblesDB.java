package com.example.aplicaciones_moviles_momento_2.datasource;

import com.example.aplicaciones_moviles_momento_2.models.Inmueble;
import com.example.aplicaciones_moviles_momento_2.utils.ValidationException;

import java.util.ArrayList;

public class InmueblesDB {
    public static ArrayList<Inmueble> data = new ArrayList<>();

    public static Inmueble selectedProperty;

    public static void update(Inmueble property) throws ValidationException {
        int index = data.indexOf(property);

        if(index == -1) {
            throw new ValidationException("Ocurrió un error inesperado");
        }

        data.add(index, property);
    }
}
