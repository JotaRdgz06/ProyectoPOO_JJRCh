package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tipo implements Serializable{
	private int codigo;
	private String nombre;
	private boolean esGenerico;
	private List<Item> items;

	
	public Tipo(int codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.esGenerico = false;
		this.items = new ArrayList<>();
	}

	public Tipo(int codigo, String nombre, boolean esGenerico) {
		this.codigo = codigo;
        this.nombre = nombre;
        this.esGenerico = esGenerico;
        this.items = new ArrayList<>();
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

	public boolean esEsGenerico() {
		return esGenerico;
	}
	
	public boolean puedeEliminarse() {
        return !esGenerico;
    }
	
	public List<Item> getItems() {
        return items;
    }
 
    public void agregarItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }
 
    public void eliminarItem(Item item) {
        items.remove(item);
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
