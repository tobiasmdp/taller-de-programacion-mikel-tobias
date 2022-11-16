package test;

import org.junit.Assert;

import java.util.ArrayList;
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
import capaDeDatos.PromocionTemporal;
import capaDeNegocios.Local;
import capaDeNegocios.MetodosFacturacion;

public class TestCajaBlancaEscenario3 {
	private Local local;
	private MetodosFacturacion mf;
	private Mesa mesa1;
	private Mozo mozo1;
	private AsignacionDiaria asignacionDiaria;
	private Producto producto1;
	private Pedido pedido1;
	private Comanda comanda1;
	private PromocionTemporal promoTemp1;
	
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
		pedido1 = new Pedido("Jueves", 2, producto1);
		comanda1 = new Comanda(mesa1, pedido1, true);
		comanda1.setListaPedidos(new ArrayList<Pedido>());
		
		local.getComandas().add(comanda1);
		promoTemp1 = new PromocionTemporal("promo1", "ctaDNI", 30, Calendar.getInstance().toString(), true, false);
		local.getPromocionesTemporales().add(promoTemp1);
	}

	@After
	public void tearDown() throws Exception {
		Local.elimInstance();
	}

	@Test
	public void testGeneracionDeFacturaC18() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			Mozo mozo = local.getMozoByMesa(mesa1);
			//float acumAnt = mozo.getAcumulados();
			
			int mesasAnt = mozo.getMesasAtentidas();
			
			//float totalSinProms = comanda1.getListaPedidos().get(0).getCantidad() * comanda1.getListaPedidos().get(0).getProducto().getPrecioVenta();
			
			
			//float descPromTemp = promoTemp1.getPorcentajeDesc()/100;
			
			factura = mf.generacionDeFactura(fecha, "Jueves", comanda1, "ctaDNI");	
			
			Assert.assertEquals("Fecha de factura deberia ser la misma que la ingresada", factura.getFecha(), fecha);
			Assert.assertEquals("Mesa de la factura deberia ser la misma que la pasada por la comanda.", factura.getMesa(), comanda1.getMesa());
			Assert.assertTrue("La lista de pedidos deberia estar vacia.", factura.getListaProductos().isEmpty());
			Assert.assertTrue("No se aplicaron promociones correctamente.", factura.getTotal() == 0);
			Assert.assertEquals("Metodo de pago deberia ser el mismo.", factura.getMetodoPago(), "ctaDNI");
			Assert.assertTrue("Lista de promociones temporales aplicadas falla.", factura.getTotPromocionesTemp().size() == 1);
			Assert.assertFalse("Lista de promociones de productos aplicadas falla.", factura.getTotPromocionesProd().isEmpty());
			
			
			//Assert.assertTrue("La cantidad total de la factura no se acumulo al mozo.", mozo.getAcumulados() == acumAnt+factura.getTotal());
			Assert.assertTrue("No se suma la mesa atendida del mozo.", mesasAnt + 1 == mozo.getMesasAtentidas());
			Assert.assertEquals("Se debe cambiar el estado de la mesa a libre.", factura.getMesa().getEstado(), "libre");
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion.");
		}
	}

}
