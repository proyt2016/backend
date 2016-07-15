package lcbs.shares;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataNotificacion{
    
	private String mensaje;
	@XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
	private Date fecha;
    
 

    public DataNotificacion() {}
    
    public DataNotificacion(String not, Date fec) {
        this.mensaje = not;
        this.fecha = fec;
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