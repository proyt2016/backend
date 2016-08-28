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
    private Boolean modulo1;
    private Boolean modulo2;
    private Boolean modulo3;
    private Boolean modulo4;
    private Boolean modulo5;
    private Boolean modulo6;
    private Boolean modulo7;
    private Boolean modulo8;
    @OneToMany
    @IndexColumn(name="LIST_INDEX")
    private List<Empleado> empleados;
    
     

    public Perfil() {}
    
    public Perfil(String id, String nom, Boolean mod1, Boolean mod2, Boolean mod3, Boolean mod4, Boolean mod5, Boolean mod6, Boolean mod7, Boolean mod8, List<Empleado> emp) {
        this.id = id;
        this.nombrePerfil = nom;
        this.modulo1 = mod1;
        this.modulo2 = mod2;
        this.modulo3 = mod3;
        this.modulo4 = mod4;
        this.modulo5 = mod5;
        this.modulo6 = mod6;
        this.modulo7 = mod7;
        this.modulo8 = mod8;
        this.empleados = emp;
    }
    
    public Perfil(DataPerfil dt, Boolean conHijos){
    	this.setId(dt.getId());
    	this.setNombrePerfil(dt.getNombre());
    	this.setModulo1(dt.getModulo1());
    	this.setModulo2(dt.getModulo2());
    	this.setModulo3(dt.getModulo3());
    	this.setModulo4(dt.getModulo4());
    	this.setModulo5(dt.getModulo5());
    	this.setModulo6(dt.getModulo6());
    	this.setModulo7(dt.getModulo7());
    	this.setModulo8(dt.getModulo8());
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
    	result.setNombre(this.getNombrePerfil());
    	result.setModulo1(this.getModulo1());
    	result.setModulo2(this.getModulo2());
    	result.setModulo3(this.getModulo3());
    	result.setModulo4(this.getModulo4());
    	result.setModulo5(this.getModulo5());
    	result.setModulo6(this.getModulo6());
    	result.setModulo7(this.getModulo7());
    	result.setModulo8(this.getModulo8());
    	if(this.getEmpleados()!=null && conHijos){
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

    public void setModulo1(Boolean val){
        this.modulo1 = val;
    }
    
    public Boolean getModulo1(){
        return this.modulo1;
    }

    public void setModulo2(Boolean val){
        this.modulo2 = val;
    }
    
    public Boolean getModulo2(){
        return this.modulo2;
    }

    public void setModulo3(Boolean val){
        this.modulo3 = val;
    }
    
    public Boolean getModulo3(){
        return this.modulo3;
    }

    public void setModulo4(Boolean val){
        this.modulo4 = val;
    }
    
    public Boolean getModulo4(){
        return this.modulo4;
    }

    public void setModulo5(Boolean val){
        this.modulo5 = val;
    }
    
    public Boolean getModulo5(){
        return this.modulo5;
    }

    public void setModulo6(Boolean val){
        this.modulo6 = val;
    }
    
    public Boolean getModulo6(){
        return this.modulo6;
    }

    public void setModulo7(Boolean val){
        this.modulo7 = val;
    }
    
    public Boolean getModulo7(){
        return this.modulo7;
    }

    public void setModulo8(Boolean val){
        this.modulo8 = val;
    }
    
    public Boolean getModulo8(){
        return this.modulo8;
    }
    
    public void setEmpleados(List<Empleado> val){
        this.empleados = val;
    }
    
    public List<Empleado> getEmpleados(){
        return this.empleados;
    }
}