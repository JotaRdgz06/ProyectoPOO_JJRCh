package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {
	private int codigo;
	private String nombre;
	private String descripcion;
	private boolean prestado;
	private Tipo tipo;
	private List<Categoria> categorias;
	
	public Item(int codigo, String nombre, String descripcion, Tipo tipo) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.prestado = false;
		this.tipo = tipo;
		this.categorias = new ArrayList<>();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public boolean estaPrestado() {
		return prestado;
	}
	
	public void marcarComoPrestado() {
		this.prestado = true;
	}
	
	public void marcarComoLibre() {
		this.prestado = false;
	}
	
	public void devolverATipoGenerico(Tipo generico) {
		this.tipo = generico;
	}
	
	public void agregarCategoria(Categoria categoria) {
        if (!categorias.contains(categoria)) {
            categorias.add(categoria);
        }
    }
	
	public void eliminarCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }
	
	public boolean puedeEliminarse() {
        return !prestado;
    }
}
