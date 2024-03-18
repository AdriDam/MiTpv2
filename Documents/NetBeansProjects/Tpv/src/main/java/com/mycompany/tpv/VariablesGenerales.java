/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adrián Estraviz
 */
public class VariablesGenerales {
    public static double totalTicket;
    public static List<LineaTicket> lineasTicket = new ArrayList<>();
    public static List<Producto> productosSeleccionados = new ArrayList<>(); // Asegúrate de inicializarla
    public static DefaultTableModel modeloTablaTicket;
}

