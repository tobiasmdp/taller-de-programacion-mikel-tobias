package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import capaDeDatos.Mesa;
import capaDeDatos.Mozo;
import capaDeDatos.Operario;
import capaDeDatos.Producto;
import capaDeNegocios.ConfiguracionDeSistema;
import capaDeNegocios.Local;

public class TestConfiguracionDeSistemaEscenario1 {
	private Mesa mesa;
	private Producto producto;
	private Local local;
	private ConfiguracionDeSistema configSistema;
	
	
	

	@Before
	public void setUp() {
		local=Local.getInstance();
		mesa= new Mesa(5);
		producto=new Producto(3,20,"Coca Cola", 25, 50);
		local.getMesas().add(mesa);
		local.getProductos().add(producto);
		configSistema=ConfiguracionDeSistema.getInstance();
		
	}
	
	@Test 
	public void testAltaMesaCaso1() {
		boolean contiene= false;
		Mesa mesaaux;
		Mesa mesa=null;

		int tamanio= local.getMesas().size();
		configSistema.altaMesa();
		ArrayList<Mesa> mesas= local.getMesas();
		
		assertEquals("La mesa no se agrego a la lista", tamanio, local.getMesas().size()-1);
		int idUltimo=mesas.get(mesas.size()-1).getId();
		int idPenultimo=mesas.get(mesas.size()-2).getId();;
		assertEquals("La mesa no tiene asignada un id correcto",idUltimo-1,idPenultimo);
	}
	
	@Test 
	public void testBajaMesaCaso1() {
		boolean contiene=false;
		
		try{
			configSistema.bajaMesa(mesa);
		}
		catch(Exception e) {
			Assert.fail("No deberia haber largado una excepcion");
		}
		
		ArrayList<Mesa> mesas= local.getMesas();
		for(int i=0;i<mesas.size();i++) {
			if(mesas.get(i)==mesa)
				contiene= true;
		}
		assertFalse("La lista aun contiene el elemento", contiene);
	}
	
