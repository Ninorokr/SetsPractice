package com.alvarogiron;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Inventario {
    public Map<String, Producto> lista;

    public Inventario(){
        lista = new LinkedHashMap<>();
    }

    public boolean agregarStock(Producto producto){
//        System.out.println("Se llamó al metodo Inventario.agregarStock()");
        if(producto != null){
//            System.out.println("parámetro producto = " + producto.getNombre());
            Producto enStock = lista.getOrDefault(producto.getNombre(), producto);
//            System.out.println("enStock = " + enStock.getNombre());
            if(enStock != producto){
//                int nuevaCantidad = enStock.getStock() + producto.getStock(); ERRÓNEO
//                System.out.println("nuevaCantidad = " + nuevaCantidad + " = enStock.getStockDisponible " + enStock.getStock() + " producto.getStcokDisponible " + producto.getStock());
                enStock.ajustarStock(producto.getStockDisponible());
//                System.out.println("Se ajustó stock de " + enStock.getNombre());
            }
            lista.put(enStock.getNombre(), enStock);
//            System.out.println("Se agregó " + enStock.getNombre() + " al stock");
//            System.out.println("-------------------------------------------------------");
            return true;
        }
        return false;
    }

    public boolean venderStock(String nomProducto, int cantidad){
        Producto producto = lista.get(nomProducto);
        if(producto != null && cantidad > 0){
            producto.liquidarStock(cantidad);
            return true;
        }
        return false;

//        Producto enStock = lista.getOrDefault(nomProducto, null);
//        if(enStock != null && cantidad >= 0 && enStock.getStockDisponible() >= cantidad){
//            enStock.ajustarStock(-cantidad);
//            return true;
//        }
//        return false;
    }

    public int reservarStock(String nomProducto, int cantidad){
        Producto producto = lista.get(nomProducto);
        if(producto != null && cantidad > 0){
            return producto.reservarStock(cantidad);
        }
        return 0;
    }

    public int liberarStock(String nomProducto, int cantidad){
        Producto producto = lista.get(nomProducto);
        if(producto != null && cantidad > 0){
          return producto.liberarStock(cantidad);
        }
        return 0;
    }

    public Producto obtenerProducto(String key){
        return lista.getOrDefault(key, null);
    }

    public Map<String, Producto> items(){
        return Collections.unmodifiableMap(lista);
    }

    @Override
    public String toString() {
        String s = "\nStock disponible\n";
        double valorTotalDeStock = 0.0;
        for (Map.Entry<String, Producto> entrada : lista.entrySet()){
            Producto producto = entrada.getValue();
            double valortotalPorProducto = producto.getPrecio() * producto.getStockDisponible();
            s += producto + "\n";
            valorTotalDeStock += valortotalPorProducto;
        }
        return s + "El valor total del stock es " + String.format("%.02f", valorTotalDeStock);
    }
}
