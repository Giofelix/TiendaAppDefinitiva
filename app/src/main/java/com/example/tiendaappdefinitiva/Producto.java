package com.example.tiendaappdefinitiva;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Producto implements Parcelable {

    private int imgproducto;
    private String nombre;
    private String verprecio;
    private String descripcion;
    private double precio;

    public Producto(int imgproducto, String nombre, String verprecio, String descripcion, double precio) {
        this.imgproducto = imgproducto;
        this.nombre = nombre;
        this.verprecio = verprecio;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getImgproducto() {
        return imgproducto;
    }

    public void setImgproducto(int imgproducto) {
        this.imgproducto = imgproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVerprecio() {
        return verprecio;
    }

    public void setVerprecio(String verprecio) {
        this.verprecio = verprecio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Implementación de Parcelable

    protected Producto(Parcel in) {
        imgproducto = in.readInt();
        nombre = in.readString();
        verprecio = in.readString();
        descripcion = in.readString();
        precio = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgproducto);
        dest.writeString(nombre);
        dest.writeString(verprecio);
        dest.writeString(descripcion);
        dest.writeDouble(precio);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return imgproducto == producto.imgproducto &&
                Double.compare(producto.precio, precio) == 0 &&
                Objects.equals(nombre, producto.nombre) &&
                Objects.equals(verprecio, producto.verprecio) &&
                Objects.equals(descripcion, producto.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imgproducto, nombre, verprecio, descripcion, precio);
    }
}

