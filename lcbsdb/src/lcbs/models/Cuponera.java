package lcbs.models;

import java.io.Serializable; 
import lcbs.shares.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@XmlRootElement
public class Cuponera implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Float saldo;
 

    public Cuponera() {}
    
    public Cuponera(String id, Float saldo) {
        this.id = id;
        this.saldo = saldo;
    }
    
    public Cuponera(DataCuponera dt){
    	this.setId(dt.getId());
        this.setSaldo(dt.getSaldo());
    }
    
    public DataCuponera getDatatype(){
    	DataCuponera result = new DataCuponera();
    	result.setId(this.getId());
    	result.setSaldo(this.getSaldo());
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setSaldo(Float val){
        this.saldo = val;
    }
    
    public Float getSaldo(){
        return this.saldo;
    }
}