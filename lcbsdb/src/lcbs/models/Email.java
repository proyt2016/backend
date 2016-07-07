package lcbs.models;
import lcbs.shares.*;

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
    private String descripcion;
    private String email;
    public Email() {
        descripcion = "";
    	email = "";
    }
    
    public Email(DataEmail dt){
    	this.descripcion = dt.getDescripcion();
    	this.email = dt.getEmail();
    }
    
    public DataEmail getDatatype(){
       	DataEmail result = new DataEmail();
       	result.setDescripcion(this.descripcion);
    	result.setEmail(this.email);
    	return result;
    }
    
    public void setDescripcion(String val){
        this.descripcion = val;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public void setEmail(String val){
        this.email = val;
    }
    
    public String getEmail(){
        return this.email;
    }
}