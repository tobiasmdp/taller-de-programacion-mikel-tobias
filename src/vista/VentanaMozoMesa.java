package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class VentanaMozoMesa extends JFrame {

	private JPanel contentPane;
	private JList<Mesa> listMesa;
	private JList<Mozo> listMozo;
	private JButton btnCrear;
	private JButton btnCancelar;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;

	public VentanaMozoMesa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		this.contentPane.setLayout(new GridLayout(2, 2, 0, 0));
		
		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mesa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.contentPane.add(this.panel);
		
		this.listMesa = new JList();
		this.panel.add(this.listMesa);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Mozo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.contentPane.add(this.panel_1);
		
		this.listMozo = new JList();
		this.panel_1.add(this.listMozo);
		
		this.panel_2 = new JPanel();
		this.contentPane.add(this.panel_2);
		
		this.btnCrear = new JButton("Crear");
		this.panel_2.add(this.btnCrear);
		
		this.panel_3 = new JPanel();
		this.contentPane.add(this.panel_3);
		
		this.btnCancelar = new JButton("Cancelar");
		this.panel_3.add(this.btnCancelar);
	}

}
