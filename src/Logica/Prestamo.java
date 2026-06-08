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
		super();
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
    
    
}
