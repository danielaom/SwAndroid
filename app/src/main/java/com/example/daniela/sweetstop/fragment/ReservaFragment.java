package com.example.daniela.sweetstop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daniela.sweetstop.EstadoMesaActivity;
import com.example.daniela.sweetstop.R;

/**
 * Created by gonzalopro on 10/12/16.
 */

public class ReservaFragment extends Fragment {

    TextView textViewMesa1, textViewMesa2, textViewMesa3, textViewMesa4, textViewMesa5, textViewMesa6, textViewMesa7, textViewMesa8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reserva,container,false);

        textViewMesa1 = (TextView) root.findViewById(R.id.tv_reserva_0);
        textViewMesa2 = (TextView) root.findViewById(R.id.tv_reserva_1);
        textViewMesa3 = (TextView) root.findViewById(R.id.tv_reserva_2);
        textViewMesa4 = (TextView) root.findViewById(R.id.tv_reserva_3);
        textViewMesa5 = (TextView) root.findViewById(R.id.tv_reserva_4);
        textViewMesa6 = (TextView) root.findViewById(R.id.tv_reserva_5);
        textViewMesa7 = (TextView) root.findViewById(R.id.tv_reserva_6);
        textViewMesa8 = (TextView) root.findViewById(R.id.tv_reserva_7);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        textViewMesa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EstadoMesaActivity.class).putExtra("idMesa","1"));
            }
        });

        textViewMesa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EstadoMesaActivity.class).putExtra("idMesa","2"));
            }
        });

        textViewMesa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EstadoMesaActivity.class).putExtra("idMesa","3"));
            }
        });

        textViewMesa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EstadoMesaActivity.class).putExtra("idMesa","4"));
            }
        });

        textViewMesa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EstadoMesaActivity.class).putExtra("idMesa","5"));
            }
        });

        textViewMesa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EstadoMesaActivity.class).putExtra("idMesa","6"));
            }
        });

        textViewMesa7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EstadoMesaActivity.class).putExtra("idMesa","7"));
            }
        });

        textViewMesa8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EstadoMesaActivity.class).putExtra("idMesa","8"));
            }
        });
    }
}
