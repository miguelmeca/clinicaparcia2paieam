package edu.eam.clinica.jpa.enumeraciones;

public enum EstadificacionNEnum {
	
	
	 
	 NX("Nx"),
	 N0("N0"),
	 N1("N1"),
	 N2("N2"),
	 N3("N3");
	 
	 
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private EstadificacionNEnum(String descripcion) {
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
