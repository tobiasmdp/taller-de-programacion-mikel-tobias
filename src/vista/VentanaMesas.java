package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaMesas extends JFrame implements IVista{

	private JPanel contentPane;
	private JButton btnNuevaMesa;
	private JButton btnModificaMesa;
	private JButton btnEliminarMesa;
	private JButton btnVolver;
	private JPanel panel;

	public VentanaMesas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));
		
		btnNuevaMesa = new JButton("Nueva Mesa");
		contentPane.add(btnNuevaMesa);
		
		btnModificaMesa = new JButton("Modificar Mesa");
		contentPane.add(btnModificaMesa);
		
		btnEliminarMesa = new JButton("Elminar Mesa");
		contentPane.add(btnEliminarMesa);
		
		panel = new JPanel();
		contentPane.add(panel);
		
		btnVolver = new JButton("Volver");
		this.btnVolver.setActionCommand("VOLVER");
		panel.add(btnVolver);
		
		this.setVisible(true);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnVolver.addActionListener(actionListener);
	}

	@Override
	public void esconder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrar() {
		// TODO Auto-generated method stub
		
	}

}
