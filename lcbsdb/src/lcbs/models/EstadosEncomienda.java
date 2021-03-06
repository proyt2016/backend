package lcbs.models;

import java.io.Serializable;

import javax.persistence.Id;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.mapping.List;

import lcbs.shares.DataEstadosEncomienda;

@Entity
@XmlRootElement
public class EstadosEncomienda implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    
 

    public EstadosEncomienda() {
        id = "";
        nombre = "";
    }
    
    public EstadosEncomienda(String id, String nom) {
        this.id = id;
        this.nombre = nom;
    }
    
    public EstadosEncomienda(DataEstadosEncomienda dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    }
    
    public DataEstadosEncomienda getDatatype(){
    	DataEstadosEncomienda result = new DataEstadosEncomienda();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	return result;
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