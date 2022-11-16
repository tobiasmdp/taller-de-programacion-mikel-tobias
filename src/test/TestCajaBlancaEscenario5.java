package test;

import org.junit.Assert;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import capaDeDatos.AsignacionDiaria;
import capaDeDatos.Comanda;
import capaDeDatos.Factura;
import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Pedido;
import capaDeDatos.Producto;
import capaDeDatos.PromocionProducto;
import capaDeDatos.PromocionTemporal;
import capaDeNegocios.Local;
import capaDeNegocios.MetodosFacturacion;

public class TestCajaBlancaEscenario5 {
	private Local local;
	private MetodosFacturacion mf;
	private Mesa mesa1;
	private Mozo mozo1;
	private AsignacionDiaria asignacionDiaria;
	private Producto producto1;
	private Pedido pedido1;
	private Comanda comanda2;
	private PromocionProducto promoProd4;
	private PromocionProducto promoProd6;
	private Pedido pedido2;
	private Comanda comanda4;
	private PromocionProducto promoProd7;
	private Pedido pedido3;
	private Comanda comanda5;
	private PromocionProducto promoProd8;

	@Before
	public void setUp() throws Exception {
		local = Local.getInstance();
		mf = MetodosFacturacion.getInstance();
		
		mesa1 = new Mesa(1);
		local.getMesas().add(mesa1);
		mozo1 = new Mozo(1, "Juan", "01/01/2000", 2);
		local.getMozos().add(mozo1);
		asignacionDiaria = new AsignacionDiaria(mozo1, mesa1);
		local.getAsignacionDiaria().add(asignacionDiaria);
		
		producto1 = new Producto(1, 10, "Pepsi", 100, 200);
		local.getProductos().add(producto1);
		
		//---Camino 11
		pedido1 = new Pedido("Martes", 2, producto1);
		comanda2 = new Comanda(mesa1, pedido1, true);
		local.getComandas().add(comanda2);
		
		
		//--Camino 13
		
		//--Camino 16
		pedido2 = new Pedido("Martes", 3, producto1);
		comanda4 = new Comanda(mesa1, pedido2, true);
		
		//--Camino 17
		pedido3 = new Pedido("Martes", 4, producto1);
		comanda5 = new Comanda(mesa1, pedido2, true);
	}

	@After
	public void tearDown() throws Exception {
		local.getPromocionesProductos().removeAll(local.getPromocionesProductos());
		local.getMesas().removeAll(local.getMesas());
		local.getMozos().removeAll(local.getMozos());
		local.getAsignacionDiaria().removeAll(local.getAsignacionDiaria());
		local.getProductos().removeAll(local.getProductos());
		Local.elimInstance();
	}

