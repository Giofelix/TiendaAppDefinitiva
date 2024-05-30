package com.example.tiendaappdefinitiva;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtlatitud, txtlongitud;
    GoogleMap mMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_maps));

        txtlatitud =findViewById(R.id.txtlatitud);
        txtlongitud =findViewById(R.id.txtlongitud);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng colombia = new LatLng(4.6482928,-74.1902072);
        mMap.addMarker(new MarkerOptions().position(colombia).title("Colombia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colombia));

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

        txtlatitud.setText(""+latLng.latitude);
        txtlongitud.setText(""+latLng.longitude);

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {

        txtlatitud.setText(""+latLng.latitude);
        txtlongitud.setText(""+latLng.longitude);

    }
}