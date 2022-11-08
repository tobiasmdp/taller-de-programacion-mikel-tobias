package capaDeDatos;

import java.util.Calendar;

import capaDeNegocios.Local;

public class Mozo extends Persona {
	private int cantHijos;
	private String estado;
	private float acumulados;
	private float sueldo;

	public Mozo(int id, String nombreApellido, Calendar nacimiento, int cantHijos) {
		super(id, nombreApellido, nacimiento);
		this.cantHijos = cantHijos;
		this.estado = "Activo"; //�Activo�, �De franco�, �Ausente�
		this.acumulados = 0; // acumulacion de ventas totales  
		this.sueldo = Local.getInstance().getSueldoMin() * (1 + (float)0.05 * cantHijos); 
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public float getAcumulados() {
		return acumulados;
	}

	public void setAcumulados(float acumulados) {
		this.acumulados = acumulados;
	}

	public float getSueldo() {
		return sueldo;
	}

	public int getCantHijos() {
		return cantHijos;
	}

	public void setCantHijos(int cantHijos) {
		this.cantHijos = cantHijos;
	}

}
