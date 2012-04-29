
/**
 * 
 */
package edu.eam.clinica.jpa.enumeraciones;

/**
 * Enumeracion para el tipo de procedimientos.
 * @author Camilo Andres
 *
 */
public enum TipoProcedimietoEnum {
	
	QUIMIOTERAPIA("Quimioterapia"),
	CONTROL("Control"),
	EXAMEN("Examen");
	
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private TipoProcedimietoEnum(String descripcion) {
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
