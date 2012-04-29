package edu.eam.clinica.jpa.enumeraciones;

public enum ECEnum {
	
	
	 
	 INSITU("In situ"),
	 IA("IA"),
         IB("IB"),
         IC("IC"),
          IIA("IIA"),
         IIB("IIB"),
         IIC("IIC"),
         
          IIIA("IIIA"),
         IIIB("IIIB"),
         IIIC("IIIC"),
         
          IVA("IVA"),
         IVB("IVB"),
         IVC("IVC"),
         
         V("V");
         
	 
	 
	private String descripcion;

	/**
	 * @param descripcion
	 */
	private ECEnum(String descripcion) {
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
