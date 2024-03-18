/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import com.mycompany.tpv.Producto;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alber
 */
public class ProductoTest {
    
    public ProductoTest() {
    }

    @Test
    public void testGetCantidad() {
    }

    @Test
    public void testSetCantidad() {
    }

    @Test
    public void testGetNombre() {
    }

    @Test
    public void testSetNombre() {
    }

    @Test
    public void testGetPrecio() {
    }

    @Test
    public void testSetPrecio() {
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testGetPrecioDouble() {
    }
    
    @Test
    public void testConstructor() {
        Producto producto = new Producto("Producto1", "10.5");
        assertEquals("Producto1", producto.getNombre());
        assertEquals("10.5", producto.getPrecio());
        assertEquals(0, producto.getCantidad());
    }
    
    @Test
    public void testGettersAndSetters() {
        Producto producto = new Producto("Producto2", "20.75");
        producto.setNombre("NuevoProducto");
        producto.setPrecio("15.0");
        producto.setCantidad(5);
        
        assertEquals("NuevoProducto", producto.getNombre());
        assertEquals("15.0", producto.getPrecio());
        assertEquals(5, producto.getCantidad());
    }
    
    @Test
    public void testFuncionalidadGeneral() {
        Producto producto = new Producto("Producto3", "30.0");
        assertEquals("Producto3", producto.getNombre());
        assertEquals("30.0", producto.getPrecio());
        assertEquals(0, producto.getCantidad());
    }
    
    @Test(expected = NumberFormatException.class)
    public void testPrecioInvalido() {
        Producto producto = new Producto("Producto4", "no válido");
        double precio = producto.getPrecioDouble();
    }
    
}
