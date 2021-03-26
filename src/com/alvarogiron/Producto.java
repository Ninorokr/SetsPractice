package com.alvarogiron;

public class Producto implements Comparable<Producto>{
    private final String nombre;
    private double precio;
    private int stock;
    private int enReserva = 0;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = 0;
    }

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStockDisponible() {
        return stock - enReserva;
    }

    public int getEnReserva() {
        return enReserva;
    }

    public void setPrecio(double precio) {
        if(precio >= 0) {
            this.precio = precio;
        }
    }

    public int ajustarStock(int cantidad) {
        int nuevaCantidad = stock + cantidad;
        if(nuevaCantidad >= 0){
            stock = nuevaCantidad;
            return stock;
        }
        return 0;
    }

    public int reservarStock(int cantidad){
        if(cantidad < getStockDisponible()) {
            enReserva += cantidad;
//            stock -= cantidad;
            return getStockDisponible();
        }
        return 0;
    }

    public int liberarStock(int cantidad){
        if(cantidad < enReserva){
            enReserva -= cantidad;
            return enReserva;
        }
        return 0;
    }

    public int liquidarStock(int cantidad){
        if(cantidad >= enReserva){
            stock -= cantidad;
            enReserva -= cantidad;
            return cantidad;
        }
        return 0;
    }

    @Override
    public int hashCode() {
//        System.out.println(nombre + " Producto.hashCode() llamado");
        return nombre.hashCode() + 31;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println(nombre + " Producto.equals() llamado");
        if(this == obj){
            return true;
        }

        if(this.getClass() != obj.getClass()){
            return false;
        }
        boolean isEqual = nombre.equals(((Producto)obj).getNombre());

        if(isEqual){
            System.out.println(nombre + " ya está en la lista");
        } else {
            System.out.println(nombre + " es un producto nuevo");
        }
        return isEqual;
    }

    @Override
    public int compareTo(Producto producto) {
        System.out.println(nombre + " Producto.compareTo() llamado");
        if(this == producto){
            return 0;
        }

        return nombre.compareTo(producto.getNombre());
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + " | Precio: " + precio + " | Stock disponible: " + stock + " | Stock en reserva : " + enReserva;
//        return super.toString();
    }
}
