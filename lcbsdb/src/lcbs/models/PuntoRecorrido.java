package lcbs.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public abstract class PuntoRecorrido implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private String nombre;
    private String ubicacionMapa;
    
 

    public PuntoRecorrido() {
        id = "";
        nombre = "";
        ubicacionMapa = "";
    }
    
    public PuntoRecorrido(String id, String nom, String uMap) {
        this.id = id;
        this.nombre = nom;
        this.ubicacionMapa = uMap;
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

    
}