package com.example.aplicaciones_moviles_momento_2.activities.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aplicaciones_moviles_momento_2.R;
import com.example.aplicaciones_moviles_momento_2.models.Casa;
import com.example.aplicaciones_moviles_momento_2.models.Inmueble;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PropertyListAdapter extends ArrayAdapter<Inmueble> {
    public PropertyListAdapter(Context context, ArrayList<Inmueble> list){
        super(context, R.layout.list_item_property, R.id.textview, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Inmueble property = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_property, parent, false);
        }

        TextView txtAddress = convertView.findViewById(R.id.txtAddress);
        TextView txtPhone = convertView.findViewById(R.id.txtPhone);
        TextView txtCode = convertView.findViewById(R.id.txtCode);
        CircleImageView propertyImage = convertView.findViewById(R.id.propertyImage);

        txtAddress.setText(property.getDireccion());
        txtPhone.setText(property.getTelefono());
        txtCode.setText(Integer.toString(property.getCodigo()));

        int resourceId = R.drawable.buildings_1;
        if(property instanceof Casa) {
            resourceId = R.drawable.house_1;
        }

        propertyImage.setImageResource(resourceId);

        return super.getView(position, convertView, parent);
    }
}
