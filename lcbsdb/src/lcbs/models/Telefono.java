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
public class Telefono implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String telefono;
    
 

    public Telefono() {}
    
    public Telefono(String id, String nom) {
        this.id = id;
        this.telefono = nom;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setTelefono(String val){
        this.telefono = val;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
}