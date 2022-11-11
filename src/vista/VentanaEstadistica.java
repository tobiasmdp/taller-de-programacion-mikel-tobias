package vista;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;

public class VentanaEstadistica extends JFrame implements IVista {

	private JPanel contentPane;
	private ActionListener actionListener;
	private JPanel panel_13;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblMaxVentas;
	private JPanel panel_2;
	private JTextArea areaMaxVentas;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblMaxPromedio;
	private JPanel panel_5;
	private JTextArea areaMaxPromedio;
	private JPanel panel_6;
	private JPanel panel_7;
	private JLabel lblMinVentas;
	private JPanel panel_8;
	private JTextArea areaMinVentas;
	private JPanel panel_9;
	private JPanel panel_10;
	private JLabel lblMinPromedio;
	private JPanel panel_11;
	private JTextArea areaMinPromedio;
	
	public VentanaEstadistica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		this.panel_13 = new JPanel();
		this.contentPane.add(this.panel_13);
		this.panel_13.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.panel = new JPanel();
		this.panel_13.add(this.panel);
		this.panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1);
		
		this.lblMaxVentas = new JLabel("Mayor Cantidad de ventas");
		this.panel_1.add(this.lblMaxVentas);
		
		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);
		
		this.areaMaxVentas = new JTextArea();
		this.panel_2.add(this.areaMaxVentas);
		
		this.panel_3 = new JPanel();
		this.panel_13.add(this.panel_3);
		this.panel_3.setLayout(new GridLayout(2, 1, 0, 0));
		
		this.panel_4 = new JPanel();
		this.panel_3.add(this.panel_4);
		
		this.lblMaxPromedio = new JLabel("Mayor Consumo promedio");
		this.panel_4.add(this.lblMaxPromedio);
		
		this.panel_5 = new JPanel();
		this.panel_3.add(this.panel_5);
		
		this.areaMaxPromedio = new JTextArea();
		this.panel_5.add(this.areaMaxPromedio);
		
		this.panel_6 = new JPanel();
		this.panel_13.add(this.panel_6);
		this.panel_6.setLayout(new GridLayout(2, 1, 0, 0));
		
		this.panel_7 = new JPanel();
		this.panel_6.add(this.panel_7);
		
		this.lblMinVentas = new JLabel("Menor cantidad de ventas");
		this.panel_7.add(this.lblMinVentas);
		
		this.panel_8 = new JPanel();
		this.panel_6.add(this.panel_8);
		
		this.areaMinVentas = new JTextArea();
		this.panel_8.add(this.areaMinVentas);
		
		this.panel_9 = new JPanel();
		this.panel_13.add(this.panel_9);
		this.panel_9.setLayout(new GridLayout(2, 1, 0, 0));
		
		this.panel_10 = new JPanel();
		this.panel_9.add(this.panel_10);
		
		this.lblMinPromedio = new JLabel("Menor Consumo promedio");
		this.panel_10.add(this.lblMinPromedio);
		
		this.panel_11 = new JPanel();
		this.panel_9.add(this.panel_11);
		
		this.areaMinPromedio = new JTextArea();
		this.panel_11.add(this.areaMinPromedio);
		
		this.setVisible(true);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		
		this.actionListener = actionListener;
	}

	@Override
	public void esconder() {
		this.setVisible(false);
	}

	@Override
	public void mostrar() {
		this.setVisible(true); 
	}
	
	public void setMaxVentas(String cadena)
	{
		this.areaMaxVentas.setText(cadena);
	}
	
	public void setMinVentas(String cadena)
	{
		this.areaMinVentas.setText(cadena);
	}
	
	public void setMaxPromedio(String cadena)
	{
		this.areaMaxPromedio.setText(cadena);
	}
	
	public void setMinPromedio(String cadena)
	{
		this.areaMinPromedio.setText(cadena);
	}
	
	
}
