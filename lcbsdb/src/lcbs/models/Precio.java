package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Precio implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private PuntoRecorrido origen;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private PuntoRecorrido destino;
    private float monto;
     
    public Precio() {
      
    }
    
    public Precio(PuntoRecorrido orig, PuntoRecorrido dest, float monto) {
        this.origen = orig;
        this.destino = dest;
        this.monto = monto;
    }
    
    public Precio(DataPrecio dt){
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
    	if(this.getOrigen() instanceof Terminal){
    		result.setOrigen(((Terminal)this.getOrigen()).getDatatype());
    	}else{
    		result.setOrigen(((Parada)this.getOrigen()).getDatatype());
    	}
    	if(this.getDestino() instanceof Terminal){
    		result.setDestino(((Terminal)this.getDestino()).getDatatype());
    	}else{
    		result.setDestino(((Parada)this.getDestino()).getDatatype());
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

    public void setMonto(float val){
        this.monto = val;
    }
    
    public float getMonto(){
        return this.monto;
    }

    
}