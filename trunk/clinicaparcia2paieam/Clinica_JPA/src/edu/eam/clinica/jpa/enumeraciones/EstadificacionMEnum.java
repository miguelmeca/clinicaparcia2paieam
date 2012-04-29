package edu.eam.clinica.jpa.enumeraciones;

public enum EstadificacionMEnum {
	
	
	 
	 MX("Mx"),
	 M0("M0"),
	 M1("M1");
	 
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private EstadificacionMEnum(String descripcion) {
		this.setDescripcion(descripcion);
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		
		return descripcion;
	}


}
