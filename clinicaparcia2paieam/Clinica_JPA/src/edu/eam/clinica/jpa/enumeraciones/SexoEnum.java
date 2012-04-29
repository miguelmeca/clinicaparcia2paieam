/**
 * 
 */
package edu.eam.clinica.jpa.enumeraciones;

/**
 * Enumeracion para el sexo de una persona.
 * @author Camilo Andres
 *
 */
public enum SexoEnum {

		MASCULINO("Masculino","M"),
		FEMENINO("Femenino","F");
		
		/**
		 * Descripcion del sexo
		 */
		private String descripcion;
		/**
		 * sigla del sexo
		 */
		private String codigo;
		/**
		 * Constructor
		 * @param descripcion
		 * @param codigo
		 */
		private SexoEnum(String descripcion, String codigo) {
			this.descripcion = descripcion;
			this.codigo = codigo;
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
