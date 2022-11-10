package capaDeNegocios;

import java.util.ArrayList;
import java.util.Calendar;

import capaDeDatos.Comanda;
import capaDeDatos.Factura;
import capaDeDatos.Mesa;
import capaDeDatos.Pedido;
import capaDeDatos.Producto;
import capaDeDatos.PromocionProducto;
import capaDeDatos.PromocionTemporal;

public class MetodosFacturacion {

private static MetodosFacturacion instance=null;

private MetodosFacturacion() {}// constructor

public static MetodosFacturacion getInstance() {
	if (MetodosFacturacion.instance==null)
		MetodosFacturacion.instance=new MetodosFacturacion();
	return MetodosFacturacion.instance;
}

public void altaPromocionProducto(Producto producto, String diaProm, boolean dosXuno, boolean descuentoCantMin,int cantidadMinima, float descCantMin, boolean activa) {
	PromocionProducto aux =new	PromocionProducto(producto,diaProm,dosXuno,descuentoCantMin,cantidadMinima, descCantMin,activa);
	Local.getInstance().getPromocionesProductos().add(aux);
}

public void bajaPromocionProducto(PromocionProducto prom) {
	Local.getInstance().getPromocionesProductos().remove(prom);
}

public void modificacionPromocionProducto(PromocionProducto prom, String diaProm, boolean dosXuno, boolean descuentoCantMin, float porcentajeCantMin, boolean activa, int cantidadMinima) {
	prom.setDiaProm(diaProm);
	prom.setDosXuno(dosXuno);
	prom.setDescuentoCantMin(descuentoCantMin);
	prom.setCantidadMinima(cantidadMinima);
	prom.setPorcentajeDescCantMin(porcentajeCantMin);
	prom.setActiva(activa);
}

public void altaPromocionTemporal(String nombre, String formaPago, int porcentajeDesc, String diasDePromo, boolean activa, boolean acumulable) {
	PromocionTemporal aux =new	PromocionTemporal(nombre,formaPago,porcentajeDesc,diasDePromo,activa,acumulable);
	Local.getInstance().getPromocionesTemporales().add(aux);
}

public void bajaPromocionTemporal(PromocionTemporal prom) {
	Local.getInstance().getPromocionesTemporales().remove(prom);
}

public void modificacionPromocionTemporal(PromocionTemporal prom, String nombre, String formaPago, int porcentajeDesc, String diasDePromo, boolean activa, boolean acumulable) {
	prom.setNombre(nombre);
	prom.setFormaPago(formaPago);
	prom.setPorcentajeDesc(porcentajeDesc);
	prom.setDiasDePromo(diasDePromo);
	prom.setActiva(activa);
	prom.setAcumulable(acumulable);
}

public Factura generacionDeFactura(Calendar fecha, String diaSemana,Comanda comanda , String metodoDePago) {
	Factura aux;
	float tot=0, min,valor;
	Producto auxproducto;
	ArrayList<PromocionProducto> promsProducto=new ArrayList <PromocionProducto>();
	ArrayList<PromocionTemporal> promsTemp=new ArrayList <PromocionTemporal>();
	int i=0,j=0, bandera=-1, cantmin;
	for(i=0;i<Local.getInstance().getPromocionesTemporales().size();i++) {
		if (Local.getInstance().getPromocionesTemporales().get(i).getFormaPago().equalsIgnoreCase(metodoDePago) && Local.getInstance().getPromocionesTemporales().get(i).isActiva() && Local.getInstance().getPromocionesTemporales().get(i).getDiasDePromo().equals(fecha)) {
			bandera=i;
			promsTemp.add(Local.getInstance().getPromocionesTemporales().get(i));
		}
	}	
		for (i=0;i< comanda.getListaPedidos().size();i++) {
		auxproducto=comanda.getListaPedidos().get(i).getProducto();
		while (j<Local.getInstance().getPromocionesProductos().size() && !Local.getInstance().getPromocionesProductos().get(j).getProducto().equals(auxproducto))// busco la del producto
			j++;
		if (j<Local.getInstance().getPromocionesProductos().size() && Local.getInstance().getPromocionesProductos().get(j).getDiaProm().equalsIgnoreCase(diaSemana) && Local.getInstance().getPromocionesProductos().get(j).isActiva()==true) { //no hay oferta disponible 
			if (Local.getInstance().getPromocionesProductos().get(j).isDosXuno()) { // si se aplica 2x1
				if (comanda.getListaPedidos().get(i).getCantidad()==2 || comanda.getListaPedidos().get(i).getCantidad()==1 )
					tot+=auxproducto.getPrecioVenta(); 
				else if (comanda.getListaPedidos().get(i).getCantidad()%2==0)
					tot+=comanda.getListaPedidos().get(i).getCantidad()*auxproducto.getPrecioVenta()/2;
				else
					tot+=(comanda.getListaPedidos().get(i).getCantidad()-1)*auxproducto.getPrecioVenta()/2 + auxproducto.getPrecioVenta();
				promsProducto.add(Local.getInstance().getPromocionesProductos().get(j));
			}
			else if (Local.getInstance().getPromocionesProductos().get(j).isDescuentoCantMin()){// si hay descuento por cantidad minima
				min=Local.getInstance().getPromocionesProductos().get(j).getCantidadMinima();
				if (comanda.getListaPedidos().get(i).getCantidad()>min) {
					valor=comanda.getListaPedidos().get(i).getCantidad()*auxproducto.getPrecioVenta();
					valor=valor-valor*Local.getInstance().getPromocionesProductos().get(j).getPorcentajeDescCantMin()/100;
					tot+=valor;
					promsProducto.add(Local.getInstance().getPromocionesProductos().get(j));
					}
			}
			else
				tot+=comanda.getListaPedidos().get(i).getCantidad()*auxproducto.getPrecioVenta();
		}
		else 
			tot+=comanda.getListaPedidos().get(i).getCantidad()*auxproducto.getPrecioVenta();
		}  // hasta aca es la promo producto
		if (bandera>-1)
			tot=tot-tot*Local.getInstance().getPromocionesTemporales().get(bandera).getPorcentajeDesc()/100;
	return aux =new Factura(fecha,comanda.getMesa(),comanda.getListaPedidos(), tot, metodoDePago, promsTemp, promsProducto);
}

public Pedido altaPedido(Calendar hoy,int cantidad,Producto producto)  {
	if (producto.getStock()-cantidad<=0)
		new ExcepcionNoDisponible();
	else {
	producto.setStock(producto.getStock()-cantidad);
	Pedido nuevo=new Pedido(hoy,cantidad,producto);
	return nuevo;
	}
}

public void bajaPedido(Comanda comanda, Pedido pedido) {
	comanda.getListaPedidos().remove(pedido);
}

public Comanda altaComanda(Mesa mesa, Pedido pedido) {
	Comanda nuevo=new Comanda(mesa,pedido,true);
	Local.getInstance().getComandasActivas().add(nuevo);
	return nuevo;
}	

public void bajaComanda (Comanda comanda) {
	Local.getInstance().getComandasActivas().remove(comanda);
}

public void modificacionComanda(Comanda comanda,Pedido pedido, boolean agregar) {
	int pos=Local.getInstance().getComandasActivas().indexOf(comanda);
	if (agregar)
		Local.getInstance().getComandasActivas().get(pos).getListaPedidos().add(pedido);
	else
		Local.getInstance().getComandasActivas().get(pos).getListaPedidos().remove(pedido);
}

}