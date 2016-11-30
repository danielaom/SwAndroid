package com.example.daniela.sweetstop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniela.sweetstop.DetallePromocionActivity;
import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.model.Promocion;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gonzalopro on 11/30/16.
 */

public class PromocionesAdapter extends RecyclerView.Adapter<PromocionesAdapter.CellPromocion> {

    private Context context;
    private List<Promocion> promocions;

    public PromocionesAdapter(Context context, List<Promocion> promocions) {
        this.context = context;
        this.promocions = promocions;
    }

    @Override
    public CellPromocion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_promocion, parent, false);
        return new CellPromocion(view);
    }

    @Override
    public void onBindViewHolder(CellPromocion holder, int position) {
        final Promocion promocion = promocions.get(position);
        holder.textViewTitle.setText(promocion.getNombre());
        holder.textViewDetail.setText(promocion.getDescripcion());
        holder.textViewPrice.setText(promocion.getPrecio() + " Bs.");
        holder.textViewDateStart.setText("Inicio: " + promocion.getFechaInicio());
        holder.textViewDateEnd.setText("Fin: " + promocion.getFechaFin());
        Glide.with(context).load(promocion.getImagen()).into(holder.imageViewLogo);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetallePromocionActivity.class).putExtra("idPromocion", promocion.getIdPromocion()).putExtra("precio",promocion.getPrecio()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return promocions.size();
    }

    public class CellPromocion extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView textViewTitle;
        TextView textViewDetail;
        TextView textViewPrice;
        TextView textViewDateStart;
        TextView textViewDateEnd;
        ImageView imageViewLogo;

        public CellPromocion(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cv_promocion);
            textViewTitle = (TextView) itemView.findViewById(R.id.tv_cell_promotion_title);
            textViewDetail = (TextView) itemView.findViewById(R.id.tv_cell_promotion_detail);
            textViewPrice = (TextView) itemView.findViewById(R.id.tv_cell_promotion_price);
            textViewDateStart = (TextView) itemView.findViewById(R.id.tv_cell_promotion_init);
            textViewDateEnd = (TextView) itemView.findViewById(R.id.tv_cell_promotion_end);
            imageViewLogo = (ImageView) itemView.findViewById(R.id.iv_cell_promotion_logo);
        }
    }
}
