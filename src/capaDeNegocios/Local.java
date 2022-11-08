package capaDeNegocios;

import java.util.ArrayList;

import capaDeDatos.Comanda;
import capaDeDatos.LocalDatos;
import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Operario;
import capaDeDatos.OperarioAdministrador;
import capaDeDatos.Producto;
import capaDeDatos.PromocionProducto;
import capaDeDatos.PromocionTemporal;

public class Local {
private static Local instance= null;
public static final int prefijoOperario = 10000;
public static final int prefijoMozo = 20000;
public static final int prefijoMesa = 30000;
public static final int prefijoProducto = 40000;

public String nombreLocal;
public float sueldoMin;
public LocalDatos localdatos;
public ArrayList<Mesa> mesas = new ArrayList<Mesa>();
public ArrayList <Mozo> mozos = new ArrayList<Mozo>();
public ArrayList <PromocionProducto> promocionesProductos = new ArrayList<PromocionProducto>();
public ArrayList <PromocionTemporal> promocionesTemporales = new ArrayList <PromocionTemporal>();
public ArrayList <Operario> operarios = new ArrayList<Operario>();
public ArrayList <OperarioAdministrador> operariosAdmin = new ArrayList<OperarioAdministrador>();
public ArrayList <Operario> operariosLogeados= new ArrayList<Operario>();
public ArrayList <Comanda> comandasActivas= new ArrayList<Comanda>();
private GestionDePersonal zonaPersonal;
private MetodosFacturacion zonaFacturacion;
private ConfiguracionDeSistema zonaConfSistema;

private Local () {
	nombreLocal="Local1";
	sueldoMin=0;
	this.zonaFacturacion= MetodosFacturacion.getInstance();
	this.zonaConfSistema=ConfiguracionDeSistema.getInstance();
	this.zonaPersonal=GestionDePersonal.getInstance();
}

public static Local getInstance() { // Singelton
	if (instance==null) 
		instance=new Local();
	return instance;
}

public String getNombreLocal() {
	return nombreLocal;
}

public void setNombreLocal(String nombreLocal) {
	this.nombreLocal = nombreLocal;
}

public float getSueldoMin() {
	return sueldoMin;
}

public void setSueldoMin(float sueldoMin) {
	this.sueldoMin = sueldoMin;
}

public ArrayList<Mozo> getMozos() {
	// TODO Auto-generated method stub
	return null;
}

public ArrayList<Operario> getOperarios() {
	// TODO Auto-generated method stub
	return null;
}

public ArrayList<Mesa> getMesas() {
	// TODO Auto-generated method stub
	return null;
}

public ArrayList<Producto> getProductos() {
	// TODO Auto-generated method stub
	return null;
}

public float getSueldo() {
	// TODO Auto-generated method stub
	return 0;
}


public void Login(String nombreUsuario, String contra) {
int i=0;
while (i < operarios.size() && !(operarios.get(i).getNombreUsuario().equals(nombreUsuario)))
	i++;
if (i < operarios.size() && operarios.get(i).getNombreUsuario().equals(nombreUsuario)) // agrego a logeado
	if (operarios.get(i).getPassword().equals(contra)) {
		operariosLogeados.add(operarios.get(i));
	} else
		throw new ContraException("la contrase�a ingresada no es la correcta", contra);
else {
	i = 0;
	while (i < operariosAdmin.size() && !(operariosAdmin.get(i).getNombreUsuario().equals(nombreUsuario)))
		i++;
	if (i < operariosAdmin.size() && operariosAdmin.get(i).getNombreUsuario().equals(nombreUsuario)) // agrego a logeado
		if (operariosAdmin.get(i).getPassword().equals(contra)) {
			operariosLogeados.add(operariosAdmin.get(i));
		} else
			throw new ContraException("la contrase�a ingresada no es la correcta", contra);

public void Logout (Operario operario) {
	operariosLogeados.remove(operario);
}

}