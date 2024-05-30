package com.example.tiendaappdefinitiva;// ProductListActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView listadoProductos;
    ArrayList<Producto> listaArrayProductos;
    ArrayList<Producto> cartProductList; // Lista para almacenar los productos en el carrito
    private int cartItemCount = 0;
    private TextView cartCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        RelativeLayout cartLayout = findViewById(R.id.cart_layout);
        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
                // Pasa la lista del carrito a CartActivity
                intent.putParcelableArrayListExtra("cartProductList", cartProductList);
                startActivity(intent);
            }
        });

        listadoProductos = findViewById(R.id.ListadoProductos);
        listadoProductos.setLayoutManager(new LinearLayoutManager(this));

        cartCounter = findViewById(R.id.cart_counter);

        listaArrayProductos = new ArrayList<>();
        listaArrayProductos.add(new Producto(R.drawable.zapatilla, "Zapatilla", "$ 4000", "Zapatilla negra Running", 4000));
        listaArrayProductos.add(new Producto(R.drawable.camiseta, "Camiseta", "$ 4000", "Camiseta estampada ajustada", 4000));
        listaArrayProductos.add(new Producto(R.drawable.sudadera, "Sudadera", "$ 5000", "Sudadera con capucha", 5000));
        listaArrayProductos.add(new Producto(R.drawable.pantalon, "Pantalón", "$ 6000", "Pantalon Levis almohadón", 6000));
        listaArrayProductos.add(new Producto(R.drawable.bolso, "Bolso", "$ 7000", "Bolso de cuero", 7000));
        listaArrayProductos.add(new Producto(R.drawable.reloj, "Reloj", "$ 8000", "Reloj de pulsera", 8000));
        listaArrayProductos.add(new Producto(R.drawable.gorra, "Gorra", "$ 9000", "Gorra deportiva", 9000));
        listaArrayProductos.add(new Producto(R.drawable.bufanda, "Bufanda", "$ 3000", "Bufanda de lana", 3000));
        listaArrayProductos.add(new Producto(R.drawable.sandalia, "Sandalia", "$ 2000", "Sandalia de verano", 2000));
        listaArrayProductos.add(new Producto(R.drawable.sombrero, "Sombrero", "$ 4000", "Sombrero de paja", 4000));

        cartProductList = new ArrayList<>(); // Inicializa la lista del carrito

        ListaProductoAdapter adapter = new ListaProductoAdapter(listaArrayProductos, new ListaProductoAdapter.OnItemClickListener() {
            @Override
            public void onAddToCartClick(Producto producto) {
                cartProductList.add(producto); // Agrega el producto seleccionado al carrito
                cartItemCount++;
                cartCounter.setText(String.valueOf(cartItemCount));
            }
        });

        listadoProductos.setAdapter(adapter);
    }
}
