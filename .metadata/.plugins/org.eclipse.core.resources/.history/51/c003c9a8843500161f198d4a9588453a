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
    
    private String email;
    public Email() {
       
    	email = "";
    }
    
    public Email(DataEmail dt){
    	this.email = dt.getEmail();
    }
    
    public DataEmail getDatatype(){
    	DataEmail result = new DataEmail();
    	result.setEmail(this.email);
    	return result;
    }

    public void setEmail(String val){
        this.email = val;
    }
    
    public String getEmail(){
        return this.email;
    }
}