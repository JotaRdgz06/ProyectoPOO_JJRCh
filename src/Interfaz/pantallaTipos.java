package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

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
import Logica.Categoria;

public class pantallaTipos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			pantallaTipos dialog = new pantallaTipos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public pantallaTipos() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 10, 416, 166);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"C\u00F3digo", "Nombre"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(1).setResizable(false);
				scrollPane.setViewportView(table);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		}
		{
			JButton btnNewButton = new JButton("Crear");
			btnNewButton.setBounds(10, 202, 84, 20);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("Editar");
			btnNewButton_1.setBounds(182, 202, 84, 20);
			contentPanel.add(btnNewButton_1);
		}
		{
			JButton btnNewButton_2 = new JButton("Borrar");
			btnNewButton_2.setBounds(342, 202, 84, 20);
			contentPanel.add(btnNewButton_2);
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
	
	private void crearCategoria() {
		crearEditarCategoria ventanaDetalleCliente = new crearEditarCategoria();
		ventanaDetalleCliente.setVisible(true);
		cargarCategorias();
	}
	
	private void cargarCategorias() {
		Controladora control = Controladora.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<Categoria> listaUsuarios = control.consultarCategoria();
		for (Categoria categoria: listaUsuarios) {
			Object[] fila = new Object[] {String.valueOf(categoria.getCodigo()), categoria.getNombre()};
			model.addRow(fila);
		}
	}
	
	private void borrarCategoria() {
		int numeroFila = table.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(contentPanel, "Debe seleccionar una categoria", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			Controladora control = Controladora.getInstance();
			Categoria categoria= control.consultarCategoria().get(numeroFila);
			int respuesta = JOptionPane.showConfirmDialog(contentPanel, "Se eliminará la categoria " + categoria.getNombre(), "Confirmar", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				try {
					control.borrarCategoria(categoria);
					cargarCategorias();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(contentPanel, "Error al borrar la categoria, " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private void editarCategoria() {
		int numeroFila = table.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(contentPanel, "Debe seleccionar una categoria", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			Categoria categoria = Controladora.getInstance().consultarCategoria().get(numeroFila);
			crearEditarCategoria ventana = new crearEditarCategoria(categoria);
			ventana.setVisible(true); 
			cargarCategorias();
		}
	}
	
	private void cargarDatos() {
		try {
			Controladora.cargarDatos();
		} catch (java.io.FileNotFoundException e) {
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Error al cargar los datos" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void guardarDatos() {
		try {
			Controladora.guardarDatos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPanel, "Error al guardar los datos: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
