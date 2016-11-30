package com.example.daniela.sweetstop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.daniela.sweetstop.PromocionesActivity;
import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.service.ObtenerCategorias;

public class CategoriaFragment extends Fragment {

    ListView listViewCategories;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categoria,container,false);
        listViewCategories = (ListView) root.findViewById(R.id.lv_categories);
        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.fab_promocion);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        new ObtenerCategorias(getContext(),listViewCategories).execute();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PromocionesActivity.class));
            }
        });
    }
}
