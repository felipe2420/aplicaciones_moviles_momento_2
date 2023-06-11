package com.example.aplicaciones_moviles_momento_2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aplicaciones_moviles_momento_2.activities.AddHouseActivity;
import com.example.aplicaciones_moviles_momento_2.activities.AddOfficeActivity;
import com.example.aplicaciones_moviles_momento_2.activities.AppActivity;
import com.example.aplicaciones_moviles_momento_2.activities.DetailHouseActivity;
import com.example.aplicaciones_moviles_momento_2.activities.DetailOfficeActivity;
import com.example.aplicaciones_moviles_momento_2.activities.components.PropertyListAdapter;
import com.example.aplicaciones_moviles_momento_2.datasource.InmueblesDataSource;
import com.example.aplicaciones_moviles_momento_2.models.Casa;
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

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Inmueble property = InmueblesDataSource.data.get(position);
            InmueblesDataSource.selectedProperty = property;

            if(property instanceof Casa) {
                navigate(DetailHouseActivity.class);
            } else {
                navigate(DetailOfficeActivity.class);
            }
        });
    }

    private void setupListView() {
        adapter = new PropertyListAdapter(MainActivity.this, InmueblesDataSource.data);

        listView.setAdapter(adapter);
        listView.setClickable(true);
    }
}