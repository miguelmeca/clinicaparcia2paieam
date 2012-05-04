package edu.eam.clinica.jpa.entidades;

public class ListarMedicamentos {
	
	private String nombre;
	private int cantidad;
	private long codigo;
	
	public ListarMedicamentos(String nombre,long codigo, int cantidad) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.codigo= codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	
	

}
