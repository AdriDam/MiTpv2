/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import com.mycompany.tpv.PantallaPrincipal;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alber
 */
public class PantallaPrincipalTest {
    
    public PantallaPrincipalTest() {
    }

    @Test
    public void testMain() {
    }
    
    @Test
    public void testConstructor() {
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        
        // Verifica que se hayan inicializado los componentes visuales
        assertTrue(pantallaPrincipal.getContentPane().getComponent(0) instanceof JPanel);
        assertTrue(pantallaPrincipal.getContentPane().getComponent(1) instanceof JButton);
        assertTrue(pantallaPrincipal.getContentPane().getComponent(2) instanceof JButton);
    }
    
    @Test
    public void testObtenerFamiliasYProductosDesdeDB() {
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        
        // Simula una conexión exitosa a la base de datos
        try {
            pantallaPrincipal.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpv", "root", "");
        } catch (SQLException e) {
            fail("No se pudo establecer la conexión a la base de datos: " + e.getMessage());
        }
        
        // Verifica que se obtengan las familias y productos desde la base de datos
        pantallaPrincipal.obtenerFamiliasYProductosDesdeDB();
        assertFalse(pantallaPrincipal.familias.isEmpty());
        assertFalse(pantallaPrincipal.productosHM.isEmpty());
    }
    
}
