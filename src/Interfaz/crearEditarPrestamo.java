package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controladora;
import Logica.Alerta;
import Logica.Categoria;
import Logica.Item;
import Logica.Prestamo;
import Logica.Tipo;
import Logica.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private List<Item> itemsAgregados = new ArrayList<>();
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
	
	public crearEditarPrestamo() {
		this(null);
	}
	
	public crearEditarPrestamo(Prestamo prestamo) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
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
			comboBox_1 = new JComboBox<>(new String[]{"Sin alerta", "Una vez", "Repetido"});
			comboBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String alertaSeleccionada = (String) comboBox_1.getSelectedItem();
					if (alertaSeleccionada.equals("Sin alerta")) {
			            lblNewLabel_4.setText("minutos (ignorado)");
			        } else {
			            lblNewLabel_4.setText("minutos");
			        }
				}
			});
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
		
		lblNewLabel_4 = new JLabel("minutos");
		lblNewLabel_4.setBounds(176, 173, 162, 12);
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
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarItem();
			}
		});
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
						for (Item item : itemsAgregados) {
				            item.marcarComoLibre();
				        }
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
		comboBox_1.setSelectedIndex(0);
	}
	
	public void guardarPrestamo() {
		Usuario usuario = (Usuario) comboBox.getSelectedItem();
        
        if (usuario == null) {
        	JOptionPane.showMessageDialog(contentPanel, "Debe seleccionar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (itemsAgregados.isEmpty()) {
            JOptionPane.showMessageDialog(contentPanel, "Debe agregar al menos un item", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String tipoAlertaSeleccionado = (String) comboBox_1.getSelectedItem();
        
        if (tipoAlertaSeleccionado.equals("Sin alerta")) {
        	lblNewLabel_4.setText("minutos (si ingresa algo será ignorado)");
        }
        try {
            Controladora control = Controladora.getInstance();
            Prestamo nuevoPrestamo = control.crearPrestamo(usuario);
            for (Item item : itemsAgregados) {
                nuevoPrestamo.agregarItem(item);
            }
            JOptionPane.showMessageDialog(contentPanel, "Se ha creado el prestamo");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(contentPanel, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public void agregarItem() {
		agregarItemsPrestamo ventana = new agregarItemsPrestamo();
		ventana.setVisible(true);
	    Item itemElegido = ventana.getItemSeleccionado();
	    if (itemElegido != null) {
	        try {
	        	itemElegido.marcarComoPrestado();
	        	itemsAgregados.add(itemElegido);
	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            model.addRow(new Object[]{itemElegido.getNombre()});
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(contentPanel, "Error: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	private void borrarItem() {
		int numeroFila = table.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(contentPanel, "Debe seleccionar un item", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			Controladora control = Controladora.getInstance();
			Item item= control.consultarItem().get(numeroFila);
			int respuesta = JOptionPane.showConfirmDialog(contentPanel, "Se eliminará el item " + item.getNombre(), "Confirmar", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				try {
					Item itemBorrar = itemsAgregados.get(numeroFila);
					itemBorrar.marcarComoLibre();
					itemsAgregados.remove(numeroFila);
					((DefaultTableModel) table.getModel()).removeRow(numeroFila);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPanel, "Error al borrar el item, " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
