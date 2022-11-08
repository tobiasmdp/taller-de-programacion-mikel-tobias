package capaDeNegocios;

import java.util.ArrayList;
import java.util.Calendar;

import capaDeDatos.Operario;
import capaDeDatos.Mozo;

public class GestionDePersonal {

private static GestionDePersonal instance=null;
	
	private GestionDePersonal () {}

	public static GestionDePersonal getInstance() { // Singelton
		if (instance==null) 
			instance=new GestionDePersonal();
		return instance;
	}
	
	public void altaOperario(String nombreApellido, Calendar nacimiento, String nombreUsuario, String password) {
		int id;
		ArrayList<Operario> operarios = Local.getInstance().getOperarios();
		if (operarios.isEmpty()) {
			id = Local.prefijoOperario;
		}
		else {
			id = operarios.get(operarios.size()-1).getId() + 1;
		}
		Operario operario = new Operario(id, nombreApellido, nacimiento, nombreUsuario, password);
		Local.getInstance().getOperarios().add(operario);
	}

	public void bajaOperario(Operario operario) {
	}

	public void modificaOperario(Operario operario, String accion, String valor) {
		switch (accion) {
		case "nombreApellido":
			operario.setNombreApellido(valor);
			break;
		case "nombreUsuario":
			operario.setNombreUsuario(valor);
			break;
		case "password":
			operario.setPassword(valor);
			break;
		}
	}

	public void modificaOperario(Operario operario, String accion, Calendar valor) {
		switch (accion) {
		case "nacimiento":
			operario.setNacimiento(valor);
			break;
		}
	}

	public void altaMozo(String nombreApellido, Calendar nacimiento, int cantHijos) {
		int id;
		ArrayList<Mozo> mozos = Local.getInstance().getMozos();
		if (mozos.isEmpty()) {
			id = Local.prefijoMozo;
		}
		else{
			id = mozos.get(mozos.size()-1).getId() + 1;
		}
		Mozo mozo = new Mozo(id, nombreApellido, nacimiento, cantHijos);
		Local.getInstance().getMozos().add(mozo);
	}

	public void bajaMozo(Mozo mozo) {
		Local.getInstance().getMozos().remove(mozo);
	}
	
	public void modificaMozo(Mozo mozo, String accion, String valor) {
		switch (accion) {
		case "nombreApellido":
			mozo.setNombreApellido(valor);
			break;
		default:
			Raise exception;
		}
	}
	
	public void modificaMozo(Mozo mozo, String accion, Calendar valor) {
		switch (accion) {
		case "nacimiento":
			mozo.setNacimiento(valor);
			break;
		default:
			Raise exception;
		}
	}
	
	public void modificaMozo(Mozo mozo, String accion, int valor) {
		switch (accion) {
		case "cantHijos":
			mozo.setCantHijos(valor);
			break;
		default:
			Raise exception;
		}
	}
}
