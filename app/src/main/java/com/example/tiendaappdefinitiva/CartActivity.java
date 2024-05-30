package com.example.tiendaappdefinitiva;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<Producto> uniqueProductList;
    private Button checkoutButton;
    private TextView cartTitle;
    private TextView subtotalcompra;
    private TextView totalcompra;
    private double subtotal = 0.0;

    // Mapa para mantener el contador de productos en el carrito
    private HashMap<Producto, Integer> productCounterMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_recycler_view);
        checkoutButton = findViewById(R.id.btnpagar);
        cartTitle = findViewById(R.id.cart_title);
        subtotalcompra = findViewById(R.id.subtotalcompra);
        totalcompra = findViewById(R.id.totalcompra);

        Button btncancelarcompra = findViewById(R.id.btncancelarcompra);

        btncancelarcompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar la lista de productos únicos en el carrito y el mapa del contador
        uniqueProductList = new ArrayList<>();
        productCounterMap = new HashMap<>();

        // Obtener la lista de productos agregados al carrito desde el intent
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("cartProductList")) {
            ArrayList<Producto> cartProductList = extras.getParcelableArrayList("cartProductList");
            for (Producto product : cartProductList) {
                if (productCounterMap.containsKey(product)) {
                    // Si el producto ya está en el carrito, aumenta su contador
                    int count = productCounterMap.get(product);
                    productCounterMap.put(product, count + 1);
                } else {
                    // Si es un nuevo producto, agrega uno al contador
                    productCounterMap.put(product, 1);
                    uniqueProductList.add(product); // Agregar el producto a la lista única solo la primera vez
                }
                // Calcula el subtotal
                subtotal += product.getPrecio();
            }
        }

        // Establecer el título del carrito y el adaptador
        cartTitle.setText("Carrito de Compras (" + uniqueProductList.size() + ")");
        cartAdapter = new CartAdapter(uniqueProductList, productCounterMap);
        recyclerView.setAdapter(cartAdapter);

        // Mostrar el subtotal y el total
        subtotalcompra.setText(String.format("Subtotal: $%.2f", subtotal));
        totalcompra.setText(String.format("Total: $%.2f", subtotal)); // En este caso el total es igual al subtotal, podrías actualizarlo si hay impuestos u otros cargos adicionales.

        checkoutButton.setOnClickListener(view -> {
            // Acción para el botón de proceder al pago
            Toast.makeText(CartActivity.this, "Procediendo al pago", Toast.LENGTH_SHORT).show();
        });
    }
}

