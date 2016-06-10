package lcbs.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Cuponera implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private Usuario usuario;
    private float saldo;
 

    public Cuponera() {
        id = "";
        saldo = 0.0f;
    }
    
    public Cuponera(String id, float saldo) {
        this.id = id;
        this.saldo = saldo;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setSaldo(float val){
        this.saldo = val;
    }
    
    public float getSaldo(){
        return this.saldo;
    }
}