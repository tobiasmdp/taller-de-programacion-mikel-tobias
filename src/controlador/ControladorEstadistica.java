package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import capaDeNegocios.Local;
import vista.VentanaEstadistica;
import vista.VentanaMesas;

public class ControladorEstadistica implements ActionListener, Observer{
	private Local modelo;
	private VentanaEstadistica vista;
	
	public ControladorEstadistica()
	{
		this.modelo = Local.getInstance(); // arranca el modelo
		this.modelo.addObserver(this);
		
		this.vista = new VentanaEstadistica(); // arranca la vista
		this.vista.setActionListener(this);
		
		this.vista.setMaxPromedio(null);
		this.vista.setMinPromedio(null);
		this.vista.setMaxVentas(null);
		this.vista.setMinVentas(null);

	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		if (comando.equals("VOLVER")) {
			this.vista.esconder();
			ControladorOperarioAdmin controladorOperarioAdmin = new ControladorOperarioAdmin();
		}
	}

}
