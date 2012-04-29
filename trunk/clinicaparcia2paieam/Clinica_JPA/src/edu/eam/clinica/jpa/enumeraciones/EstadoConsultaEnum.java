package edu.eam.clinica.jpa.enumeraciones;
/**
 * Enumeracion que indica el estado de una cita.
 * @author Camilo Andres
 *
 */
public enum EstadoConsultaEnum {
	
	
	CANCELADA("Cancelada"),
	TOMADA("Tomada"),
	EN_ESPERA("En Espera");
	
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private EstadoConsultaEnum(String descripcion) {
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
