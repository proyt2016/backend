package lcbs.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lcbs.shares.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

@Entity
@XmlRootElement
public class ReglaCobroEncomienda implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    @OneToMany(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @IndexColumn(name="LIST_INDEX")
    private List<ReglaCobroEncomiendaCriteria> criterias;
    
 

    public ReglaCobroEncomienda() {}
    
    public ReglaCobroEncomienda(String id, String nom, List<ReglaCobroEncomiendaCriteria> crit) {
        this.id = id;
        this.nombre = nom;
        this.criterias = crit;
    }
    
    public ReglaCobroEncomienda(DataReglaCobroEncomienda dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	if(dt.getCriterias() != null){
	        List<ReglaCobroEncomiendaCriteria> auxEm = new ArrayList<ReglaCobroEncomiendaCriteria>();
	        dt.getCriterias().stream().forEach((crit) -> {
	        	auxEm.add(new ReglaCobroEncomiendaCriteria(crit));
	        });
	        this.setCriterias(auxEm);
        }
    }
    
    public DataReglaCobroEncomienda getDatatype(){
    	DataReglaCobroEncomienda result = new DataReglaCobroEncomienda();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	if(this.getCriterias() != null){
	    	List<DataReglaCobroEncomiendaCriteria> aux = new ArrayList<DataReglaCobroEncomiendaCriteria>();
	    	this.getCriterias().stream().forEach((crit) -> {
	    		aux.add(crit.getDatatype());
	        });
	    	result.setCriterias(aux);
    	}
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
    
    public void setCriterias(List<ReglaCobroEncomiendaCriteria> val){
        this.criterias = val;
    }
    
    public List<ReglaCobroEncomiendaCriteria> getCriterias(){
        return this.criterias;
    }
   
}