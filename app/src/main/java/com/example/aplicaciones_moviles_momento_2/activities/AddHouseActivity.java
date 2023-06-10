package com.example.aplicaciones_moviles_momento_2.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicaciones_moviles_momento_2.R;
import com.example.aplicaciones_moviles_momento_2.models.Casa;
import com.example.aplicaciones_moviles_momento_2.models.Inmueble;
import com.example.aplicaciones_moviles_momento_2.utils.ValidationException;

public class AddHouseActivity extends AddPropertyActivity {

    EditText txtRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_house);

        createNavigationBar(R.string.add_house_title);
        setViewComponents();
    }

    @Override
    protected void setViewComponents() {
        super.setViewComponents();
        txtRooms = findViewById(R.id.txtRooms);

        setOnClickListeners();
    }

    @Override
    protected void createProperty() {
        try {
            Inmueble property = tryToCreateBaseProperty();
            int rooms = tryToGetRooms();

            save(new Casa(property, rooms));

            showMessageAndClose();
        } catch (ValidationException exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(this, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show();
        }
    }

    private int tryToGetRooms() throws ValidationException {
        String roomsString = txtRooms.getText().toString();

        if (roomsString.isEmpty()) {
            throw new ValidationException("El número de habitaciones no puede estar vacío");
        }

        try {
            return Integer.parseInt(roomsString);
        } catch (Exception e) {
            throw new ValidationException("El número de habitaciones solo admite números");
        }
    }
}