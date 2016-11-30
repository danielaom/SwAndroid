package com.example.daniela.sweetstop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.model.DetallePromocion;

import java.util.List;

/**
 * Created by gonzalopro on 11/30/16.
 */

public class DetallePromocionAdapter extends ArrayAdapter<DetallePromocion> {

    public DetallePromocionAdapter(Context paramContext, List<DetallePromocion> detallePromocions) {
        super(paramContext, 0, detallePromocions);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cell_detalle_promocion, parent, false);

        }

        TextView textViewTitle = (TextView) convertView.findViewById(R.id.tv_cell_detail_promotion_title);
        TextView textViewDescription = (TextView) convertView.findViewById(R.id.tv_cell_detail_promotion_description);
        ImageView imageViewLogo = (ImageView) convertView.findViewById(R.id.iv_cell_detail_promotion_logo);

        final DetallePromocion detallePromocion = getItem(position);

        assert detallePromocion != null;

        textViewTitle.setText(detallePromocion.getNombre());
        textViewDescription.setText(detallePromocion.getDescripcion());
        Glide.with(getContext()).load(detallePromocion.getImagen()).into(imageViewLogo);

        return convertView;
    }

}
