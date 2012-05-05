package edu.eam.clinica.jpa.entidades;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Entidad que representa el inventario.
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Inventario.CONSULTA_FIND_BY_CODIGO, 
        query = "select inv from Inventario inv where inv.articulo=:" 
        + Inventario.PARAMETRO_CODIGO),
        
        @NamedQuery(name = Inventario.CONSULTA_FIND_BY_CODIGOBARRAS, 
                query = "select inv from Inventario inv where inv.codigo=:" 
                + Inventario.PARAMETRO_CODIGO),
    
    @NamedQuery(name = Inventario.CONSULTA_FIND_BY_ARTICULO, 
        query = "select inv from Inventario inv where inv.fechaSalida is NULL and inv.articulo.codigo=:" 
        + Inventario.PARAMETRO_CODIGO ),  
        
        
        @NamedQuery(name = Inventario.CONSULTA_FIND_BY_SALIDA_ARTICULO, 
                query = "select inv from Inventario inv where inv.fechaSalida is NOT NULL and inv.articulo.nombre=:"
                	+Inventario.PARAMENTRO_NOMBRE), 
       
        @NamedQuery(name = Inventario.CONSULTA_FIND_BY_ENTRADA_ARTICULO, 
                      query = "select inv from Inventario inv where inv.fechaSalida is  NULL and inv.articulo.nombre=:" 
                        + Inventario.PARAMENTRO_NOMBRE)  ,      
        
   @NamedQuery(name = Inventario.FIND_ARTICULO_BY_CANTIDAD, query = "SELECT COUNT(i) from Inventario i where i.articulo.id=:"+Inventario.PARAMETRO_CODIGO)
     
     
})

public class Inventario implements Serializable {

    
	/**
	 * 
	 */
	 public static final String CONSULTA_FIND_BY_CODIGOBARRAS="Inventario.findbyCodigoBarrasT";
	 
     /**
     * Consulta para buscar el inventario por codigo.
     */
    public static final String CONSULTA_FIND_BY_CODIGO="Inventario.findbyCodigoBarras";
    
    
    /**
	 * constante para sacr la cantida de un articulo del inventario
	 */
	public static final String FIND_ARTICULO_BY_CANTIDAD="Articulo.findArticuloBYCantidad";
	/**
	 * parametro para buscar por nombre
	 */
	
	/**
	 * constante para sacar los que no tiennene salida
	 */
	
	public static final String CONSULTA_FIND_BY_SALIDA_ARTICULO="Inventario.findSalidad";
	
	/**
	 * Constante para sacar los que tiene salidad
	 */
	public static final String CONSULTA_FIND_BY_ENTRADA_ARTICULO="Inventario.findEntrada";
	
    /**
     * Consulta el invetario y articulo por nombre
     */
    public static final String PARAMENTRO_NOMBRE="nombre";
  
    /**
     * Consulta para buscar el inventario por codigo.
     */
    public static final String CONSULTA_FIND_BY_ARTICULO="Inventario.findByArticulo";
    
     /**
     * Consulta para buscar el inventario por codigo.
     */
    public static final String PARAMETRO_CODIGO="codigo";
  
    
    /**
     * Constante de serialización.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Idenificador unico de la entidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Articulo del inventario.
     */
    @ManyToOne
    @JoinColumn(name = "ARTICULO")
    private Articulo articulo;
    /**
     * Fecha de ingreso del articulo.
     */
    @Basic(optional = false)
    @Temporal(TIMESTAMP)
    private Date fechaIngreso;
    /**
     * Fecha de salida del articulo.
     */
    @Basic(optional = true)
    @Temporal(TIMESTAMP)
    private Date fechaSalida;
    /**
     * Codigo del articulo.
     */
    @Column(unique = true)
    @Basic(optional = false)
    private String codigo;
    /**
     * Motivo por el cual se registra la salida de un articulo.
     */
    @Basic(optional = true)
    private String motivoSalida;
    /**
     * Funcionario que ingresa el articulo al inventario.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "FUNCIONARIO_INGRESO")
    private Funcionario funcionarioIngreso;
    /**
     * Funcionario que da salida a el articulo del inventario.
     */
    @ManyToOne(optional = true)
    @JoinColumn(name = "FUNCIONARIO_SALIDA")
    private Funcionario funcionarioSalida;

    /**
     * Constructor sin parametros.
     */
    public Inventario() {
        super();
    }
   
    /**
     * 
     * @param articulo
     * @param fechaIngreso
     * @param fechaSalida
     * @param codigo
     * @param motivoSalida
     * @param funcionarioIngreso
     * @param funcionarioSalida
     * 
     *
     */
    public Inventario(Articulo articulo, Date fechaIngreso, Date fechaSalida,
			String codigo, String motivoSalida, Funcionario funcionarioIngreso,
			Funcionario funcionarioSalida) {
		super();
		this.articulo = articulo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.codigo = codigo;
		this.motivoSalida = motivoSalida;
		this.funcionarioIngreso = funcionarioIngreso;
		this.funcionarioSalida = funcionarioSalida;
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the articulo
     */
    public Articulo getArticulo() {
        return articulo;
    }

    /**
     * @param articulo the articulo to set
     */
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /**
     * @return the fechaIngreso
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaSalida
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigo;
    }

    /**
     * @param codigoBarras the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigo = codigoBarras;
    }

    /**
     * @return the funcionarioSalida
     */
    public Funcionario getFuncionarioSalida() {
        return funcionarioSalida;
    }

    /**
     * @param funcionarioSalida the funcionarioSalida to set
     */
    public void setFuncionarioSalida(Funcionario funcionarioSalida) {
        this.funcionarioSalida = funcionarioSalida;
    }

    /**
     * @return the funcionarioIngreso
     */
    public Funcionario getFuncionarioIngreso() {
        return funcionarioIngreso;
    }

    /**
     * @param funcionarioIngreso the funcionarioIngreso to set
     */
    public void setFuncionarioIngreso(Funcionario funcionarioIngreso) {
        this.funcionarioIngreso = funcionarioIngreso;
    }

    /**
     * @return the motivoSalida
     */
    public String getMotivoSalida() {
        return motivoSalida;
    }

    /**
     * @param motivoSalida the motivoSalida to set
     */
    public void setMotivoSalida(String motivoSalida) {
        this.motivoSalida = motivoSalida;
    }
    
    
    //TODO:falta toString

    @Override
    public boolean equals(Object  obj) {
        
        Inventario inv=(Inventario) obj;
        return codigo.equals(inv.codigo);
        
    }
}
