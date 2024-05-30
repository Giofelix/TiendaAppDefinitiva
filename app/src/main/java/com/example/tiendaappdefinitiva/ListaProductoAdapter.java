package com.example.tiendaappdefinitiva;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaProductoAdapter extends RecyclerView.Adapter<ListaProductoAdapter.ProductoViewHolder> {

    ArrayList<Producto> listaArrayProductos;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onAddToCartClick(Producto producto);
    }

    public ListaProductoAdapter(ArrayList<Producto> listaArrayProductos, OnItemClickListener listener) {
        this.listaArrayProductos = listaArrayProductos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, null, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        holder.assignData(listaArrayProductos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaArrayProductos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {

        ImageView viewImage;
        TextView viewName, viewPrice, viewDescription;
        Button viewBtnCart;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewImage = itemView.findViewById(R.id.viewImage);
            viewName = itemView.findViewById(R.id.viewName);
            viewPrice = itemView.findViewById(R.id.viewPrice);
            viewDescription = itemView.findViewById(R.id.viewDescription);
            viewBtnCart = itemView.findViewById(R.id.viewBtnAddCart);
        }

        public void assignData(final Producto producto) {
            viewImage.setImageResource(producto.getImgproducto());
            viewName.setText(producto.getNombre());
            viewPrice.setText(producto.getVerprecio());
            viewDescription.setText(producto.getDescripcion());

            viewBtnCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onAddToCartClick(producto);
                }
            });
        }
    }
}

