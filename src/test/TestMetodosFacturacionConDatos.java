package test;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

public class TestMetodosFacturacionConDatos {
	private MetodosFacturacion mf;
	private Local local;
	private Producto producto1; 
	private PromocionProducto promocionProducto1;
	private PromocionTemporal promocionTemporal1;
	private Mesa mesa1;
	private Pedido pedido1;
	private Comanda comanda1;
	private Pedido pedido2;
	private Comanda comanda2;
	private PromocionProducto promocionProducto2;
	private PromocionProducto promocionProducto3;
	private Comanda comanda3;
	private Comanda comanda4;
	
	@Before
	public void setUp() throws Exception {
		mf = MetodosFacturacion.getInstance();
		local = Local.getInstance();
		
		producto1 = new Producto(1, 10, "Pepsi", 100, 200);
		local.getProductos().add(producto1);
		
		promocionProducto1 = new PromocionProducto(local.getProductos().get(0), "Martes", true, true, 2, 50, true);
		local.getPromocionesProductos().add(promocionProducto1);
		promocionTemporal1 = new PromocionTemporal("Promocion1", "ctaDNI", 40, "Jueves", true, true);
		local.getPromocionesTemporales().add(promocionTemporal1);
		
		
		//----- para facturacion
		mesa1 = new Mesa(1);
		local.getMesas().add(mesa1);
		Mozo mozo1 = new Mozo(1, "Juan", "01/01/2000", 2);
		AsignacionDiaria asignacionDiaria = new AsignacionDiaria(mozo1, mesa1);
		local.getAsignacionDiaria().add(asignacionDiaria);
		
		promocionProducto2 = new PromocionProducto(local.getProductos().get(0), "Jueves", true, true, 2, 50, true);
		local.getPromocionesProductos().add(promocionProducto2);
		pedido1 = new Pedido("Jueves", 2, local.getProductos().get(0));
		comanda1 = new Comanda(mesa1, pedido1, true);
		local.getComandas().add(comanda1);			//Preparado caso 1
		

		
		Producto producto2 = new Producto(2, 10, "Coca", 100, 300);
		local.getProductos().add(producto2);
		pedido2 = new Pedido("Lunes", 2, local.getProductos().get(1));
		promocionProducto3 = new PromocionProducto(local.getProductos().get(1), "Lunes", true, true, 2, 50, true);
		local.getPromocionesProductos().add(promocionProducto3);
		comanda2 = new Comanda(mesa1, pedido2, true);
		local.getComandas().add(comanda2);		//Preparado caso 2
		
		Producto producto3 = new Producto(3, 10, "Fanta", 100, 500);
		local.getProductos().add(producto3);
		Pedido pedido3 = new Pedido("Jueves", 2, local.getProductos().get(2));
		comanda3 = new Comanda(mesa1, pedido3, true);
		local.getComandas().add(comanda3);		//Preparado caso 3
		
		Pedido pedido4 = new Pedido("Martes", 2, local.getProductos().get(0));
		comanda4 = new Comanda(mesa1, pedido4, true);
		local.getComandas().add(comanda4);		//Preparado caso 4
	}

	@After
	public void tearDown() throws Exception {
		local.getProductos().removeAll(local.getProductos());
		local.getPromocionesProductos().removeAll(local.getPromocionesProductos());
		local.getPromocionesTemporales().removeAll(local.getPromocionesTemporales());
		local.getComandas().removeAll(local.getComandas());
		local.getMesas().removeAll(local.getMesas());
		Local.elimInstance();

	}

