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
public abstract class PuntoRecorrido implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private String ubicacionMapa;
    private Boolean eliminado;
    
 

    public PuntoRecorrido() {}
    
    public PuntoRecorrido(String id, String nom, String uMap, Boolean elim) {
        this.id = id;
        this.nombre = nom;
        this.ubicacionMapa = uMap;
        this.eliminado = elim;
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

    public void setUbicacionMapa(String val){
        this.ubicacionMapa = val;
    }
    
    public String getUbicacionMapa(){
        return this.ubicacionMapa;
    }

    public void setEliminado(Boolean val){
        this.eliminado = val;
    }
    
    public Boolean getEliminado(){
        return this.eliminado;
    }
}