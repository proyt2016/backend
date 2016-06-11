package lcbs.models;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@XmlRootElement
public class Recorrido implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String nombre;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="RecorridoId")
    private List<PuntoRecorrido> puntosDeRecorrido;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="RecorridoId")
    private List<GrupoHorario> horarios;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="RecorridoId")
    private List<Precio> precios;
    
 

    public Recorrido() {}
    
    public Recorrido(String id, String nom, List<PuntoRecorrido> punRec, List<GrupoHorario> hor, List<Precio> prec) {
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

    public void setHorarios(List<GrupoHorario> val){
        this.horarios = val;
    }
    
    public List<GrupoHorario> getHorarios(){
        return this.horarios;
    }

    public void setPrecios(List<Precio> val){
        this.precios = val;
    }
    
    public List<Precio> getPrecios(){
        return this.precios;
    }

    
}