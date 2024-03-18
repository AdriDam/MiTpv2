/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Clase que representa la gestión de productos en el sistema TPV (Terminal Punto de Venta).
 * Permite mostrar productos en páginas, así como seleccionarlos y mostrar sus precios.
 * También gestiona la paginación de productos.
 * 
 * @author Adrián Estraviz
 */
public class Productos {

    private List<Producto> productos;
    public List<Producto> productosSeleccionados; // Lista para almacenar productos seleccionados
    final int PRODUCTOSxPAGINA = 9;
    final int FILAS = 3;
    final int COLUMNAS = 3;
    private int paginaActual;
    private int numeroPaginas;
    public JPanel panelProductosContainer; // Nombre más descriptivo
    Container contenedorGeneral;
    JPanel pladur; // Es el panel que contiene todo

    /**
     * Constructor de la clase Productos.
     * 
     * @param contenedorGeneral Contenedor general donde se encuentran los productos.
     * @param panelProductos Panel donde se mostrarán los productos.
     */
    public Productos(Container contenedorGeneral, JPanel panelProductos) {
        this.panelProductosContainer = panelProductos; // Nombre más descriptivo
        this.contenedorGeneral = contenedorGeneral;
        this.productos = new ArrayList<>(); // Usamos la interfaz List en lugar de la implementación ArrayList
        this.productosSeleccionados = new ArrayList<>(); // Inicializamos la lista de productos seleccionados
        botonesPasaPaginaProducto();
        crearPladur();
    }

    /**
     * Obtiene la página actual de productos.
     * 
     * @return La página actual de productos.
     */
    public int getPaginaActual() {
        return paginaActual;
    }

    /**
     * Establece la página actual de productos.
     * 
     * @param paginaActual La nueva página actual de productos.
     */
    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    /**
     * Obtiene el número total de páginas de productos.
     * 
     * @return El número total de páginas de productos.
     */
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * Establece el número total de páginas de productos.
     * 
     * @param numeroPaginas El nuevo número total de páginas de productos.
     */
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * Obtiene la lista de productos.
     * 
     * @return La lista de productos.
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Establece la lista de productos.
     * 
     * @param productos La nueva lista de productos.
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Método para mostrar los botones de navegación entre páginas de productos.
     */
    public void botonesPasaPaginaProducto() {
        // Pongo flecha de antes y despues

        JLabel flechaAnterior = new JLabel();
        flechaAnterior.setIcon(new ImageIcon("recursos\\imagenes\\anterior.png"));
        flechaAnterior.setVisible(true);
        flechaAnterior.setBounds(0, 180, 50, 50);
        contenedorGeneral.add(flechaAnterior);

        flechaAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual = (--paginaActual + numeroPaginas) % numeroPaginas;
                muestraPaginaProductos();
            }

        });

        JLabel flechaSiguiente = new JLabel();
        flechaSiguiente.setIcon(new ImageIcon("recursos\\imagenes\\siguiente.png"));
        flechaSiguiente.setVisible(true);
        flechaSiguiente.setBounds(440, 180, 50, 50);
        contenedorGeneral.add(flechaSiguiente);

        flechaSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual = (++paginaActual) % numeroPaginas;
                muestraPaginaProductos();
            }

        });
    }

    /**
     * Método para mostrar la página actual de productos.
     */
    public void muestraPaginaProductos() {
        panelProductosContainer.removeAll();
        // Pongo las familias
        for (int i = paginaActual * PRODUCTOSxPAGINA; i < paginaActual * PRODUCTOSxPAGINA + PRODUCTOSxPAGINA && i < productos.size(); i++) {
            final int PRODUCTOACTUAL = i;
            // Creo el panel con la familia
            JPanel panel = new JPanel();
            JLabel imagen = new JLabel();
            JLabel texto = new JLabel();

            panel.setLayout(null);
            // Añado el panel al JFrame
            panelProductosContainer.add(panel);
            // Añado los labels al Panel
            panel.add(imagen);
            panel.add(texto);

            panel.setOpaque(true);
            panel.setBounds(110 * ((i - paginaActual * PRODUCTOSxPAGINA) % COLUMNAS), 125 * ((i - paginaActual * PRODUCTOSxPAGINA) / FILAS), 100, 115);
            imagen.setOpaque(true);
            imagen.setBounds(0, 0, 100, 100);
            texto.setBounds(0, 100, 100, 15);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setVerticalAlignment(SwingConstants.CENTER);
            imagen.setIcon(new ImageIcon("recursos\\imagenes\\" + productos.get(i) + ".jpg"));
            texto.setText(productos.get(i).toString());
            imagen.setVisible(true);
            texto.setVisible(true);

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Producto productoTemporal = productos.get(PRODUCTOACTUAL);
                    imprimePrecio(productoTemporal);
                    // Incrementar la cantidad del producto seleccionado
                    productoTemporal.setCantidad(productoTemporal.getCantidad() + 1);
                }
            });

            panelProductosContainer.repaint();
        }
    }

    /**
     * Método para imprimir el precio del producto seleccionado.
     * 
     * @param producto El producto seleccionado.
     */
    private void imprimePrecio(Producto producto) {
    boolean encontrado = false;

    for (int i = 0; i < VariablesGenerales.lineasTicket.size(); i++) {
        if (VariablesGenerales.lineasTicket.get(i).getNombreProducto().equals(producto.getNombre())) {
            VariablesGenerales.lineasTicket.get(i).setCantidadProducto(VariablesGenerales.lineasTicket.get(i).getCantidadProducto() + 1);
            encontrado = true;
            VariablesGenerales.modeloTablaTicket.setValueAt(VariablesGenerales.lineasTicket.get(i).getCantidadProducto(), i, 2);
            break;
        }
    }

    if (!encontrado) {
        // Si no se encuentra el producto en la lista de productos seleccionados, agregarlo con cantidad 1
        VariablesGenerales.lineasTicket.add(new LineaTicket(producto.getNombre(), producto.getPrecioDouble(), 1));
        VariablesGenerales.modeloTablaTicket.addRow(new Object[]{producto.getNombre(), producto.getPrecio(), "1"});
    }

    System.out.println("Producto seleccionado: " + producto.getNombre() + " (" + producto.getPrecio() + ")");
    VariablesGenerales.totalTicket += producto.getPrecioDouble();
    System.out.println("Total actual: " + VariablesGenerales.totalTicket);

    // Agrega el producto seleccionado a la lista productosSeleccionados
    VariablesGenerales.productosSeleccionados.add(producto);
}

    /**
     * Método para crear el panel general que contiene todos los elementos relacionados con los productos.
     */
    private void crearPladur() {

    }
}
