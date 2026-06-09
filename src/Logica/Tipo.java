package Logica;

import java.io.Serializable;

public class Tipo implements Serializable{
	private int codigo;
	private String nombre;
	private boolean esGenerico;
	
	public Tipo(int codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.esGenerico = false;
	}

	public Tipo(int codigo, String nombre, boolean esGenerico) {
		this.codigo = codigo;
        this.nombre = nombre;
        this.esGenerico = esGenerico;
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
}
