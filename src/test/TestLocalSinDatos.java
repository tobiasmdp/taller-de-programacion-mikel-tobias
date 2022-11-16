package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import capaDeDatos.Comanda;
import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeNegocios.Local;

public class TestLocalSinDatos {
	private Local local;

	@Before
	public void setUp() throws Exception {
		this.local = Local.getInstance();		
	}

	@After
	public void tearDown() throws Exception {
		Local.elimInstance();

	}

	@Test
	public void testgetComandaByMesaCaso3() {
		Mesa mesa1 = new Mesa(1);
		try {
			Comanda comanda1 = local.getComandaByMesa(mesa1);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testgetMozoByMesaCaso3() {
		Mesa mesa1 = new Mesa(1);
		
		try {
			Mozo mozo1 = local.getMozoByMesa(mesa1);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testgetMozoMaxVentasCaso2() {
		try {
			Mozo mozo = local.getMozoMaxVentas();
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testgetMozoMinVentasCaso2() {
		try {	
			Mozo mozo = local.getMozoMinVentas();
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testgetMozoMaxPromedioCaso2() {
		try {
			Mozo mozo = local.getMozoMaxPromedio();
			Assert.fail("No deberia seguir con la ejecucion.");		
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testgetMozoMinPromedioCaso2() {
		try {
			Mozo mozo = local.getMozoMinPromedio();
			Assert.fail("No deberia seguir con la ejecucion.");		
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testLoginCaso4() {
		try {
			local.login("Juan", "Admin1234");
		
			Assert.fail("Deberia haber lanzado excepcion de que no se encuentra registrado en el sistema.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testLoginCaso5() {
		try {
			local.login("Miguel", "Oper123");
			Assert.fail("Deberia haber lanzado excepcion de que no se encuentra registrado en el sistema.");
		}catch(Exception e) {
			
		}
	}
}
