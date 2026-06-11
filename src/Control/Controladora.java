package Control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
	private int codigoAutomatico;
	private Tipo tipoGenerico;
	
	private Controladora() {
		prestamos = new ArrayList<>();
		usuarios = new ArrayList<>();
		categorias = new ArrayList<>();
		tipos = new ArrayList<>();
		codigoAutomatico = 1;
		tipoGenerico = new Tipo(0, "Generico", true);
	}
	
	public static Controladora getInstance() {
        if (instance == null) {
            instance = new Controladora();
        }
        return instance;
    }
	
	public void crearItem(String nombre, String descripcion, Tipo tipo) throws Exception {
		items.add(new Item(codigoAutomatico, nombre, descripcion, tipo));
		codigoAutomatico++;
	}
	
	public void modificarItem(Item item, String nombre, String descripcion, Tipo tipo) throws Exception {
		if (!items.contains(item)) {
			throw new Exception("No se encontró el item");
		} else {
			item.setNombre(nombre);
			item.setDescripcion(descripcion);
			item.setTipo(tipo);
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
			throw new Exception("No se encontró el item");
		if (!usuario.puedeEliminarse())
			throw new Exception("El usuario tiene prestamos pendientes");
		usuarios.remove(usuario);
	}
	
	public List<Usuario> consultarUsuario() {
		return usuarios;
	}
	
	public void crearCategoria(String nombre) {
		categorias.add(new Categoria(codigoAutomatico, nombre));
		codigoAutomatico++;
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
	}
	
	public List<Categoria> consultarCategoria() {
		return categorias;
	}
	
	public void crearTipo(String nombre) {
		tipos.add(new Tipo(codigoAutomatico, nombre));
		codigoAutomatico++;
	}
	
	public void modificarTipo(Tipo tipo, String nombre) throws Exception {
		if (!tipos.contains(tipo)) {
			throw new Exception("No se encontró el tipo");
		} else {
			tipo.setNombre(nombre);
		}
	}
	
	public void borrarTipo(Tipo tipo) throws Exception {
		if (tipo.puedeEliminarse())
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
		Prestamo nuevoPrestamo = new Prestamo(codigoAutomatico++, usuario);
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
	
	 public void agregarAlertaPrestamo(Prestamo prestamo, Alerta alerta) {
	        prestamo.setAlerta(alerta);
	    }
	
	 public List<Prestamo> getPrestamos() {
	        return prestamos;
	    }
	
	public void reporteUsuario() {
		
	}
	
	public void reporteItem() {
		
	}
	
	public void reporteCategoria() {
		
	}
	
	public void reporteTipo() {
		
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
