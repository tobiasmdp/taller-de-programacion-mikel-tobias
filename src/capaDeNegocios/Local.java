package capaDeNegocios;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;

import capaDeDatos.AsignacionDiaria;
import capaDeDatos.Comanda;
import capaDeDatos.LocalDatos;
import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Operario;
import capaDeDatos.OperarioAdministrador;
import capaDeDatos.Producto;
import capaDeDatos.PromocionProducto;
import capaDeDatos.PromocionTemporal;
import controlador.ControladorLogin;

@SuppressWarnings("deprecation")
public class Local extends Observable {
	private static Local instance = null;
	public static final int prefijoOperario = 10000;
	public static final int prefijoMozo = 20000;
	public static final int prefijoMesa = 30000;
	public static final int prefijoProducto = 40000;

	public String nombreLocal;
	public float sueldo;
	public LocalDatos localdatos;
	public OperarioAdministrador operarioAdministrador;
	public ArrayList<Mesa> mesas = new ArrayList<Mesa>();
	public ArrayList<Mozo> mozos = new ArrayList<Mozo>();
	public ArrayList<PromocionProducto> promocionesProductos = new ArrayList<PromocionProducto>();
	public ArrayList<PromocionTemporal> promocionesTemporales = new ArrayList<PromocionTemporal>();
	public ArrayList<Operario> operarios = new ArrayList<Operario>();
	public ArrayList<Comanda> comandasActivas = new ArrayList<Comanda>();
	public ArrayList<Producto> productos = new ArrayList<Producto>();
	public ArrayList<AsignacionDiaria> asignacionDiaria = new ArrayList<AsignacionDiaria>();
	private GestionDePersonal zonaPersonal;
	private MetodosFacturacion zonaFacturacion;
	private ConfiguracionDeSistema zonaConfSistema;

	private Local() {
		nombreLocal = "Local1";
		sueldo = 999;
		operarioAdministrador = new OperarioAdministrador(0, "pepe", GregorianCalendar.getInstance(), "a1", "a1");
		this.zonaFacturacion = MetodosFacturacion.getInstance();
		this.zonaConfSistema = ConfiguracionDeSistema.getInstance();
		this.zonaPersonal = GestionDePersonal.getInstance();
	}

	public static Local getInstance() { // Singelton
		if (instance == null)
			instance = new Local();
		return instance;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public ArrayList<Mozo> getMozos() {

		return mozos;
	}

	public ArrayList<Operario> getOperarios() {

		return operarios;
	}

	public ArrayList<Mesa> getMesas() {

		return mesas;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void login(String nombreUsuario, String password) {
		this.setChanged();
		
		int i = 0;
		if (operarioAdministrador.getNombreUsuario().equals(nombreUsuario)) {
			if (operarioAdministrador.getPassword().equals(password)) {
				this.notifyObservers("OPERARIO ADMIN");	
			}
			else {
				this.notifyObservers("PASSWORD INCORRECTO");
			}
		}else {
			while (i < operarios.size() && !(operarios.get(i).getNombreUsuario().equals(nombreUsuario)))
				i++;
			if (i < operarios.size()) {

				if (operarios.get(i).getPassword().equals(password)) {

					this.notifyObservers("OPERARIO");
				} else {
					this.notifyObservers("PASSWORD INCORRECTO");
				}			
			}else {
				this.notifyObservers("USER INCORRECTO");
			}
		}		
	}

	public void Logout(Operario operario) {
	}

}