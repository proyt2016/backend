package lcbs.models;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class MedioDePago implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private String nombre;
    private boolean activo;
    private String cuenta;
    private String usuario;
    private String clave;
    
    

    public MedioDePago() {
        id = "";
        nombre = "";
        activo = false;
        cuenta = "";
        usuario = "";
        clave = "";
    }
    
    public MedioDePago(String id, String nom, boolean, act, String cue, String usu, String clav) {
        this.id = id;
        this.nombre = nom;
        this.activo = act;
        this.cuenta = cue;
        this.usuario = usu;
        this.clave = clav;
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

    public void setActivo(boolean val){
        this.activo = val;
    }
    
    public boolean getActivo(){
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