package com.example.daniela.sweetstop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.model.EstadoMesa;
import com.example.daniela.sweetstop.model.EstadoPedido;

import java.util.List;

/**
 * Created by gonzalopro on 11/29/16.
 */
public class EstadoMesaAdapter extends RecyclerView.Adapter<EstadoMesaAdapter.EstadoMesaCell> {

    private List<EstadoMesa> estadoMesas;

    public EstadoMesaAdapter(List<EstadoMesa> estadoMesas) {
        this.estadoMesas = estadoMesas;
    }

    @Override
    public EstadoMesaCell onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_estado_mesa, parent, false);
        return new EstadoMesaCell(view);
    }

    @Override
    public void onBindViewHolder(EstadoMesaCell holder, int position) {
        EstadoMesa estadoMesa = estadoMesas.get(position);
        holder.textViewInicio.setText(estadoMesa.getFechaInicio());
        holder.textViewFin.setText(estadoMesa.getFechaFin());
    }

    @Override
    public int getItemCount() {
        return estadoMesas.size();
    }

    public class EstadoMesaCell extends RecyclerView.ViewHolder {

        TextView textViewInicio;
        TextView textViewFin;

        public EstadoMesaCell(View itemView) {
            super(itemView);
            textViewInicio = (TextView) itemView.findViewById(R.id.tv_estado_mesa_inicio);
            textViewFin = (TextView) itemView.findViewById(R.id.tv_estado_mesa_fin);
        }
    }
}
