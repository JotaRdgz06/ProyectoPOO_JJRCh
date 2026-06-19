package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controladora;
import Logica.Tipo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class crearEditarTipo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private Tipo seEstaEditando;
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
	
	public crearEditarTipo() {
		this(null);
	}
	
	public crearEditarTipo(Tipo tipo) {
		setModal(true);
		setResizable(false);
		this.seEstaEditando = tipo;
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
            Integer siguienteCodigo = control.obtenerSiguienteCodigoTipo();
            textoCodigo = siguienteCodigo.toString();
        } else {
            textoCodigo = String.valueOf(tipo.getCodigo()).toString();
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
						guardarUsuario();
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
				
				if (tipo != null) {
					lblNewLabel_1.setText(String.valueOf(tipo.getCodigo()));
		            textField_1.setText(tipo.getNombre());
		        }
			}
		}
	}
	
	public void guardarUsuario() {
        String nombre = textField_1.getText().trim();
        
        if (nombre.isEmpty()) {
        	JOptionPane.showMessageDialog(contentPanel, "Debe ingresar el nombre del tipo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		Controladora control = Controladora.getInstance();
		try {
			if (seEstaEditando == null) {
				control.crearTipo(nombre);
				JOptionPane.showMessageDialog(contentPanel, "Se ha creado el tipo");
			} else {
				control.modificarTipo(seEstaEditando, nombre);
				JOptionPane.showMessageDialog(contentPanel, "Se ha modificado el tipo");
			}
			dispose();
		} catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
}
