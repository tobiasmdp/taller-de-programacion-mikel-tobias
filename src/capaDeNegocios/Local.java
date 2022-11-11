package capaDeNegocios;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Observable;

import capaDeDatos.AsignacionDiaria;
import capaDeDatos.Comanda;
import capaDeDatos.Factura;
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

	private boolean admin; // indica si quien logeo es admin o no
	private String nombreLocal;
	private float sueldo;
	private LocalDatos localdatos;
	private OperarioAdministrador operarioAdministrador;
	private ArrayList<String> formasDePago = new ArrayList<String>();
	private ArrayList<Mesa> mesas = new ArrayList<Mesa>();
	private ArrayList<Mozo> mozos = new ArrayList<Mozo>();
	private ArrayList<Factura> facturas = new ArrayList<Factura>();
	private ArrayList<PromocionProducto> promocionesProductos = new ArrayList<PromocionProducto>();
	private ArrayList<PromocionTemporal> promocionesTemporales = new ArrayList<PromocionTemporal>();
	private ArrayList<Operario> operarios = new ArrayList<Operario>();
	private ArrayList<Comanda> comandas = new ArrayList<Comanda>();
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private ArrayList<AsignacionDiaria> asignacionDiaria = new ArrayList<AsignacionDiaria>();
	private GestionDePersonal zonaPersonal;
	private MetodosFacturacion zonaFacturacion;
	private ConfiguracionDeSistema zonaConfSistema;

	private Local() {
		this.nombreLocal = "Local1";
		this.sueldo = 999;
		this.admin = false;
		this.formasDePago.add("Efectivo");
		this.formasDePago.add("Debito");
		this.formasDePago.add("MercadoPago");
		this.formasDePago.add("Cuenta DNI");
		operarioAdministrador = new OperarioAdministrador(0, "pepe", "01/05/2000", "a1", "a1");
		this.zonaFacturacion = MetodosFacturacion.getInstance();
		this.zonaConfSistema = ConfiguracionDeSistema.getInstance();
		this.zonaPersonal = GestionDePersonal.getInstance();
	}

	public ArrayList<AsignacionDiaria> getAsignacionDiaria() {
		return asignacionDiaria;
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

	public ArrayList<Factura> getFacturas() {
		return facturas;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean esAdmin) {
		this.admin = esAdmin;
	}

	// pre-condiciones: mesa != null
	public Comanda getComandaByMesa(Mesa mesa) {
		Comanda comanda = null;
		int i = 0;
		while (i < comandas.size() && !(comandas.get(i).getMesa().equals(mesa)))
			i++;
		if (i < comandas.size()) {
			comanda = comandas.get(i);
		}
		return comanda;
	}
	
	// pre-condiciones: mesa != null
	public Mozo getMozoByMesa(Mesa mesa) {
		Mozo mozo= null;
		int i = 0;
		while (i < asignacionDiaria.size() && !(asignacionDiaria.get(i).getMesa().equals(mesa)))
			i++;
		if (i < asignacionDiaria.size()) {
			mozo = asignacionDiaria.get(i).getMozo();
		}
		return mozo;
	}
	
	
	//pre: mozos no esta vacia
	public Mozo getMozoMaxVentas() {
		float max = -1;
		Mozo mozo= null;
		for (Mozo mozoActual :mozos) {
			if (mozoActual.getAcumulados() > max) {
				max = mozoActual.getAcumulados();
				mozo = mozoActual;
			}
		}
		if (mozo.getAcumulados() == 0) {
			mozo = null;
		}
		return mozo;
	}

	
	//pre: mozos no esta vacia
	public Mozo getMozoMinVentas() {
		float min = 99999;
		Mozo mozo= null;
		for (Mozo mozoActual :mozos) {
			if (mozoActual.getAcumulados() < min) {
				min = mozoActual.getAcumulados();
				mozo = mozoActual;
			}
		}
		if (mozo.getAcumulados() == 0) {
			mozo = null;
		}
		return mozo;
	}
	
	
	//pre: mozos no esta vacia
	public Mozo getMozoMaxPromedio() {
		float max = -1;
		Mozo mozo= null;
		for (Mozo mozoActual :mozos) {
			if ((mozoActual.getAcumulados()/mozoActual.getMesasAtentidas()) > max) {
				max = (mozoActual.getAcumulados()/mozoActual.getMesasAtentidas());
				mozo = mozoActual;
			}
		}
		return mozo;
	}

	//pre: mozos no esta vacia
	public Mozo getMozoMinPromedio() {
		float min = 99999;
		Mozo mozo= null;
		for (Mozo mozoActual :mozos) {
			if (mozoActual.getAcumulados()/mozoActual.getMesasAtentidas() < min) {
				min = mozoActual.getAcumulados();
				mozo = mozoActual;
			}
		}
		return mozo;
	}
	
	// precondiciones: nombreUsuario y password != ""
	public void login(String nombreUsuario, String password) {
		this.setChanged();

		int i = 0;
		if (operarioAdministrador.getNombreUsuario().equals(nombreUsuario)) {
			if (operarioAdministrador.getPassword().equals(password)) {
				this.admin = true;
				this.notifyObservers("LOGIN CORRECTO");
			} else {
				this.notifyObservers("PASSWORD INCORRECTO");
			}
		} else {
			while (i < operarios.size() && !(operarios.get(i).getNombreUsuario().equals(nombreUsuario)))
				i++;
			if (i < operarios.size()) {

				if (operarios.get(i).getPassword().equals(password)) {
					this.notifyObservers("LOGIN CORRECTO");
				} else {
					this.notifyObservers("PASSWORD INCORRECTO");
				}
			} else {
				this.notifyObservers("USER INCORRECTO");
			}
		}
	}

	public void Logout(Operario operario) {
	}

	public String getDiaSemana(int numero) {
		String dia;
		switch (numero) {
		case 1: {
			dia = "Domingo";
			break;
		}
		case 2: {
			dia = "Lunes";
			break;
		}
		case 3: {
			dia = "Martes";
			break;
		}
		case 4: {
			dia = "Miercoles";
			break;
		}
		case 5: {
			dia = "Jueves";
			break;
		}
		case 6: {
			dia = "Viernes";
			break;
		}
		case 7: {
			dia = "Sabado";
			break;
		}
		default:
			dia = "";
		}
		return dia;
	}

	public ArrayList<PromocionProducto> getPromocionesProductos() {
		return promocionesProductos;
	}

	public ArrayList<PromocionTemporal> getPromocionesTemporales() {
		return promocionesTemporales;
	}

	public ArrayList<Comanda> getComandas() {
		return comandas;
	}

	public ArrayList<String> getFormasDePago() {
		return formasDePago;
	}

}