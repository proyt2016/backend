package lcbs.models;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Recorrido implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private String nombre;
    private List<PuntoRecorrido> puntosDeRecorrido;
    private List<GrupoHorario> horarios;
    private List<Precio> precios;
    
 

    public Recorrido() {
        id = "";
        nombre = "";
        puntosDeRecorrido = List<PuntosRecorrido>();
        horarios = new List<Horario>();
        precios = new List<Precio>();
    }
    
    public Recorrido(String id, String nom, List<PuntoRecorrido> punRec, List<Horario> hor, List<Precio> prec) {
        this.id = id;
        this.nombre = nom;
        this.puntosDeRecorrido = punRec;
        this.horarios = hor;
        this.precios = prec;
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

    public void setPuntosDeRecorrido(List<PuntoRecorrido> val){
        this.puntosDeRecorrido = val;
    }
    
    public List<PuntoRecorrido> getPuntosDeRecorrido(){
        return this.puntosDeRecorrido;
    }

    public void setHorarios(List<Horario> val){
        this.horarios = val;
    }
    
    public List<Horario> getHorarios(){
        return this.horarios;
    }

    public void setPrecios(List<Precio> val){
        this.precios = val;
    }
    
    public List<Precio> getPrecios(){
        return this.precios;
    }

    
}