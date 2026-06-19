package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controladora;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class crearEditarUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			crearEditarUsuario dialog = new crearEditarUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public crearEditarUsuario() {
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 305, 224);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 10, 50, 24);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(70, 13, 156, 18);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			JLabel lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(10, 67, 50, 24);
			contentPanel.add(lblCorreo);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(70, 70, 156, 18);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblTelfono = new JLabel("Teléfono:");
			lblTelfono.setBounds(10, 122, 66, 24);
			contentPanel.add(lblTelfono);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(70, 125, 156, 18);
			contentPanel.add(textField_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nombre   = textField.getText().trim();
		                String correo   = textField_1.getText().trim();
		                String telefono = textField_2.getText().trim();
		                
		                if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
		                	JOptionPane.showMessageDialog(contentPanel, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }
						Controladora control = Controladora.getInstance();
						control.crearUsuario(nombre, telefono, correo);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
