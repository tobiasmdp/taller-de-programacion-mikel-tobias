package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import capaDeDatos.Factura;
import capaDeDatos.Producto;
import capaDeDatos.PromocionProducto;
import capaDeDatos.PromocionTemporal;
import capaDeNegocios.Local;
import capaDeNegocios.MetodosFacturacion;

public class TestMetodosFacturacionSinDatos {
	private MetodosFacturacion mf;
	private Local local;
	
	@Before
	public void setUp() throws Exception {
		mf = MetodosFacturacion.getInstance();
		local = Local.getInstance();
		
	}

	@After
	public void tearDown() throws Exception {
		Local.elimInstance();

	}

	@Test
	public void testAltaPromocionProductoCaso5() {
		try {
			mf.altaPromocionProducto(new Producto(2, 10, "Coca", 100, 200),"Lunes", true, true, 2, 50, false);
			Assert.fail("No deberia seguir con la ejecucion porque producto no esta en la lista de productos del Local");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionProductoCaso6() {
		try {
			mf.altaPromocionProducto(null,"Lunes", true, true, 2, 50, false);
			Assert.fail("No deberia seguir con la ejecucion porque producto es nulo");
		}catch(Exception e) {
			
		}
	}
	
	
	@Test
	public void testBajaPromocionProductoCaso3() {
		try {
			mf.bajaPromocionProducto(null);
						
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testModificacionPromocionProductoCaso5(){
		try {
			mf.modificacionPromocionProducto(null, "Martes", true, true, 50, 2, false);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionProductoCaso10(){
		try {
			mf.modificacionPromocionProducto(new PromocionProducto(local.getProductos().get(0), "Martes", true, true, 2, 50, true), "Martes", false, true, 50, -100, true);
			Assert.fail("No deberia seguir ejecucion");			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionTemporalCaso1() {
		try {
			mf.altaPromocionTemporal("Gran promo", "efectivo", 30, "Martes", true, true);
			
			int i = 0;
			ArrayList<PromocionTemporal> promTemp = local.getPromocionesTemporales();
			
			while(i< promTemp.size() && !promTemp.get(i).getNombre().equals("Gran promo") && !promTemp.get(i).getFormaPago().equals("efectivo") 
					&& promTemp.get(i).getPorcentajeDesc() != 30 && !promTemp.get(i).getDiasDePromo().equals("Martes") 
					&& promTemp.get(i).isActiva() != true && promTemp.get(i).isAcumulable() != true)
				i++;
			
			Assert.assertTrue("Deberia haberse agregado correctamente la promocion temporal.", i < promTemp.size());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testAltaPromocionTemporalCaso2() {
		try {
			mf.altaPromocionTemporal("Gran promo", "efectivo", 30, "Martes", false, true);
			
			int i = 0;
			ArrayList<PromocionTemporal> promTemp = local.getPromocionesTemporales();
			
			while(i< promTemp.size() && !promTemp.get(i).getNombre().equals("Gran promo") && !promTemp.get(i).getFormaPago().equals("efectivo") 
					&& promTemp.get(i).getPorcentajeDesc() != 30 && !promTemp.get(i).getDiasDePromo().equals("Martes") 
					&& promTemp.get(i).isActiva() != false && promTemp.get(i).isAcumulable() != true)
				i++;
			
			Assert.assertTrue("Deberia haberse agregado correctamente la promocion temporal.", i < promTemp.size());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testAltaPromocionTemporalCaso3() {
		try {
			mf.altaPromocionTemporal("Gran promo", "efectivo", 30, "Martes", true, false);
			
			int i = 0;
			ArrayList<PromocionTemporal> promTemp = local.getPromocionesTemporales();
			
			while(i< promTemp.size() && !promTemp.get(i).getNombre().equals("Gran promo") && !promTemp.get(i).getFormaPago().equals("efectivo") 
					&& promTemp.get(i).getPorcentajeDesc() != 30 && !promTemp.get(i).getDiasDePromo().equals("Martes") 
					&& promTemp.get(i).isActiva() != true && promTemp.get(i).isAcumulable() != false)
				i++;
			
			Assert.assertTrue("Deberia haberse agregado correctamente la promocion temporal.", i < promTemp.size());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testAltaPromocionTemporalCaso4() {
		try {
			mf.altaPromocionTemporal(null, "efectivo", 30, "Martes", true, true);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionTemporalCaso5() {
		try {
			mf.altaPromocionTemporal("Gran promo", "ninguno", 30, "Martes", true, true);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionTemporalCaso6() {
		try {
			mf.altaPromocionTemporal("Gran promo", null, 30, "Martes", true, true);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionTemporalCaso7() {
		try {
			mf.altaPromocionTemporal("Gran promo", "efectivo", -1000, "Martes", true, true);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionTemporalCaso8() {
		try {
			mf.altaPromocionTemporal("Gran promo", "efectivo", 30, "ninguno", true, true);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionTemporalCaso9() {
		try {
			mf.altaPromocionTemporal("Gran promo", "efectivo", 30, null, true, true);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testBajaPromocionTemporalCaso3() {
		try {
			mf.bajaPromocionProducto(null);
						
		}catch(Exception e) {

			Assert.fail("No deberia lanzar excepcion, porque no deberia hacer nada.");
		}
	}
	
	@Test
	public void testModificacionPromocionTemporalCaso4(){
		try {
			mf.modificacionPromocionTemporal(new PromocionTemporal("Promocion2", "efectivo", 40, "Sabado", true, true), "Promocion2", "tarjeta", 25, "Sabado", true, true);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionTemporalCaso5(){
		try {
			mf.modificacionPromocionTemporal(null, "Promocion1", "ctaDNI", 40, "Jueves", true, true);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	
	
	@Test
	public void testBajaPedidoCaso5() {
		try {
			mf.bajaPedido(null, null);
			Assert.fail("No deberia seguir con la ejecucion");
		}catch(Exception e) {
			
		}
	}
}
