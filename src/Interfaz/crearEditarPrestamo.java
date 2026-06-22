package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controladora;
import Logica.Categoria;
import Logica.Prestamo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class crearEditarPrestamo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private Prestamo seEstaEditando;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			crearEditarUsuario dialog = new crearEditarUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	
	public crearEditarPrestamo() {
		this(null);
	}
	
	public crearEditarPrestamo(Prestamo prestamo) {
		setModal(true);
		setResizable(false);
		this.seEstaEditando = prestamo;
		setBounds(100, 100, 305, 189);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(10, 10, 50, 24);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblCorreo = new JLabel("Nombre");
			lblCorreo.setBounds(10, 67, 50, 24);
			contentPanel.add(lblCorreo);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(70, 70, 156, 18);
			contentPanel.add(textField_1);
		}
		
		Controladora control = Controladora.getInstance();
        String textoCodigo;
        if (seEstaEditando == null) {
            Integer siguienteCodigo = control.obtenerSiguienteCodigoCategoria();
            textoCodigo = siguienteCodigo.toString();
        } else {
            textoCodigo = String.valueOf(prestamo.getCodigo()).toString();
        }
		
		lblNewLabel_1 = new JLabel(textoCodigo);
		lblNewLabel_1.setBounds(70, 16, 44, 12);
		contentPanel.add(lblNewLabel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarPrestamo();
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
				
				if (prestamo != null) {
					lblNewLabel_1.setText(String.valueOf(prestamo.getCodigo()));
		            textField_1.setText(prestamo.getUsuario().getNombre());
		        }
			}
		}
	}
	
	public void guardarPrestamo() {
        String nombre = textField_1.getText().trim();
        
        if (nombre.isEmpty()) {
        	JOptionPane.showMessageDialog(contentPanel, "Debe ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		Controladora control = Controladora.getInstance();
		try {
			if (seEstaEditando == null) {
				control.crearCategoria(nombre);
				JOptionPane.showMessageDialog(contentPanel, "Se ha creado el usuario");
			} else {
				control.modificarPrestamo(seEstaEditando, nombre);
				JOptionPane.showMessageDialog(contentPanel, "Se ha modificado el usuario");
			}
			dispose();
		} catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
}
