package com.example.aplicaciones_moviles_momento_2.activities;

import android.app.AlertDialog;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicaciones_moviles_momento_2.R;
import com.example.aplicaciones_moviles_momento_2.datasource.InmueblesDataSource;
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
        btnRent.setOnClickListener(view -> tryToPerformRent());
        btnFileProperty.setOnClickListener(view -> tryToFileProperty());
    }

    private void tryToPerformRent() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DetailPropertyActivity.this);
        dialog.setTitle(R.string.rent_confirmation_message);

        EditText input = buildDialogEditText();
        dialog.setView(input);

        dialog.setPositiveButton(R.string.continue_btn, (dialogInterface, i) -> {
            try {
                int code = tryToGetCode(input.getText().toString());
                this.rent(code);

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

    private void rent(int code) {
        String message = "El inmueble ya fue arrendado con este código anteriormente";

        if(InmueblesDataSource.currentRentCode != code) {
            property.arrendar(code);
            message = "Inmueble arrendado con exito (" + code + ")";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    private void tryToFileProperty() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailPropertyActivity.this);
        builder.setTitle(R.string.file_property_confirmation_message);

        EditText input = buildDialogEditText();
        builder.setView(input);

        builder.setPositiveButton(R.string.continue_btn, (dialogInterface, i) -> {
            try {
                int code = tryToGetCode(input.getText().toString());
                this.fileProperty(code);

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

    private void fileProperty(int code) {
        String message = "El inmueble ya fue radicado con este código anteriormente";

        if(InmueblesDataSource.currentSettled != code) {
            property.radicar(code);
            message = "Inmueble radico con exito (" + code + ")";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private EditText buildDialogEditText() {
        final EditText input = new EditText(DetailPropertyActivity.this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        return input;
    }

    protected void loadProperty() {
        property = InmueblesDataSource.selectedProperty;

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
        InmueblesDataSource.resetSelection();
        finish();
        Toast.makeText(getApplicationContext(), "Ocurrió un error, por favor inténtalo de nuevo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InmueblesDataSource.resetSelection();
    }
}