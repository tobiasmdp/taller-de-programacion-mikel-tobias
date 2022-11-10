package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class VentanaOperario extends JFrame implements IVista, ActionListener{

	private ActionListener actionListener;
	private JButton btnAdmin;
	private JList listFaturas;
	private JButton btnNuevoPedido;
	private JPanel panelIzquierdaAbajo;
	private JList listMesas;
	private JPanel panelFacturas;
	private JPanel panelMesasDisponibles;
	private JPanel panelCerrarComanda;
	private JPanel panelNuevoPedido;
	private JButton btnCerrarComanda;
	private JPanel panelAdministrador;

	public void setActionListener(ActionListener actionListener) {
		this.btnAdmin.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	/**
	 * Create the frame.
	 */
	public VentanaOperario() {
		getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
		
		panelMesasDisponibles = new JPanel();
		panelMesasDisponibles.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mesas Disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelMesasDisponibles);
		
		listMesas = new JList();
		listMesas.setToolTipText("");
		panelMesasDisponibles.add(listMesas);
		
		panelFacturas = new JPanel();
		panelFacturas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Facturas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panelFacturas);
		
		listFaturas = new JList();
		listFaturas.setVisibleRowCount(18);
		panelFacturas.add(listFaturas);
		
		panelNuevoPedido = new JPanel();
		getContentPane().add(panelNuevoPedido);
		
		btnNuevoPedido = new JButton("Nuevo Pedido");
		panelNuevoPedido.add(btnNuevoPedido);
		
		panelIzquierdaAbajo = new JPanel();
		getContentPane().add(panelIzquierdaAbajo);
		panelIzquierdaAbajo.setLayout(new GridLayout(2, 0, 0, 0));
		
		panelCerrarComanda = new JPanel();
		panelIzquierdaAbajo.add(panelCerrarComanda);
		
		btnCerrarComanda = new JButton("Cerrar Comanda");
		btnCerrarComanda.addActionListener(this);
		panelCerrarComanda.add(btnCerrarComanda);
		
		panelAdministrador = new JPanel();
		panelIzquierdaAbajo.add(panelAdministrador);
		
		btnAdmin = new JButton("Administrador");
		panelAdministrador.add(btnAdmin);
		btnAdmin.addActionListener(this);
		btnAdmin.setActionCommand("ADMINISTRADOR");
		
		this.setVisible(true);
	}


	public JButton getBtnAdmin() {
		return btnAdmin;
	}

	@Override
	public void esconder() {
		this.setVisible(false);
	}

	@Override
	public void mostrar() {
		this.setVisible(true);		
	}
	
	public void actionPerformed(ActionEvent e) {
	}
}
