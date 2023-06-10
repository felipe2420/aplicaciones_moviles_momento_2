package com.example.aplicaciones_moviles_momento_2.activities;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.aplicaciones_moviles_momento_2.R;
import com.example.aplicaciones_moviles_momento_2.models.Inmueble;
import com.example.aplicaciones_moviles_momento_2.models.Oficina;
import com.example.aplicaciones_moviles_momento_2.utils.ValidationException;

public class AddOfficeActivity extends AddPropertyActivity {
    ToggleButton btnAssemblyRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_office);

        createNavigationBar(R.string.add_office_title);
        setViewComponents();
    }

    @Override
    protected void setViewComponents() {
        super.setViewComponents();
        btnAssemblyRoom = findViewById(R.id.btnAssemblyRoom);

        setOnClickListeners();
    }

    @Override
    protected void createProperty() {
        try {
            Inmueble property = tryToCreateBaseProperty();
            boolean hasAssemblyRoom = btnAssemblyRoom.isChecked();

            save(new Oficina(property, hasAssemblyRoom));

            showMessageAndClose();
        } catch (ValidationException exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(this, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show();
        }
    }
}