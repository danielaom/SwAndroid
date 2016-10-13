package com.example.daniela.sweetstop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.model.Catalogo;

import java.util.List;

/**
 * Created by gonzalopro on 10/13/16.
 */

public class CatalogoAdapter extends ArrayAdapter<Catalogo> {

    public CatalogoAdapter(Context paramContext, List<Catalogo> catalogos) {
        super(paramContext, 0, catalogos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cell_catalago, parent, false);

        }

        TextView textViewPrecio = (TextView) convertView.findViewById(R.id.tv_cell_catalogo_precio);
        TextView textViewTitle = (TextView) convertView.findViewById(R.id.tv_cell_catalogo_title);
        TextView textViewDescripcion = (TextView) convertView.findViewById(R.id.tv_cell_catalogo_descripcion);
        ImageView icon = (ImageView) convertView.findViewById(R.id.iV_cell_catalogo);

        final Catalogo catalogo = getItem(position);

        assert catalogo != null;
        textViewPrecio.setText(catalogo.getPrecio());
        textViewTitle.setText(catalogo.getNombre());
        textViewDescripcion.setText(catalogo.getDescripcion());
        Glide.with(getContext()).load(catalogo.getImagen()).into(icon);

        return convertView;
    }
}
