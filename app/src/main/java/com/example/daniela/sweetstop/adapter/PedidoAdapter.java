package com.example.daniela.sweetstop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daniela.sweetstop.R;
import com.example.daniela.sweetstop.SwAndroid;
import com.example.daniela.sweetstop.model.Catalogo;
import com.example.daniela.sweetstop.model.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonzalopro on 10/20/16.
 */

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.CellPedido> {

    private Context context;
    private List<Catalogo> catalogos;
    private List<Pedido> pedidos;


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
        pedidos = ((SwAndroid) context.getApplicationContext()).getPedidos();
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
                if (addItemOrderList.contains(catalogo)) {
                    Toast.makeText(context,"El producto ya fue seleccionado!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,"Nuevo producto añadido",Toast.LENGTH_SHORT).show();
                    addItemOrderList.add(catalogo);
                    addItemOrder(new Pedido(catalogo.getIdProducto(),"1",catalogo));
                }
                //addItemOrder(catalogo);
            }
        });
    }

    private void addItemOrder(Pedido pedido) {
        pedidos.add(pedido);
        ((SwAndroid) context.getApplicationContext()).setPedidos(pedidos);

        listViewOrder.setAdapter(new ProductoAdapter(context, pedidos));
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
