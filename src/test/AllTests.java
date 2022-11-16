package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCajaBlancaEscenario3.class, TestCajaBlancaEscenario5.class,
		TestConfiguracionDeSistemaEscenario1.class, TestConfiguracionDeSistemaEscenario2.class,
		TestGestionDePersonalEscenario1.class, TestGestionDePersonalEscenario2.class, TestGuiConDatos.class,
		TestGuiSinDatos.class, TestIntegracionEstadisticasEscenario1.class, TestIntegracionEstadisticasEscenario2.class,
		TestLocalConDatos.class, TestLocalSinDatos.class, TestMetodosFacturacionConDatos.class,
		TestMetodosFacturacionSinDatos.class, TestPersistenciaInicializado.class,
		TestPersistenciaNoInicializado.class })
public class AllTests {

}
