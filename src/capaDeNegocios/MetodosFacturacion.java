package capaDeNegocios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import capaDeDatos.Comanda;
import capaDeDatos.Factura;
import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Pedido;
import capaDeDatos.Producto;
import capaDeDatos.PromocionProducto;
import capaDeDatos.PromocionTemporal;

public class MetodosFacturacion {

	private static MetodosFacturacion instance = null;

	private MetodosFacturacion() {
	}// constructor
	
	/**
	 * @return instancia de este SubSingleton para separar los metodos
	 */
	public static MetodosFacturacion getInstance() {
		if (MetodosFacturacion.instance == null)
			MetodosFacturacion.instance = new MetodosFacturacion();
		return MetodosFacturacion.instance;
	}

	/**
	 * Se crea la promocion de los productos
	 * 
	 * @param producto distinto de null
	 * @param diaProm distinto de null
	 * @param dosXuno distinto de null
	 * @param descuentoCantMin distinto de null
	 * @param cantidadMinima distinto de null
	 * @param descCantMin distinto de null
	 * @param activa distinto de null
	 */
	public void altaPromocionProducto(Producto producto, String diaProm, boolean dosXuno, boolean descuentoCantMin,
			int cantidadMinima, float descCantMin, boolean activa) {
		PromocionProducto aux = new PromocionProducto(producto, diaProm, dosXuno, descuentoCantMin, cantidadMinima,
				descCantMin, activa);
		Local.getInstance().getPromocionesProductos().add(aux);
	}
	
	/**
	 * Se elimina la promocion de los productos
	 * 
	 * @param prom distinto de null
	 */
	public void bajaPromocionProducto(PromocionProducto prom) {
		Local.getInstance().getPromocionesProductos().remove(prom);
	}

	/**
	 * Se modifican todos los parametros de la promocion de los productos
	 * 
	 * @param prom distinto de null
	 * @param diaProm distinto de null
	 * @param dosXuno distinto de null
	 * @param descuentoCantMin distinto de null
	 * @param porcentajeCantMin distinto de null
	 * @param cantidadMinima distinto de null
	 * @param activa distinto de null
	 */
	public void modificacionPromocionProducto(PromocionProducto prom, String diaProm, boolean dosXuno,
			boolean descuentoCantMin, float porcentajeCantMin, int cantidadMinima, boolean activa) {
		prom.setDiaProm(diaProm);
		prom.setDosXuno(dosXuno);
		prom.setDescuentoCantMin(descuentoCantMin);
		prom.setCantidadMinima(cantidadMinima);
		prom.setPorcentajeDescCantMin(porcentajeCantMin);
		prom.setActiva(activa);
	}

	/**
	 * Se crea una nueva promocion temporal
	 * 
	 * @param nombre distinto de null
	 * @param formaPago distinto de null
	 * @param porcentajeDesc distinto de null
	 * @param diasDePromo distinto de null
	 * @param activa distinto de null
	 * @param acumulable distinto de null
	 */
	public void altaPromocionTemporal(String nombre, String formaPago, int porcentajeDesc, String diasDePromo,
			boolean activa, boolean acumulable) {
		PromocionTemporal aux = new PromocionTemporal(nombre, formaPago, porcentajeDesc, diasDePromo, activa,
				acumulable);
		Local.getInstance().getPromocionesTemporales().add(aux);
	}
	
	/**
	 * Se elimina una promocion temporal
	 * 
	 * @param prom distinto de null
	 */
	public void bajaPromocionTemporal(PromocionTemporal prom) {
		Local.getInstance().getPromocionesTemporales().remove(prom);
	}

	/**
	 * Se modifica una promocion temporal
	 * 
	 * @param prom distinto de null
	 * @param nombre distinto de null
	 * @param formaPago distinto de null
	 * @param porcentajeDesc distinto de null
	 * @param diasDePromo distinto de null
	 * @param activa distinto de null
	 * @param acumulable distinto de null
	 */
	public void modificacionPromocionTemporal(PromocionTemporal prom, String nombre, String formaPago,
			int porcentajeDesc, String diasDePromo, boolean activa, boolean acumulable) {
		prom.setNombre(nombre);
		prom.setFormaPago(formaPago);
		prom.setPorcentajeDesc(porcentajeDesc);
		prom.setDiasDePromo(diasDePromo);
		prom.setActiva(activa);
		prom.setAcumulable(acumulable);
	}

