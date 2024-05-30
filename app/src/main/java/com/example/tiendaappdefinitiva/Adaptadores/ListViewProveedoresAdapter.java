package com.example.tiendaappdefinitiva.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tiendaappdefinitiva.Models.Proveedor;
import com.example.tiendaappdefinitiva.R;

import java.util.ArrayList;

public class ListViewProveedoresAdapter extends BaseAdapter {

    Context context;
    ArrayList<Proveedor> proveedorData;
    LayoutInflater layoutInflater;
    Proveedor proveedorModel;

    public ListViewProveedoresAdapter(Context context, ArrayList<Proveedor> proveedorData) {
        this.context = context;
        this.proveedorData = proveedorData;
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
    }

    @Override
    public int getCount() {
        return proveedorData.size();
    }

    @Override
    public Object getItem(int position) {
        return proveedorData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView==null){
            rowView = layoutInflater.inflate(R.layout.lista_proveedores,
                    null,
             true);
        }
        //Enlazar vistas
        TextView nombre = rowView.findViewById(R.id.nombres);
        TextView telefono = rowView.findViewById(R.id.telefono);
        TextView fecharegistro = rowView.findViewById(R.id.fecharegistro);

        proveedorModel = proveedorData.get(position);
        nombre.setText(proveedorModel.getNombre());
        telefono.setText(proveedorModel.getTelefono());
        fecharegistro.setText(proveedorModel.getFecharegistro());

        return rowView;
    }
}
