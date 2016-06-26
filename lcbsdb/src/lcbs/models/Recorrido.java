package lcbs.models;
import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

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
    @OneToMany(fetch=FetchType.EAGER)
    @IndexColumn(name="LIST_INDEX")
    private List<PuntoRecorrido> puntosDeRecorrido;
    @OneToMany(fetch=FetchType.EAGER)
    @IndexColumn(name="LIST_INDEX")
    private List<GrupoHorario> horarios;
    @OneToMany(fetch=FetchType.EAGER)
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
    
    public DataRecorrido getDatatype(){
    	DataRecorrido result = new DataRecorrido();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	if(this.getPuntosDeRecorrido()!=null){
    	List<DataPuntoRecorrido> auxPr = new ArrayList<DataPuntoRecorrido>();
    	this.getPuntosDeRecorrido().stream().forEach((pr) -> {
    		if(pr instanceof Terminal){
    			auxPr.add(((Terminal)pr).getDatatype());
        	}else{
        		auxPr.add(((Parada)pr).getDatatype());
        	}
        });
    	result.setPuntosDeRecorrido(auxPr);
    	}
    	if(this.getHorarios()!=null){
    	List<DataGrupoHorario> auxHr = new ArrayList<DataGrupoHorario>();
    	this.getHorarios().stream().forEach((hr) -> {
    		auxHr.add(hr.getDatatype());
        });
    	result.setHorarios(auxHr);}
    	if(this.getPrecios()!=null){
    	List<DataPrecio> aux = new ArrayList<DataPrecio>();
    	this.getPrecios().stream().forEach((pr) -> {
    		aux.add(pr.getDatatype());
        });
    	result.setPrecios(aux);}
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