package edu.eam.clinica.jpa.enumeraciones;

public enum TipoAntecedenteEnum {
	
	
	 
	PERSONAL("Personal"),
        FAMILIAR("Familiares"),
        GINECOLOGICOS("Ginecologicos");
         
         
         
	 
	 
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private TipoAntecedenteEnum(String descripcion) {
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
