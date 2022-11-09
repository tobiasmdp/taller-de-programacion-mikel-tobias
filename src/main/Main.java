package main;

import java.util.Calendar;
import java.util.GregorianCalendar;

import capaDeNegocios.GestionDePersonal;
import capaDeNegocios.Local;
import controlador.ControladorLogin;

public class Main {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ControladorLogin controladorLogin = new ControladorLogin();
		GestionDePersonal.getInstance().altaOperario("pepe", GregorianCalendar.getInstance(), "1", "1");
		
		
	}

}