	@Test
	public void testGeneracionDeFacturaC11() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		promoProd4 = new PromocionProducto(producto1, "Martes", false, false, 0, 0, true);
		local.getPromocionesProductos().add(promoProd4);
		try {
			Mozo mozo = local.getMozoByMesa(mesa1);
			float acumAnt = mozo.getAcumulados();
			
			int mesasAnt = mozo.getMesasAtentidas();
			
			
			//float descPromProd = promoProd4.getPorcentajeDescCantMin()/100;
			//float descPromTemp = promoTemp1.getPorcentajeDesc()/100;
			
			factura = mf.generacionDeFactura(fecha, "Martes", comanda2, "ctaDNI");	
			
			Assert.assertEquals("Fecha de factura deberia ser la misma que la ingresada", factura.getFecha(), fecha);
			Assert.assertEquals("Mesa de la factura deberia ser la misma que la pasada por la comanda.", factura.getMesa(), comanda2.getMesa());
			Assert.assertTrue("La lista de pedidos deberia estar vacia.", factura.getListaProductos().size() == 1);
			Assert.assertTrue("No se aplicaron promociones correctamente.", factura.getTotal() == comanda2.getListaPedidos().get(0).getProducto().getPrecioVenta()*comanda2.getListaPedidos().get(0).getCantidad());
			Assert.assertEquals("Metodo de pago deberia ser el mismo.", factura.getMetodoPago(), "ctaDNI");
			Assert.assertTrue("Lista de promociones temporales aplicadas falla.", factura.getTotPromocionesTemp().isEmpty());
			Assert.assertFalse("Lista de promociones de productos aplicadas falla.", factura.getTotPromocionesProd().size() == 1);
			
			
			Assert.assertTrue("La cantidad total de la factura no se acumulo al mozo.", mozo.getAcumulados() == acumAnt+factura.getTotal());
			Assert.assertTrue("No se suma la mesa atendida del mozo.", mesasAnt + 1 == mozo.getMesasAtentidas());
			Assert.assertEquals("Se debe cambiar el estado de la mesa a libre.", factura.getMesa().getEstado(), "libre");
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion.");
		}
	}

	
	@Test
	public void testGeneracionDeFacturaC13() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		promoProd6 = new PromocionProducto(producto1, "Martes", false, true, 1, 50, true);
		local.getPromocionesProductos().add(promoProd6);
		try {
			Mozo mozo = local.getMozoByMesa(mesa1);
			float acumAnt = mozo.getAcumulados();
			
			int mesasAnt = mozo.getMesasAtentidas();
			
			
			float descPromProd = promoProd6.getPorcentajeDescCantMin()/100;
			//float descPromTemp = promoTemp1.getPorcentajeDesc()/100;
			
			factura = mf.generacionDeFactura(fecha, "Martes", comanda2, "ctaDNI");	
			
			Assert.assertEquals("Fecha de factura deberia ser la misma que la ingresada", factura.getFecha(), fecha);
			Assert.assertEquals("Mesa de la factura deberia ser la misma que la pasada por la comanda.", factura.getMesa(), comanda2.getMesa());
			Assert.assertTrue("La lista de pedidos deberia estar vacia.", factura.getListaProductos().size() == 1);
			Assert.assertTrue("No se aplicaron promociones correctamente.", factura.getTotal() == comanda2.getListaPedidos().get(0).getProducto().getPrecioVenta()*comanda2.getListaPedidos().get(0).getCantidad()*descPromProd);
			Assert.assertEquals("Metodo de pago deberia ser el mismo.", factura.getMetodoPago(), "ctaDNI");
			Assert.assertTrue("Lista de promociones temporales aplicadas falla.", factura.getTotPromocionesTemp().isEmpty());
			Assert.assertTrue("Lista de promociones de productos aplicadas falla.", factura.getTotPromocionesProd().size() == 1);
			
			
			Assert.assertTrue("La cantidad total de la factura no se acumulo al mozo.", mozo.getAcumulados() == acumAnt+factura.getTotal());
			Assert.assertTrue("No se suma la mesa atendida del mozo.", mesasAnt + 1 == mozo.getMesasAtentidas());
			Assert.assertEquals("Se debe cambiar el estado de la mesa a libre.", factura.getMesa().getEstado(), "libre");
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion.");
		}
	}
	
	@Test
	public void testGeneracionDeFacturaC16() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		promoProd7 = new PromocionProducto(producto1, "Martes", true, false, 1, 50, true);
		local.getPromocionesProductos().add(promoProd7);
		try {
			Mozo mozo = local.getMozoByMesa(mesa1);
			float acumAnt = mozo.getAcumulados();
			
			int mesasAnt = mozo.getMesasAtentidas();
			
			
			//float descPromProd = promoProd7.getPorcentajeDescCantMin()/100;
			//float descPromTemp = promoTemp1.getPorcentajeDesc()/100;
			
			factura = mf.generacionDeFactura(fecha, "Martes", comanda4, "ctaDNI");	
			
			Assert.assertEquals("Fecha de factura deberia ser la misma que la ingresada", factura.getFecha(), fecha);
			Assert.assertEquals("Mesa de la factura deberia ser la misma que la pasada por la comanda.", factura.getMesa(), comanda2.getMesa());
			Assert.assertTrue("La lista de pedidos deberia estar vacia.", factura.getListaProductos().size() == 1);
			Assert.assertTrue("No se aplicaron promociones correctamente.", factura.getTotal() == comanda2.getListaPedidos().get(0).getProducto().getPrecioVenta()*2);
			Assert.assertEquals("Metodo de pago deberia ser el mismo.", factura.getMetodoPago(), "ctaDNI");
			Assert.assertTrue("Lista de promociones temporales aplicadas falla.", factura.getTotPromocionesTemp().isEmpty());
			Assert.assertTrue("Lista de promociones de productos aplicadas falla.", factura.getTotPromocionesProd().size() == 1);
			
			
			Assert.assertTrue("La cantidad total de la factura no se acumulo al mozo.", mozo.getAcumulados() == acumAnt+factura.getTotal());
			Assert.assertTrue("No se suma la mesa atendida del mozo.", mesasAnt + 1 == mozo.getMesasAtentidas());
			Assert.assertEquals("Se debe cambiar el estado de la mesa a libre.", factura.getMesa().getEstado(), "libre");
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion.");
		}
	}
	
	@Test
	public void testGeneracionDeFacturaC17() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		promoProd8 = new PromocionProducto(producto1, "Martes", true, false, 1, 50, true);
		local.getPromocionesProductos().add(promoProd8);
		try {
			Mozo mozo = local.getMozoByMesa(mesa1);
			float acumAnt = mozo.getAcumulados();
			
			int mesasAnt = mozo.getMesasAtentidas();
			
			
			//float descPromProd = promoProd7.getPorcentajeDescCantMin()/100;
			//float descPromTemp = promoTemp1.getPorcentajeDesc()/100;
			
			factura = mf.generacionDeFactura(fecha, "Martes", comanda4, "ctaDNI");	
			
			Assert.assertEquals("Fecha de factura deberia ser la misma que la ingresada", factura.getFecha(), fecha);
			Assert.assertEquals("Mesa de la factura deberia ser la misma que la pasada por la comanda.", factura.getMesa(), comanda2.getMesa());
			Assert.assertTrue("La lista de pedidos deberia estar vacia.", factura.getListaProductos().size() == 1);
			Assert.assertTrue("No se aplicaron promociones correctamente.", factura.getTotal() == comanda2.getListaPedidos().get(0).getProducto().getPrecioVenta()*2);
			Assert.assertEquals("Metodo de pago deberia ser el mismo.", factura.getMetodoPago(), "ctaDNI");
			Assert.assertTrue("Lista de promociones temporales aplicadas falla.", factura.getTotPromocionesTemp().isEmpty());
			Assert.assertTrue("Lista de promociones de productos aplicadas falla.", factura.getTotPromocionesProd().size() == 1);
			
			
			Assert.assertTrue("La cantidad total de la factura no se acumulo al mozo.", mozo.getAcumulados() == acumAnt+factura.getTotal());
			Assert.assertTrue("No se suma la mesa atendida del mozo.", mesasAnt + 1 == mozo.getMesasAtentidas());
			Assert.assertEquals("Se debe cambiar el estado de la mesa a libre.", factura.getMesa().getEstado(), "libre");
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion.");
		}
	}
}
