package edu.eam.clinica.jpa.enumeraciones;
/**
 * Enumeración para la presentación del medicamento.
 * @author Camilo Andres
 *
 */
public enum PresentacionEnum {
	
	JARABE("Jarabe"),/*TODO:faltan los datos*/
        PASTILLA("Pastilla"),
        INTRAVENOSO("Intravenoso");
	/**
	 * descripcion del mootivo.
	 */
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private PresentacionEnum(String descripcion) {
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
