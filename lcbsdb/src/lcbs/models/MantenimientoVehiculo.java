package lcbs.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class MantenimientoVehiculo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private String descripcionReducida;
    private String descripcionCompleta
    private float costo;
    private DateTime fechaIgreso;
    private DateTime fechaCompleado
    
    

    public MantenimientoVehiculo() {
        id = "";
        descripcionReducida = "";
        descripcionCompleta = "";
        costo = 0.0f;
        fechaIgreso = new DateTime();
        fechaCompleado = new DateTime();
    }
    
    public MantenimientoVehiculo(String id, String decRes, String descComp, float costo, DateTome fecIng, DateTime fecComp) {
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

    public void setFechaIgreso(DateTome val){
        this.fechaIgreso = val;
    }
    
    public DateTome getFechaIgreso(){
        return this.fechaIgreso;
    }

    public void setFechaCompleado(DateTome val){
        this.fechaCompleado = val;
    }
    
    public DateTome getFechaCompleado(){
        return this.fechaCompleado;
    }
}