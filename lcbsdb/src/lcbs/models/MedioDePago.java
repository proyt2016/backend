package lcbs.models;
import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class MedioDePago implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private Boolean activo;
    private String cuenta;
    private String usuario;
    private String clave;
    
    

    public MedioDePago() { }
    
    public MedioDePago(String id, String nom, Boolean act, String cue, String usu, String clav) {
        this.id = id;
        this.nombre = nom;
        this.activo = act;
        this.cuenta = cue;
        this.usuario = usu;
        this.clave = clav;
    }
    
    public MedioDePago(DataMedioDePago dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	this.setActivo(dt.getActivo());
    	this.setCuenta(dt.getClave());
    	this.setUsuario(dt.getUsuario());
    	this.setClave(dt.getClave());
    }
    
    public DataMedioDePago getDatatype(){
    	DataMedioDePago result = new DataMedioDePago();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setActivo(this.getActivo());
    	result.setCuenta(this.getCuenta());
    	result.setUsuario(this.getUsuario());
    	result.setClave(this.getClave());
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setActivo(Boolean val){
        this.activo = val;
    }
    
    public Boolean getActivo(){
        return this.activo;
    }

    public void setCuenta(String val){
        this.cuenta = val;
    }
    
    public String getCuenta(){
        return this.cuenta;
    }

    public void setUsuario(String val){
        this.usuario = val;
    }
    
    public String getUsuario(){
        return this.usuario;
    }

    public void setClave(String val){
        this.clave = val;
    }
    
    public String getClave(){
        return this.clave;
    }
}