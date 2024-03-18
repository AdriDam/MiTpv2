/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tpv;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Esta clase representa la pantalla principal de la aplicación TPV (Terminal de Punto de Venta).
 * Proporciona funcionalidades para mostrar familias de productos, seleccionar productos, imprimir tickets, etc.
 * 
 * @author Adrián Estraviz
 */

public class PantallaPrincipal extends javax.swing.JFrame {

    public List<String> familias = new ArrayList<>();
    public HashMap<String, Productos> productosHM = new HashMap<>();
    final int FAMILIASxPAGINA = 6;
    int paginaActual;
    int numeroPaginas;
    JPanel panelFamilias;
    JPanel panelProductos;
    JPanel pladur; // Es el panel que contiene todo
    public Connection connection;
    JButton btnTerminarTicket;
    JButton btnBorrarProducto;

    
     /**
     * Constructor de la clase PantallaPrincipal.
     * Inicializa la interfaz de usuario y otros componentes.
     */
    public PantallaPrincipal() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        initComponents();
        initUI();

        // Inicialización de componentes visuales
        creaPaneles();
        obtenerFamiliasYProductosDesdeDB();
        botonesPasaPaginaFamilia();
        muestraPaginaFamilias();
        mostrarProductosPrimeraFamilia();
        getContentPane().add(new ZonaTicket(600, 0, 400, 500));

        btnTerminarTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirTicket();
            }
        });

        btnBorrarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarUltimoProductoSeleccionado();
            }
        });

        // Ajustamos el tamaño de la ventana automáticamente
        pack();
        setVisible(true);
    }
    
    /**
     * Inicializa la interfaz de usuario.
     */
    private void initUI() {
        btnTerminarTicket = new JButton("Terminar Ticket");
        btnTerminarTicket.setBounds(600, 510, 150, 30);
        getContentPane().add(btnTerminarTicket);

        // Agregar botón para borrar producto
        btnBorrarProducto = new JButton("Borrar Producto");
        btnBorrarProducto.setBounds(600, 550, 150, 30);
        getContentPane().add(btnBorrarProducto);
    }

    /**
     * Crea los botones para pasar de página de familias de productos.
     */
    private void botonesPasaPaginaFamilia() {
        JLabel flechaAnterior = new JLabel();
        flechaAnterior.setIcon(new ImageIcon("recursos\\imagenes\\anterior.png"));
        flechaAnterior.setVisible(true);
        flechaAnterior.setBounds(75, 110, 50, 50);
        getContentPane().add(flechaAnterior);

        flechaAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual = (--paginaActual + numeroPaginas) % numeroPaginas;
                muestraPaginaFamilias();
            }

        });

        JLabel flechaPosterior = new JLabel();
        flechaPosterior.setIcon(new ImageIcon("recursos\\imagenes\\siguiente.png"));
        flechaPosterior.setVisible(true);
        flechaPosterior.setBounds(490, 110, 50, 50);
        getContentPane().add(flechaPosterior);

        flechaPosterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual = (++paginaActual) % numeroPaginas;
                muestraPaginaFamilias();
            }

        });
    }

    /**
     * Muestra la página actual de familias de productos.
     */
    private void muestraPaginaFamilias() {
        panelFamilias.removeAll();
        for (int i = paginaActual * FAMILIASxPAGINA; i < paginaActual * FAMILIASxPAGINA + FAMILIASxPAGINA && i < familias.size(); i++) {
            JPanel panel = new JPanel();
            JLabel imagen = new JLabel();
            JLabel texto = new JLabel();

            panel.setLayout(null);
            panelFamilias.add(panel);
            panel.add(imagen);
            panel.add(texto);

            panel.setOpaque(true);
            panel.setBounds(110 * ((i - paginaActual * FAMILIASxPAGINA) % 3), 125 * ((i - paginaActual * FAMILIASxPAGINA) / 3), 100, 115);

            imagen.setOpaque(true);
            imagen.setBounds(0, 0, 100, 100);
            texto.setBounds(0, 100, 100, 15);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setVerticalAlignment(SwingConstants.CENTER);
            imagen.setIcon(new ImageIcon("recursos\\imagenes\\" + familias.get(i) + ".jpg"));
            texto.setText((String) familias.get(i));
            imagen.setVisible(true);
            texto.setVisible(true);
            panelFamilias.repaint();

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] componentes = pladur.getComponents();
                    for (Component componente : componentes) {
                        if (componente instanceof JLabel) {
                            pladur.remove(componente);
                        }
                    }

                    productosHM.get(texto.getText()).setPaginaActual(0);
                    productosHM.get(texto.getText()).muestraPaginaProductos();
                }
            });
        }
    }

    /**
     * Obtiene las familias y productos desde la base de datos.
     */
    public void obtenerFamiliasYProductosDesdeDB() {
        familias = new ArrayList<>();
        productosHM = new HashMap<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpv", "root", "");

            String query = "SELECT f.nombre AS familia, p.nombre AS producto, p.precio FROM Familias f JOIN Productos p ON f.id_familia = p.id_familia";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String familia = resultSet.getString("familia");
                String producto = resultSet.getString("producto");
                double precio = resultSet.getDouble("precio");

                if (!familias.contains(familia)) {
                    familias.add(familia);
                    productosHM.put(familia, new Productos(pladur, panelProductos));
                }

                productosHM.get(familia).getProductos().add(new Producto(producto, Double.toString(precio)));
            }

            numeroPaginas = 1 + familias.size() / FAMILIASxPAGINA;

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Crea los paneles necesarios para la interfaz de usuario.
     */
    private void creaPaneles() {
        panelFamilias = new JPanel();
        panelFamilias.setLayout(null);
        panelFamilias.setBounds(150, 10, 320, 240);
        panelFamilias.setVisible(true);
        getContentPane().add(panelFamilias);

        pladur = new JPanel();
        pladur.setLayout(null);
        pladur.setBounds(75, 260, 490, 365);
        pladur.setVisible(true);
        pladur.setBackground(Color.pink);
        getContentPane().add(pladur);

        panelProductos = new JPanel();
        panelProductos.setLayout(null);
        panelProductos.setBounds(75, 0, 320, 365);
        panelProductos.setVisible(true);
        panelProductos.setBackground(Color.red);
        pladur.add(panelProductos);
    }

    /**
     * Muestra los productos de la primera familia.
     */
    private void mostrarProductosPrimeraFamilia() {
        productosHM.get("" + familias.get(0)).muestraPaginaProductos();
    }

     /**
     * Imprime el ticket de compra en un archivo de texto.
     */
    private void imprimirTicket() {
        File ticketFile = new File("ticket.txt");
        try (FileWriter writer = new FileWriter(ticketFile)) {
            DecimalFormat df = new DecimalFormat("0.00");
            double total = VariablesGenerales.totalTicket;

            for (Producto producto : VariablesGenerales.productosSeleccionados) {
                double precio = producto.getPrecioDouble();
                int cantidad = producto.getCantidad();
                double subtotal = precio * cantidad;
                writer.write(cantidad + "\t" + producto.getNombre() + "\t" + df.format(subtotal) + "\n");
            }

            double iva = total * 0.1;
            double totalConIva = total + iva;
            writer.write("\n");
            writer.write("Subtotal:\t" + df.format(total) + "\n");
            writer.write("IVA (10%):\t" + df.format(iva) + "\n");
            writer.write("Total:\t" + df.format(totalConIva) + "\n");

            System.out.println("Ticket generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al imprimir el ticket: " + e.getMessage());
        }
    }

    /**
     * Borra el último producto seleccionado del ticket.
     */
    private void borrarUltimoProductoSeleccionado() {
        if (!VariablesGenerales.productosSeleccionados.isEmpty()) {
            // Obtener el último producto seleccionado
            Producto ultimoProducto = VariablesGenerales.productosSeleccionados.remove(VariablesGenerales.productosSeleccionados.size() - 1);

            // Eliminar el producto de la tabla de tickets si está presente
            for (int i = 0; i < VariablesGenerales.lineasTicket.size(); i++) {
                if (VariablesGenerales.lineasTicket.get(i).getNombreProducto().equals(ultimoProducto.getNombre())) {
                    VariablesGenerales.lineasTicket.remove(i);
                    VariablesGenerales.modeloTablaTicket.removeRow(i);
                    break; // Salir del bucle una vez que se ha eliminado el producto
                }
            }

            // Actualizar el total del ticket
            VariablesGenerales.totalTicket -= ultimoProducto.getPrecioDouble();

            System.out.println("Producto borrado: " + ultimoProducto.getNombre());
            System.out.println("Total actual: " + VariablesGenerales.totalTicket);
        } else {
            System.out.println("No hay productos para borrar.");
        }
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipal().setVisible(true);
            }
        });
    }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

