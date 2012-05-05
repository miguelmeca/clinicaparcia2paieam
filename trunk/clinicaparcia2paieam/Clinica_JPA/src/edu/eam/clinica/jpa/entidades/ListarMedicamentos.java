package edu.eam.clinica.jpa.entidades;

public class ListarMedicamentos {
	
	private String nombre;
	private long cantidad;
	private String codigo;
	
	public ListarMedicamentos(String nombre,String codigo, long cantidad) {
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
	
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	

}
