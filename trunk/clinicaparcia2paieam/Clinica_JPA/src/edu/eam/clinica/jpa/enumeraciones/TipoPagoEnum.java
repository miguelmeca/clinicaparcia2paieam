/**
 * 
 */
package edu.eam.clinica.jpa.enumeraciones;

/**
 * @author Camilo Andres
 *
 */
public enum TipoPagoEnum {
	
	EFECTIVO("Efectivo"),
	CONSIGNACION_BANCARIA("Consignacion"),
	CHEQUE("Cheque"),
	TARJETA_CREDITO("Tajeta de Credito");
	
	private String descripcion;
	
	private TipoPagoEnum(String descripcion){
		this.descripcion=descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

}
