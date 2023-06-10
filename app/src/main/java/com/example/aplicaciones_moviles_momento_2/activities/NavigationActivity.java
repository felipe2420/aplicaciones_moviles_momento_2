package com.example.aplicaciones_moviles_momento_2.activities;
import androidx.appcompat.app.ActionBar;

public class NavigationActivity extends AppActivity {

    protected void createNavigationBar(int navigationTitleResourceId) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(navigationTitleResourceId);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
