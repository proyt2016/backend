package lcbs.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Perfil implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private String nombre;
    private boolean modulo1;
    private boolean modulo2;
    private boolean modulo3;
    private boolean modulo4;
    private boolean modulo5;
    private boolean modulo6;
    private boolean modulo7;
    private boolean modulo8;
    
     

    public Perfil() {
        id = "";
        nombre = "";
        modulo1 = false;
        modulo2 = false;
        modulo3 = false;
        modulo4 = false;
        modulo5 = false;
        modulo6 = false;
        modulo7 = false;
        modulo8 = false;
    }
    
    public Perfil(String id, String nom, boolean mod1, boolean mod2, boolean mod3, boolean mod4, boolean mod5, boolean mod6, boolean mod7, boolean mod8) {
        this.id = id;
        this.nombre = nom;
        this.modulo1 = mod1;
        this.modulo2 = mod2;
        this.modulo3 = mod3;
        this.modulo4 = mod4;
        this.modulo5 = mod5;
        this.modulo6 = mod6;
        this.modulo7 = mod7;
        this.modulo8 = mod8;
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

    public void setModulo1(boolean val){
        this.modulo1 = val;
    }
    
    public boolean getModulo1(){
        return this.modulo1;
    }

    public void setModulo2(boolean val){
        this.modulo2 = val;
    }
    
    public boolean getModulo2(){
        return this.modulo2;
    }

    public void setModulo3(boolean val){
        this.modulo3 = val;
    }
    
    public boolean getModulo3(){
        return this.modulo3;
    }

    public void setModulo4(boolean val){
        this.modulo4 = val;
    }
    
    public boolean getModulo4(){
        return this.modulo4;
    }

    public void setModulo5(boolean val){
        this.modulo5 = val;
    }
    
    public boolean getModulo5(){
        return this.modulo5;
    }

    public void setModulo6(boolean val){
        this.modulo6 = val;
    }
    
    public boolean getModulo6(){
        return this.modulo6;
    }

    public void setModulo7(boolean val){
        this.modulo7 = val;
    }
    
    public boolean getModulo7(){
        return this.modulo7;
    }

    public void setModulo8(boolean val){
        this.modulo8 = val;
    }
    
    public boolean getModulo8(){
        return this.modulo8;
    }
}