/**
 * 
 */
package capaDeNegocios;

import java.util.ArrayList;
import java.util.Calendar;

import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Producto;

/**
 * @author tobia
 *
 */
public class ConfiguracionDeSistema {

	private static ConfiguracionDeSistema instance=null;
	
	private ConfiguracionDeSistema () {}
	
	/**
	 * @return instancia de este SubSingleton para separar los metodos
	 */
	public static ConfiguracionDeSistema getInstance() { // Singelton
		if (instance==null) 
			instance=new ConfiguracionDeSistema();
		return instance;
	}
	
	/**
	 *  Se crea una nueva mesa
	 */
	public void altaMesa() {
		int id;
		Mesa mesa;
		ArrayList<Mesa> mesas = Local.getInstance().getMesas();
		if (mesas.isEmpty()) {
			id = Local.prefijoMesa;
		}
		else {
			id = mesas.get(mesas.size()-1).getId() + 1;
		}
		mesa = new Mesa(id);
		Local.getInstance().getMesas().add(mesa);
	}
	
	/**
	 * Se elimina una mesa
	 * 
	 * @param mesa distinto de null
	 */
	public void bajaMesa(Mesa mesa) {
		Local.getInstance().getMesas().remove(mesa);
	}
	
	/**
	 * 
	 * Se modifica una mesa (no se usa)
	 * @param mesa distinto de null
	 * @param accion distinto de null
	 * @param valor distinto de null
	 */
	public void modificaMesa(Mesa mesa, String accion, int valor) {
		switch (accion) {
		case "comensales":
			mesa.setComensales(valor);
			break;
		default:
			//Raise exception;
		}
	}
	
	/**
	 * 
	 * Se modifica el estado de una mesa
	 * @param mesa distinto de null
	 * @param accion distinto de null
	 * @param valor distinto de null
	 */
	public void modificaMesa(Mesa mesa, String accion, String valor) {
		switch (accion) {
		case "estado":
			mesa.setEstado(valor);
			break;
		default:
			//Raise exception;
		}
	}
	
	/**
	 * Se crea un nuevo producto
	 * 
	 * @param stock distinto de null
	 * @param nombre distinto de null
	 * @param precioCosto distinto de null
	 * @param precioVenta distinto de null
	 */
	public void altaProducto(int stock, String nombre, float precioCosto, float precioVenta) {
		int id;
		Producto producto;
		ArrayList<Producto> productos = Local.getInstance().getProductos();
		if (productos.isEmpty()) {
			id = Local.prefijoProducto;
		}
		else {
			id = productos.get(productos.size()-1).getId() + 1;
		}
		producto = new Producto(id, stock, nombre, precioCosto, precioVenta);
		Local.getInstance().getProductos().add(producto);
	}
	
	/**
	 * Se elimina un producto
	 * 
	 * @param producto distinto de null
	 */
	public void bajaProductos(Producto producto) {
		Local.getInstance().getProductos().remove(producto);
	}
	
	/**  
	 * Se modifica el stock de un producto
	 * @param producto distinto de null
	 * @param accion distinto de null
	 * @param valor distinto de null
	 */
	public void modificaProducto(Producto producto, String accion, int valor) {
		switch (accion) {
		case "stock":
			producto.setStock(valor);
			break;
		default:
			//Raise exception;
		}
	}
	
	/** Se modifican el nombre de los productos
	 * @param producto distinto de null
	 * @param accion distinto de null
	 * @param valor distinto de null
	 */
	public void modificaProducto(Producto producto, String accion, String valor) {
		switch (accion) {
		case "nombre":
			producto.setNombre(valor);
			break;
		default:
		//	Raise exception;
		}
	}
	/** Se modifican los precios de los productos
	 * @param producto distinto de null
	 * @param accion distinto de null
	 * @param valor distinto de null
	 */
	public void modificaProducto(Producto producto, String accion, float valor) {
		switch (accion) {
		case "precioCosto":
			producto.setPrecioCosto(valor);
			break;
		case "precioVenta":
			producto.setPrecioVenta(valor);
			break;
		default:
		//	Raise exception;
		}
	}
	
	
}
