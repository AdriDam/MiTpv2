/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que representa la zona de visualización del ticket en la interfaz gráfica del sistema TPV (Terminal Punto de Venta).
 * Esta zona incluye una tabla para mostrar los productos seleccionados y sus detalles, como nombre, precio y cantidad.
 * 
 * @author Adrián Estraviz
 */
public class ZonaTicket extends JPanel {
    
    JTable tablaTicket;
    int x, y, ancho, alto;
    
    /**
     * Constructor de la clase ZonaTicket.
     * 
     * @param x Posición horizontal de la zona del ticket.
     * @param y Posición vertical de la zona del ticket.
     * @param ancho Ancho de la zona del ticket.
     * @param alto Alto de la zona del ticket.
     */
    public ZonaTicket(int x, int y, int ancho, int alto) {
        
        this.x=x;
        this.y=y;
        this.ancho=ancho;
        this.alto=alto;
                
        setLayout(null);
        setBounds(x,y,ancho,alto);
        setOpaque(true);  
        setBackground(Color.orange);
        setVisible(true);
        
        ponTablaTicket();
        
        JLabel jlabel = new JLabel();
        jlabel.setText("Para que aparezca algo");
        jlabel.setBounds(0,0,100,10);
        jlabel.setVisible(true);
        
        add(jlabel);
    }

    /**
     * Método para agregar la tabla del ticket a la zona del ticket.
     */
    public void ponTablaTicket() {
        tablaTicket=new JTable();
        tablaTicket.setBounds(0, 0, ancho, alto/2);
        Object[] header = new Object[]{"Nombre","Precio","Cantidad"};
        VariablesGenerales.modeloTablaTicket = new DefaultTableModel(header,0);
        
        tablaTicket.setModel(VariablesGenerales.modeloTablaTicket);
        tablaTicket.setVisible(true);
        add(tablaTicket);
    }   
}
