package lcbs.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class MantenimientoVehiculo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String descripcionReducida;
    private String descripcionCompleta;
    private float costo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIgreso;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompleado;
    
    

    public MantenimientoVehiculo() {}
    
    public MantenimientoVehiculo(String id, String decRes, String descComp, float costo, Date fecIng, Date fecComp) {
        this.id = id;
        this.descripcionReducida = decRes;
        this.descripcionCompleta = descComp;
        this.costo = costo;
        this.fechaIgreso = fecIng;
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

    public void setCosto(float val){
        this.costo = val;
    }
    
    public float getCosto(){
        return this.costo;
    }

    public void setFechaIgreso(Date val){
        this.fechaIgreso = val;
    }
    
    public Date getFechaIgreso(){
        return this.fechaIgreso;
    }

    public void setFechaCompleado(Date val){
        this.fechaCompleado = val;
    }
    
    public Date getFechaCompleado(){
        return this.fechaCompleado;
    }
}