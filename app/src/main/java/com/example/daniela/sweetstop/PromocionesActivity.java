package com.example.daniela.sweetstop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.daniela.sweetstop.service.ObtenerPromocion;

/**
 * Created by gonzalopro on 11/30/16.
 */

public class PromocionesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promociones);
        recyclerView = (RecyclerView) findViewById(R.id.rv_promotions);
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        new ObtenerPromocion(this,recyclerView).execute();

    }


}
