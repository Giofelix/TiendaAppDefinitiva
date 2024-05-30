package com.example.tiendaappdefinitiva;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Producto> productList;
    private HashMap<Producto, Integer> productCounterMap;

    public CartAdapter(List<Producto> productList, HashMap<Producto, Integer> productCounterMap) {
        this.productList = productList;
        this.productCounterMap = productCounterMap;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Producto producto = productList.get(position);
        holder.productName.setText(producto.getNombre());
        holder.productPrice.setText(String.format("$%.2f", producto.getPrecio()));
        holder.productQuantity.setText("Cantidad: " + productCounterMap.get(producto));
        holder.productImage.setImageResource(producto.getImgproducto()); // Asegúrate de que `getImgproducto` retorne el recurso de la imagen.
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productQuantity;
        ImageView productImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.cart_product_name);
            productPrice = itemView.findViewById(R.id.cart_product_price);
            productQuantity = itemView.findViewById(R.id.cart_product_quantity);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}

