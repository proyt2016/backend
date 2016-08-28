package lcbs.shares;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataVehiculo{
    
	private String id;

    private String numeroVehiculo;
    private String matricula;
    private String marca;
    private String modelo;
    private Integer anioFabricacion;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaAlta;
    private Integer cantidadAsientos;
    private Boolean conGuarda;
    private List<DataMantenimientoVehiculo> mantenimientos;
    private Boolean eliminado;
    private List<DataEncomienda> encomiendas;
   

    public DataVehiculo() {}
    
    public DataVehiculo(String id, String numV, String mat, String mar, String mod, Integer anioFab, Date fecAlta, Integer cantAs, Boolean conG, List<DataMantenimientoVehiculo> mant, Boolean elim, List<DataEncomienda> enc) {
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
        this.eliminado = elim;
        this.encomiendas = enc;
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

    public void setMantenimientos(List<DataMantenimientoVehiculo> val){
        this.mantenimientos = val;
    }
    
    public List<DataMantenimientoVehiculo> getMantenimientos(){
        return this.mantenimientos;
    }  
    
    public void setEliminado(Boolean val){
        this.eliminado = val;
    }
    
    public Boolean getEliminado(){
        return this.eliminado;
    }
    
    public void setEncomiendas(List<DataEncomienda> val){
        this.encomiendas = val;
    }
    
    public List<DataEncomienda> getEncomiendas(){
        return this.encomiendas;
    }
}