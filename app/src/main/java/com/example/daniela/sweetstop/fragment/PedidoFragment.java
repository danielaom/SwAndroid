package com.example.daniela.sweetstop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniela.sweetstop.PedidoActivity;
import com.example.daniela.sweetstop.R;

/**
 * Created by gonzalopro on 10/12/16.
 */

public class PedidoFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerViewItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedido,container,false);

        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.fab_nuevo_pedido);
        recyclerViewItem = (RecyclerView) root.findViewById(R.id.rv_items);
        recyclerViewItem.hasFixedSize();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PedidoActivity.class));
            }
        });
    }
}
