package controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.Observable;
import java.util.Observer;

import capaDeDatos.Comanda;
import capaDeDatos.Mesa;
import capaDeDatos.Operario;
import capaDeNegocios.Local;
import capaDeNegocios.MetodosFacturacion;
import vista.VentanaLogin;
import vista.VentanaOperario;
import vista.VentanaPedido;

@SuppressWarnings("deprecation")
public class ControladorPedido implements ActionListener, Observer {
	private Local modelo;
	private Mesa mesa;
	private Operario operario = null;
	private int tipoUsuario = 0; // 0 = operario, 1 = operarioAdmin;
	private VentanaPedido vista;

	public ControladorPedido(Mesa mesa) {

		this.mesa = mesa;
		
		this.modelo = Local.getInstance(); // arranca el modelo
		this.modelo.addObserver(this);
		
		this.vista = new VentanaPedido(); // arranca la vista
		this.vista.setActionListener(this);

	}

	@Override
	public void update(Observable o, Object arg) { //escucha al modelo
	    	if (o != this.modelo)
	    	    throw new InvalidParameterException();
	  	}

	@Override
	public void actionPerformed(ActionEvent e) { // escucha la vista
		String comando = e.getActionCommand();
		if (comando.equals("ADMINISTRADOR")) {
			this.vista.esconder();
			ControladorOperarioAdmin controladorOperarioAdmin= new ControladorOperarioAdmin();
		}
		else if(comando.equals("CERRAR COMANDA")) {
			
		}
	}

	public VentanaPedido getVista() {
		return vista;
	}
}
