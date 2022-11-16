package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import capaDeDatos.Mozo;
import capaDeDatos.Operario;
import capaDeNegocios.GestionDePersonal;
import capaDeNegocios.Local;

public class TestGestionDePersonalEscenario1 {//Escenario con lista llena, para conocer los numeros de casos verificar la documentacion de testing
	private Mozo mozo;
	private Operario operario;
	private Local local;
	private GestionDePersonal gestionPersonal;
	
	@Before
	public void setUp() {
		local=Local.getInstance();
		mozo =new Mozo(5,"Nahuel","13/10/1980", 2);
		operario= new Operario(5,"NahuelNievas", "13/10/1996","Nahuel", "contrasenia");
		local.getMozos().add(mozo);
		local.getOperarios().add(operario);
		gestionPersonal=GestionDePersonal.getInstance();
		
	}
	
	@Test
	public void testBajaOperarioCaso1() {
		boolean contiene=false;
		try{
			gestionPersonal.bajaOperario(operario);
		}
		catch(Exception e) {
			Assert.fail("No deberia haber largado una excepcion");
		}
		ArrayList<Operario> operarios= local.getOperarios();
		for(int i=0;i<operarios.size();i++) {
			if(operarios.get(i)==operario)
				contiene= true;
		}
		assertFalse("La lista aun contiene el elemento", contiene);
	}
	
