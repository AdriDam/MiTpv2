/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

/**
 * Clase que representa un producto en el sistema TPV (Terminal Punto de Venta).
 * Cada producto tiene un nombre, un precio y una cantidad.
 * 
 * @author Adrián Estraviz
 */
public class Producto {
    
    /** Nombre del producto */
    private String nombre;
    
    /** Precio del producto */
    private String precio;
    
    /** Cantidad del producto */
    private int cantidad;

    /**
     * Constructor de la clase Producto.
     * 
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     */
    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = 0;
    }
    
    /**
     * Obtiene la cantidad del producto.
     * 
     * @return Cantidad del producto.
     */
    public int getCantidad() {
        return cantidad;
    }
    
    /**
     * Establece la cantidad del producto.
     * 
     * @param cantidad Nueva cantidad del producto.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre Nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return Precio del producto.
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio Nuevo precio del producto.
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * Método toString que devuelve el nombre del producto.
     * 
     * @return Nombre del producto.
     */
    @Override
    public String toString() {
        return nombre;
    }
    
    /**
     * Obtiene el precio del producto como un valor double.
     * 
     * @return Precio del producto como un valor double.
     */
    public double getPrecioDouble(){
        return Double.parseDouble(precio);
    }
}
