package vista;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class VentanaMesasModificar extends JFrame implements IVista {
	private JButton btnVolver;
	private JPanel panelVolver;
	private JList listMesas;
	private JLabel lblEstado;
	private JButton btnActivo;
	private JButton btnInactivo;
	private JPanel panelMesas;

	public VentanaMesasModificar() {
		getContentPane().setLayout(new GridLayout(2, 0, 0, 0));
		
		panelMesas = new JPanel();
		panelMesas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mesas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelMesas);
		
		listMesas = new JList();
		panelMesas.add(listMesas);
		
		panelVolver = new JPanel();
		getContentPane().add(panelVolver);
		
		lblEstado = new JLabel("Estado:");
		panelVolver.add(lblEstado);
		
		JButton btnActivo = new JButton("Activo");
		panelVolver.add(btnActivo);
		this.btnActivo.setActionCommand("ACTIVO");
		
		btnInactivo = new JButton("Inactivo");
		panelVolver.add(btnInactivo);
		this.btnInactivo.setActionCommand("INACTIVO");
		
		btnVolver = new JButton("Volver");
		panelVolver.add(btnVolver);
		btnVolver.setActionCommand("VOLVER");
		
		this.setVisible(true);
	}


	@Override
	public void setActionListener(ActionListener actionListener) {
		btnActivo.addActionListener(actionListener);
		btnInactivo.addActionListener(actionListener);
		btnVolver.addActionListener(actionListener);
		
	}

	@Override
	public void esconder() {
		this.setVisible(false);
	}

	@Override
	public void mostrar() {
		this.setVisible(true);		
	}
}
