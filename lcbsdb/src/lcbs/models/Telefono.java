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
public class Telefono implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private String telefono;
    
 

    public Telefono() {
        id = "";
        telefono = "";
    }
    
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