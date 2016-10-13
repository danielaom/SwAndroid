package com.example.daniela.sweetstop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniela.sweetstop.CatalogoActivity;
import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.model.Categoria;

import java.util.List;

/**
 * Created by gonzalopro on 10/12/16.
 */

public class CategoryAdapter extends ArrayAdapter<Categoria> {

    public CategoryAdapter(Context paramContext, List<Categoria> paramProgrammings) {
        super(paramContext, 0, paramProgrammings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cell_category, parent, false);

        }

        TextView textViewTitle = (TextView) convertView.findViewById(R.id.tv_cell_category_title);
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.ll_cell_categoria);

        final Categoria categoria = getItem(position);

        assert categoria != null;
        textViewTitle.setText(categoria.getNombre());

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"ID: " + categoria.getIdCategoria(),Toast.LENGTH_SHORT).show();
                getContext().startActivity(new Intent(getContext(), CatalogoActivity.class).putExtra("idCategoria",categoria.getIdCategoria()));
            }
        });

        return convertView;
    }

}
