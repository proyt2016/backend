package lcbs.models;
import java.io.Serializable;
import java.util.Date;

import lcbs.shares.*;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlRootElement;


@Embeddable
@Access(AccessType.FIELD)
@XmlRootElement
public class Notificacion implements Serializable{
    private static final long serialVersionUID = 1L;
    
    
    private String mensaje;
    private Date fecha;
 

    public Notificacion() {}
    
    public Notificacion(DataNotificacion dt){
    	this.setMensaje(dt.getMensaje());
    	this.setFecha(dt.getFecha());
    }
    
    public DataNotificacion getDatatype(){
    	DataNotificacion result = new DataNotificacion();
    	result.setMensaje(this.getMensaje());
    	result.setFecha(this.getFecha());
    	return result;
    }
    
  
    public void setMensaje(String val){
        this.mensaje = val;
    }
    
    public String getMensaje(){
        return this.mensaje;
    }
    
    public void setFecha(Date val){
        this.fecha = val;
    }
    
    public Date getFecha(){
        return this.fecha;
    }
}