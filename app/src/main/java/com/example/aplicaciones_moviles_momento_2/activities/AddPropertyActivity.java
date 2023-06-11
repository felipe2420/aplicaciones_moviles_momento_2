package com.example.aplicaciones_moviles_momento_2.activities;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplicaciones_moviles_momento_2.R;
import com.example.aplicaciones_moviles_momento_2.datasource.InmueblesDB;
import com.example.aplicaciones_moviles_momento_2.models.Inmueble;
import com.example.aplicaciones_moviles_momento_2.utils.ValidationException;

public class AddPropertyActivity extends NavigationActivity {
    protected Button btnSave;
    protected EditText txtCode, txtAddress, txtPhone;

    protected void setViewComponents() {
        btnSave = findViewById(R.id.btnSave);
        txtCode = findViewById(R.id.txtCode);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
    }

    protected void setOnClickListeners(){
        btnSave.setOnClickListener(view -> createProperty());
    }

    protected void createProperty() {}

    protected void save(Inmueble property) throws ValidationException {
        validateIfExists(property.getCodigo());

        InmueblesDB.data.add(property);
    }
    protected Inmueble tryToCreateBaseProperty() throws ValidationException {
        int code = tryGetCode();
        String address = tryToGetAddress();
        String phone = tryGetPhone();

        return new Inmueble(code, address, phone);
    }

    private int tryGetCode() throws ValidationException {
        String codeString = txtCode.getText().toString();

        if (codeString.isEmpty()) {
            throw new ValidationException("El código no puede estar vacío");
        }

        try {
            return Integer.parseInt(codeString);
        } catch (Exception e) {
            throw new ValidationException("El código solo admite números");
        }
    }

    private String tryToGetAddress() throws ValidationException {
        String address = txtAddress.getText().toString();

        if (address.isEmpty()) {
            throw new ValidationException("La dirección no puede estar vacía");
        }

        return address;
    }

    private String tryGetPhone() throws ValidationException {
        String phone = txtPhone.getText().toString();

        if (phone.isEmpty()) {
            throw new ValidationException("El teléfono no puede estar vacío");
        }

        return phone;
    }

    protected void showMessageAndClose() {
        Toast.makeText(this, R.string.property_saved, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void validateIfExists(int code) throws ValidationException{
        Inmueble result = InmueblesDB.data.stream()
                .filter(item -> item.getCodigo() == code)
                .findFirst()
                .orElse(null);

        if (result != null) {
            throw new ValidationException("Ya existe un inmueble con el mismo código");
        }
    }
}