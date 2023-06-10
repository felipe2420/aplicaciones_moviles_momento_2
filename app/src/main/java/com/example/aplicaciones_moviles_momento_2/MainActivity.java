package com.example.aplicaciones_moviles_momento_2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.example.aplicaciones_moviles_momento_2.activities.AddHouseActivity;
import com.example.aplicaciones_moviles_momento_2.activities.AddOfficeActivity;
import com.example.aplicaciones_moviles_momento_2.activities.AppActivity;
import com.example.aplicaciones_moviles_momento_2.activities.components.PropertyListAdapter;
import com.example.aplicaciones_moviles_momento_2.datasource.InmueblesDB;
import com.example.aplicaciones_moviles_momento_2.models.Inmueble;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppActivity {

    FloatingActionButton btnAddHouse, btnAddOffice;
    ListView listView;

    ArrayAdapter<Inmueble> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void setViewComponents() {
        btnAddHouse = findViewById(R.id.btnAddHouse);
        btnAddOffice = findViewById(R.id.btnAddOffice);
        listView = findViewById(R.id.propertiesListView);

        setOnClickListeners();
        setupListView();
    }

    private void setOnClickListeners(){
        btnAddHouse.setOnClickListener(view -> navigate(AddHouseActivity.class));
        btnAddOffice.setOnClickListener(view -> navigate(AddOfficeActivity.class));
    }

    private void setupListView() {
        adapter = new PropertyListAdapter(MainActivity.this, InmueblesDB.data);

        listView.setAdapter(adapter);
        listView.setClickable(true);
    }
}