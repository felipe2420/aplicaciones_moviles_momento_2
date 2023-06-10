package com.example.aplicaciones_moviles_momento_2.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AppActivity extends AppCompatActivity {

    public void navigate(Class<?> destination) {
        try {
            Intent intent = new Intent(this, destination);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Ocurrió un error, por favor inténtalo de nuevo", Toast.LENGTH_SHORT).show();
        }
    }
}
