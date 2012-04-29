package edu.eam.clinica.jpa.enumeraciones;

public enum EstadificacionTEnum {
	
	
	 TX("Tx"),
	 TO("T0"),
	 TI_S("TiS"),
	 T1("T1"),
	 T2("T2"),
	 T3("T3"),
	 T4("T4");
	 
	 
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private EstadificacionTEnum(String descripcion) {
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
