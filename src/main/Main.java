package main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import capaDeDatos.Mesa;
import capaDeDatos.Producto;
import capaDeNegocios.ConfiguracionDeSistema;
import capaDeNegocios.GestionDePersonal;
import capaDeNegocios.Local;
import controlador.ControladorLogin;

public class Main {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ControladorLogin controladorLogin = new ControladorLogin();
		GestionDePersonal.getInstance().altaOperario("pepe", "01/05/2000", "1", "1");
		
		Local.getInstance().getMesas().add(new Mesa(0));
		Local.getInstance().getMesas().add(new Mesa(1));
		Local.getInstance().getMesas().add(new Mesa(2));
		Local.getInstance().getMesas().add(new Mesa(3));
		
		ConfiguracionDeSistema.getInstance().altaProducto(10, "Pepeya", 400, 500);
		ConfiguracionDeSistema.getInstance().altaProducto(10, "Papaya", 200, 300);
		
	}

}
