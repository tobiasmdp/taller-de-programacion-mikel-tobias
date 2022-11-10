package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

import capaDeDatos.Factura;
import capaDeDatos.Mesa;
import capaDeDatos.Pedido;
import capaDeDatos.Producto;
import capaDeNegocios.Local;

import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class VentanaPedido extends JFrame implements IVista{

	private ActionListener actionListener;
	private JList<Producto> listProductos;
	private DefaultListModel<Producto> productos= new DefaultListModel<Producto>(); //modelo del arrayList 
	private JPanel panelProductos;
	private JPanel panelBotones;
	private JList list;
	private JLabel lblCantidad;
	private JTextField txtTexto;
	private JButton btnAceptar;
	private JPanel panelCantidad;
	private JPanel panelTexto;
	private JPanel panelAceptar;


	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	/**
	 * Create the frame.
	 */
	public VentanaPedido() {
		
		productos.addAll(Local.getInstance().getProductos());
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.panelProductos = new JPanel();
		getContentPane().add(this.panelProductos, BorderLayout.NORTH);
		
		this.listProductos = new JList<Producto>();
		this.panelProductos.add(this.listProductos);
		listProductos.setModel(productos);
		
		this.panelBotones = new JPanel();
		getContentPane().add(this.panelBotones, BorderLayout.SOUTH);
		this.panelBotones.setLayout(new GridLayout(0, 3, 0, 0));
		
		this.panelCantidad = new JPanel();
		this.panelBotones.add(this.panelCantidad);
		
		this.lblCantidad = new JLabel("Cantidad:");
		this.panelCantidad.add(this.lblCantidad);
		
		this.panelTexto = new JPanel();
		this.panelBotones.add(this.panelTexto);
		
		this.txtTexto = new JTextField();
		this.panelTexto.add(this.txtTexto);
		this.txtTexto.setColumns(10);
		
		this.panelAceptar = new JPanel();
		this.panelBotones.add(this.panelAceptar);
		
		this.btnAceptar = new JButton("Aceptar");
		this.panelAceptar.add(this.btnAceptar);
		
		this.setVisible(true);
	}

	@Override
	public void esconder() {
		this.setVisible(false);
	}

	@Override
	public void mostrar() {
		this.setVisible(true);		
	}
	
	public Producto getProductoSeleccionado() {
		return this.listProductos.getSelectedValue();
	}

}
