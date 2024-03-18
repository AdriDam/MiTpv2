/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import com.mycompany.tpv.ZonaTicket;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alber
 */
public class ZonaTicketTest {
    
    public ZonaTicketTest() {
    }
    
    @Test
    public void testPonTablaTicket() {
        int x = 10, y = 20, ancho = 300, alto = 200;
        ZonaTicket zonaTicket = new ZonaTicket(x, y, ancho, alto);
        
        // Invocamos el método para poner la tabla
        zonaTicket.ponTablaTicket();
        
        // Verificamos que se haya añadido una tabla al panel
        assertTrue(zonaTicket.getComponent(0) instanceof JTable);
        
        // Verificamos que la tabla tenga el modelo correcto
        JTable tablaTicket = (JTable) zonaTicket.getComponent(0);
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaTicket.getModel();
        assertEquals(3, modeloTabla.getColumnCount());
        assertEquals(0, modeloTabla.getRowCount());
    }
    
    @Test
    public void testConstructor() {
        int x = 10, y = 20, ancho = 300, alto = 200;
        ZonaTicket zonaTicket = new ZonaTicket(x, y, ancho, alto);
        
        // Verificamos que las propiedades se hayan establecido correctamente
        assertEquals(x, zonaTicket.getX());
        assertEquals(y, zonaTicket.getY());
        assertEquals(ancho, zonaTicket.getWidth());
        assertEquals(alto, zonaTicket.getHeight());
        assertEquals(Color.ORANGE, zonaTicket.getBackground());
        
        // Verificamos que la tabla se haya añadido correctamente
        assertTrue(zonaTicket.getComponent(0) instanceof JTable);
    }
}
