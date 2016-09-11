package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Precio implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
    @ManyToOne(fetch=FetchType.LAZY)
    private PuntoRecorrido origen;
    @ManyToOne(fetch=FetchType.LAZY)
    private PuntoRecorrido destino;
    private Float monto;
     
    public Precio() {
      
    }
    
    public Precio(String id,PuntoRecorrido orig, PuntoRecorrido dest, Float monto) {
    	this.id = id;
    	this.origen = orig;
        this.destino = dest;
        this.monto = monto;
    }
    
    public Precio(DataPrecio dt){
    	this.setId(dt.getId());
    	if(dt.getOrigen() != null){
	    	if(dt.getOrigen() instanceof DataTerminal){
	    		this.setOrigen(new Terminal((DataTerminal)dt.getOrigen()));
	    	}else{
	    		this.setOrigen(new Parada((DataParada)dt.getOrigen()));
	    	}
    	}
    	if(dt.getDestino() != null){
	    	if(dt.getDestino() instanceof DataTerminal){
	    		this.setDestino(new Terminal((DataTerminal)dt.getDestino()));
	    	}else{
	    		this.setDestino(new Parada((DataParada)dt.getDestino()));
	    	}
    	}
    	this.setMonto(dt.getMonto());
    }
    
    public DataPrecio getDatatype(){
    	DataPrecio result = new DataPrecio();
    	result.setId(this.getId());
    	if(this.getOrigen() != null){
    		PuntoRecorrido origen = HibernateUtils.initializeAndUnproxy(this.getOrigen());
	    	if(origen instanceof Terminal){
	    		result.setOrigen(((Terminal)origen).getDatatype());
	    	}else{
	    		result.setOrigen(((Parada)origen).getDatatype());
	    	}
    	}
    	if(this.getDestino() != null){
    		PuntoRecorrido destino = HibernateUtils.initializeAndUnproxy(this.getDestino());
	    	if(destino instanceof Terminal){
	    		result.setDestino(((Terminal)destino).getDatatype());
	    	}else{
	    		result.setDestino(((Parada)destino).getDatatype());
	    	}
    	}
    	result.setMonto(this.getMonto());
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setOrigen(PuntoRecorrido val){
        this.origen = val;
    }
    
    public PuntoRecorrido getOrigen(){
        return this.origen;
    }

    public void setDestino(PuntoRecorrido val){
        this.destino = val;
    }
    
    public PuntoRecorrido getDestino(){
        return this.destino;
    }

    public void setMonto(Float val){
        this.monto = val;
    }
    
    public Float getMonto(){
        return this.monto;
    }

    
}