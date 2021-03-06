package lcbs.models;
import java.io.Serializable;

import lcbs.beans.EntityManagerProducer;
import lcbs.shares.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class Recorrido implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    @ManyToMany(fetch=FetchType.LAZY)
    @IndexColumn(name="LIST_INDEX")
    private List<PuntoRecorrido> puntosDeRecorrido;
    @OneToMany(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @IndexColumn(name="LIST_INDEX")
    private List<GrupoHorario> horarios;
    @OneToMany(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @IndexColumn(name="LIST_INDEX")
    private List<Precio> precios;
    private Boolean eliminado;
    
 

    public Recorrido() {}
    
    public Recorrido(String id, String nom, List<PuntoRecorrido> punRec, List<GrupoHorario> hor, List<Precio> prec, Boolean elim) {
        this.id = id;
        this.nombre = nom;
        this.puntosDeRecorrido = punRec;
        this.horarios = hor;
        this.precios = prec;
        this.eliminado = elim;
    }
    
    public Recorrido(DataRecorrido dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	if(dt.getPuntosDeRecorrido() != null){
	    	List<PuntoRecorrido> auxPr = new ArrayList<PuntoRecorrido>();
	    	dt.getPuntosDeRecorrido().stream().forEach((pr) -> {
	    		if(pr instanceof DataTerminal){
	    			auxPr.add(new Terminal((DataTerminal)pr));
	        	}else{
	        		auxPr.add(new Parada((DataParada)pr));
	        	}
	        });
	    	this.setPuntosDeRecorrido(auxPr);
    	}
    	if(dt.getHorarios() != null){
	    	List<GrupoHorario> auxHr = new ArrayList<GrupoHorario>();
	    	dt.getHorarios().stream().forEach((hr) -> {
	    		auxHr.add(new GrupoHorario(hr));
	        });
	    	this.setHorarios(auxHr);
    	}
    	if(dt.getPrecios() != null){
	    	List<Precio> aux = new ArrayList<Precio>();
	    	dt.getPrecios().stream().forEach((pr) -> {
	    		aux.add(new Precio(pr));
	        });
	    	this.setPrecios(aux);
    	}
    	this.setEliminado(dt.getEliminado());
    }
    
    public DataRecorrido getDatatype(Boolean conHijos){
    	DataRecorrido result = new DataRecorrido();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	List<PuntoRecorrido> temp = this.getPuntosDeRecorrido();
    	if(temp != null && temp.size()>0 && conHijos){
	    	List<DataPuntoRecorrido> auxPr = new ArrayList<DataPuntoRecorrido>();
	    	temp.stream().forEach((pr) -> {
	    		pr = HibernateUtils.initializeAndUnproxy(pr);
	    		if(pr instanceof Terminal){
	    			auxPr.add(((Terminal)pr).getDatatype());
	        	}else{
	        		auxPr.add(((Parada)pr).getDatatype());
	        	}
	        });
	    	result.setPuntosDeRecorrido(auxPr);
    	}
    	if(this.getHorarios()!=null && conHijos){
	    	List<DataGrupoHorario> auxHr = new ArrayList<DataGrupoHorario>();
	    	this.getHorarios().stream().forEach((hr) -> {
	    		auxHr.add(hr.getDatatype(true));
	        });
	    	result.setHorarios(auxHr);
    	}
    	if(this.getPrecios()!=null && conHijos){
	    	List<DataPrecio> aux = new ArrayList<DataPrecio>();
	    	this.getPrecios().stream().forEach((pr) -> {
	    		aux.add(pr.getDatatype());
	        });
	    	result.setPrecios(aux);
    	}
    	result.setEliminado(this.getEliminado());
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

    public void setPuntosDeRecorrido(List<PuntoRecorrido> val){
        this.puntosDeRecorrido = val;
    }
    
    public List<PuntoRecorrido> getPuntosDeRecorrido(){
        return this.puntosDeRecorrido;
    }

    public void setHorarios(List<GrupoHorario> val){
        this.horarios = val;
    }
    
    public List<GrupoHorario> getHorarios(){
        return this.horarios;
    }

    public void setPrecios(List<Precio> val){
        this.precios = val;
    }
    
    public List<Precio> getPrecios(){
        return this.precios;
    }

    public void setEliminado(Boolean val){
        this.eliminado = val;
    }
    
    public Boolean getEliminado(){
        return this.eliminado;
    }
}