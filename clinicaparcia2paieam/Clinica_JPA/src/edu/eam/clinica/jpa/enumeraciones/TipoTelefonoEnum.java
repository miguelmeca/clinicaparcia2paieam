/**
 * 
 */
package edu.eam.clinica.jpa.enumeraciones;

/**
 * @author Camilo Andres
 *
 */
public enum TipoTelefonoEnum {
	
	FIJO("Fijo"),
	CELULAR("Celular");
	
	private String descripcion;

	private TipoTelefonoEnum(String descripcion) {
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
