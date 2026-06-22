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
import Logica.Prestamo;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class pantallaPrincipal {

	private JFrame frame;
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
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarPrestamos();
			}
		});
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				guardarDatos();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 436, 263);
		frame.getContentPane().add(tabbedPane);
		
		JPanel prestamo = new JPanel();
		tabbedPane.addTab("Prestamo", null, prestamo, null);
		prestamo.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Crear prestamo");
		btnNewButton_1.setBounds(291, 206, 130, 20);
		prestamo.add(btnNewButton_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 411, 180);
		prestamo.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Tipo", "Fecha finalizaci\u00F3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
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
		btnNewButton_2.setBounds(10, 206, 137, 20);
		prestamo.add(btnNewButton_2);
		
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
	}
	
	private void cargarDatos() {
		try {
			Controladora.cargarDatos();
		} catch (java.io.FileNotFoundException e) {
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error al cargar los datos" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void guardarDatos() {
		try {
			Controladora.guardarDatos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error al guardar los datos: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
