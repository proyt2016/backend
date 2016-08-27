package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;


@Entity
@XmlRootElement
public class MantenimientoVehiculo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String descripcionReducida;
    private String descripcionCompleta;
    private Float costo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompleado;
    
    

    public MantenimientoVehiculo() {}
    
    public MantenimientoVehiculo(String id, String decRes, String descComp, Float costo, Date fecIng, Date fecComp) {
        this.id = id;
        this.descripcionReducida = decRes;
        this.descripcionCompleta = descComp;
        this.costo = costo;
        this.fechaIngreso = fecIng;
        this.fechaCompleado = fecComp;
    }
    
    public MantenimientoVehiculo(DataMantenimientoVehiculo dt){
    	this.setId(dt.getId());
    	this.setDescripcionReducida(dt.getDescripcionReducida());
    	this.setDescripcionCompleta(dt.getDescripcionCompleta());
    	this.setCosto(dt.getCosto());
    	this.setFechaIngreso(dt.getFechaIngreso());
    	this.setFechaCompleado(dt.getFechaCompleado());
    }
    
    public DataMantenimientoVehiculo getDatatype(){
    	DataMantenimientoVehiculo result = new DataMantenimientoVehiculo();
    	result.setId(this.getId());
    	result.setDescripcionReducida(this.getDescripcionReducida());
    	result.setDescripcionCompleta(this.getDescripcionCompleta());
    	result.setCosto(this.getCosto());
    	result.setFechaIngreso(this.getFechaIngreso());
    	result.setFechaCompleado(this.getFechaCompleado());
    	return result;
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