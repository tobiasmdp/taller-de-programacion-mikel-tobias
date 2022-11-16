package test;


import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import capaDeDatos.AsignacionDiaria;
import capaDeDatos.Comanda;
import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Operario;
import capaDeDatos.Pedido;
import capaDeDatos.Producto;
import capaDeNegocios.Local;

public class TestLocalConDatos {
	private Local local;
	
	@Before
	public void setUp() throws Exception {
		local = Local.getInstance();
		local.getMesas().add(new Mesa(1));
		local.getProductos().add(new Producto(1, 10, "Pepsi", 100, 250));
		local.getComandas().add(new Comanda(local.getMesas().get(0), new Pedido("Lunes", 2, local.getProductos().get(0)), true));
		
		local.getMozos().add(new Mozo(1, "Juan", "01/01/2000", 1));
		local.getMozos().get(0).setAcumulados(1000);
		local.getMozos().get(0).setMesasAtentidas(1);
		local.getMozos().add(new Mozo(2, "Miguel", "01/01/2000", 2));
		local.getMozos().get(1).setAcumulados(10);
		local.getMozos().get(1).setMesasAtentidas(3);
		local.getAsignacionDiaria().add(new AsignacionDiaria(local.getMozos().get(0), local.getMesas().get(0)));
		
		local.getOperarios().add(new Operario(1, "Miguel", "01/01/2000", "Miguel", "Oper123"));
		local.getOperarios().get(0).setActivo(true);
		local.getOperarios().add(new Operario(1, "Walter", "01/01/2000", "Walter", "Oper1234"));
		local.getOperarios().get(1).setActivo(false);
	}

	@After
	public void tearDown() throws Exception {
		Local.elimInstance();

	}

	@Test
	public void testgetComandaByMesaCaso1() {
		Mesa mesa1 = local.getMesas().get(0);
		try {
			Comanda comanda1 = local.getComandaByMesa(mesa1);
		
			Assert.assertNotNull("Error comanda no deberia ser nula", comanda1);
			Assert.assertEquals("Error la mesa deberia pertenecer a la comanda", comanda1.getMesa(), mesa1);
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testgetComandaByMesaCaso2() {
		Mesa mesa2 = new Mesa(2);
		try {
			Comanda comanda1 = local.getComandaByMesa(mesa2);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testgetMozoByMesaCaso1() {
		Mesa mesa1 = local.getMesas().get(0);
		
		try{
			Mozo mozo1 = local.getMozoByMesa(mesa1);
			
			Assert.assertNotNull("Error mozo no deberia ser nulo", mozo1);
			
			int i = 0;
			while(i < local.getAsignacionDiaria().size() && local.getAsignacionDiaria().get(i).getMozo().getId() != mozo1.getId())
				i++;
						
			Assert.assertEquals("Error la mesa deberia estar asignada al mozo", local.getAsignacionDiaria().get(i).getMesa(), mesa1);
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}		
	}
	
	@Test
	public void testgetMozoByMesaCaso2() {
		Mesa mesa2 = new Mesa(2);
		try {
			Mozo mozo1 = local.getMozoByMesa(mesa2);

			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testgetMozoMaxVentasCaso1() {
		try{
			Mozo mozo = local.getMozoMaxVentas();
			Assert.assertNotNull("Error mozo no deberia ser nulo", mozo);
			
			Assert.assertEquals("Error el mozo con maximo acumulable en ventas no es el correcto", local.getMozos().get(0), mozo);
			System.out.println(mozo.getAcumulados());
			System.out.println(local.getMozos().get(0).getAcumulados());
			
			Assert.assertEquals(mozo.getAcumulados(), local.getMozos().get(0).getAcumulados());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}	
		
	}	
	
	@Test
	public void testgetMozoMinVentasCaso1() {
		try{
			Mozo mozo = local.getMozoMinVentas();
			Assert.assertNotNull("Error mozo no deberia ser nulo", mozo);
			Assert.assertEquals("Error el mozo con minimo acumulable en ventas no es el correcto", local.getMozos().get(1), mozo);
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}	
	
	@Test
	public void testgetMozoMaxPromedioCaso1() {
		
		try{
			Mozo mozo = local.getMozoMaxPromedio();
			Assert.assertNotNull("Error mozo no deberia ser nulo", mozo);
			
			Assert.assertEquals("Error el mozo con maximo promedio de ventas por mesa atendida no es el correcto", local.getMozos().get(0), mozo);
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
		
	}
	
	@Test
	public void testgetMozoMinPromedioCaso1() {
		try{
			Mozo mozo = local.getMozoMinPromedio();
			Assert.assertNotNull("Error mozo no deberia ser nulo", mozo);
			
			Assert.assertEquals("Error el mozo con minimo promedio de ventas por mesa atendida no es el correcto", local.getMozos().get(1), mozo);	
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");

		}
		
	}
	
	@Test
	public void testLoginCaso1() {
		try{
			local.login("ADMIN", "ADMIN1234");
			Assert.assertTrue("Error el operario administrador predeterminado deberia encontrarse logeado en el sistema" , local.getAdmin());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");

		}		
	}
	
	@Test
	public void testLoginCaso2() {

		try{
			local.login("Miguel", "Oper123");
			Assert.assertFalse("Error el operario ingresado no es admin" , local.getAdmin());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}				
	}
	
	@Test
	public void testLoginCaso3() {
		try {
			local.login("Walter", "Oper1234");
			
			Assert.fail("No deberia continuar con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testLoginCaso6() {
		try {
			local.login(null, "Oper123");
			Assert.fail("No deberia continuar con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testLoginCaso7() {
		try {
			local.login("Miguel", null);
			Assert.fail("No deberia continuar con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
}