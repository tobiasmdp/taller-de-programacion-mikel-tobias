package controlador;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.Observable;
import java.util.Observer;

import capaDeDatos.Operario;
import capaDeNegocios.Local;
import vista.VentanaLogin;
import vista.VentanaOperario;

@SuppressWarnings("deprecation")
public class ControladorOperario implements ActionListener, Observer {
	private Local modelo;
	private Operario operario = null;
	private int tipoUsuario = 0; // 0 = operario, 1 = operarioAdmin;
	private VentanaOperario vista;

	public ControladorOperario() {

		this.modelo = Local.getInstance(); // arranca el modelo
		this.modelo.addObserver(this);
		
		this.vista = new VentanaOperario(); // arranca la vista
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

	}
}
