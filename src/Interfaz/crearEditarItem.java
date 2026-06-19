package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controladora;
import Logica.Categoria;
import Logica.Item;
import Logica.Tipo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class crearEditarItem extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private Item seEstaEditando;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JLabel nombre_1;
	private JLabel lblDescripcin;
	private JComboBox comboBox;

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
	
	public crearEditarItem() {
		this(null);
	}
	
	public crearEditarItem(Item item) {
		setModal(true);
		setResizable(false);
		this.seEstaEditando = item;
		setBounds(100, 100, 305, 289);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(10, 10, 50, 24);
		contentPanel.add(lblNewLabel);
		{
			nombre_1 = new JLabel("Nombre");
			nombre_1.setBounds(10, 67, 50, 24);
			contentPanel.add(nombre_1);
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
            textoCodigo = String.valueOf(item.getCodigo()).toString();
        }
		
		lblNewLabel_1 = new JLabel(textoCodigo);
		lblNewLabel_1.setBounds(70, 16, 44, 12);
		contentPanel.add(lblNewLabel_1);
		
		lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(10, 119, 66, 24);
		contentPanel.add(lblDescripcin);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(86, 122, 195, 18);
		contentPanel.add(textField);
		{
			JLabel lblNewLabel_2 = new JLabel("Tipo");
			lblNewLabel_2.setBounds(10, 175, 44, 12);
			contentPanel.add(lblNewLabel_2);
		}
		
		comboBox = new JComboBox();
		comboBox.setBounds(70, 171, 28, 20);
		contentPanel.add(comboBox);
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
				
				if (item != null) {
					lblNewLabel_1.setText(String.valueOf(item.getCodigo()));
		            textField_1.setText(item.getNombre());
		        }
			}
		}
	}
	
	public void guardarUsuario() {
        String nombre = textField_1.getText().trim();
        String descripcion = lblDescripcin.getText().trim();
        String tipo = comboBox.getSelectedIndex();
        
        if (nombre.isEmpty()) {
        	JOptionPane.showMessageDialog(contentPanel, "Debe ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		Controladora control = Controladora.getInstance();
		try {
			if (seEstaEditando == null) {
				control.crearItem(nombre, descripcion, tipo);
				JOptionPane.showMessageDialog(contentPanel, "Se ha creado el usuario");
			} else {
				control.modificarItem(seEstaEditando, nombre);
				JOptionPane.showMessageDialog(contentPanel, "Se ha modificado el usuario");
			}
			dispose();
		} catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
}
