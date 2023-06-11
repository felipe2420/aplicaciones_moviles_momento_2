package com.example.aplicaciones_moviles_momento_2.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaciones_moviles_momento_2.R;
import com.example.aplicaciones_moviles_momento_2.models.Oficina;

public class DetailOfficeActivity extends DetailPropertyActivity {

    Button btnInstallWifi;
    TextView txtAssemblyRoom;

    Oficina selectedProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_office);

        createNavigationBar(R.string.detail_office_title);
        setViewComponents();
        loadProperty();
    }

    @Override
    protected void loadProperty() {
        super.loadProperty();

        if(!(property instanceof Oficina)) {
            onErrorCloseScreen();
            return;
        }

        selectedProperty = (Oficina) property;

        int assemblyRoom = R.string.assembly_room_placeholder_off;
        if (selectedProperty.isSalaAsamblea()) {
            assemblyRoom = R.string.assembly_room_placeholder_on;
        }

        txtAssemblyRoom.setText(assemblyRoom);
    }

    @Override
    protected void setViewComponents() {
        super.setViewComponents();
        txtAssemblyRoom = findViewById(R.id.txtRooms);
        btnInstallWifi = findViewById(R.id.btnRepair);

        setOnClickListeners();
    }

    @Override
    protected void setOnClickListeners() {
        super.setOnClickListeners();

        btnInstallWifi.setOnClickListener(view -> installWifi());
    }

    private void installWifi() {
        selectedProperty.instalarInternet();
        Toast.makeText(this, "Wifi instalado", Toast.LENGTH_SHORT).show();
    }
}