package com.example.daniela.sweetstop.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniela.sweetstop.EstadoPedidoActivity;
import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.model.EstadoPedido;
import com.example.daniela.sweetstop.utilitarios.ObjectWrapperForBinder;

import java.util.List;

/**
 * Created by gonzalopro on 11/14/16.
 */

public class EstadoPedidoAdapter extends RecyclerView.Adapter<EstadoPedidoAdapter.CellEstados> {

    private Context context;
    private List<EstadoPedido> estadoPedidos;
    private final LinearLayout.LayoutParams layoutParams =  new LinearLayout.LayoutParams(200, 200);
    private ImageView imageViewRecibido, imageViewRechazado, imageViewEnProceso, imageViewTerminado, imageViewDespachado;
    private ImageView imageViewHRecibido, imageViewHEnProceso, imageViewHTerminado, imageViewHDespachado;

    public EstadoPedidoAdapter(Context context, List<EstadoPedido> estadoPedidos) {
        this.context = context;
        this.estadoPedidos = estadoPedidos;
    }

    @Override
    public CellEstados onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_estados, parent, false);

        /* Hint */
        imageViewHRecibido = new ImageView(context);
        imageViewHRecibido.setLayoutParams(layoutParams);
        imageViewHRecibido.setBackgroundResource(R.drawable.hint_recibido);

        imageViewHEnProceso = new ImageView(context);
        imageViewHEnProceso.setLayoutParams(layoutParams);
        imageViewHEnProceso.setBackgroundResource(R.drawable.hint_proceso);

        imageViewHTerminado = new ImageView(context);
        imageViewHTerminado.setLayoutParams(layoutParams);
        imageViewHTerminado.setBackgroundResource(R.drawable.hint_terminado);

        imageViewHDespachado = new ImageView(context);
        imageViewHDespachado.setLayoutParams(layoutParams);
        imageViewHDespachado.setBackgroundResource(R.drawable.hint_despachado);

        /* Logo */
        imageViewRecibido = new ImageView(context);
        imageViewRecibido.setLayoutParams(layoutParams);
        imageViewRecibido.setBackgroundResource(R.drawable.recibido);

        imageViewRechazado = new ImageView(context);
        imageViewRechazado.setLayoutParams(layoutParams);
        imageViewRechazado.setBackgroundResource(R.drawable.rechazado);

        imageViewEnProceso = new ImageView(context);
        imageViewEnProceso.setLayoutParams(layoutParams);
        imageViewEnProceso.setBackgroundResource(R.drawable.proceso);

        imageViewTerminado = new ImageView(context);
        imageViewTerminado.setLayoutParams(layoutParams);
        imageViewTerminado.setBackgroundResource(R.drawable.terminado);

        imageViewDespachado = new ImageView(context);
        imageViewDespachado.setLayoutParams(layoutParams);
        imageViewDespachado.setBackgroundResource(R.drawable.despachado);



        return new CellEstados(view);
    }

    @Override
    public void onBindViewHolder(CellEstados holder, int position) {
        final EstadoPedido estadoPedido = estadoPedidos.get(position);
        holder.textViewCodigo.setText(estadoPedido.getCodigo());
        holder.textViewFecha.setText(estadoPedido.getFecha());

        holder.linearLayout.removeAllViews();

        switch (estadoPedido.getEstadoIdEstado()) {
            case "3":
                holder.linearLayout.addView(imageViewRecibido);
                holder.linearLayout.addView(imageViewHEnProceso);
                holder.linearLayout.addView(imageViewHTerminado);
                holder.linearLayout.addView(imageViewHDespachado);
                break;
            case "4":
                holder.linearLayout.addView(imageViewRechazado);
                break;
            case "5":
                holder.linearLayout.addView(imageViewRecibido);
                holder.linearLayout.addView(imageViewEnProceso);
                holder.linearLayout.addView(imageViewHTerminado);
                holder.linearLayout.addView(imageViewHDespachado);
                break;
            case "6":
                holder.linearLayout.addView(imageViewRecibido);
                holder.linearLayout.addView(imageViewEnProceso);
                holder.linearLayout.addView(imageViewTerminado);
                holder.linearLayout.addView(imageViewDespachado);
                break;
            case "7":
                holder.linearLayout.addView(imageViewHRecibido);
                holder.linearLayout.addView(imageViewHEnProceso);
                holder.linearLayout.addView(imageViewHTerminado);
                holder.linearLayout.addView(imageViewHDespachado);
                break;
            case "9":
                holder.linearLayout.addView(imageViewRecibido);
                holder.linearLayout.addView(imageViewEnProceso);
                holder.linearLayout.addView(imageViewTerminado);
                holder.linearLayout.addView(imageViewHDespachado);
                break;

        }

        holder.imageViewInfo.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                final Bundle bundle = new Bundle();
                bundle.putBinder("object_estado_pedido", new ObjectWrapperForBinder(estadoPedido));
                context.startActivity(new Intent(context, EstadoPedidoActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return estadoPedidos.size();
    }

    public class CellEstados extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView textViewCodigo;
        TextView textViewFecha;
        ImageView imageViewInfo;

        public CellEstados(View itemView) {
            super(itemView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_cell_estados_pedidos);
            textViewCodigo = (TextView) itemView.findViewById(R.id.tv_cell_estado_codigo);
            textViewFecha = (TextView) itemView.findViewById(R.id.tv_cell_estado_fecha);
            imageViewInfo = (ImageView) itemView.findViewById(R.id.iv_cell_estado_info);
        }
    }
}
