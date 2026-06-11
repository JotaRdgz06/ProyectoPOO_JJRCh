package Control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	private Controladora() {
		prestamos = new ArrayList<>();
		usuarios = new ArrayList<>();
		categorias = new ArrayList<>();
		tipos = new ArrayList<>();
		codigoAutomatico = 1;
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
	
	public void crearUsuario() {
		
	}
	
	public void modificarUsuario() {
		
	}
	
	public void borrarUsuario() {
		
	}
	
	public List<Usuario> consultarUsuario() {
		return usuarios;
	}
	
	public void crearCategoria() {
		
	}
	
	public void modificarCategoria() {
		
	}
	
	public void borrarCategoria() {
		
	}
	
	public List<Categoria> consultarCategoria() {
		return categorias;
	}
	
	public void crearTipo() {
		
	}
	
	public void modificarTipo() {
		
	}
	
	public void borrarTipo() {
		
	}
	
	public List<Tipo> consultarTipo() {
		return tipos;
	}
	
	public void crearPrestamo() {
		
	}
	
	public void agregarItemPrestamo() {
		
	}
	
	public void eliminarItemPrestamo() {
		
	}
	
	public void retornarItemPrestamo() {
		
	}
	
	public void finalizarPrestamo() {
		
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
