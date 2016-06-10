package lcbs.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Usuario extends Persona implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    private String nombreAMostrar;
    private String clave;
    private String redSocialUsada;
    private String idRedSocial;
    private Cuponera cuponera;

 

    public Usuario() {
        nombreAMostrar = "";
        clave = "";
        redSocialUsada = "";
        idRedSocial = "";
        cuponera = new Cuponera();
    }
    
    public Usuario(String id, String nom, String ape, Email mail, List<Telefono> tels, Date fecNac, String nomMos, String clave, String redSoc, String idRedsoc, Cuponera cup) {
        super(id, nom, ape, mail, tels, fecNac);
        this.nombreAMostrar = nomMos;
        this.clave = clave;
        this.redSocialUsada = redSoc;
        this.idRedSocial = idRedsoc;
        this.cuponera = cup;
    }
    
    public void setNombreAMostrar(String val){
        this.nombreAMostrar = val;
    }
    
    public String getNombreAMostrar(){
        return this.nombreAMostrar;
    }

    public void setClave(String val){
        this.clave = val;
    }
    
    public String getClave(){
        return this.clave;
    }

    public void setRedSocialUsada(String val){
        this.redSocialUsada = val;
    }
    
    public String getRedSocialUsada(){
        return this.redSocialUsada;
    }

    public void setIdRedSocial(String val){
        this.idRedSocial = val;
    }
    
    public String getIdRedSocial(){
        return this.idRedSocial;
    }

    public void setCuponera(Cuponera val){
        this.cuponera = val;
    }
    
    public Cuponera getCuponera(){
        return this.cuponera;
    }
}