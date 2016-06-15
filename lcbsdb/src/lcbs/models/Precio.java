package lcbs.models;

import java.io.Serializable;

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