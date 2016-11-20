package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class Perfil implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombrePerfil;
    private Boolean gestionEncomiendas;
    private Boolean gestionPasajes;
    private Boolean configuracionEmpresa;
    private Boolean gestionReportes;
    private Boolean mantenimientoFlota;

    @OneToMany
    @IndexColumn(name="LIST_INDEX")
    private List<Empleado> empleados;
    
    public Perfil() {}
    
    public Perfil(String id, String nom, Boolean gestionEncomiendas, Boolean gestionPasajes, Boolean ConfiguracionEmpresa, Boolean gestionResportes, Boolean mantenimientoFlota, List<Empleado> emp) {
        this.id = id;
        this.nombrePerfil = nom;
        this.gestionEncomiendas = gestionEncomiendas;
        this.gestionPasajes = gestionPasajes;
        this.configuracionEmpresa = ConfiguracionEmpresa;
        this.gestionReportes = gestionResportes;
        this.mantenimientoFlota = mantenimientoFlota;
        this.empleados = emp;
    }
    
    public Perfil(DataPerfil dt, Boolean conHijos){
    	this.setId(dt.getId());
    	this.setNombrePerfil(dt.getNombrePerfil());
    	this.setGestionEncomiendas(dt.getGestionEncomiendas());
    	this.setGestionPasajes(dt.getGestionPasajes());
    	this.setConfiguracionEmpresa(dt.getConfiguracionEmpresa());
    	this.setGestionReportes(dt.getGestionReportes());
    	this.setMantenimientoFlota(dt.getMantenimientoFlota());
    	if(dt.getEmpleados() != null && conHijos){
	    	List<Empleado> aux = new ArrayList<Empleado>();
	    	dt.getEmpleados().stream().forEach((emp) -> {
	    		aux.add(new Empleado(emp, false));
	        });
	    	this.setEmpleados(aux);
    	}
    }
    
    public DataPerfil getDatatype(Boolean conHijos){
    	DataPerfil result = new DataPerfil();
    	result.setId(this.getId());
    	result.setNombrePerfil(this.getNombrePerfil());
    	result.setGestionEncomiendas((this.getGestionEncomiendas()));
    	result.setGestionPasajes(this.getGestionPasajes());
    	result.setConfiguracionEmpresa(this.getConfiguracionEmpresa());
    	result.setGestionReportes(this.getGestionReportes());
    	result.setMantenimientoFlota(this.getMantenimientoFlota());
    	if(result.getEmpleados()!=null && conHijos){
    	List<DataEmpleado> aux = new ArrayList<DataEmpleado>();
    	this.getEmpleados().stream().forEach((emp) -> {
    		aux.add(emp.getDatatype(false));
        });
    	result.setEmpleados(aux);}
    	return result;
    }
    
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setNombrePerfil(String val){
        this.nombrePerfil = val;
    }
    
    public String getNombrePerfil(){
        return this.nombrePerfil;
    }

    public void setGestionEncomiendas(Boolean val){
        this.gestionEncomiendas = val;
    }
    
    public Boolean getGestionEncomiendas(){
        return this.gestionEncomiendas;
    }

    public void setGestionPasajes(Boolean val){
        this.gestionPasajes = val;
    }
    
    public Boolean getGestionPasajes(){
        return this.gestionPasajes;
    }

    public void setConfiguracionEmpresa(Boolean val){
        this.configuracionEmpresa = val;
    }
    
    public Boolean getConfiguracionEmpresa(){
        return this.configuracionEmpresa;
    }

    public void setGestionReportes(Boolean val){
        this.gestionReportes = val;
    }
    
    public Boolean getGestionReportes(){
        return this.gestionReportes;
    }

    public void setMantenimientoFlota(Boolean val){
        this.mantenimientoFlota = val;
    }
    
    public Boolean getMantenimientoFlota(){
        return this.mantenimientoFlota;
    }

    public void setEmpleados(List<Empleado> val){
        this.empleados = val;
    }
    
    public List<Empleado> getEmpleados(){
        return this.empleados;
    }
}