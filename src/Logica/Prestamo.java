package Logica;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Prestamo implements Serializable {
	private int codigo;
    private Usuario usuario;
    private List<Item> items;
    private Tipo tipo;
    private Alerta alerta;
    private LocalDateTime fechaCreacion;
    
	public Prestamo(int codigo, Usuario usuario) {
		this.codigo = codigo;
		this.usuario = usuario;
		this.tipo = tipo;
		this.items = new ArrayList<>();
		this.alerta = null;
		this.fechaCreacion = LocalDateTime.now();
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
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
    
	public void agregarItem(Item item) {
	    items.add(item);
	    item.marcarComoPrestado();
	}
	
	public void eliminarItem(Item item) {
        items.remove(item);
        item.marcarComoLibre();
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
	
	public LocalDateTime getFechaCreacion() {
	    return fechaCreacion;
	}
	
	public String getFechaAlertaConFormato() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    return alerta.getFechaActivacion().format(formatter);
	}
}
