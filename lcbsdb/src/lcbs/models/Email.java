package lcbs.models;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;


@Embeddable
@Access(AccessType.FIELD)
@XmlRootElement
public class Email implements Serializable{
    private static final long serialVersionUID = 1L;

    private String nombre;
    public Email() {
       
        nombre = "";
    }

    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }
}