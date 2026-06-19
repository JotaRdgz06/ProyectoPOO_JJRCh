package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import Control.Controladora;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class pantallaPrincipal {

	private JFrame frame;

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
		
		JPanel administracion = new JPanel();
		tabbedPane.addTab("Administración", null, administracion, null);
		administracion.setLayout(null);
		
		JButton btnNewButton = new JButton("Usuarios");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(155, 10, 111, 33);
		administracion.add(btnNewButton);
		
		JButton btnCategorias = new JButton("Categorias");
		btnCategorias.setBounds(155, 131, 111, 33);
		administracion.add(btnCategorias);
		
		JButton btnItems = new JButton("Ítems");
		btnItems.setBounds(155, 72, 111, 33);
		administracion.add(btnItems);
		
		JButton btnTipos = new JButton("Tipos");
		btnTipos.setBounds(155, 193, 111, 33);
		administracion.add(btnTipos);
		
		JPanel reportes = new JPanel();
		tabbedPane.addTab("Reportes", null, reportes, null);
	}
	
	private void cargarDatos() {
		try {
			Controladora.cargarDatos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error al cargar los datos" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void guardarDatos() {
		try {
			Controladora.guardarDatos();
		} catch (java.io.FileNotFoundException e) {
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error al guardar los datos: " + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
