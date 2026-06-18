package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
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
}
