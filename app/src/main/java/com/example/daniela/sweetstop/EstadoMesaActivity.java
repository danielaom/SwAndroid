package com.example.daniela.sweetstop;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.daniela.sweetstop.adapter.CatalogoAdapter;
import com.example.daniela.sweetstop.model.Catalogo;
import com.example.daniela.sweetstop.service.ObtenerEstadosMesas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by gonzalopro on 10/30/16.
 */

public class EstadoMesaActivity extends AppCompatActivity {

    public static final String TAG = EstadoMesaActivity.class.getSimpleName();
    private String idMesa;
    private FloatingActionButton floatingActionButton;
    private SliderLayout mDemoSlider;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    HashMap<String,Integer> file_maps;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_mesa);

        recyclerView = (RecyclerView) findViewById(R.id.rv_state_table);
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);


        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_nueva_reserva);

        idMesa = getIntent().getStringExtra("idMesa");
        new ObtenerEstadosMesas(idMesa,EstadoMesaActivity.this, recyclerView).execute();
    }

    @Override
    protected void onStart() {
        super.onStart();

        file_maps = new HashMap<String, Integer>();
        Log.d(TAG, "id Seleccionado: " + idMesa);
        switch (idMesa) {
            case "1":
                file_maps.clear();
                file_maps.put("Mesa 11",R.drawable.mesa_1);
                showImage();
                break;
            case "2":
                file_maps.clear();
                file_maps.put("Mesa 20",R.drawable.mesa_2);
                showImage();
                break;
            case "3":
                file_maps.clear();
                file_maps.put("Mesa 30",R.drawable.mesa_3_1);
                file_maps.put("Mesa 31", R.drawable.mesa_3_2);
                showImage();
                break;
            case "4":
                file_maps.clear();
                file_maps.put("Mesa 40",R.drawable.mesa_4_1);
                file_maps.put("Mesa 41", R.drawable.mesa_4_2);
                showImage();
                break;
            case "5":
                file_maps.clear();
                file_maps.put("Mesa 50",R.drawable.mesa_5_1);
                file_maps.put("Mesa 51", R.drawable.mesa_5_2);
                file_maps.put("Mesa 52", R.drawable.mesa_5_3);
                showImage();
                break;
            case "6":
                file_maps.clear();
                file_maps.put("Mesa 60",R.drawable.mesa_6_1);
                file_maps.put("Mesa 61", R.drawable.mesa_6_2);
                showImage();
                break;
            case "7":
                file_maps.clear();
                file_maps.put("Mesa 70",R.drawable.mesa_7);
                showImage();
                break;
            case "8":
                file_maps.clear();
                file_maps.put("Mesa 80",R.drawable.mesa_8_1);
                file_maps.put("Mesa 81", R.drawable.mesa_8_2);
                showImage();
                break;
        }

        //file_maps.clear();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EstadoMesaActivity.this, ReservaActivity.class).putExtra("idMesa", idMesa));
            }
        });

    }

    private void showImage() {

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name));

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
    }

    @Override
    protected void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }


}
