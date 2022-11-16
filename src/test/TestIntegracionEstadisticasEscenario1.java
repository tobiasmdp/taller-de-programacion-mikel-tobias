package test;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import capaDeDatos.Mozo;
import capaDeNegocios.Local;

public class TestIntegracionEstadisticasEscenario1 {
	private Local local;
	private Mozo mozo;
	private Mozo mozo2;
	
	@Before
	public void setUp() throws Exception {
		local = Local.getInstance();
		mozo = new Mozo(1,"Nahuel","13/10/1980", 2);
		mozo.setAcumulados(1000);
		mozo.setMesasAtentidas(1);
		local.getMozos().add(mozo);
		mozo2 = new Mozo(2,"Miguel","13/10/1980", 2);
		mozo2.setAcumulados(1500);
		mozo2.setMesasAtentidas(10);
		
	}

	@After
	public void tearDown() throws Exception {
		Local.elimInstance();
	}

	@Test
	public void testConsultarEstadisticasCaso1() {
		Mozo mozoMaxPromedio;
		Mozo mozoMinPromedio;
		Mozo mozoMaxVentas;
		Mozo mozoMinVentas;
		try {
			mozoMaxPromedio = local.getMozoMaxPromedio();
			mozoMinPromedio = local.getMozoMinPromedio();
			mozoMaxVentas = local.getMozoMaxVentas();
			mozoMinVentas = local.getMozoMinVentas();
			
			Assert.assertNotNull("No deberia ser nulo", mozoMaxPromedio);
			Assert.assertNotNull("No deberia ser nulo", mozoMinPromedio);
			Assert.assertNotNull("No deberia ser nulo", mozoMaxVentas);
			Assert.assertNotNull("No deberia ser nulo", mozoMinVentas);
			
			Assert.assertEquals("Deberia ser el mismo mozo maximo promedio", mozoMaxPromedio, this.mozo);
			Assert.assertEquals("Deberia ser el mismo mozo minimo promedio", mozoMinPromedio, this.mozo2);
			Assert.assertEquals("Deberia ser el mismo mozo maximo promedio", mozoMaxVentas, this.mozo2);
			Assert.assertEquals("Deberia ser el mismo mozo minimo promedio", mozoMinVentas, this.mozo);
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion.");
		}
	}
}
