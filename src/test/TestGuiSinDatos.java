package test;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import capaDeNegocios.GestionDePersonal;
import capaDeNegocios.Local;
import controlador.ControladorLogin;

public class TestGuiSinDatos {

	Robot robot;
    ControladorLogin controlador;
	Local local;
	GestionDePersonal gestionPersonal;
	
	 public TestGuiSinDatos()
	    {
	        try
	        {
	            robot = new Robot();
	        } catch (AWTException e)
	        {
	        }
	    }
	 
	 

	    @Before
	    public void setUp() throws Exception
	    {
	        controlador = new ControladorLogin();
	        local=Local.getInstance();
	        
	    }

	    @After
	    public void tearDown() throws Exception
	    {
	        controlador.getVista().setVisible(false);
			Local.elimInstance();
	    }
	    
	    @Test
	    public void testCantidadDeUsuarios()
	    {
	        Assert.assertEquals("Debe haber 0 usuario registrados.", 0, local.getOperarios().size());
	    }

	
	 
	    @Test
	    public void testLogeoUsuarioIncorrecto() {
	    	robot.delay(TestUtil.getDelay());
	    	
	    	JTextField nombreUsuario= (JTextField) TestUtil.getComponentForName(controlador.getVista(), "textUsuario");
	    	JTextField contrasenia =(JTextField) TestUtil.getComponentForName(controlador.getVista(), "textPassword");
	    	JButton ingresar= (JButton) TestUtil.getComponentForName(controlador.getVista(), "botonIngreso");
	    	JLabel lblusuario=(JLabel) TestUtil.getComponentForName(controlador.getVista(),"labelErrorUsuario");
	    	
	    	
	    	TestUtil.clickComponent(nombreUsuario, robot);
	    	TestUtil.tipeaTexto("Nahuel1", robot);
	    	TestUtil.clickComponent(contrasenia, robot);
	    	TestUtil.tipeaTexto("Calle123", robot);
	    	TestUtil.clickComponent(ingresar, robot);
	    	
	    	Assert.assertEquals("El error de usuario no es correcto","Error Usuario",lblusuario.getText());
	    	Assert.assertNull("El usuario obtenido no deberia ser null",controlador.getOperario());
	    }
	    
	    

}
