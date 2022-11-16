package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import capaDeDatos.Mozo;
import capaDeNegocios.Local;

public class TestIntegracionEstadisticasEscenario2 {
	private Local local;
	@Before
	public void setUp() throws Exception {
		local = Local.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		Local.elimInstance();
	}

	@Test
	public void testConsultarEstadisticasCaso2() {
		Mozo mozo1;
		Mozo mozo2;
		Mozo mozo3;
		Mozo mozo4;
		try {
			mozo1 = local.getMozoMaxPromedio();
			mozo2 = local.getMozoMinPromedio();
			mozo3 = local.getMozoMaxVentas();
			mozo4 = local.getMozoMinPromedio();
			
			Assert.fail("No deberia continuar con la ejecucion.");			
		}catch(Exception e) {
			
		}
	}

}
