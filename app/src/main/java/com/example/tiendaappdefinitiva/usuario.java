package com.example.tiendaappdefinitiva;


public class usuario {

    private String uid;
    private String nombre;
    private String telefono;
    private String direccion;
    private String ciudad;
    private String correo;
    private String contrasena;
    private String confirmarcontrasena;

    private boolean terminoscondiciones;

    public usuario(String uid, String nombre, String telefono, String direccion, String ciudad, String correo, String contrasena, String confirmarcontrasena, boolean terminoscondiciones) {
        this.uid = uid;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.correo = correo;
        this.contrasena = contrasena;
        this.confirmarcontrasena = confirmarcontrasena;
        this.terminoscondiciones = terminoscondiciones;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getConfirmarcontrasena() {
        return confirmarcontrasena;
    }

    public void setConfirmarcontrasena(String confirmarcontrasena) {
        this.confirmarcontrasena = confirmarcontrasena;
    }

    public boolean isTerminoscondiciones() {
        return terminoscondiciones;
    }

    public void setTerminoscondiciones(boolean terminoscondiciones) {
        this.terminoscondiciones = terminoscondiciones;
    }

    @Override
    public String toString(){

        return "Usuario{" +
                "uid='" + uid + '\'' +
                "Nombre= '" + nombre + '\''+
                ", Telefono ='"+ telefono + '\''+
                ", Direccion ='"+ direccion + '\''+
                ", Ciudad ='"+ ciudad + '\''+
                ", Correo ='"+ correo + '\''+
                ", Contraseña ='"+ contrasena + '\''+
                ", Confirmar contrasena ='"+ confirmarcontrasena + '\''+
                ", Terminos y condiciones ='"+ terminoscondiciones + '\''+
                '}';

    }
}

