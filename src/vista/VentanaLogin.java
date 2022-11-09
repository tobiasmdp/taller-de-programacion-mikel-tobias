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
public class VentanaLogin extends JFrame implements IVista, MouseListener{

	private ActionListener actionListener;

	private JTextField txtUsuario;
	private JButton btnLogin;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblUsuario;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblPasswordError;
	private JPanel panel_5;
	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JPanel panel_4; 
	private JPanel panel_6;
	private JPanel panel_7;
	private JLabel lblNewLabel;
	private JLabel lblUserError;

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		panel_1 = new JPanel();
		panel.add(panel_1);

		lblUsuario = new JLabel(" Usuario");
		panel_1.add(lblUsuario);

		panel_2 = new JPanel();
		panel.add(panel_2);

		txtUsuario = new JTextField();
		panel_2.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		this.panel_6 = new JPanel();
		this.panel.add(this.panel_6);
		
		this.lblNewLabel = new JLabel("error user");
		this.lblNewLabel.setVisible(false);
		this.lblNewLabel.setForeground(Color.RED);
		this.panel_6.add(this.lblNewLabel);

		panel_3 = new JPanel();
		panel.add(panel_3);

		lblPasswordError = new JLabel("Password");
		panel_3.add(lblPasswordError);
		
		this.panel_4 = new JPanel();
		this.panel.add(this.panel_4);
		
		this.txtPassword = new JPasswordField();
		this.txtPassword.setColumns(10);
		this.panel_4.add(this.txtPassword);
		
		this.panel_7 = new JPanel();
		this.panel.add(this.panel_7);
		
		this.lblUserError = new JLabel("error passwowrd");
		this.lblUserError.setVisible(false);
		this.lblUserError.setForeground(Color.RED);
		
		this.panel_7.add(this.lblUserError);

		panel_5 = new JPanel();
		panel.add(panel_5);

		btnLogin = new JButton("Login");
		this.btnLogin.addMouseListener(this);
		btnLogin.setVerticalAlignment(SwingConstants.TOP);
		panel_5.add(btnLogin);
		
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

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public JLabel getLblPasswordError() {
		return lblPasswordError;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	
	

	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	@SuppressWarnings("deprecation")
	public void mouseReleased(MouseEvent e) {
		ActionEvent evento;
		String comando;
		String nombreUsuario = "";
		String password = "";
		JButton boton = (JButton) e.getSource();
		try {
			nombreUsuario = this.txtUsuario.getText();
			password = this.txtPassword.getText();
		} catch (Exception exception) {
		}
		if (!nombreUsuario.equals("") && !password.equals("")){
			comando = "LOGIN";
			evento = new ActionEvent(boton,0,comando);
			this.actionListener.actionPerformed(evento); //el action listener va a ser la referencia l controlador
		}
	} 
}
