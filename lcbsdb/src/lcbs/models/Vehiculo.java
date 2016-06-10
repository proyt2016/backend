package lcbs.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Vehiculo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    private String numeroVehiculo;
    private String matricula;
    private String marca;
    private String modelo;
    private Integer anioFabricacion;
    private Date fechaAlta;
    private Integer cantidadAsientos;
    private Boolean conGuarda;
    private List<MantenimientoVehiculo> mantenimientos;
   

    public Vehiculo() {
        id = "";
        numeroVehiculo = "";
        matricula = "";
        marca = "";
        modelo = "";
        anioFabricacion = 0;
        fechaAlta = new Date();
        cantidadAsientos = 0;
        conGuarda = false;
        mantenimientos = new List<MantenimientoVehiculo>();
    }
    
    public Vehiculo(String id, String numV, String mat, String mar, String mod, Integer anioFab, Date fecAlta, Integer cantAs, Boolean conG, List<MantenimientoVehiculo> mant) {
        this.id = id;
        this.numeroVehiculo = numV;
        this.matricula = mat;
        this.marca = mar;
        this.modelo = mod;
        this.anioFabricacion = anioFab;
        this.fechaAlta = fecAlta;
        this.cantidadAsientos = cantAs;
        this.conGuarda = conG;
        this.mantenimientos = mant;
    }

    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setNumeroVehiculo(String val){
        this.numeroVehiculo = val;
    }
    
    public String getNumeroVehiculo(){
        return this.numeroVehiculo;
    }

    public void setMatricula(String val){
        this.matricula = val;
    }
    
    public String getMatricula(){
        return this.matricula;
    }

    public void setMarca(String val){
        this.marca = val;
    }
    
    public String getMarca(){
        return this.marca;
    }

    public void setModelo(String val){
        this.modelo = val;
    }
    
    public String getModelo(){
        return this.modelo;
    }

    public void setAnioFabricacion(Integer val){
        this.anioFabricacion = val;
    }
    
    public Integer getAnioFabricacion(){
        return this.anioFabricacion;
    }

    public void setFechaAlta(Date val){
        this.fechaAlta = val;
    }
    
    public Date getFechaAlta(){
        return this.fechaAlta;
    }

    public void setCantidadAsientos(Integer val){
        this.cantidadAsientos = val;
    }
    
    public Integer getCantidadAsientos(){
        return this.cantidadAsientos;
    }

    public void setConGuarda(Boolean val){
        this.conGuarda = val;
    }
    
    public Boolean getConGuarda(){
        return this.conGuarda;
    }

    public void setMantenimientos(List<MantenimientoVehiculo> val){
        this.mantenimientos = val;
    }
    
    public List<MantenimientoVehiculo> getMantenimientos(){
        return this.mantenimientos;
    }   
}