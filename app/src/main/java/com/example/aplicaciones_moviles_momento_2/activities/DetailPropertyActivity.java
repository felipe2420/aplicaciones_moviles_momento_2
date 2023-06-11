package com.example.aplicaciones_moviles_momento_2.activities;

import android.app.AlertDialog;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaciones_moviles_momento_2.R;
import com.example.aplicaciones_moviles_momento_2.datasource.InmueblesDB;
import com.example.aplicaciones_moviles_momento_2.models.Inmueble;
import com.example.aplicaciones_moviles_momento_2.utils.ValidationException;

public class DetailPropertyActivity extends NavigationActivity {
    protected Button btnFileProperty, btnRent;
    protected TextView txtCode, txtAddress, txtPhone;
    protected Inmueble property;

    protected void setViewComponents() {
        btnRent = findViewById(R.id.btnRent);
        btnFileProperty = findViewById(R.id.btnFile);
        txtCode = findViewById(R.id.txtCode);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
    }

    protected void setOnClickListeners() {
        btnRent.setOnClickListener(view -> rent());
        btnFileProperty.setOnClickListener(view -> fileProperty());
    }

    private void rent() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DetailPropertyActivity.this);
        dialog.setTitle(R.string.rent_confirmation_message);

        EditText input = buildDialogEditText();
        dialog.setView(input);

        dialog.setPositiveButton(R.string.continue_btn, (dialogInterface, i) -> {
            try {
                int code = tryToGetCode(input.getText().toString());
                property.arrendar(code);

                dialogInterface.dismiss();
            } catch (ValidationException exception) {
                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (Exception exception) {
                Toast.makeText(this, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setNeutralButton(R.string.cancel_btn, (dialogInterface, i) -> dialogInterface.dismiss());
        dialog.show();
    }

    private void fileProperty() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailPropertyActivity.this);
        builder.setTitle(R.string.file_property_confirmation_message);

        EditText input = buildDialogEditText();
        builder.setView(input);

        builder.setPositiveButton(R.string.continue_btn, (dialogInterface, i) -> {
            try {
                int code = tryToGetCode(input.getText().toString());
                property.radicar(code);

                dialogInterface.dismiss();
            } catch (ValidationException exception) {
                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (Exception exception) {
                Toast.makeText(this, "Ocurrió un error inesperado", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton(R.string.cancel_btn, (dialogInterface, i) -> dialogInterface.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private EditText buildDialogEditText() {
        final EditText input = new EditText(DetailPropertyActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        return input;
    }

    protected void loadProperty() {
        property = InmueblesDB.selectedProperty;

        if(property == null) {
            onErrorCloseScreen();
            return;
        }

        txtAddress.setText(property.getDireccion());
        txtPhone.setText(property.getTelefono());
        txtCode.setText(Integer.toString(property.getCodigo()));
    }

    private int tryToGetCode(String codeString) throws ValidationException{
        if (codeString.isEmpty()) {
            throw new ValidationException("El código no puede estar vacío");
        }

        try {
            return Integer.parseInt(codeString);
        } catch (Exception e) {
            throw new ValidationException("Asegurese de ingresar un código valido");
        }
    }

    protected void onErrorCloseScreen() {
        InmueblesDB.selectedProperty = null;
        finish();
        Toast.makeText(getApplicationContext(), "Ocurrió un error, por favor inténtalo de nuevo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InmueblesDB.selectedProperty = null;
    }
}