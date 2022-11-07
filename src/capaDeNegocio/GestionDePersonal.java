package capaDeNegocio;

import java.util.ArrayList;
import java.util.Calendar;

import capaDeDatos.Operario;
import capaDeDatos.Mozo;

public abstract class GestionDePersonal {

	public void altaOperario(String nombreApellido, Calendar nacimiento, String nombreUsuario, String password) {
		int id;
		ArrayList<Operario> operarios = Sistema.getInstance().getOperarios();
		if (operarios.isEmpty()) {
			id = Sistema.getInstance().getPrefijoOperario();
		}
		else {
			id = operarios.get(operarios.size()-1).getId() + 1;
		}
		Operario operario = new Operario(id, nombreApellido, nacimiento, nombreUsuario, password);
		Sistema.getInstance().getOperarios().add(operario);
	}

	public void bajaOperario(Operario operario) {
		Sistema.getInstance().getOperarios().remove(operario);
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
		ArrayList<Mozo> mozos = Sistema.getInstance().getMozos();
		if (mozos.isEmpty()) {
			id = Sistema.getInstance().getPrefijoMozo();
		}
		else{
			id = mozos.get(mozos.size()-1).getId() + 1;
		}
		Mozo mozo = new Mozo(id, nombreApellido, nacimiento, cantHijos);
		Sistema.getInstance().getMozos().add(mozo);
	}

	public void bajaMozo(Mozo mozo) {
		Sistema.getInstance().getMozos().remove(mozo);
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
