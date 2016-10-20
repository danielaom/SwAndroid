package com.example.daniela.sweetstop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.model.Catalogo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonzalopro on 10/20/16.
 */

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.CellPedido> {

    private Context context;
    private List<Catalogo> catalogos;
    private List<Catalogo> addItemOrderList = new ArrayList<>();
    ListView listViewOrder;

    public PedidoAdapter(Context context, List<Catalogo> catalogos, ListView listView) {
        this.context = context;
        this.catalogos = catalogos;
        listViewOrder = listView;
    }

    @Override
    public PedidoAdapter.CellPedido onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_pedido, parent, false);

        return new CellPedido(root);
    }

    @Override
    public void onBindViewHolder(PedidoAdapter.CellPedido holder, int position) {
        final Catalogo catalogo = catalogos.get(position);
        holder.textViewPrecio.setText(catalogo.getPrecio());
        Glide.with(context).load(catalogo.getImagen()).into(holder.imageViewLogo);
        holder.textViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemOrder(catalogo);
            }
        });
    }

    private void addItemOrder(Catalogo catalogo) {
        addItemOrderList.add(catalogo);
        listViewOrder.setAdapter(new ProductoAdapter(context, addItemOrderList));
    }

    @Override
    public int getItemCount() {
        return catalogos.size();
    }

    public class CellPedido extends RecyclerView.ViewHolder {

        ImageView imageViewLogo;
        TextView textViewPrecio, textViewAdd;

        public CellPedido(View itemView) {
            super(itemView);

            imageViewLogo = (ImageView) itemView.findViewById(R.id.iV_cell_pedido_logo);
            textViewPrecio = (TextView) itemView.findViewById(R.id.tv_cell_pedido_precio);
            textViewAdd = (TextView) itemView.findViewById(R.id.tv_cell_pedido_adicionar);

        }
    }
}
