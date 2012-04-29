package edu.eam.clinica.jpa.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import edu.eam.clinica.jpa.enumeraciones.SexoEnum;
import edu.eam.clinica.jpa.enumeraciones.TipoDocumentoEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;

@NamedQuery(name = Persona.FIND_PACIENTE_BY_NUMERO_Y_TIPO_DOCUMENTO_PACIENTE,
query = "select pac from Persona pac where pac.documento=:"
+ Persona.PARAMETRO_NUMERO_DOCUMENTO_PACIENTE + " and pac.tipoDocumento=:"
+ Persona.PARAMETRO_TIPO_DOCUMENTO_PACIENTE)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona implements Serializable {

    /**
     * Constante para la named Query de buscar Paciente por numero de identificacion del paciente y tipo de documento.
     */
    public static final String FIND_PACIENTE_BY_NUMERO_Y_TIPO_DOCUMENTO_PACIENTE = "Persona.findPacienteByNumeroYTipoDocumento";
    /**
     *Constante para el parametro para buscar por numero documento del paciente.
     */
    public static final String PARAMETRO_NUMERO_DOCUMENTO_PACIENTE = "numeroDocumentoPaciente";
    /**
     *Constante para el parametro para buscar por tipo Documento del paciente.
     */
    public static final String PARAMETRO_TIPO_DOCUMENTO_PACIENTE = "tipoDocumentoPaciente";
    /**
     * Serializacion
     */
    private static final long serialVersionUID = 1L;
//        @Id
//        @GeneratedValue(strategy= GenerationType.IDENTITY)
//	private Long id;
    /**
     * identificador unico de la persona.
     */
    @Id
    private String documento;
    /**
     * primer nombre de la persona.
     */
    @Basic(optional = false)
    private String primerNombre;
    /**
     * Segundo nombre de la persona, puede ser Null
     */
    @Basic(optional = true)
    private String segundoNombre;
    /**
     * Primer apellido de la persona
     */
    @Basic(optional = false)
    private String primerApellido;
    /**
     * Segundo apellido de la persona.
     */
    @Basic(optional = true)
    private String segundoApellido;
    /**
     * Tipo de documento de la persona.
     */
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumentoEnum tipoDocumento;
    /**
     * Direccion de la persona
     */
    @Basic(optional = false)
    private String direccion;
    /**
     * Direccion de correo de la persona.
     */
    @Basic(optional = true)
    private String email;
    /**
     * Genero de la persona.
     */
    @Basic(optional = false)
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Telefono> telefonos;
   
    
    
    @ManyToOne
    @JoinColumn(name="CIUDAD")
    private Ciudad ciudad;

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

  

    /**
     * 
     */
    public Persona() {
        super();
    }

    public String getNombre() {
        return primerNombre + " " + primerApellido;
    }

    /**
     * @param documento
     * @param primerNombre
     * @param segundoNombre
     * @param primerApellido
     * @param segundoApellido
     * @param tipoDocumento
     * @param direccion
     * @param email
     * @param sexo
     */
    public Persona(String documento, String primerNombre,
            String segundoNombre, String primerApellido,
            String segundoApellido, TipoDocumentoEnum tipoDocumento,
            String direccion, String email, SexoEnum sexo) {
        super();
        this.documento = documento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.tipoDocumento = tipoDocumento;
        this.direccion = direccion;
        this.email = email;
        this.sexo = sexo;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the primerNombre
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * @param primerNombre the primerNombre to set
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * @return the segundoNombre
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * @param segundoNombre the segundoNombre to set
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * @return the primerApellido
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * @param primerApellido the primerApellido to set
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the segundoApellido
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return the tipoDocumento
     */
    public TipoDocumentoEnum getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the sexo
     */
    public SexoEnum getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the telefonos
     */
    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    /**
     * @param telefonos the telefonos to set
     */
    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String toString() {
        return primerNombre + " " + segundoNombre + " " + primerApellido
                + " " + segundoApellido;
    }

    @Override
    public boolean equals(Object obj) {
        Persona per = (Persona) obj;
        if (per.getDocumento().equals(documento) && tipoDocumento == per.tipoDocumento) {
            return true;
        } else {
            return false;
        }
    }

   
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
}
