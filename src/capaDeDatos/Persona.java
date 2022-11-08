package capaDeDatos;

import java.util.Calendar;

public abstract class Persona {
	private int id;
	private String nombreApellido;
	private Calendar nacimiento;

	public Persona(int id, String nombreApellido, Calendar nacimiento) {
		super();
		this.id = id; 
		this.nombreApellido = nombreApellido;
		this.nacimiento = nacimiento;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public Calendar getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Calendar nacimiento) {
		this.nacimiento = nacimiento;
	}

	public int getId() {
		return id;
	}

}
