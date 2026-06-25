package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Control.Controladora;
import Logica.Alerta;
import Logica.Alerta.TipoAlerta;
import Logica.Categoria;
import Logica.Item;
import Logica.Prestamo;
import Logica.Tipo;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLabel;

public class pantallaPrincipal {

	private JFrame frmPrestamos;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pantallaPrincipal window = new pantallaPrincipal();
					window.frmPrestamos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pantallaPrincipal() {
		initialize();
		cargarDatos();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrestamos = new JFrame();
		frmPrestamos.setTitle("Prestamos");
		frmPrestamos.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarPrestamos();
			}
		});
		frmPrestamos.setResizable(false);
		frmPrestamos.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				guardarDatos();
			}
			@Override
			public void windowOpened(WindowEvent e) {
				verificarAlerta();
			}
		});
		frmPrestamos.setBounds(100, 100, 450, 289);
		frmPrestamos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrestamos.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 436, 252);
		frmPrestamos.getContentPane().add(tabbedPane);
		
		JPanel prestamo = new JPanel();
		tabbedPane.addTab("Prestamo", null, prestamo, null);
		prestamo.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Crear prestamo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearPrestamo();
			}
		});
		btnNewButton_1.setBounds(10, 200, 130, 20);
		prestamo.add(btnNewButton_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 28, 411, 162);
		prestamo.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Prestado a", "Cant. de items", "Alerta"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(48);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Finalizar prestamo");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarPrestamo();
			}
		});
		btnNewButton_2.setBounds(144, 200, 151, 20);
		prestamo.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Prestamos activos:");
		lblNewLabel.setBounds(10, 10, 130, 12);
		prestamo.add(lblNewLabel);
		
		JPanel administracion = new JPanel();
		tabbedPane.addTab("Administración", null, administracion, null);
		administracion.setLayout(null);
		
		JButton btnNewButton = new JButton("Usuarios");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaUsuarios ventanaDetalleCliente = new pantallaUsuarios();
				ventanaDetalleCliente.setVisible(true);
			}
		});
		btnNewButton.setBounds(155, 10, 111, 33);
		administracion.add(btnNewButton);
		
		JButton btnCategorias = new JButton("Categorias");
		btnCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaCategorias ventanaDetalleCliente = new pantallaCategorias();
				ventanaDetalleCliente.setVisible(true);
			}
		});
		btnCategorias.setBounds(155, 131, 111, 33);
		administracion.add(btnCategorias);
		
		JButton btnItems = new JButton("Ítems");
		btnItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controladora control = Controladora.getInstance();
				List<Categoria> categorias = control.consultarCategoria();
				List<Tipo> tipos = control.consultarTipo();
				if (categorias.isEmpty() || tipos.isEmpty()) {
					JOptionPane.showMessageDialog(frmPrestamos, "Debe crear al menos una categoría y un tipo primero", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				pantallaItems ventanaDetalleCliente = new pantallaItems();
				ventanaDetalleCliente.setVisible(true);
			}
		});
		btnItems.setBounds(155, 72, 111, 33);
		administracion.add(btnItems);
		
		JButton btnTipos = new JButton("Tipos");
		btnTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaTipos ventanaDetalleCliente = new pantallaTipos();
				ventanaDetalleCliente.setVisible(true);
			}
		});
		btnTipos.setBounds(155, 193, 111, 33);
		administracion.add(btnTipos);
		
		JPanel reportes = new JPanel();
		tabbedPane.addTab("Reportes", null, reportes, null);
		reportes.setLayout(null);
		
		JButton btnReportePorUsuario = new JButton("Reporte por usuario");
		btnReportePorUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporteUsuario();
			}
		});
		btnReportePorUsuario.setBounds(126, 10, 167, 33);
		reportes.add(btnReportePorUsuario);
		
		JButton btnReportePorItem = new JButton("Reporte por item");
		btnReportePorItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporteItem();
			}
		});
		btnReportePorItem.setBounds(126, 65, 167, 33);
		reportes.add(btnReportePorItem);
		
		JButton btnReportePorCategora = new JButton("Reporte por categoría");
		btnReportePorCategora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporteCategoria();
			}
		});
		btnReportePorCategora.setBounds(126, 124, 167, 33);
		reportes.add(btnReportePorCategora);
		
		JButton btnReportePorTipo = new JButton("Reporte por tipo");
		btnReportePorTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporteTipo();
			}
		});
		btnReportePorTipo.setBounds(126, 182, 167, 33);
		reportes.add(btnReportePorTipo);
	}
	
	private void cargarPrestamos() {
		Controladora control = Controladora.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		List<Prestamo> listaUsuarios = control.consultarPrestamo();
		
		for (Prestamo prestamo: listaUsuarios) {
			Object[] fila = new Object[] {String.valueOf(prestamo.getCodigo()), prestamo.getUsuario().getNombre(), prestamo.getItems().size(), prestamo.getDescripcionAlerta()};
			model.addRow(fila);
		}
	}
	
	private void crearPrestamo() {
		crearEditarPrestamo ventanaDetalleCliente = new crearEditarPrestamo();
		ventanaDetalleCliente.setVisible(true);
		cargarPrestamos();
	}
	
	private void borrarPrestamo() {
		int numeroFila = table.getSelectedRow();
		if (numeroFila == -1) {
			JOptionPane.showMessageDialog(frmPrestamos, "Debe seleccionar un prestamo", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			Controladora control = Controladora.getInstance();
			Prestamo prestamo = control.consultarPrestamo().get(numeroFila);
			int respuesta = JOptionPane.showConfirmDialog(frmPrestamos, "Se finalizará el prestamo " + prestamo.getCodigo() + " del usuario " + prestamo.getUsuario().getNombre(), "Confirmar", JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				try {
					control.finalizarPrestamo(prestamo);
					cargarPrestamos();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmPrestamos, "Error al finalizar el prestamo", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private void verificarAlerta() {
		Controladora control = Controladora.getInstance();
		List<Prestamo> prestamos = control.consultarPrestamo();
		
		for (Prestamo prestamo : prestamos) {
			if (prestamo.tieneAlerta()) {
				Alerta alerta = prestamo.getAlerta();
				if (alerta.debeActivarse()) {
					JOptionPane.showMessageDialog(frmPrestamos, "Atención: el prestamo del usuario " + prestamo.getUsuario() + " ha finalizado", "Alerta de prestamo", JOptionPane.WARNING_MESSAGE);
					alerta.marcarComoActivada();
				}
			}
		}
	}
	
	private void reporteUsuario() {
		reporteUsuario ventanaDetalleCliente = new reporteUsuario();
		ventanaDetalleCliente.setVisible(true);
	}
	
	private void reporteItem() {
		reporteItem ventanaDetalleCliente = new reporteItem();
		ventanaDetalleCliente.setVisible(true);
	}
	
	private void reporteCategoria() {
		reporteCategoria ventanaDetalleCliente = new reporteCategoria();
		ventanaDetalleCliente.setVisible(true);
	}
	
	private void reporteTipo() {
		reporteTipo ventanaDetalleCliente = new reporteTipo();
		ventanaDetalleCliente.setVisible(true);
	}
	
	private void cargarDatos() {
		try {
			Controladora.cargarDatos();
		} catch (java.io.FileNotFoundException e) {
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmPrestamos, "Error al cargar los datos" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void guardarDatos() {
		try {
			Controladora.guardarDatos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmPrestamos, "Error al guardar los datos: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
