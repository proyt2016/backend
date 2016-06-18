package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Parada extends PuntoRecorrido implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    public Parada() {}
    
    public Parada(String id, String nom, String uMap, Boolean elim) {
        super(id, nom, uMap, elim);
    }
    
    public Parada(DataParada dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	this.setUbicacionMapa(dt.getUbicacionMapa());
    	this.setEliminado(dt.getEliminado());
    }
    
    public DataParada getDatatype(){
    	DataParada result = new DataParada();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setUbicacionMapa(this.getUbicacionMapa());
    	result.setEliminado(this.getEliminado());
    	return result;
    }
    
}