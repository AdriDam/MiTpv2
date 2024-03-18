/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import com.mycompany.tpv.Producto;
import com.mycompany.tpv.Productos;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alber
 */
public class ProductosTest {
    
    public ProductosTest() {
    }

    @Test
    public void testGetPaginaActual() {
    }

    @Test
    public void testSetPaginaActual() {
    }

    @Test
    public void testGetNumeroPaginas() {
    }

    @Test
    public void testSetNumeroPaginas() {
    }

    @Test
    public void testGetProductos() {
    }

    @Test
    public void testSetProductos() {
    }

    @Test
    public void testBotonesPasaPaginaProducto() {
    }
    
    @Test
    public void testMuestraPaginaProductos() {
        Container contenedor = new Container();
        JPanel panelProductos = new JPanel();
        Productos productos = new Productos(contenedor, panelProductos);

        // Simulamos la carga de productos en la instancia de Productos
        productos.getProductos().add(new Producto("Producto1", "10.50"));
        productos.getProductos().add(new Producto("Producto2", "20.00"));

        // Establecemos la página actual y número de páginas
        productos.setPaginaActual(0);
        productos.setNumeroPaginas(1);

        // Invocamos el método muestraPaginaProductos()
        productos.muestraPaginaProductos();

        // Verificamos que se hayan añadido correctamente los componentes al panel de productos
        assertEquals(2, panelProductos.getComponentCount());
    }
}
