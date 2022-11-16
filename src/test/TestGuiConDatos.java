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

public class TestGuiConDatos {

	Robot robot;
    ControladorLogin controlador;
	Local local;
	GestionDePersonal gestionPersonal;
	 public TestGuiConDatos()
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
	        gestionPersonal= GestionDePersonal.getInstance();
	        gestionPersonal.altaOperario("NahuelNievas", "22/10/1998", "Nahuel", "Calle123");
	        gestionPersonal.altaOperario("ImanolVazquez", "22/09/1997", "Imanol", "Calle456");
	    }

	    @After
	    public void tearDown() throws Exception
	    {
	        controlador.getVista().setVisible(false);
	        local.getOperarios().clear();
			Local.elimInstance();
	    }
	    
	    @Test
	    public void testCantidadDeUsuarios()
	    {
	        Assert.assertEquals("Debe haber 2 usuario registrados.", 2, local.getOperarios().size());
	    }

	    @Test
	    public void testLogeoCorrecto() {
	    	robot.delay(TestUtil.getDelay());
	    	
	    	JTextField nombreUsuario= (JTextField) TestUtil.getComponentForName(controlador.getVista(), "textUsuario");
	    	JTextField contrasenia =(JTextField) TestUtil.getComponentForName(controlador.getVista(), "textPassword");
	    	JButton ingresar= (JButton) TestUtil.getComponentForName(controlador.getVista(), "botonIngreso");
	    	
	    	TestUtil.clickComponent(nombreUsuario, robot);
	    	TestUtil.tipeaTexto("Nahuel", robot);
	    	TestUtil.clickComponent(contrasenia, robot);
	    	TestUtil.tipeaTexto("Calle123", robot);
	    	TestUtil.clickComponent(ingresar, robot);
	    	
	    	Assert.assertEquals("El tipo de usuario debe ser 0", 0, controlador.getTipoUsuario());
	    	
	    	Assert.assertNotNull("El usuario obtenido no deberia ser null",controlador.getOperario());
	    	Assert.assertEquals("El usuario obtenido no tiene el mismo nombre de usuario que el ingresado","Nahuel",controlador.getOperario().getNombreUsuario());
	    	Assert.assertEquals("El usuario obtenido no tiene la misma contrasenia que la ingresada","Calle123",controlador.getOperario().getPassword());
	    	
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
	    
	    @Test
	    public void testLogeoContraseniaIncorrecta() {
	    	robot.delay(TestUtil.getDelay());
	    	
	    	JTextField nombreUsuario= (JTextField) TestUtil.getComponentForName(controlador.getVista(), "textUsuario");
	    	JTextField contrasenia =(JTextField) TestUtil.getComponentForName(controlador.getVista(), "textPassword");
	    	JButton ingresar= (JButton) TestUtil.getComponentForName(controlador.getVista(), "botonIngreso");
	    	JLabel lblcontrasenia=(JLabel) TestUtil.getComponentForName(controlador.getVista(),"labelErrorContrasenia");
	    	
	    	
	    	TestUtil.clickComponent(nombreUsuario, robot);
	    	TestUtil.tipeaTexto("Nahuel", robot);
	    	TestUtil.clickComponent(contrasenia, robot);
	    	TestUtil.tipeaTexto("Calle12", robot);
	    	TestUtil.clickComponent(ingresar, robot);
	    	
	    	Assert.assertEquals("El error de contrasenia no es correcto","Error Contraseña",lblcontrasenia.getText());
	    	Assert.assertNull("El usuario obtenido no deberia ser null",controlador.getOperario());
	    }
}
