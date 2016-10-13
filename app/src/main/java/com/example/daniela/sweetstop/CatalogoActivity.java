package com.example.daniela.sweetstop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.daniela.sweetstop.adapter.CatalogoAdapter;
import com.example.daniela.sweetstop.service.ObtenerCatalogo;

/**
 * Created by gonzalopro on 10/13/16.
 */
public class CatalogoActivity extends AppCompatActivity {

    ListView listViewCatalogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        listViewCatalogo = (ListView) findViewById(R.id.lv_catalogo);
        new ObtenerCatalogo(CatalogoActivity.this, listViewCatalogo).execute(getIntent().getStringExtra("idCategoria"));
    }
}
