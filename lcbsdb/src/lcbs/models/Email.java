package lcbs.models;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;


@Embeddable
@Access(AccessType.FIELD)
@XmlRootElement
public class Email implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String nombre;
    
  

    public Email() {
        id = "";
        nombre = "";
    }
    
    public Email(String id, String nom) {
        this.id = id;
        this.nombre = nom;
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
}