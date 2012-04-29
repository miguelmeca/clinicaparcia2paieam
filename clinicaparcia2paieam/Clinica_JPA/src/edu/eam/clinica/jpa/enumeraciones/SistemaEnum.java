package edu.eam.clinica.jpa.enumeraciones;

public enum SistemaEnum {
	CABEZAYCUELLO("Cabeza Y Cuello"),
        TORAX("Torax"),
        SENOS("Senos"),
        ABDOMEN("Abdomen"),
        UROGENITAL("Urogenital"),
        EXTREMIDADES("Extremidades"),
        NEUROLOGICO("Neurologico"),
        PIEL("Piel");
	/**
	 * descripcion del grupo terapeuta.
	 */
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private SistemaEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

    @Override
    public String toString() {
        return descripcion;
    }

        
}
