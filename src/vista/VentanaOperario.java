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

@SuppressWarnings("serial")
public class VentanaOperario extends JFrame implements IVista{

	private ActionListener actionListener;
	private JLabel lblNewLabel;

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	/**
	 * Create the frame.
	 */
	public VentanaOperario() {
		
		this.lblNewLabel = new JLabel("ES SOLO OPERARIO");
		getContentPane().add(this.lblNewLabel, BorderLayout.CENTER);
		
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
	
}
