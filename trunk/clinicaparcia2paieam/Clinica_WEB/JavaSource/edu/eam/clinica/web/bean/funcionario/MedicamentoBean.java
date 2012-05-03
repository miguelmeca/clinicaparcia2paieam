package edu.eam.clinica.web.bean.funcionario;

public class MedicamentoBean {
	
	/*
	 *Atributos Necesarios de la Entidad Medicamento 
	 */
	//CODIGO DE BARRAS DEL MEDICAMENTO
	private String codigoBarras;
	//NOMBRE ASIGNADO AL MEDICAMENTO
	private String nombre;
	//PRECIO DEL MEDICAMENTO
	private double precio;
	//CANTIDAD DEL MEDICAMENTO
    private int cantidad;
	
	public MedicamentoBean(){
		
	}


	//GETTERS AND SETTERS***
	public String getCodigoBarras() {
		return codigoBarras;
	}


	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