	@Test
	public void testBajaOperarioCaso2() {
		try{
			Operario operario2= new Operario(7,"NahuelNievas", "13/10/1996","Nahuel", "contrasenia");
			gestionPersonal.bajaOperario(operario2);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}

	@Test
	public void testBajaOperarioCaso3() {
		try{
			gestionPersonal.bajaOperario(null);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test
	public void testModificaOperarioCaso1() {
		Operario operario2= new Operario(7,"NahuelNievas", "13/10/1996","Nahuel", "contrasenia");
		String nuevoNombre="hola";
		gestionPersonal.modificaOperario(operario2, "nombreApellido", nuevoNombre);
		
		Assert.assertEquals("El nombre no se modifico correctamente",nuevoNombre, operario2.getNombreApellido());
	}
	
	@Test
	public void testModificaOperarioCaso2() {
		Operario operario2= new Operario(7,"NahuelNievas", "13/10/1996","Nahuel", "contrasenia");
		String nuevoNombre="ImanolVazquez";
		gestionPersonal.modificaOperario(operario2, "nombreApellido", nuevoNombre);
		
		Assert.assertEquals("El nombre no se modifico correctamente",nuevoNombre, operario2.getNombreApellido());
	}
	@Test
	public void testModificaOperarioCaso3() {
		Operario operario2= new Operario(7,"NahuelNievas", "13/10/1996","Nahuel", "contrasenia");
		String nuevoNombreUsuario="hola";
		gestionPersonal.modificaOperario(operario2, "nombreUsuario", nuevoNombreUsuario);
		
		Assert.assertEquals("El nombre no se modifico correctamente",nuevoNombreUsuario, operario2.getNombreUsuario());
	}
	@Test
	public void testModificaOperarioCaso4() {
		Operario operario2= new Operario(7,"NahuelNievas", "13/10/1996","Nahuel", "contrasenia");
		String nuevoPassword="hola";
		gestionPersonal.modificaOperario(operario2, "password", nuevoPassword);
		
		Assert.assertEquals("El nombre no se modifico correctamente",nuevoPassword, operario2.getPassword());
	}
	
	@Test
	public void testModificaOperarioCaso5() {
		String nuevoPassword="hola";
		try {
			gestionPersonal.modificaOperario(null, "password", nuevoPassword);
			Assert.fail("No deberia seguir con la ejecucion");
			
		}
		catch(Exception e) {
			
		}
	}
	
	
	@Test
	public void testModificaOperarioCaso6() {
		Operario operario2= new Operario(7,"NahuelNievas", "13/10/1996","Nahuel", "contrasenia");
		
		try {
			gestionPersonal.modificaOperario(operario2, "password", null);
			Assert.fail("No deberia seguir con la ejecucion");
			
		}
		catch(Exception e) {
			
		}
	}
	

	@Test
	public void testAltaMozoCaso1() {
		boolean contiene= false;
		Mozo mozoaux;
		Mozo mozo=null;
		gestionPersonal.altaMozo("NahuelNievas", "14/01/2000", 5);
		
		ArrayList<Mozo> mozos= local.getMozos();
		
		for(int i=0;i< mozos.size();i++) {
			mozoaux=mozos.get(i);
			if( mozoaux.getNacimiento().equals("14/01/2000") && mozoaux.getNombreApellido().equals("NahuelNievas") && mozoaux.getCantHijos()==5) {
				contiene= true;
				mozo=mozoaux;
			}
		}
		assertTrue("La lista no contiene el elementado a agregar", contiene);
		assertEquals("El id no se genera correctamente", mozo.getId(), 6);
		
	}
	
	@Test
	public void testAltaMozoCaso2() {
		try {
			gestionPersonal.altaMozo(null, "14/01/2000", 5);
			Assert.fail("No deberia seguir con la ejecucion");
			
		}
		catch(Exception e) {
			
		}
	}
	@Test
	public void testAltaMozoCaso3() {
		
		
		try {
			gestionPersonal.altaMozo("NahuelNievas", null, 5);
			Assert.fail("No deberia seguir con la ejecucion");
			
		}
		catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaMozoCaso4() {
		
		
		try {
			gestionPersonal.altaMozo("NahuelNievas","14/01/2021",5);
			Assert.fail("No deberia seguir con la ejecucion");
			
		}
		catch(Exception e) {
			
		}
	}
	
	@Test
	public void testAltaMozoCaso5() {
		
		
		try {
			gestionPersonal.altaMozo("NahuelNievas","14/01/2000",-3);
			Assert.fail("No deberia seguir con la ejecucion");
			
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testBajaMozoCaso1() {
		boolean contiene=false;
		try{
			gestionPersonal.bajaMozo(mozo);
		}
		catch(Exception e) {
			Assert.fail("No deberia haber largado una excepcion");
		}
		
		ArrayList<Mozo> mozos= local.getMozos();
		for(int i=0;i<mozos.size();i++) {
			if(mozos.get(i)==mozo)
				contiene= true;
		}
		assertFalse("La lista aun contiene el elemento", contiene);
	}
	
	@Test 
	public void testBajaMozoCaso2() {
		try{
			gestionPersonal.bajaMozo(null);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testBajaMozoCaso3() {
		try{
			Mozo mozo2 =new Mozo(7,"Imanol","13/10/1980", 3);
			gestionPersonal.bajaMozo(mozo2);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMozoNombreYEstadosCaso1() {
		Mozo mozo2 =new Mozo(7,"Imanol","13/10/1980", 3);
		String nombreNuevo="hola";
		gestionPersonal.modificaMozo(mozo2, "nombreApellido", nombreNuevo);
		
		assertEquals("El nombre no se modifico correctamente", mozo2.getNombreApellido(), nombreNuevo);
	}
	
	@Test 
	public void testModificaMozoNombreYEstadosCaso2() {
		Mozo mozo2 =new Mozo(7,"Imanol","13/10/1980", 3);
		String estadoNuevo="hola";
		gestionPersonal.modificaMozo(mozo2, "estado", estadoNuevo);
		
		assertEquals("El estado no se modifico correctamente", mozo2.getEstado(), estadoNuevo);
	}
	
	@Test 
	public void testModificaMozoNombreYEstadosCaso3() {
		String estadoNuevo="hola";
		
		try{
			gestionPersonal.modificaMozo(null, "estado", estadoNuevo);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMozoNombreYEstadosCaso4() {
		String estadoNuevo=null;
		Mozo mozo2 =new Mozo(7,"Imanol","13/10/1980", 3);
		try{
			gestionPersonal.modificaMozo(mozo2, "estado", estadoNuevo);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMozoCantHijosCaso1() {
		Mozo mozo2 =new Mozo(7,"Imanol","13/10/1980", 5);
		gestionPersonal.modificaMozo(mozo2, "cantHijos", 3);
		assertEquals("La cantidad de hijos no se modifico correctamente", mozo2.getCantHijos(), 3);
			
	}
	
	@Test 
	public void testModificaMozoCantHijosCaso2() {
		
		try{
			gestionPersonal.modificaMozo(null, "cantHijos", 3);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	
	@Test 
	public void testModificaMozoCantHijosCaso3() {
		Mozo mozo2 =new Mozo(7,"Imanol","13/10/1980", 5);
		try{
			gestionPersonal.modificaMozo(mozo2, "cantHijos", -4);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
	}
	@Test 
	public void testAltaOperariocaso1() {
		boolean contiene=false;
		gestionPersonal.altaOperario("NahuelNievas", "13/10/1996","Nahuel", "contrasenia");
		ArrayList<Operario> operarios= local.getOperarios();
		
		for(int i=0;i<operarios.size();i++) {
			if(operarios.get(i).getNombreApellido().equals("NahuelNievas") && operarios.get(i).getNacimiento().equals("13/10/1996") && operarios.get(i).getNombreUsuario().equals("Nahuel") && operarios.get(i).getPassword().equals("contrasenia"))
				contiene= true;
		}
		assertTrue("La lista aun contiene el elemento", contiene);
	}
	
	@Test 
	public void testAltaOperariocaso2() {
		try{
			gestionPersonal.altaOperario(null, "14/01/2000","Nahuel", "contrasenia");
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
			
		}
		
	}
	
	@Test 
	public void testAltaOperariocaso3() {
		try{
			gestionPersonal.altaOperario("Nahuel", null,"Nahuel", "contrasenia");
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
		}
	}
	
	@Test 
	public void testAltaOperariocaso4() {
		try{
			gestionPersonal.altaOperario("Nahuel", "14/01/2000",null, "contrasenia");
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
		}
	}
	@Test 
	public void testAltaOperariocaso5() {
		try{
			gestionPersonal.altaOperario("Nahuel", "14/01/2000","Nahuel", null);
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
		}
	}
	
	@Test 
	public void testAltaOperariocaso6() {
		try{
			gestionPersonal.altaOperario("Nahuel", "14/01/2000","Nahuel", "Nah38");
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
		}
	}
	
	
	@Test 
	public void testAltaOperariocaso7() {
		try{
			gestionPersonal.altaOperario("Nahuel", "14/01/2000","Nahuel", "Nahuasdasdael38");
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
		}
	}
	
	@Test 
	public void testAltaOperariocaso8() {
		try{
			gestionPersonal.altaOperario("Nahuel", "14/01/2000","Nahuel", "Nahuelll");
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
		}
	}
	
	@Test 
	public void testAltaOperariocaso9() {
		try{
			gestionPersonal.altaOperario("Nahuel", "14/01/2000","Nahuel", "nahuel38");
			Assert.fail("No deberia seguir con la ejecucion");
		}
		catch(Exception e) {
		}
	}
	
	@After
	public void tearDown(){
		local.getMozos().removeAll(local.getMozos());
		local.getOperarios().removeAll(local.getOperarios());
		Local.elimInstance();
	}
}
