package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.Controladora;
import Logica.Item;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class agregarItemsPrestamo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Item itemSeleccionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			agregarItemsPrestamo dialog = new agregarItemsPrestamo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public agregarItemsPrestamo() {
		setResizable(false);
		setModal(true);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarItems();
			}
		});
		setBounds(100, 100, 396, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 362, 199);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						seleccionarItem();
						guardarDatos();
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

	private void cargarItems() {
		Controladora control = Controladora.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<Item> listaUsuarios = control.consultarItem();
		for (Item item: listaUsuarios) {
			if (!item.estaPrestado()) {
				Object[] fila = new Object[] {String.valueOf(item.getNombre())};
				model.addRow(fila);
			}
		}
	}
	
	private void seleccionarItem() {
	    int numeroFila = table.getSelectedRow();
	    if (numeroFila == -1) {
	        JOptionPane.showMessageDialog(contentPanel, "Debe seleccionar un item", "Error", JOptionPane.ERROR_MESSAGE);
	    } else {
	    	Controladora control = Controladora.getInstance();
	    	List<Item> todosLosItems = control.consultarItem();
	    	List<Item> itemsDisponibles = new ArrayList<>();
	        for (Item item : todosLosItems) {
	            if (!item.estaPrestado()) {
	                itemsDisponibles.add(item);
	            }
	        }
	        itemSeleccionado = itemsDisponibles.get(numeroFila);
	        itemSeleccionado.marcarComoPrestado();
	        dispose();
	    }
	}
	
	public Item getItemSeleccionado() {
	    return itemSeleccionado;
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
