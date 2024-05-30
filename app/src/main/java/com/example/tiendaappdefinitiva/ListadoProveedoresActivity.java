package com.example.tiendaappdefinitiva;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.example.tiendaappdefinitiva.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tiendaappdefinitiva.Adaptadores.ListViewProveedoresAdapter;
import com.example.tiendaappdefinitiva.Models.Proveedor;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PrivateKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class ListadoProveedoresActivity extends AppCompatActivity {

    private ArrayList<Proveedor> listproveedor = new ArrayList<Proveedor>();
    ArrayAdapter<Proveedor> arrayAdapterProveedor;
    ListViewProveedoresAdapter listviewProveedorAdapter;
    LinearLayout linearLayoutEditar;

    EditText inputNombre, inputTelefono;
    Button btnCancelar;
    ListView listViewProveedores;

    Proveedor proveedorSeleccionado;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);

        inputNombre = findViewById(R.id.inputNombre);
        inputTelefono = findViewById(R.id.inputTelefono);
        btnCancelar = findViewById(R.id.btnCancelar);

        listViewProveedores = findViewById(R.id.listViewProveedores);
        linearLayoutEditar = findViewById(R.id.linearLayoutEditar);

        listViewProveedores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                proveedorSeleccionado = (Proveedor) parent.getItemAtPosition(position);
                inputNombre.setText(proveedorSeleccionado.getNombre());
                inputTelefono.setText(proveedorSeleccionado.getTelefono());
                //hacer visible el linearLayout

                linearLayoutEditar.setVisibility(View.VISIBLE);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutEditar.setVisibility(View.GONE);
                proveedorSeleccionado = null;
            }
        });

        inicializarFirebase();
        listarproveedores();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarproveedores() {
        databaseReference.child("Proveedor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listproveedor.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()) {

                    Proveedor p = objSnaptshot.getValue(Proveedor.class);
                    listproveedor.add(p);
                }

                //Iniciar adaptador
                listviewProveedorAdapter = new ListViewProveedoresAdapter(ListadoProveedoresActivity.this,listproveedor);
                //arrayAdapterProveedor = new ArrayAdapter<Proveedor>(
                //        ListadoProveedoresActivity.this,
                      //  android.R.layout.simple_list_item_1,
                      //  listproveedor
              //  );

                listViewProveedores.setAdapter(listviewProveedorAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String nombre = inputNombre.getText().toString();
        String telefono = inputTelefono.getText().toString();

        switch (item.getItemId()) {
            case R.id.menu_agregar:
                insertar();
                break;
            case R.id.menu_guardar:
                if (proveedorSeleccionado != null) {
                    if (validarInputs() == false) {
                        Proveedor p = new Proveedor();
                        p.setIdProveedor(proveedorSeleccionado.getIdProveedor());
                        p.setNombre(nombre);
                        p.setTelefono(telefono);
                        p.setFecharegistro(proveedorSeleccionado.getFecharegistro());
                        p.setTimestamp(proveedorSeleccionado.getTimestamp());
                        databaseReference.child("Proveedor").child(p.getIdProveedor()).setValue(p);

                        Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show();
                        linearLayoutEditar.setVisibility(View.GONE);
                        proveedorSeleccionado = null;

                    }
                } else {
                    Toast.makeText(this, "Seleccione un proveedor", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.menu_eliminar:
                if (proveedorSeleccionado != null) {
                    Proveedor p2 = new Proveedor();
                    p2.setIdProveedor(proveedorSeleccionado.getIdProveedor());
                    databaseReference.child("Proveedor").child(p2.getIdProveedor()).removeValue();
                    linearLayoutEditar.setVisibility(View.GONE);
                    proveedorSeleccionado = null;
                    Toast.makeText(this, "Registro eliminado corerctamente", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Seleccione un registro para eliminar", Toast.LENGTH_SHORT).show();
                }
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertar() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(
                ListadoProveedoresActivity.this
        );

        View mView = getLayoutInflater().inflate(R.layout.insertar, null);
        Button btnInsertar = (Button) mView.findViewById(R.id.btninsertar);
        final EditText mInputNombre = (EditText) mView.findViewById(R.id.inputNombre);
        final EditText mInputTelefono = (EditText) mView.findViewById(R.id.inputTelefono);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = mInputNombre.getText().toString();
                String telefono = mInputTelefono.getText().toString();
                if (nombre.isEmpty() || nombre.length() < 3) {
                    showError(mInputNombre, "Nombre invalido, minimo 3 caracteres");
                } else if (telefono.isEmpty() || telefono.length() < 9) {
                    showError(mInputTelefono, "Telefono invalido, minimo nueve numeros");

                } else {

                    Proveedor p = new Proveedor();
                    p.setIdProveedor(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setTelefono(telefono);
                    p.setFecharegistro(getFechaNormal(getFechaMilisegundos()));
                    p.setTimestamp(getFechaMilisegundos()*-1);
                    databaseReference.child("Proveedor").child(p.getIdProveedor()).setValue(p);
                    Toast.makeText(ListadoProveedoresActivity.this,"Registro exitoso",Toast.LENGTH_LONG).show();

                    dialog.dismiss();
                }
            }
        });
    }

    public void showError(EditText input, String s) {
        input.requestFocus();
        input.setError(s);
    }

    public long getFechaMilisegundos() {
        Calendar calendar = Calendar.getInstance();
        long tiempounix = calendar.getTimeInMillis();
        return tiempounix;
    }

    public String getFechaNormal(long fechamilisegundos) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        String fecha = sdf.format(fechamilisegundos);
        return fecha;
    }

    public boolean validarInputs(){

        String nombre = inputNombre.getText().toString();
        String telefono = inputTelefono.getText().toString();
        if (nombre.isEmpty()||nombre.length()<3){
            showError(inputNombre,"Nombre invalido, minimo 3 caracteres");
            return true;
        }else if (telefono.isEmpty()||telefono.length()<9){
            showError(inputTelefono, "Telefono invalido, minimo nueve numeros");
            return true;
        }else {
            return false;
        }
    }

}
