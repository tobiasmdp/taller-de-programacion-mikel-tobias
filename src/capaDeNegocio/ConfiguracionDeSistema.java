/**
 * 
 */
package capaDeNegocio;

import java.util.ArrayList;
import java.util.Calendar;

import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Producto;

/**
 * @author tobia
 *
 */
public abstract class ConfiguracionDeSistema {
	
	public void altaMesa() {
		int id;
		Mesa mesa;
		ArrayList<Mesa> mesas = Sistema.getInstance().getMesas();
		if (mesas.isEmpty()) {
			id = Sistema.prefijoMesa;
		}
		else {
			id = mesas.get(mesas.size()-1).getId() + 1;
		}
		mesa = new Mesa(id);
		Sistema.getInstance().getMesas().add(mesa);
	}
	
	public void bajaMesa(Mesa mesa) {
		Sistema.getInstance().getMesas().remove(mesa);
	}
	
	public void modificaMesa(Mesa mesa, String accion, int valor) {
		switch (accion) {
		case "comensales":
			mesa.setComensales(valor);
			break;
		default:
			Raise exception;
		}
	}
	
	public void modificaMesa(Mesa mesa, String accion, String valor) {
		switch (accion) {
		case "estado":
			mesa.setEstado(valor);
			break;
		default:
			Raise exception;
		}
	}
	
	
	public void altaProducto(int stock, String nombre, float precioCosto, float precioVenta) {
		int id;
		Producto producto;
		ArrayList<Producto> productos = Sistema.getInstance().getProductos();
		if (productos.isEmpty()) {
			id = Sistema.prefijoProducto;
		}
		else {
			id = productos.get(productos.size()-1).getId() + 1;
		}
		producto = new Producto(id, stock, nombre, precioCosto, precioVenta);
		Sistema.getInstance().getProductos().add(producto);
	}
	
	public void bajaProductos(Producto producto) {
		Sistema.getInstance().getProductos().remove(producto);
	}
	
	public void modificaProducto(Producto producto, String accion, int valor) {
		switch (accion) {
		case "stock":
			producto.setStock(valor);
			break;
		default:
			Raise exception;
		}
	}
	
	public void modificaProducto(Producto producto, String accion, String valor) {
		switch (accion) {
		case "nombre":
			producto.setNombre(valor);
			break;
		default:
			Raise exception;
		}
	}
	
	public void modificaProducto(Producto producto, String accion, float valor) {
		switch (accion) {
		case "precioCosto":
			producto.setPrecioCosto(valor);
			break;
		case "precioVenta":
			producto.setPrecioVenta(valor);
			break;
		default:
			Raise exception;
		}
	}
	
	
}
