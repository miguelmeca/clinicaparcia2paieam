package edu.eam.clinica.jpa.entidades;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Entity que representa el funcionario.
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Funcionario.FIND_FUNCIONARIO_BY_LOGIN, query = "select fun from Funcionario fun where fun.login=:" + Funcionario.PARAMETRO_LOGIN),
    @NamedQuery(name = Funcionario.FIND_ALL, query = "select fun from Funcionario fun")
})
@Inheritance(strategy=InheritanceType.JOINED)
public class Funcionario extends Persona implements Serializable {

    /**
     * Constante para la named Query de buscar todos los funcionarios.
     */
    public static final String FIND_ALL = "Funcionario.findAll";
    /**
     * Constante para la named query de buscar Funcionario por login.
     */
    public static final String FIND_FUNCIONARIO_BY_LOGIN = "Funcionario.findFuncionarioByLogin";
    /**
     * Constante para el parametro de login del Funcionario.
     */
    public static final String PARAMETRO_LOGIN = "login";
    /**
     * Constante de Serializacion
     */
    private static final long serialVersionUID = 1L;
    /**
     * nombre de usuario para el login
     */
    @Basic(optional = false)
    @Column(unique = true)
    private String login;
    /**
     * contraseña del usuario.
     */
    @Basic(optional = false)
    @Column(unique = true)
    private String password;
    
    /**
     * Lista de inventario
     */
    @OneToMany(fetch = LAZY, mappedBy = "funcionarioIngreso", cascade = ALL)
    private List<Inventario> inventarioIngreso;
    /**
     * Lista de inventario
     */
    @OneToMany(fetch = LAZY, mappedBy = "funcionarioSalida", cascade = ALL)
    private List<Inventario> inventarioSalida;
    
    
  

    public Funcionario() {

        super();
       
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

   
    /**
     * @return the inventarioIngreso
     */
    public List<Inventario> getInventarioIngreso() {
        return inventarioIngreso;
    }

    /**
     * @param inventarioIngreso the inventarioIngreso to set
     */
    public void setInventarioIngreso(List<Inventario> inventarioIngreso) {
        this.inventarioIngreso = inventarioIngreso;
    }

    /**
     * @return the inventarioSalida
     */
    public List<Inventario> getInventarioSalida() {
        return inventarioSalida;
    }

    /**
     * @param inventarioSalida the inventarioSalida to set
     */
    public void setInventarioSalida(List<Inventario> inventarioSalida) {
        this.inventarioSalida = inventarioSalida;
    }
    //TODO:falta toString

	
}
