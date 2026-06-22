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
import Logica.Prestamo;
import Logica.Tipo;
import Logica.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class crearEditarPrestamo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Prestamo seEstaEditando;
	private JLabel lblNewLabel_1;
	private JComboBox<Usuario> comboBox;
	private JTable table;
	private JComboBox comboBox_1;
	private JTextField textField;

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
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarItems();
			}
		});
		setModal(true);
		setResizable(false);
		this.seEstaEditando = prestamo;
		setBounds(100, 100, 476, 258);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(10, 10, 50, 24);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblCorreo = new JLabel("Usuario:");
			lblCorreo.setBounds(10, 67, 61, 24);
			contentPanel.add(lblCorreo);
		}
		
		Controladora control = Controladora.getInstance();
        String textoCodigo;
        if (seEstaEditando == null) {
            Integer siguienteCodigo = control.obtenerSiguienteCodigoPrestamo();
            textoCodigo = siguienteCodigo.toString();
        } else {
            textoCodigo = String.valueOf(prestamo.getCodigo()).toString();
        }
		
		lblNewLabel_1 = new JLabel(textoCodigo);
		lblNewLabel_1.setBounds(70, 16, 44, 12);
		contentPanel.add(lblNewLabel_1);
		
		comboBox = new JComboBox<>();
		for (Usuario u : control.consultarUsuario())
			comboBox.addItem(u);
		comboBox.setBounds(65, 69, 88, 20);
		contentPanel.add(comboBox);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(195, 15, 257, 125);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Items agregados:"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				scrollPane.setViewportView(table);
			}
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Alerta");
			lblNewLabel_2.setBounds(10, 122, 50, 12);
			contentPanel.add(lblNewLabel_2);
		}
		{
			comboBox_1 = new JComboBox();
			comboBox_1.setBounds(65, 120, 88, 20);
			contentPanel.add(comboBox_1);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Activar en:");
			lblNewLabel_3.setBounds(10, 168, 69, 22);
			contentPanel.add(lblNewLabel_3);
		}
		
		textField = new JTextField();
		textField.setBounds(70, 170, 96, 18);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("minutos");
		lblNewLabel_4.setBounds(176, 173, 50, 12);
		contentPanel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Agregar item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarItem();
				
			}
		});
		btnNewButton.setBounds(222, 150, 116, 20);
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Borrar item");
		btnNewButton_1.setBounds(348, 150, 104, 20);
		contentPanel.add(btnNewButton_1);
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
		        }
			}
		}
	}
	
	public void guardarPrestamo() {
		Usuario usuario = (Usuario) comboBox.getSelectedItem();
        
        if (usuario == null) {
        	JOptionPane.showMessageDialog(contentPanel, "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		Controladora control = Controladora.getInstance();
		try {
			
			JOptionPane.showMessageDialog(contentPanel, "Se ha creado prestamo");
			dispose();
		} catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void agregarItem() {
		pantallaItems ventanaDetalleCliente = new pantallaItems();
		ventanaDetalleCliente.setVisible(true);
		cargarItems();
	}
	
	private void cargarItems() {
		Controladora control = Controladora.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<Item> listaUsuarios = control.consultarItem();
		for (Item item: listaUsuarios) {
			Object[] fila = new Object[] {String.valueOf(item.getNombre())};
			model.addRow(fila);
		}
	}
}
