/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import javax.swing.JButton;

/**
 * Esta clase representa una línea de producto en un ticket de compra.
 * Contiene información sobre el nombre del producto, su precio y la cantidad comprada.
 * Además, puede incluir un botón para eliminar la línea de producto del ticket.
 * 
 * @author Adrián Estraviz
 */
public class LineaTicket {
    
    private String nombreProducto; // Nombre del producto
    private double precioProducto; // Precio del producto
    private int cantidadProducto; // Cantidad del producto
    private JButton botonEliminar; // Botón para eliminar la línea de producto del ticket

    /**
     * Constructor de la clase LineaTicket.
     * 
     * @param nombreProducto Nombre del producto.
     * @param precioProducto Precio del producto.
     * @param cantidadProducto Cantidad del producto.
     */
    public LineaTicket(String nombreProducto, double precioProducto, int cantidadProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return El nombre del producto.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombreProducto El nombre del producto a establecer.
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return El precio del producto.
     */
    public double getPrecioProducto() {
        return precioProducto;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precioProducto El precio del producto a establecer.
     */
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Obtiene la cantidad del producto.
     * 
     * @return La cantidad del producto.
     */
    public int getCantidadProducto() {
        return cantidadProducto;
    }

    /**
     * Establece la cantidad del producto.
     * 
     * @param cantidadProducto La cantidad del producto a establecer.
     */
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
