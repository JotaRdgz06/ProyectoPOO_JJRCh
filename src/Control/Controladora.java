package Control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Logica.Categoria;
import Logica.Prestamo;
import Logica.Tipo;
import Logica.Usuario;

public class Controladora implements Serializable {
	
	private static Controladora instance;
	
	private List<Prestamo> prestamos;
	private List<Usuario> usuarios;
	private List<Categoria> categorias;
	private List<Tipo> tipos;
	
	private Controladora() {
		prestamos = new ArrayList<>();
		usuarios = new ArrayList<>();
		categorias = new ArrayList<>();
		tipos = new ArrayList<>();
	}
	
	public static Controladora getInstance() {
        if (instance == null) {
            instance = new Controladora();
        }
        return instance;
    }
	
	public void crearItem() {
		
	}
	
	public void modificarItem() {
		
	}
	
	public void borrarItem() {
		
	}
	
	public void consultarItem() {
		
	}
	
	public void crearUsuario() {
		
	}
	
	public void modificarUsuario() {
		
	}
	
	public void borrarUsuario() {
		
	}
	
	public void consultarUsuario() {
		
	}
	
	public void crearCategoria() {
		
	}
	
	public void modificarCategoria() {
		
	}
	
	public void borrarCategoria() {
		
	}
	
	public void consultarCategoria() {
		
	}
	
	public void crearTipo() {
		
	}
	
	public void modificarTipo() {
		
	}
	
	public void borrarTipo() {
		
	}
	
	public void consultarTipo() {
		
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