	@Test 
	public void testBajaMesaCaso2() {
		try{
			Mesa mesa2 =new Mesa(5);
			configSistema.bajaMesa(mesa2);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testBajaMesaCaso3() {
		try{
			configSistema.bajaMesa(null);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMesaComensalesCaso1() {
		Mesa mesa2 =new Mesa(5);
		configSistema.modificaMesa(mesa2, "comensales", 2);
		assertEquals("La cantidad de comensales de la mesa no se modifico correctamente",2,mesa2.getComensales());
	}
	
	@Test 
	public void testModificaMesaComensalesCaso2() {
		Mesa mesa2 =new Mesa(5);
		
		try{
			configSistema.modificaMesa(mesa2, "cubiertos", 2);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMesaComensalesCaso3() {
		
		try{
			configSistema.modificaMesa(null, "comensales", 2);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	@Test 
	public void testModificaMesaComensalesCaso4() {
		Mesa mesa2 =new Mesa(5);
		try{
			configSistema.modificaMesa(mesa2, null, 2);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMesaComensalesCaso5() {
		Mesa mesa2 =new Mesa(5);
		try{
			configSistema.modificaMesa(mesa2, "comensales", -1);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMesaEstadoCaso1() {
		Mesa mesa2 =new Mesa(5);
		String nuevoEstado="Libre";
		configSistema.modificaMesa(mesa2, "estado", nuevoEstado);
		assertEquals("El estado de la mesa no se modifico correctamente",nuevoEstado,mesa2.getEstado());
	}
	@Test 
	public void testModificaMesaEstadoCaso2() {
		Mesa mesa2 =new Mesa(5);
		String nuevoEstado="Ocupada";
		configSistema.modificaMesa(mesa2, "estado", nuevoEstado);
		assertEquals("El estado de la mesa no se modifico correctamente",nuevoEstado,mesa2.getEstado());
	}
	
	@Test 
	public void testModificaMesaEstadoCaso3() {
		Mesa mesa2 =new Mesa(5);
		String nuevoEstado="Libre";
		try{
			configSistema.modificaMesa(mesa2, "cantidad", nuevoEstado);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMesaEstadoCaso4() {
		Mesa mesa2 =new Mesa(5);
		try{
			configSistema.modificaMesa(mesa2, "estado", "Nada");
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMesaEstadoCaso5() {
		String nuevoEstado="Libre";
		try{
			configSistema.modificaMesa(null, "estado", nuevoEstado);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	
	@Test 
	public void testModificaMesaEstadoCaso6() {
		Mesa mesa2 =new Mesa(5);
		String nuevoEstado="Libre";
		try{
			configSistema.modificaMesa(mesa2, null, nuevoEstado);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMesaEstadoCaso7() {
		Mesa mesa2 =new Mesa(5);
		String nuevoEstado=null;
		try{
			configSistema.modificaMesa(mesa2, "estado", nuevoEstado);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	@Test 
	public void testAltaProductoCaso1() {
		boolean contiene= false;
		Producto productoaux;
		Producto producto=null;
		
		configSistema.altaProducto(10,"Pepsi", 150, 300);
		ArrayList<Producto> productos= local.getProductos();
		
		for(int i=0;i< productos.size();i++) {
			productoaux=productos.get(i);
			if( productoaux.getNombre().equals("Pepsi") && productoaux.getStock()==10 && productoaux.getPrecioCosto()==150 && productoaux.getPrecioVenta()==300) {
				contiene= true;
				producto=productoaux;
			}
		}
		assertTrue("La lista no contiene el elementado a agregar", contiene);
		assertEquals("El id no se genera correctamente", producto.getId(), 4);
		
	}
	
	@Test 
	public void testAltaProductoCaso2() {

		try{
			configSistema.altaProducto(-1,"Fanta", 150, 300);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testAltaProductoCaso3() {

		try{
			configSistema.altaProducto(13,null, 150, 300);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testAltaProductoCaso4() {

		try{
			configSistema.altaProducto(13,"coca", -10, 300);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	@Test 
	public void testAltaProductoCaso5() {

		try{
			configSistema.altaProducto(13,"Torta", 100, -10);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testAltaProductoCaso6() {

		try{
			configSistema.altaProducto(13,"Torta", 100, 500);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test
	public void testBajaProductoCaso1() {
		boolean contiene=false;
		try{
			configSistema.bajaProductos(producto);
		}
		catch(Exception e) {
			Assert.fail("No deberia haber largado una excepcion");
		}
		ArrayList<Producto> productos= local.getProductos();
		for(int i=0;i<productos.size();i++) {
			if(productos.get(i)==producto)
				contiene= true;
		}
		assertFalse("La lista aun contiene el elemento", contiene);
	}
	
	@Test
	public void testBajaProductoCaso2() {
		try{
			Producto producto2= new Producto(4,20,"Pepsi", 25, 50);
			configSistema.bajaProductos(producto2);
			Assert.fail("No deaberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}

	@Test
	public void testBajaProductoCaso3() {
		try{
			configSistema.bajaProductos(null);
			Assert.fail("No deaberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	

	@Test 
	public void testModificaProductoStockCaso1() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		configSistema.modificaProducto(producto2, "stock", 10);
		assertEquals("El stock no se modifico correctamente", producto2.getStock(), 10);
	}
	

	@Test 
	public void  testModificaProductoStockCaso2() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		try{
			configSistema.modificaProducto(producto2, "cantidad", 10);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void  testModificaProductoStockCaso3() {
		try{
			configSistema.modificaProducto(null,"stock", 10);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void  testModificaProductoStockCaso4() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		try{
			configSistema.modificaProducto(producto2,null, 10);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	

	@Test 
	public void  testModificaProductoStockCaso5() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		try{
			configSistema.modificaProducto(producto2,"stock", -10);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaProductoNombreCaso1() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		String nuevoNombre="pepsiMax";
		configSistema.modificaProducto(producto2, "nombre", nuevoNombre);
		assertEquals("El nombre del producto no se modifico correctamente", producto2.getNombre(), nuevoNombre);
	}
	

	@Test 
	public void  testModificaProductoNombreCaso2() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		String nuevoNombre="pepsiMax";
		try{
			configSistema.modificaProducto(producto2, "etiqueta", nuevoNombre);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	
	@Test 
	public void  testModificaProductoNombreCaso3() {
		String nuevoNombre="pepsiMax";

		try{
			configSistema.modificaProducto(null,"nombre", nuevoNombre);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void  testModificaProductoNombreCaso4() {
		String nuevoNombre="pepsiMax";

		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		try{
			configSistema.modificaProducto(producto2,null, nuevoNombre);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	

	@Test 
	public void  testModificaProductoNombreCaso5() {
		String nuevoNombre=null;

		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		try{
			configSistema.modificaProducto(producto2,"nombre", nuevoNombre);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	
	@Test 
	public void testModificaProductoPreciosCaso1() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		configSistema.modificaProducto(producto2, "precioCosto", (float)10);
		Assert.assertEquals("El precio de costo no se modifico correctamente",  producto2.getPrecioCosto(), 10,1500);
	}
	
	@Test 
	public void testModificaProductoPreciosCaso2() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		configSistema.modificaProducto(producto2, "precioVenta", (float)100);
		Assert.assertEquals("El precio de costo no se modifico correctamente",  producto2.getPrecioVenta(), 100,1500);
	}
	

	@Test 
	public void  testModificaProductoPreciosCaso3() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		try{
			configSistema.modificaProducto(producto2, "PRECIO", (float)10);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void  testModificaProductoPreciosCaso4() {
		try{
			configSistema.modificaProducto(null,"precioCosto", (float)50);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void  testModificaProductoPreciosCaso5() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		try{
			configSistema.modificaProducto(producto2,"precioCosto", (float)-10);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	

	@Test 
	public void  testModificaProductoPreciosCaso6() {
		Producto producto2 =new Producto(4,20,"Pepsi", 25, 50);
		try{
			configSistema.modificaProducto(producto2,null, (float)50);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	
	@After
	public void tearDown(){
		local.getMesas().removeAll(local.getMesas());
		local.getProductos().removeAll(local.getProductos());
		Local.elimInstance();
	}
}
