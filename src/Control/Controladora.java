package Control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Logica.Alerta;
import Logica.Categoria;
import Logica.Item;
import Logica.Prestamo;
import Logica.Tipo;
import Logica.Usuario;

public class Controladora implements Serializable {
	
	private static Controladora instance;
	
	private List<Prestamo> prestamos;
	private List<Usuario> usuarios;
	private List<Categoria> categorias;
	private List<Tipo> tipos;
	private List<Item> items;
	private int codigoAutomaticoCategoria;
	private int codigoAutomaticoTipo;
	private int codigoAutomaticoItem;
	private int codigoAutomaticoPrestamo;
	private Tipo tipoGenerico;
	
	private Controladora() {
		prestamos = new ArrayList<>();
		usuarios = new ArrayList<>();
		categorias = new ArrayList<>();
		tipos = new ArrayList<>();
		items = new ArrayList<>();
		codigoAutomaticoCategoria = 1;
		codigoAutomaticoTipo = 1;
		codigoAutomaticoItem = 1;
		codigoAutomaticoPrestamo = 1;
		tipoGenerico = new Tipo(0, "Generico", true);
	}
	
	public static Controladora getInstance() {
        if (instance == null) {
            instance = new Controladora();
        }
        return instance;
    }
	
	public void crearItem(String nombre, String descripcion, Tipo tipo, List<Categoria> categorias) throws Exception {
		Item nuevoItem = new Item(codigoAutomaticoItem, nombre, descripcion, tipo);
		for (Categoria categoria : categorias) {
			nuevoItem.agregarCategoria(categoria);
			categoria.agregarItem(nuevoItem);
		}
		items.add(nuevoItem);
		codigoAutomaticoItem++;
	}
	
	public void modificarItem(Item item, String nombre, String descripcion, Tipo tipo, List<Categoria> categorias) throws Exception {
		if (!items.contains(item)) {
			throw new Exception("No se encontró el item");
		} else {
			item.setNombre(nombre);
			item.setDescripcion(descripcion);
			item.setTipo(tipo);
			item.getCategorias().clear();
			for (Categoria categoria : item.getCategorias())
				categoria.eliminarItem(item);
			item.getCategorias().clear();
			for (Categoria categoria : categorias) {
				item.agregarCategoria(categoria);
				categoria.agregarItem(item);
			}
		}
    }
	
	public void borrarItem(Item item) throws Exception {
		if (!items.contains(item))
			throw new Exception("No se encontró el item");
		if (!item.puedeEliminarse())
			throw new Exception("El item está siendo prestado");
		items.remove(item);
	}
	
	public List<Item> consultarItem() {
		return items;
	}
	
	public void crearUsuario(String nombre, String telefono, String correo) {
		usuarios.add(new Usuario(nombre, telefono, correo));
	}
	
	public void modificarUsuario(Usuario usuario, String nombre, String telefono, String correo) throws Exception {
		if (!usuarios.contains(usuario)) {
			throw new Exception("No se encontró el usuario");
		} else {
			usuario.setNombre(nombre);
			usuario.setTelefono(telefono);
			usuario.setCorreo(correo);
		}
	}
	
	public void borrarUsuario(Usuario usuario) throws Exception {
		if (!usuarios.contains(usuario))
			throw new Exception("No se encontró el usuario");
		if (!usuario.puedeEliminarse())
			throw new Exception("El usuario tiene prestamos pendientes");
		usuarios.remove(usuario);
	}
	
	public List<Usuario> consultarUsuario() {
		return usuarios;
	}
	
	public Categoria crearCategoria(String nombre) {
		Categoria nuevaCategoria = new Categoria(codigoAutomaticoCategoria, nombre);
		categorias.add(nuevaCategoria);
		codigoAutomaticoCategoria++;
		return nuevaCategoria;
	}
	
	public void modificarCategoria(Categoria categoria, String nombre) throws Exception {
		if (!categorias.contains(categoria))
			throw new Exception("No se encontró la categoria");
		categoria.setNombre(nombre);
	}
	
	public void borrarCategoria(Categoria categoria) {
		for (Item i : items) {
			i.eliminarCategoria(categoria);
		}
		categorias.remove(categoria);
	}
	
	public List<Categoria> consultarCategoria() {
		return categorias;
	}
	
	public void crearTipo(String nombre) {
		tipos.add(new Tipo(codigoAutomaticoTipo, nombre));
		codigoAutomaticoTipo++;
	}
	
	public void modificarTipo(Tipo tipo, String nombre) throws Exception {
		if (!tipos.contains(tipo)) {
			throw new Exception("No se encontró el tipo");
		} else {
			tipo.setNombre(nombre);
		}
	}
	
