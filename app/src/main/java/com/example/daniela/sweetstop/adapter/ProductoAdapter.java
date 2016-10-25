package com.example.daniela.sweetstop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.model.Catalogo;
import com.example.daniela.sweetstop.model.Categoria;

import java.util.List;

/**
 * Created by gonzalopro on 10/16/16.
 */

public class ProductoAdapter extends ArrayAdapter<Catalogo> {

    public static final String TAG = ProductoAdapter.class.getSimpleName();

    public ProductoAdapter(Context paramContext, List<Catalogo> productos) {
        super(paramContext, 0, productos);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.card_producto, parent, false);

        }

        TextView textViewTitle = (TextView) convertView.findViewById(R.id.tv_card_producto_nombre);
        TextView textViewDescription = (TextView) convertView.findViewById(R.id.tv_card_producto_detalle);
        final TextView textViewPrecio = (TextView) convertView.findViewById(R.id.tv_card_producto_precio);
        Button buttonAdd = (Button) convertView.findViewById(R.id.btn_card_producto_add);
        Button buttonQuit = (Button) convertView.findViewById(R.id.btn_card_producto_quit);
        Button buttonRemove = (Button) convertView.findViewById(R.id.btn_card_producto_remove);


        final Catalogo catalogo = getItem(position);

        assert catalogo != null;
        textViewTitle.setText(catalogo.getNombre());
        textViewDescription.setText(catalogo.getDescripcion());

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textViewPrecio.getText().toString();
                if (s.equals("1")) {
                    Log.d(TAG, "Cantidad " + s);
                } else {
                    int c = Integer.parseInt(textViewPrecio.getText().toString()) - 1;
                    textViewPrecio.setText(String.valueOf(c));
                }

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String s = textViewPrecio.getText().toString();
                int c = Integer.parseInt(textViewPrecio.getText().toString()) + 1;
                textViewPrecio.setText(String.valueOf(c));
            }
        });

        return convertView;
    }

}
