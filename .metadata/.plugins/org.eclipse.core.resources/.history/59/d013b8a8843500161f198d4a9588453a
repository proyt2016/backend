package lcbs.models;
import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;


@Embeddable
@Access(AccessType.FIELD)
@XmlRootElement
public class Telefono implements Serializable{
    private static final long serialVersionUID = 1L;
    
    
    private String telefono;
    
 

    public Telefono() {}
    
    public Telefono(DataTelefono dt){
    	this.setTelefono(dt.getTelefono());
    }
    
    public DataTelefono getDatatype(){
    	DataTelefono result = new DataTelefono();
    	result.setTelefono(this.getTelefono());
    	return result;
    }
    
  
    public void setTelefono(String val){
        this.telefono = val;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
}