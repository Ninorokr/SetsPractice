package com.alvarogiron;

import org.w3c.dom.ls.LSOutput;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    static Inventario inventario = new Inventario();

    public static void main(String[] args) {
        Carrito alvaro = new Carrito("Alvaro");
//        long inicio = System.nanoTime();
	    inventario.agregarStock(new Producto("Caramelo", 0.10, 100));
        inventario.agregarStock(new Producto("Sublime", 1.50, 100));
        inventario.agregarStock(new Producto("Pan con pollo", 2.00, 30));
        inventario.agregarStock(new Producto("Pan triple", 3.50, 15));
        inventario.agregarStock(new Producto("Sporade", 1.80, 20));
        inventario.agregarStock(new Producto("Sublime", 1.00, 30));
//        long fin = System.nanoTime();
        System.out.println(inventario.toString());
        System.out.println("----------------------------");

//        System.out.println(fin - inicio + " nanos");

        alvaro.agregarItemAlCarrito(inventario.items().get("Caramelo"), 3);
        alvaro.agregarItemAlCarrito(inventario.items().get("Pan con pollo"), 5);
        alvaro.agregarItemAlCarrito(inventario.items().get("Sporade"), 2);
        alvaro.agregarItemAlCarrito(inventario.items().get("Sublime"), 10);
        alvaro.agregarItemAlCarrito(inventario.items().get("Pan con pollo"), 3);
        alvaro.agregarItemAlCarrito(inventario.items().get("Sporade"), 1000);
        alvaro.agregarItemAlCarrito(inventario.items().get("Club Social"), 55); //No se consigue "Club social", se devuelve "null".

        System.out.println(alvaro.toString());
        System.out.println("----------------------------");

        alvaro.quitarItemDelCarrito(inventario.items().get("Caramelo"), 3);
        alvaro.quitarItemDelCarrito(inventario.items().get("Sporade"), 1);
        alvaro.quitarItemDelCarrito(inventario.items().get("Sublime"), 7);
        alvaro.quitarItemDelCarrito(inventario.items().get("Turrón"), 1);

        System.out.println(alvaro.toString());
        System.out.println("----------------------------");
        System.out.println(inventario.toString());

    }

    public static boolean venderItem(Carrito carrito, String producto, int cantidad){
        Producto item = inventario.obtenerProducto(producto);
        if(item == null){
            System.out.println("No se vende " + producto);
            return false;
        }
        if(inventario.reservarStock(producto, cantidad) != 0){
            return carrito.agregarItemAlCarrito(item, cantidad);
        }
        return false;
    }

    public static void imprimirLista(){
        for (Map.Entry<String, Producto> producto : inventario.items().entrySet()){
            System.out.println(producto.getValue());
        }
    }
}
