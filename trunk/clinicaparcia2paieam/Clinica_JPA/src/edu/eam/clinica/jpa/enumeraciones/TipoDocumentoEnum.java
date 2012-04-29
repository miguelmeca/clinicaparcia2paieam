package edu.eam.clinica.jpa.enumeraciones;

/**
 * Enumeracion para el Tipo de documento.
 * @author Camilo Andres
 *
 */
public enum TipoDocumentoEnum {
	
	
	CEDULA_CIUDAUDANIA("Cedula de Ciudadania ","CC"),
	TARJETA_IDENTIDAD("Tarjeta de Identidad","TI"),
	PASAPORTE("Pasaporte ","PAS"),
	NIT("Numero de identificacion Tributaria","NIT");
	
	private String descripcion;
	private String codigo;
	
	private TipoDocumentoEnum(String descripcion,String codigo) {
		
		this.descripcion=descripcion;
		this.codigo=codigo;
		
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

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
