package edu.eam.clinica.jpa.enumeraciones;
/**
 * enumeracion para el tipo de consultas.
 * @author Camilo Andres
 *
 */
public enum MotivoConsultaEnum {
	
	OCASIONAL("Consulta Ocasional"),
	PROGRAMADA("Consulta Programada"),
        QUIMIOTERAPIA("Quimioterapia"),
        PRIMERA_VEZ("Primera Vez");
	
	/**
	 * descripcion del mootivo.
	 */
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private MotivoConsultaEnum(String descripcion) {
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

    @Override
    public String toString() {
        return descripcion;
    }
	
	
	
	
}
