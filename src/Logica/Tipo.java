package Logica;

import java.io.Serializable;

public class Tipo implements Serializable{
	private int codigo;
	private String nombre;
	private Item item;
	
	public Tipo(int codigo, String nombre, Item item) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.item = item;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
