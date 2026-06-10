package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prestamo implements Serializable {
	private int codigo;
    private Usuario usuario;
    private List<Item> items;
    private Alerta alerta;
    
	public Prestamo(int codigo, Usuario usuario) {
		this.codigo = codigo;
		this.usuario = usuario;
		this.items = new ArrayList<>();
		this.alerta = null;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Alerta getAlerta() {
		return alerta;
	}

	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}

	public List<Item> getItems() {
		return items;
	}
    
	public void agregarItem(Item item) {
		if (!item.estaPrestado()) {
			items.add(item);
			item.marcarComoPrestado();
		}
	}
	
	public void borrarItem(Item item) {
		if (items.remove(item)) {
			item.marcarComoLibre();
		}
	}
	
	public void finalizar() {
        for (Item item : items) {
            item.marcarComoLibre();
        }
        items.clear();
        alerta = null;
    }
	
	public boolean tieneAlerta() {
        return alerta != null;
    }
	
	public boolean estaVacio() {
        return items.isEmpty();
    }
}
