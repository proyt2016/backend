package lcbs.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import lcbs.shares.*;


@Entity
@XmlRootElement
public class HistorialEstadosEncomienda implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private EstadosEncomienda estado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
 

    public HistorialEstadosEncomienda() {}
    
    public HistorialEstadosEncomienda(String id, EstadosEncomienda est, Date fec) {
        this.id = id;
        this.estado = est;
        this.fecha = fec;
    }
    
    public HistorialEstadosEncomienda(DataHistorialEstadosEncomienda dt){
    	this.setId(dt.getId());
    	if(dt.getEstado() != null){
    		this.setEstado(new EstadosEncomienda(dt.getEstado()));
    	}
    	this.setFecha(dt.getFecha());
    }
    
    public DataHistorialEstadosEncomienda getDatatype(){
    	DataHistorialEstadosEncomienda result = new DataHistorialEstadosEncomienda();
    	result.setId(this.getId());
    	if(this.getEstado()!=null)
    		result.setEstado(this.getEstado().getDatatype());
    	result.setFecha(this.getFecha());
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setEstado(EstadosEncomienda val){
        this.estado = val;
    }
    
    public EstadosEncomienda getEstado(){
        return this.estado;
    }
    
    public void setFecha(Date val){
        this.fecha = val;
    }
    
    public Date getFecha(){
        return this.fecha;
    }
}