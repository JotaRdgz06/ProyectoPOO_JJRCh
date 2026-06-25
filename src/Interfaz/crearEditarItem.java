package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Controladora;
import Logica.Categoria;
import Logica.Item;
import Logica.Tipo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle.Control;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class crearEditarItem extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private Item seEstaEditando;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JLabel nombre_1;
	private JLabel lblDescripcin;
	private JComboBox<Tipo> comboBox;
	private JScrollPane scrollPane;
	private JList<Categoria> list;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;

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
		setTitle("Crear/editar item");
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarItems();
			}
		});
		setModal(true);
		setResizable(false);
		this.seEstaEditando = item;
		setBounds(100, 100, 497, 289);
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
            Integer siguienteCodigo = control.obtenerSiguienteCodigoItem();
            textoCodigo = siguienteCodigo.toString();
        } else {
            textoCodigo = String.valueOf(item.getCodigo()).toString();
        }
		
		lblNewLabel_1 = new JLabel(textoCodigo);
		lblNewLabel_1.setBounds(70, 16, 44, 12);
		contentPanel.add(lblNewLabel_1);
		
		lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(10, 119, 91, 24);
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
		
		comboBox = new JComboBox<>();
		for (Tipo t : control.consultarTipo())
			comboBox.addItem(t);
		comboBox.setBounds(70, 171, 110, 20);
		contentPanel.add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(305, 39, 168, 172);
		contentPanel.add(scrollPane);
		
		list = new JList<>();
		scrollPane.setViewportView(list);
		
		lblNewLabel_3 = new JLabel("Categorias:");
		lblNewLabel_3.setBounds(303, 8, 82, 12);
		contentPanel.add(lblNewLabel_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardarItem();
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
				list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				
				lblNewLabel_4 = new JLabel("(Presione ctrl+clic para seleccionar varias)");
				lblNewLabel_4.setBounds(218, 22, 255, 12);
				contentPanel.add(lblNewLabel_4);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				if (item != null) {
		            textField_1.setText(item.getNombre());
		            textField.setText(seEstaEditando.getDescripcion());
		        }
			}
		}
	}
	
	private void cargarItems() {
		Controladora control = Controladora.getInstance();
		DefaultListModel<Categoria> model = new DefaultListModel<>();
		List<Categoria> listaUsuarios = control.consultarCategoria();
		
		for (Categoria categoria: control.consultarCategoria()) {
			model.addElement(categoria);
		}
		list.setModel(model);
	}
	
	public void guardarItem() {
        String nombre = textField_1.getText().trim();
        String descripcion = textField.getText().trim();
        Tipo tipo = (Tipo) comboBox.getSelectedItem();
        List<Categoria> categorias = list.getSelectedValuesList();
        
        if (tipo == null) {
        	JOptionPane.showMessageDialog(contentPanel, "Debe Seleccionar un tipo", "Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        if (nombre.isEmpty()) {
        	JOptionPane.showMessageDialog(contentPanel, "Debe ingresar un nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (descripcion.isEmpty()) {
        	JOptionPane.showMessageDialog(contentPanel, "Debe ingresar una descripcion", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (categorias.isEmpty()) {
        	JOptionPane.showMessageDialog(contentPanel, "Debe ingresar al menos una categoria", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		Controladora control = Controladora.getInstance();
		try {
			if (seEstaEditando == null) {
				control.crearItem(nombre, descripcion, tipo, categorias);
				JOptionPane.showMessageDialog(contentPanel, "Se ha creado el usuario");
			} else {
				control.modificarItem(seEstaEditando, nombre, descripcion, tipo, categorias);
				JOptionPane.showMessageDialog(contentPanel, "Se ha modificado el usuario");
			}
			dispose();
		} catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
}
