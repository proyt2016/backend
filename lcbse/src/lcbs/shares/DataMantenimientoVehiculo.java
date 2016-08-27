package lcbs.shares;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataMantenimientoVehiculo{
    
	private String id;
    
    private String descripcionReducida;
    private String descripcionCompleta;
    private Float costo;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaIngreso;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaCompleado;
    
    

    public DataMantenimientoVehiculo() {}
    
    public DataMantenimientoVehiculo(String id, String decRes, String descComp, Float costo, Date fecIng, Date fecComp) {
        this.id = id;
        this.descripcionReducida = decRes;
        this.descripcionCompleta = descComp;
        this.costo = costo;
        this.fechaIngreso = fecIng;
        this.fechaCompleado = fecComp;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setDescripcionReducida(String val){
        this.descripcionReducida = val;
    }
    
    public String getDescripcionReducida(){
        return this.descripcionReducida;
    }

    public void setDescripcionCompleta(String val){
        this.descripcionCompleta = val;
    }
    
    public String getDescripcionCompleta(){
        return this.descripcionCompleta;
    }

    public void setCosto(Float val){
        this.costo = val;
    }
    
    public Float getCosto(){
        return this.costo;
    }

    public void setFechaIngreso(Date val){
        this.fechaIngreso = val;
    }
    
    public Date getFechaIngreso(){
        return this.fechaIngreso;
    }

    public void setFechaCompleado(Date val){
        this.fechaCompleado = val;
    }
    
    public Date getFechaCompleado(){
        return this.fechaCompleado;
    }
}