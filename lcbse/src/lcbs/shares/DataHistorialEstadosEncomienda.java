package lcbs.shares;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataHistorialEstadosEncomienda{
    
	private String id;
    private DataEstadosEncomienda estado;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
 

    public DataHistorialEstadosEncomienda() {}
    
    public DataHistorialEstadosEncomienda(String id, DataEstadosEncomienda est, Date fec) {
        this.id = id;
        this.estado = est;
        this.fecha = fec;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setEstado(DataEstadosEncomienda val){
        this.estado = val;
    }
    
    public DataEstadosEncomienda getEstado(){
        return this.estado;
    }
    
    public void setFecha(Date val){
        this.fecha = val;
    }
    
    public Date getFecha(){
        return this.fecha;
    }
}