package com.alvarogiron;

import java.util.*;

public class Carrito {
    private final String nombre;
    private final Map<Producto, Integer> lista;

    public Carrito(String nombre){
        this.nombre = nombre;
        lista = new TreeMap<>();
    }

    public boolean agregarItemAlCarrito(Producto producto, int cantidad){
        if(producto != null && cantidad > 0 && cantidad <= producto.getStockDisponible()){
            int enCarrito = lista.getOrDefault(producto, 0);
            lista.put(producto, enCarrito + cantidad);
//            producto.ajustarStock(-cantidad);
            System.out.println("Se " + ((cantidad > 1) ? "agregaron " : "agregó ") + cantidad + " " + producto.getNombre() + " al carrito.");
            return true;
        }
        if(producto != null)
            System.out.println("No se agregó " + producto.getNombre() +" al carrito");
        return false;
    }

    public boolean quitarItemDelCarrito(Producto producto, int cantidad){
        if(producto != null) {
            int enCarrito = lista.getOrDefault(producto, 0);
            if (cantidad > 0 && cantidad <= enCarrito) {
                if (cantidad == enCarrito) {
                    lista.remove(producto);
                } else
                    lista.put(producto, enCarrito - cantidad);
                producto.ajustarStock(cantidad);
                return true;
            }
        }
        if(producto != null)
            System.out.println(producto.getNombre() + "no está en el carrito");
        return false;
    }

    public Map<Producto, Integer> items(){
        return Collections.unmodifiableMap(lista);
    }

    @Override
    public String toString() {
        String s = "\nCanastilla " + nombre + " contiene " + lista.size() + ((lista.size() == 1) ? " item" : " items") + "\n";
        double costoTotal = 0.0;
        for (Map.Entry<Producto, Integer> item : lista.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " comprados\n";
            costoTotal += item.getKey().getPrecio() * item.getValue();
        }
        return s + "Costo total " + String.format("%.02f", costoTotal);
    }
}