	/**
	 * Se genera una factura
	 * 
	 * @param fecha distinto de null, la fecha del dia
	 * @param diaSemana distinto de null, el dia en que se hizo la factura
	 * @param comanda distinto de null
	 * @param metodoDePago distinto de null
	 * @return la factura generada
	 */
	public Factura generacionDeFactura(Calendar fecha, String diaSemana, Comanda comanda, String metodoDePago) {
		Factura aux;
		float tot = 0, min, valor;
		Producto auxproducto;
		Mozo mozo;
		ArrayList<PromocionProducto> promsProducto = new ArrayList<PromocionProducto>();
		ArrayList<PromocionTemporal> promsTemp = new ArrayList<PromocionTemporal>();
		int i = 0, j = 0, bandera = -1, cantmin;
		for (i = 0; i < Local.getInstance().getPromocionesTemporales().size(); i++) {
			if (Local.getInstance().getPromocionesTemporales().get(i).getFormaPago().equalsIgnoreCase(metodoDePago)
					&& Local.getInstance().getPromocionesTemporales().get(i).isActiva()
					&& Local.getInstance().getPromocionesTemporales().get(i).getDiasDePromo().equals(fecha)) {
				bandera = i;
				promsTemp.add(Local.getInstance().getPromocionesTemporales().get(i));
			}
		}
		for (i = 0; i < comanda.getListaPedidos().size(); i++) {
			auxproducto = comanda.getListaPedidos().get(i).getProducto();
			while (j < Local.getInstance().getPromocionesProductos().size()
					&& !Local.getInstance().getPromocionesProductos().get(j).getProducto().equals(auxproducto))// busco
																												// la
																												// del
																												// producto
				j++;
			if (j < Local.getInstance().getPromocionesProductos().size()
					&& Local.getInstance().getPromocionesProductos().get(j).getDiaProm().equalsIgnoreCase(diaSemana)
					&& Local.getInstance().getPromocionesProductos().get(j).isActiva() == true) { // no hay oferta
																									// disponible
				if (Local.getInstance().getPromocionesProductos().get(j).isDosXuno()) { // si se aplica 2x1
					if (comanda.getListaPedidos().get(i).getCantidad() == 2
							|| comanda.getListaPedidos().get(i).getCantidad() == 1)
						tot += auxproducto.getPrecioVenta();
					else if (comanda.getListaPedidos().get(i).getCantidad() % 2 == 0)
						tot += comanda.getListaPedidos().get(i).getCantidad() * auxproducto.getPrecioVenta() / 2;
					else
						tot += (comanda.getListaPedidos().get(i).getCantidad() - 1) * auxproducto.getPrecioVenta() / 2
								+ auxproducto.getPrecioVenta();
					promsProducto.add(Local.getInstance().getPromocionesProductos().get(j));
				} else if (Local.getInstance().getPromocionesProductos().get(j).isDescuentoCantMin()) {// si hay
																										// descuento por
																										// cantidad
																										// minima
					min = Local.getInstance().getPromocionesProductos().get(j).getCantidadMinima();
					if (comanda.getListaPedidos().get(i).getCantidad() > min) {
						valor = comanda.getListaPedidos().get(i).getCantidad() * auxproducto.getPrecioVenta();
						valor = valor - valor
								* Local.getInstance().getPromocionesProductos().get(j).getPorcentajeDescCantMin() / 100;
						tot += valor;
						promsProducto.add(Local.getInstance().getPromocionesProductos().get(j));
					}
				} else
					tot += comanda.getListaPedidos().get(i).getCantidad() * auxproducto.getPrecioVenta();
			} else
				tot += comanda.getListaPedidos().get(i).getCantidad() * auxproducto.getPrecioVenta();
		} // hasta aca es la promo producto
		if (bandera > -1) {
			tot = tot - tot * Local.getInstance().getPromocionesTemporales().get(bandera).getPorcentajeDesc() / 100;
		}
		mozo = Local.getInstance().getMozoByMesa(comanda.getMesa());
		mozo.setAcumulados(mozo.getAcumulados() + tot);
		mozo.setMesasAtentidas(mozo.getMesasAtentidas() + 1);
		return aux = new Factura(fecha, comanda.getMesa(), comanda.getListaPedidos(), tot, metodoDePago, promsTemp,
				promsProducto);
	}

	// precondicion: la cantidad es positiva
	/**
	 * Se crea un pedido
	 * 
	 * @param hoy distinto de null
	 * @param cantidad distinto de null, y positivo
	 * @param producto distinto de null
	 * @return el nuevo pedido, de no ser asi no se 
	 */
	public Pedido altaPedido(String hoy, int cantidad, Producto producto) {
		Pedido nuevo = null;
		if (producto.getStock() - cantidad >= 0) {
			producto.setStock(producto.getStock() - cantidad);
			nuevo = new Pedido(hoy, cantidad, producto);
		}
		return nuevo;
	}

	/**
	 * Se da de baja un pedido
	 * @param comanda distinto de null
	 * @param pedido distinto de null
	 */
	public void bajaPedido(Comanda comanda, Pedido pedido) {
		comanda.getListaPedidos().remove(pedido);
	}

	/**
	 * Se crea una comanda
	 * 
	 * @param mesa distinto de null, tiene que estar libre
	 * @param pedido distinto de null 
	 * @return nueva comanda
	 */
	public Comanda altaComanda(Mesa mesa, Pedido pedido) {
		Comanda nuevo = new Comanda(mesa, pedido, true);
		mesa.setEstado("Ocupada");
		Local.getInstance().getComandas().add(nuevo);
		return nuevo;
	}
	
	/**
	 * Se da de baja la comanda y se da de baja la comanda
	 * 
	 * @param comanda distinto de null 
	 * @param metodoDePago distinto de null
	 * @return factura generada
	 */
	public Factura bajaComanda(Comanda comanda, String metodoDePago) {

		Calendar ahora = new GregorianCalendar();
		String diaSemana = Local.getInstance().getDiaSemana(ahora.DAY_OF_WEEK);

		Local.getInstance().getComandas().remove(comanda);

		return generacionDeFactura(ahora, diaSemana, comanda, metodoDePago);
	}

	public void modificacionComanda(Comanda comanda, Pedido pedido, boolean agregar) {
		int pos = Local.getInstance().getComandas().indexOf(comanda);
		if (agregar)
			Local.getInstance().getComandas().get(pos).getListaPedidos().add(pedido);
		else
			Local.getInstance().getComandas().get(pos).getListaPedidos().remove(pedido);
	}

}