	public void borrarTipo(Tipo tipo) throws Exception {
		if (!tipo.puedeEliminarse())
			throw new Exception("No se pudo eliminar");
		for (Item i : items) {
			Tipo tipoItem = i.getTipo();
			if (tipoItem == tipo)
				i.ponerTipoGenerico(tipoGenerico);
		}
		tipos.remove(tipo);
	}
	
	public List<Tipo> consultarTipo() {
		return tipos;
	}
	
	public Prestamo crearPrestamo(Usuario usuario) {
		Prestamo nuevoPrestamo = new Prestamo(codigoAutomaticoPrestamo++, usuario);
        prestamos.add(nuevoPrestamo);
        usuario.agregarPrestamo(nuevoPrestamo);
        return nuevoPrestamo;
	}
	
	public void agregarItemPrestamo(Prestamo prestamo, Item item) throws Exception {
		if (item.estaPrestado()) {
	        throw new Exception("El ítem no puede agregarse porque ya está prestado.");
	    }
	    prestamo.agregarItem(item);
	}
	
	public void eliminarItemPrestamo(Prestamo prestamo, Item item) throws Exception {
		if (item.estaPrestado()) {
	        throw new Exception("El ítem no puede agregarse porque ya está prestado.");
	    }
	    prestamo.eliminarItem(item);
	}
	
	public void retornarItemPrestamo(Prestamo prestamo, Item item) throws Exception {
		if (item.estaPrestado()) {
	        throw new Exception("El ítem no puede agregarse porque ya está prestado.");
	    }
	    prestamo.eliminarItem(item);
	}
	
	public void finalizarPrestamo(Prestamo prestamo) {
        prestamo.finalizar();
        Usuario usuarioDelPrestamo = prestamo.getUsuario();
        usuarioDelPrestamo.borrarPrestamo(prestamo);
        prestamos.remove(prestamo);
    }
	
	public List<Prestamo> consultarPrestamo() {
		return prestamos;
	}
	
	 public void agregarAlertaPrestamo(Prestamo prestamo, Alerta alerta) {
	        prestamo.setAlerta(alerta);
	    }
	
	 public List<Prestamo> getPrestamos() {
	        return prestamos;
	    }
	
	public List<Usuario> reporteUsuario() {
		List<Usuario> reporteUsuario = new ArrayList<>();
	    for (Usuario usuario : usuarios) {
	        if (!usuario.puedeEliminarse()) {
	        	reporteUsuario.add(usuario);
	        }
	    }
	    return reporteUsuario;
	}
	
	public List<Item> reporteItem() {
		List<Item> reporteItem = new ArrayList<>();
	    for (Item item : items) {
	        reporteItem.add(item);
	    }
	    reporteItem.sort(Comparator.comparing(Item::getNombre, String.CASE_INSENSITIVE_ORDER));
	    return reporteItem;
	}
	
	public List<Categoria> reporteCategoria() {
		List<Categoria> reporteCate = new ArrayList<>();
	    for (Categoria categoria : categorias) {
	        reporteCate.add(categoria);
	    }
	    reporteCate.sort(Comparator.comparing(Categoria::getNombre, String.CASE_INSENSITIVE_ORDER));
	    return reporteCate;
	}
	
	public List<Tipo> reporteTipo() {
		List<Tipo> reporteTipo = new ArrayList<>();
	    for (Tipo tipo : tipos) {
	    	reporteTipo.add(tipo);
	    }
	    reporteTipo.sort(Comparator.comparing(Tipo::getNombre, String.CASE_INSENSITIVE_ORDER));
	    return reporteTipo;
	}

	public Integer obtenerSiguienteCodigoItem() {
        return codigoAutomaticoItem;
    }
	
	public Integer obtenerSiguienteCodigoPrestamo() {
        return codigoAutomaticoPrestamo;
    }
	
	public Integer obtenerSiguienteCodigoTipo() {
        return codigoAutomaticoTipo;
    }
	
	public Integer obtenerSiguienteCodigoCategoria() {
        return codigoAutomaticoCategoria;
    }
	
	public static void guardarDatos() throws IOException {
    	FileOutputStream file = new FileOutputStream("DatosProyecto.dat");
    	ObjectOutputStream stream = new ObjectOutputStream(file);
    	stream.writeObject(instance);
    	stream.close();
    	file.close();
    }
    
    public static void cargarDatos() throws IOException, ClassNotFoundException {
    	FileInputStream file = new FileInputStream("DatosProyecto.dat");
    	ObjectInputStream stream = new ObjectInputStream(file);
    	instance = (Controladora) stream.readObject();
    	stream.close();
    	file.close();
    }
}
