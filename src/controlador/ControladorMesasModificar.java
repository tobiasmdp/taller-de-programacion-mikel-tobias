package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import capaDeDatos.Operario;
import capaDeNegocios.ConfiguracionDeSistema;
import capaDeNegocios.Local;
import vista.VentanaMesas;
import vista.VentanaMesasNueva;
import vista.VentanaOperarioAdmin;

public class ControladorMesasModificar implements ActionListener, Observer{
	private Local modelo;
	private VentanaMesasNueva vista;
	
	public ControladorMesasModificar() {

		this.modelo = Local.getInstance(); // arranca el modelo
		this.modelo.addObserver(this);
		
		this.vista = new VentanaMesasNueva(); // arranca la vista
		this.vista.setActionListener(this);

	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		if (comando.equals("ACTIVO")) {
			ConfiguracionDeSistema.getInstance().modificaMesa(null, "estado", "activo");// en el null va la que consigo cliqueando de la lista
		}
		else if (comando.equals("INACTIVO")) {
			ConfiguracionDeSistema.getInstance().modificaMesa(null, "estado", "inactivo");
			}
		else if (comando.equals("VOLVER")) {
			this.vista.esconder();
			ControladorMesas controladorMesas = new ControladorMesas();
		}
	}

}
