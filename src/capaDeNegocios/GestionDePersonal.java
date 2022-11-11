package capaDeNegocios;

import java.util.ArrayList;
import java.util.Calendar;

import capaDeDatos.Operario;
import capaDeDatos.Mozo;

public class GestionDePersonal {

private static GestionDePersonal instance=null;
	
	private GestionDePersonal () {}

	/**
	 * 
	 * @return instancia de este SubSingleton para separar los metodos
	 */
	public static GestionDePersonal getInstance() { // Singelton
		if (instance==null) 
			instance=new GestionDePersonal();
		return instance;
	}
	/**
	 * Se crea un operario
	 * 
	 * @param nombreApellido distitnto de null
	 * @param nacimiento distitnto de null
	 * @param nombreUsuario distitnto de null
	 * @param password distitnto de null
	 */
	public void altaOperario(String nombreApellido, String nacimiento, String nombreUsuario, String password) {
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

	/**
	 * Se elimina un operario
	 * 
	 * @param operario distitnto de null
	 */
	public void bajaOperario(Operario operario) {
		Local.getInstance().getOperarios().remove(operario);
	}

	/**
	 * Se modifica un operario
	 * 
	 * @param operario distitnto de null
	 * @param accion distitnto de null
	 * @param valor distitnto de null
	 */
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
		case "nacimiento":
			operario.setNacimiento(valor);
			break;
		}
	}

	/**
	 * Se crea un nuevo mozo
	 * 
	 * @param nombreApellido distitnto de null
	 * @param nacimiento distitnto de null
	 * @param cantHijos distitnto de null
	 */
	public void altaMozo(String nombreApellido, String nacimiento, int cantHijos) {
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
	/**
	 * Se elimina un mozo
	 * @param mozo distitnto de null
	 */
	public void bajaMozo(Mozo mozo) {
		Local.getInstance().getMozos().remove(mozo);
	}
	/**
	 * Se modifica un mozo
	 * 
	 * @param mozo distitnto de null
	 * @param accion distitnto de null
	 * @param valor distitnto de null
	 */
	public void modificaMozo(Mozo mozo, String accion, String valor) {
		switch (accion) {
		case "nombreApellido":
			mozo.setNombreApellido(valor);
			break;
		case "estado":
			mozo.setEstado(valor);
			break;
		default:
			//Raise exception;
		}
	}
	/**
	 * Se modifica la cantidad de hijos de un mozo
	 * 
	 * @param mozo distitnto de null
	 * @param accion distitnto de null
	 * @param valor distitnto de null
	 */
	public void modificaMozo(Mozo mozo, String accion, int valor) {
		switch (accion) {
		case "cantHijos":
			mozo.setCantHijos(valor);
			break;
		default:
			//Raise exception;
		}
	}
}
