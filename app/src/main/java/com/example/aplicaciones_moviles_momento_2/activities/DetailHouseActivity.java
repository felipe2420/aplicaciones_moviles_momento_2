package com.example.aplicaciones_moviles_momento_2.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaciones_moviles_momento_2.R;
import com.example.aplicaciones_moviles_momento_2.datasource.InmueblesDataSource;
import com.example.aplicaciones_moviles_momento_2.models.Casa;

public class DetailHouseActivity extends DetailPropertyActivity {

    Button btnRepairGarden;
    TextView txtRoomsNumber;

    Casa selectedProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_house);

        createNavigationBar(R.string.detail_house_title);
        setViewComponents();
        loadProperty();
    }

    @Override
    protected void loadProperty() {
        super.loadProperty();

        if(!(property instanceof Casa)) {
            onErrorCloseScreen();
            return;
        }

        selectedProperty = (Casa) property;
        String roomsNumber = getResources().getString(R.string.rooms_number_placeholder) + " " + selectedProperty.getNrohabitaciones() ;
        txtRoomsNumber.setText(roomsNumber);
    }

    @Override
    protected void setViewComponents() {
        super.setViewComponents();
        txtRoomsNumber = findViewById(R.id.txtRooms);
        btnRepairGarden = findViewById(R.id.btnRepair);

        setOnClickListeners();
    }

    @Override
    protected void setOnClickListeners() {
        super.setOnClickListeners();

        btnRepairGarden.setOnClickListener(view -> repairGarden());
    }

    private void repairGarden() {
        String message = "El Jardín reparado ya fue reparado anteriormente";

        if(!InmueblesDataSource.didImprovedProperty) {
            selectedProperty.repararJardin();
            message = "Jardín reparado exitosamente";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}