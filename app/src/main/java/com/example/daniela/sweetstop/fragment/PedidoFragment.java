package com.example.daniela.sweetstop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniela.sweetstop.PedidoActivity;
import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.SwAndroid;
import com.example.daniela.sweetstop.service.ObtenerEstadosPedidos;

/**
 * Created by gonzalopro on 10/12/16.
 */

public class PedidoFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerViewItem;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pedido,container,false);

        floatingActionButton = (FloatingActionButton) root.findViewById(R.id.fab_nuevo_pedido);
        mSwipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.srl_fragment_pedido);
        recyclerViewItem = (RecyclerView) root.findViewById(R.id.rv_items);
        recyclerViewItem.hasFixedSize();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewItem.setLayoutManager(linearLayoutManager);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        id = ((SwAndroid)getActivity().getApplicationContext()).getId_cliente();
        Log.d("PF", "id Estado Usuario: " + id);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ObtenerEstadosPedidos(getActivity(),recyclerViewItem,id,mSwipeRefreshLayout).execute();
            }
        });

        new ObtenerEstadosPedidos(getActivity(),recyclerViewItem,id,mSwipeRefreshLayout).execute();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PedidoActivity.class));
            }
        });
    }
}
