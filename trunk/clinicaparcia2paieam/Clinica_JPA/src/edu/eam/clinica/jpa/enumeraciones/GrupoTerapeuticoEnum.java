package edu.eam.clinica.jpa.enumeraciones;

/**
 * Enumeración para el grupo terapeuta.
 * @author Camilo Andres
 *
 */
public enum GrupoTerapeuticoEnum {
	
	ALGO("")/*TODO:faltan los valores*/;
	/**
	 * descripcion del grupo terapeuta.
	 */
	private String descripcion;
	

	/**
	 * @param descripcion
	 */
	private GrupoTerapeuticoEnum(String descripcion) {
		this.descripcion = descripcion;
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


}
