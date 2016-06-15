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
    
    private String direccion;
    public Email() {
       
    	direccion = "";
    }

    public void setDireccion(String val){
        this.direccion = val;
    }
    
    public String getDireccion(){
        return this.direccion;
    }
}