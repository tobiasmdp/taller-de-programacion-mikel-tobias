package capaDeDatos;

import java.util.Calendar;


public class Pedido {
private static int nroID=0;
private int iDPedido;
private Calendar fecha;
private int cantidad;
private Producto producto;

public Pedido(Calendar fecha, int cantidad, Producto producto) {
	nroID++;
	this.iDPedido=nroID;
	this.fecha = fecha;
	this.cantidad = cantidad;
	this.producto = producto;
}

public int getiDPedido() {
	return iDPedido;
}


public Calendar getFecha() {
	return fecha;
}

public void setFecha(Calendar fecha) {
	this.fecha = fecha;
}

public int getCantidad() {
	return cantidad;
}

public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}

public Producto getProducto() {
	return producto;
}

public void setProducto(Producto producto) {
	this.producto = producto;
}

}