	@Test
	public void testAltaPromocionProductoCaso1() {
		try {
			mf.altaPromocionProducto(local.getProductos().get(0),"Lunes", true, true, 2, 50, true);
			
			int i = 0;
			ArrayList<PromocionProducto> promProd = local.getPromocionesProductos();
			
			while(i< promProd.size() && promProd.get(i).getProducto() != local.getProductos().get(0) && !promProd.get(i).getDiaProm().equals("Lunes") && 
					promProd.get(i).isDosXuno() != true && promProd.get(i).isDescuentoCantMin() != true && promProd.get(i).getCantidadMinima() != 2
					&& promProd.get(i).getPorcentajeDescCantMin() != (float) 50 && promProd.get(i).isActiva() != true) {
				i++;
			}
			Assert.assertTrue("Deberia haberse agregado correctamente la promocion producto.", i < promProd.size());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	
	@Test
	public void testAltaPromocionProductoCaso2() {
		try {
			mf.altaPromocionProducto(local.getProductos().get(0),"Lunes", false, true, 2, 50, true);
			
			int i = 0;
			ArrayList<PromocionProducto> promProd = local.getPromocionesProductos();
			while(i< promProd.size() && promProd.get(i).getProducto() != local.getProductos().get(0) && !promProd.get(i).getDiaProm().equals("Lunes") && 
					promProd.get(i).isDosXuno() != false && promProd.get(i).isDescuentoCantMin() != true && promProd.get(i).getCantidadMinima() != 2
					&& promProd.get(i).getPorcentajeDescCantMin() != (float) 50 && promProd.get(i).isActiva() != true) {
				i++;
			}
			Assert.assertTrue("Deberia haberse agregado correctamente la promocion producto.", i < promProd.size());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testAltaPromocionProductoCaso3() {
		try {
			mf.altaPromocionProducto(local.getProductos().get(0),"Lunes", true, false, 2, 50, true);
			
			int i = 0;
			ArrayList<PromocionProducto> promProd = local.getPromocionesProductos();
			while(i< promProd.size() && promProd.get(i).getProducto() != local.getProductos().get(0) && !promProd.get(i).getDiaProm().equals("Lunes") && 
					promProd.get(i).isDosXuno() != true && promProd.get(i).isDescuentoCantMin() != false && promProd.get(i).getCantidadMinima() != 2
					&& promProd.get(i).getPorcentajeDescCantMin() != (float) 50 && promProd.get(i).isActiva() != true) {
				i++;
			}
			Assert.assertTrue("Deberia haberse agregado correctamente la promocion producto.", i < promProd.size());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testAltaPromocionProductoCaso4() {
		try {
			mf.altaPromocionProducto(local.getProductos().get(0),"Lunes", true, false, 2, 50, false);
			
			int i = 0;
			ArrayList<PromocionProducto> promProd = local.getPromocionesProductos();
			while(i< promProd.size() && promProd.get(i).getProducto() != local.getProductos().get(0) && !promProd.get(i).getDiaProm().equals("Lunes") && 
					promProd.get(i).isDosXuno() != true && promProd.get(i).isDescuentoCantMin() != false && promProd.get(i).getCantidadMinima() != 2
					&& promProd.get(i).getPorcentajeDescCantMin() != (float) 50 && promProd.get(i).isActiva() != false) {
				i++;
			}
			Assert.assertTrue("Deberia haberse agregado correctamente la promocion producto.", i < promProd.size());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
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
	public void testAltaPromocionProductoCaso7() {
		try {
			mf.altaPromocionProducto(local.getProductos().get(0),"ninguno", true, true, 2, 50, false);
			Assert.fail("No deberia seguir con la ejecucion porque el dia tiene que ser uno de la semana");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionProductoCaso8() {
		try {
			mf.altaPromocionProducto(local.getProductos().get(0),null, true, true, 2, 50, false);
			Assert.fail("No deberia seguir con la ejecucion porque el dia no puede ser nulo");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionProductoCaso9() {
		try {
			mf.altaPromocionProducto(local.getProductos().get(0),"Lunes", true, true, -1, 50, true);
			Assert.fail("No deberia seguir con la ejecucion porque la cantidad minima deberia ser mayor a 0");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPromocionProductoCaso10() {
		try {
			mf.altaPromocionProducto(local.getProductos().get(0),"Lunes", true, true, 2, -100, true);
			Assert.fail("No deberia seguir con la ejecucion porque el descuento por cantidad minima deberia ser mayor a 0");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testBajaPromocionProductoCaso1() {
		try {
			mf.bajaPromocionProducto(local.getPromocionesProductos().get(0));
			
			int i = 0;
			ArrayList<PromocionProducto> promProd = local.getPromocionesProductos();
			while(i< promProd.size() && promocionProducto1 != promProd.get(i))
				i++;
			
			Assert.assertTrue("Deberia haberse eliminado la promocion producto.", i >= promProd.size());
		}catch(Exception e) {
			Assert.fail("No deberia haber lanzado excepcion.");
		}
	}
	
	@Test
	public void testBajaPromocionProductoCaso2() {
		try {
			mf.bajaPromocionProducto(new PromocionProducto(local.getProductos().get(0), "Martes", true, true, 2, 50, true));
						
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion, porque no deberia hacer nada.");
		}
	}
	
	@Test
	public void testBajaPromocionProductoCaso3() {
		try {
			mf.bajaPromocionProducto(null);
						
		}catch(Exception e) {

			Assert.fail("No deberia lanzar excepcion, porque no deberia hacer nada.");
		}
	}
	
	@Test
	public void testModificacionPromocionProductoCaso1() {
		try {
			String pre = promocionProducto1.getDiaProm();
			mf.modificacionPromocionProducto(promocionProducto1, "Lunes", true, true, 50, 2, true);

			Assert.assertNotEquals("Deberia haber cambiado el dia de promocion", pre, local.getPromocionesProductos().get(0).getDiaProm());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testModificacionPromocionProductoCaso2() {
		try {
			Boolean pre = promocionProducto1.isDosXuno();
			mf.modificacionPromocionProducto(promocionProducto1, "Martes", false, true, 50, 2, true);

			Assert.assertNotEquals("Deberia haber cambiado el dia de promocion", pre, local.getPromocionesProductos().get(0).isDosXuno());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testModificacionPromocionProductoCaso3(){
		try {
			Boolean pre = promocionProducto1.isDescuentoCantMin();
			mf.modificacionPromocionProducto(promocionProducto1, "Martes", true, false, 50, 2, true);

			Assert.assertNotEquals("Deberia haber cambiado el dia de promocion", pre, local.getPromocionesProductos().get(0).isDescuentoCantMin());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testModificacionPromocionProductoCaso4(){
		try {
			Boolean pre = promocionProducto1.isActiva();
			mf.modificacionPromocionProducto(promocionProducto1, "Martes", true, true, 50, 2, false);

			Assert.assertNotEquals("Deberia haber cambiado el dia de promocion", pre, local.getPromocionesProductos().get(0).isActiva());
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
	public void testModificacionPromocionProductoCaso6(){
		try {
			mf.modificacionPromocionProducto(promocionProducto1, "ninguno", true, true, 50, 2, false);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionProductoCaso7(){
		try {
			mf.modificacionPromocionProducto(promocionProducto1, null, true, true, 50, 2, false);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionProductoCaso8(){
		try {
			mf.modificacionPromocionProducto(promocionProducto1, "Martes", false, true, -1, 2, true);
			Assert.fail("No deberia seguir ejecucion");			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionProductoCaso9(){
		try {
			mf.modificacionPromocionProducto(promocionProducto1, "Martes", false, true, 50, -100, true);
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
	public void testBajaPromocionTemporalCaso1() {
		try {
			mf.bajaPromocionProducto(local.getPromocionesProductos().get(0));
			
			int i = 0;
			ArrayList<PromocionTemporal> promTemps = local.getPromocionesTemporales();
			while(i< promTemps.size() && promocionTemporal1 != promTemps.get(i)) 
				i++;
			
			Assert.assertTrue("Deberia haberse eliminado la promocion producto.", i >= promTemps.size());
		}catch(Exception e) {
			Assert.fail("No deberia haber lanzado excepcion.");
		}
	}
	
	@Test
	public void testBajaPromocionTemporalCaso2() {
		try {
			mf.bajaPromocionTemporal(new PromocionTemporal("Promosao", "tarjeta", 20, "Viernes", false, false));
			
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion, porque no deberia hacer nada.");
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
	public void testModificacionPromocionTemporalCaso1() {
		try {
			String pre1 = promocionTemporal1.getNombre();
			int pre2 = promocionTemporal1.getPorcentajeDesc();
			mf.modificacionPromocionTemporal(promocionTemporal1, "Promo", "ctaDNI", 30, "Jueves", true, true);


			Assert.assertNotEquals("Deberia haber cambiado el nombre de la promocion", pre1, local.getPromocionesTemporales().get(0).getNombre());
			Assert.assertNotEquals("Deberia haber cambiado el porcentaje de la promocion", pre2, local.getPromocionesTemporales().get(0).getPorcentajeDesc());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}

	@Test
	public void testModificacionPromocionTemporalCaso2() {
		try {
			Boolean pre = promocionTemporal1.isActiva();
			mf.modificacionPromocionTemporal(promocionTemporal1, "Promocion1", "ctaDNI", 40, "Jueves", false, true);
						
			Assert.assertNotEquals("Deberia haber cambiado el atributo activa de la promocion", pre, local.getPromocionesTemporales().get(0).isActiva());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
		}
	}
	
	@Test
	public void testModificacionPromocionTemporalCaso3() {
		try {
			Boolean pre = promocionTemporal1.isAcumulable();
			mf.modificacionPromocionTemporal(promocionTemporal1, "Promocion1", "ctaDNI", 40, "Jueves", true, false);
						
		
			Assert.assertNotEquals("Deberia haber cambiado el atributo acumulable de la promocion", pre, local.getPromocionesTemporales().get(0).isAcumulable());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion");
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
	public void testModificacionPromocionTemporalCaso6(){
		try {
			mf.modificacionPromocionTemporal(promocionTemporal1, null, "ctaDNI", 40, "Jueves", true, true);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionTemporalCaso7(){
		try {
			mf.modificacionPromocionTemporal(promocionTemporal1, "Promocion1", "ninguno", 40, "Jueves", true, true);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionTemporalCaso8(){
		try {
			mf.modificacionPromocionTemporal(promocionTemporal1, "Promocion1", null, 40, "Jueves", true, true);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionTemporalCaso9(){
		try {
			mf.modificacionPromocionTemporal(promocionTemporal1, "Promocion1", "ctaDNI", -1000, "Jueves", true, true);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionTemporalCaso10(){
		try {
			mf.modificacionPromocionTemporal(promocionTemporal1, "Promocion1", "ctaDNI", 40, "ninguno", true, true);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionPromocionTemporalCaso11(){
		try {
			mf.modificacionPromocionTemporal(promocionTemporal1, "Promocion1", "ctaDNI", 40, null, true, true);
			Assert.fail("No deberia seguir ejecucion");
			
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testGeneracionDeFacturaCaso1() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			Mozo mozo = local.getMozoByMesa(mesa1);
			float acumAnt = mozo.getAcumulados();
			
			int mesasAnt = mozo.getMesasAtentidas();
			
			float totalSinProms = comanda1.getListaPedidos().get(0).getCantidad() * comanda1.getListaPedidos().get(0).getProducto().getPrecioVenta();
			
			float descPromProd = promocionProducto2.getPorcentajeDescCantMin()/100;
			float descPromTemp = promocionTemporal1.getPorcentajeDesc()/100;
			
			factura = mf.generacionDeFactura(fecha, "Jueves", comanda1, "ctaDNI");	
			
			
			
			Assert.assertTrue("La cantidad total de la factura no se acumulo al mozo.", mozo.getAcumulados() == acumAnt+factura.getTotal());
			Assert.assertTrue("No se suma la mesa atendida del mozo.", mesasAnt + 1 == mozo.getMesasAtentidas());
			Assert.assertTrue("No se aplicaron promociones correctamente.", factura.getTotal() == totalSinProms*descPromProd*descPromTemp);
			Assert.assertEquals("Se debe cambiar el estado de la mesa a libre.", factura.getMesa().getEstado(), "libre");
		}catch (Exception e) {
			Assert.fail("No deberia lanzar una excepcion.");
		}
	}
	
	@Test
	public void testGeneracionDeFacturaCaso2() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			Mozo mozo = local.getMozoByMesa(mesa1);
			float acumAnt = mozo.getAcumulados();
			int mesasAnt = mozo.getMesasAtentidas();
			
			float totalSinProms = comanda2.getListaPedidos().get(0).getCantidad() * comanda2.getListaPedidos().get(0).getProducto().getPrecioVenta();
			
			float descPromProd = promocionProducto3.getPorcentajeDescCantMin()/100;
			
			factura = mf.generacionDeFactura(fecha, "Lunes", comanda2, "efectivo");	
			
			
			Assert.assertTrue("La cantidad total de la factura no se acumulo al mozo.", mozo.getAcumulados() == acumAnt+factura.getTotal());
			Assert.assertTrue("No se suma la mesa atendida del mozo.", mesasAnt + 1 == mozo.getMesasAtentidas());
			Assert.assertTrue("No se aplicaron promociones correctamente.", factura.getTotal() == totalSinProms*descPromProd);
			Assert.assertEquals("Se debe cambiar el estado de la mesa a libre.", factura.getMesa().getEstado(), "libre");
		}catch (Exception e) {
			Assert.fail("No deberia lanzar una excepcion.");
		}
	}
	
	@Test
	public void testGeneracionDeFacturaCaso3() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			Mozo mozo = local.getMozoByMesa(mesa1);
			float acumAnt = mozo.getAcumulados();
			int mesasAnt = mozo.getMesasAtentidas();
			
			float totalSinProms = comanda3.getListaPedidos().get(0).getProducto().getPrecioVenta() * comanda3.getListaPedidos().get(0).getProducto().getPrecioVenta();
			
			float descPromTemp = promocionTemporal1.getPorcentajeDesc()/100;
			
			factura = mf.generacionDeFactura(fecha, "Jueves", comanda3, "ctaDNI");	
			
			
			Assert.assertTrue("La cantidad total de la factura no se acumulo al mozo.", mozo.getAcumulados() == acumAnt+factura.getTotal());
			Assert.assertTrue("No se suma la mesa atendida del mozo.", mesasAnt + 1 == mozo.getMesasAtentidas());
			Assert.assertTrue("No se aplicaron promociones correctamente.", factura.getTotal() == totalSinProms*descPromTemp);
			Assert.assertEquals("Se debe cambiar el estado de la mesa a libre.", factura.getMesa().getEstado(), "libre");
		}catch (Exception e) {
			Assert.fail("No deberia lanzar una excepcion.");
		}
	}
	
	@Test
	public void testGeneracionDeFacturaCaso4() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			Mozo mozo = local.getMozoByMesa(mesa1);
			System.out.println(mozo);
		
			float acumAnt = mozo.getAcumulados();
			int mesasAnt = mozo.getMesasAtentidas();
			
			float totalSinProms = comanda3.getListaPedidos().get(0).getProducto().getPrecioVenta() * comanda3.getListaPedidos().get(0).getProducto().getPrecioVenta();
			
			System.out.println(totalSinProms);
			factura = mf.generacionDeFactura(fecha, "Martes", comanda4, "efectivo");	
			
			
			Assert.assertTrue("La cantidad total de la factura no se acumulo al mozo.", mozo.getAcumulados() == acumAnt+factura.getTotal());
			Assert.assertTrue("No se suma la mesa atendida del mozo.", mesasAnt + 1 == mozo.getMesasAtentidas());
			Assert.assertTrue("No se calculo total factura correctamente.", factura.getTotal() == totalSinProms);
			Assert.assertEquals("Se debe cambiar el estado de la mesa a libre.", factura.getMesa().getEstado(), "libre");
		}catch (Exception e) {
			Assert.fail("No deberia lanzar una excepcion.");
		}
	}
	
	@Test
	public void testGeneracionDeFacturaCaso5() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			factura = mf.generacionDeFactura(null, "Jueves", comanda1, "ctaDNI");
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	@Test
	public void testGeneracionDeFacturaCaso6() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			factura = mf.generacionDeFactura(fecha, "dia", comanda1, "ctaDNI");
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testGeneracionDeFacturaCaso7() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			factura = mf.generacionDeFactura(fecha, null, comanda1, "ctaDNI");
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testGeneracionDeFacturaCaso8() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			factura = mf.generacionDeFactura(fecha, "Jueves", comanda1, "Lavado de platos");
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testGeneracionDeFacturaCaso9() {
		Calendar fecha = Calendar.getInstance();
		Factura factura;
		try {
			factura = mf.generacionDeFactura(fecha, "Jueves", comanda1, null);
			Assert.fail("No deberia seguir con la ejecucion.");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPedidoCaso1() {
		try {
			int stockAnt = producto1.getStock();
			Pedido ped = mf.altaPedido(new GregorianCalendar().toString(), 2, producto1);
			
			Assert.assertNotNull("Deberia haber creado el pedido", ped);
			Assert.assertEquals("No se actualizo el stock del producto", producto1.getStock(), stockAnt);			
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion.");
		}
	}
	
	@Test
	public void testAltaPedidoCaso2() {
		try {
			Pedido ped = mf.altaPedido(null, 2, producto1);
			Assert.fail("No deberia seguir con la ejecucion");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaPedidoCaso3() {
		try {
			Pedido ped = mf.altaPedido(new GregorianCalendar().toString(), 11, producto1);
			Assert.assertNull("Pedido devuelto deberia haber sido null", ped);
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion.");
		}
	}
	
	
	@Test
	public void testBajaPedidoCaso1() {
		try {
			mf.bajaPedido(comanda1, pedido1);
			int i = 0;
			while(i<comanda1.getListaPedidos().size() && comanda1.getListaPedidos().get(i) != pedido1)
				i++;
			Assert.assertTrue("No se deberia haber encontrado el pedido en la lista de pedidos una vez encontrado", i >= comanda1.getListaPedidos().size());
		}catch(Exception e) {
			Assert.fail("No deberia lanzar excepcion.");
		}
	}
	
	@Test
	public void testBajaPedidoCaso2() {
		try {
			mf.bajaPedido(comanda1, pedido2);
			Assert.fail("No deberia seguir con la ejecucion");
		}catch(Exception e) {
			
		}
	}
	@Test
	public void testBajaPedidoCaso3() {
		try {
			mf.bajaPedido(null, pedido2);
			Assert.fail("No deberia seguir con la ejecucion");
		}catch(Exception e) {
			
		}
	}
	@Test
	public void testBajaPedidoCaso4() {
		try {
			mf.bajaPedido(comanda1, null);
			Assert.fail("No deberia seguir con la ejecucion");
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
	
	@Test
	public void testAltaComandaCaso1() {
		try {
			Comanda comanda = mf.altaComanda(mesa1, pedido1);
			
			int i = 0;
			while(i < local.getComandas().size() && local.getComandas().get(i) != comanda)
				i++;
					
			Assert.assertTrue("Se deberia haber encontrado la comanda en la lista de comandas.", i <= local.getComandas().size());
			Assert.assertTrue("La comanda deberia iniciar como activa", comanda.isEstado() == true);	
		}catch(Exception e) {
			Assert.fail("No deberia lanzar una excepcion.");
		}
	}
	
	@Test
	public void testModificacionComandaCaso1() {
		try {
			mf.modificacionComanda(comanda1, pedido2, true);
			int i = 0;
			while(i < comanda1.getListaPedidos().size() && comanda1.getListaPedidos().get(i) != pedido2)
				i++;
			Assert.assertTrue("Deberia haberse agregado el pedido a la lista de pedidos de la comanda", i < comanda1.getListaPedidos().size() );
		}catch(Exception e) {
			Assert.fail("No deberia lanzar una excepcion.");
		}
	}
	
	@Test
	public void testModificacionComandaCaso2() {
		try {
			mf.modificacionComanda(comanda1, pedido2, false);
			int i = 0;
			while(i < comanda1.getListaPedidos().size() && comanda1.getListaPedidos().get(i) != pedido2)
				i++;
			Assert.assertTrue("Deberia haberse dado de baja el pedido de la lista de pedidos de la comanda", i >= comanda1.getListaPedidos().size() );
		}catch(Exception e) {
			Assert.fail("No deberia lanzar una excepcion.");
		}
	}
	
	@Test
	public void testModificacionComandaCaso3() {
		try {
			mf.modificacionComanda(comanda1, pedido2, false);
			Assert.fail("No deberia continuar con la ejecucion");
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificacionComandaCaso4() {
		try {
			mf.modificacionComanda(comanda1, pedido1, true);
			Assert.fail("No deberia continuar con la ejecucion");
		}catch(Exception e) {
			
		}
	}
}
