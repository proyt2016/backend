package lcbs.models;

import java.io.Serializable;

import javax.persistence.Id;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public abstract class Persona implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private String nombre;
    private String apellido;    
    private Email email;
    private List<Telefono> telefonosContacto;
    private Date fechaNacimiento;
     

    public Persona() {
        id = "";
        nombre = "";
        apellido = "";
        email = new Email();
        telefonosContacto = new List<Telefono>();
        fechaNacimiento = new Date();
    }
    
    public Persona(String id, String nom, String ape, Email mail, List<Telefono> tels, Date fecNac) {
        this.id = id;
        this.nombre = nom;
        this.apellido = ape;
        this.email = mail;
        this.telefonosContacto = tels;
        this.fechaNacimiento = fecNac;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setApellido(String val){
        this.apellido = val;
    }
    
    public String getApellido(){
        return this.apellido;
    }

    public void setEmail(Email val){
        this.email = val;
    }
    
    public Email getEmail(){
        return this.email;
    }

    public void setTelefonosContacto(List<Telefono> val){
        this.telefonosContacto = val;
    }
    
    public List<Telefono> getTelefonosContacto(){
        return this.telefonosContacto;
    }

    public void setFechaNacimiento(Date val){
        this.fechaNacimiento = val;
    }
    
    public Date getFechaNacimiento(){
        return this.fechaNacimiento;
    }
}