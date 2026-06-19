package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Control.Controladora;
import Logica.Item;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class pantallaItems extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			pantallaItems dialog = new pantallaItems();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public pantallaItems() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 561, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 10, 527, 159);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"C\u00F3digo", "Nombre", "Descripci\u00F3n", "Tipo", "Prestado"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, Object.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						true, true, true, false, true
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(3).setResizable(false);
				table.getColumnModel().getColumn(4).setResizable(false);
				scrollPane.setViewportView(table);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		}
		{
			JButton btnNewButton = new JButton("Crear");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearItem();
				}
			});
			btnNewButton.setBounds(10, 190, 84, 20);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("Editar");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editarItem();
				}
			});
			btnNewButton_1.setBounds(105, 190, 84, 20);
			contentPanel.add(btnNewButton_1);
		}
		{
			JButton btnNewButton_2 = new JButton("Borrar");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrarItem();
				}
			});
			btnNewButton_2.setBounds(199, 190, 84, 20);
			contentPanel.add(btnNewButton_2);
		}
		{
			JButton btnNewButton_3 = new JButton("Asignar categorias");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarCategorias();
				}
			});
			btnNewButton_3.setBounds(293, 190, 140, 20);
			contentPanel.add(btnNewButton_3);
		}
		{
			JButton btnNewButton_4 = new JButton("Asignar tipo");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					agregarTipo();
				}
			});
			btnNewButton_4.setBounds(436, 190, 101, 20);
			contentPanel.add(btnNewButton_4);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void crearItem() {
		crearEditarItem ventanaDetalleCliente = new crearEditarItem();
		ventanaDetalleCliente.setVisible(true);
		cargarItems();
	}
	
	private void cargarItems() {
		Controladora control = Controladora.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<Item> listaUsuarios = control.consultarItem();
		for (Item item: listaUsuarios) {
			Object[] fila = new Object[] {String.valueOf(item.getCodigo()), item.getNombre(), item.getDescripcion(), item.getTipo(), item.estaPrestadoS()};
			model.addRow(fila);
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
					control.borrarItem(item);
					cargarItems();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPanel, "Error al borrar el item, " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private void editarItem() {
		int numeroFila = table.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(contentPanel, "Debe seleccionar una categoria", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			Item item = Controladora.getInstance().consultarItem().get(numeroFila);
			crearEditarItem ventana = new crearEditarItem(item);
			ventana.setVisible(true); 
			cargarItems();
		}
	}
	
	private void agregarCategorias() {
		pantallaCategorias ventanaDetalleCliente = new pantallaCategorias();
		ventanaDetalleCliente.setVisible(true);
		cargarItems();
	}
	
	private void agregarTipo() {
		pantallaTipos ventanaDetalleCliente = new pantallaTipos();
		ventanaDetalleCliente.setVisible(true);
		cargarItems();
	}
	
	private void cargarDatos() {
		try {
			Controladora.cargarDatos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Error al cargar los datos" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void guardarDatos() {
		try {
			Controladora.guardarDatos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
