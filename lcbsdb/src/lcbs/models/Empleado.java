package lcbs.models;
import java.io.Serializable;
import lcbs.shares.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@XmlRootElement
public class Empleado extends Persona implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    private String idEmpleadoLdap;
    @ManyToOne
    private Perfil perfil;
    

 

    public Empleado() {
        idEmpleadoLdap = "";
    }
    
    public Empleado(String id, String clave, String nm, String ape, Email mail, List<Telefono> tels, Date fecNac, String idEmpLdap, Perfil perf, Boolean elim) {
        super(id,clave, nm, ape, mail, tels, fecNac, elim);
        this.idEmpleadoLdap = idEmpLdap;
        this.perfil = perf;
       
    }
    
    public Empleado(DataEmpleado dt, Boolean conHijos){
    	this.setId(dt.getId());
    	this.setNombrePila(dt.getNombrePila());
    	this.setApellido(dt.getApellido());
    	this.setClave(dt.getClave());
    	
    	if(dt.getEmail() != null)
    		this.setEmail(new Email(dt.getEmail()));
    	if(dt.getTelefonosContacto() != null){
	    	List<Telefono> temp = new ArrayList<Telefono>();
	    	dt.getTelefonosContacto().stream().forEach((tel) -> {
	    		temp.add(new Telefono(tel));
	        });
	    	this.setTelefonosContacto(temp);
    	}
    	this.setFechaNacimiento(dt.getFechaNacimiento());
    	this.setEliminado(dt.getEliminado());
    	this.setIdEmpleadoLdap(dt.getIdEmpleadoLdap());
    	if(dt.getPerfil() != null && conHijos){
    		this.setPerfil(new Perfil(dt.getPerfil(), false));
    	}
    }
    
    public DataEmpleado getDatatype(Boolean conHijos){
    	DataEmpleado result = new DataEmpleado();
    	result.setId(this.getId());
    	result.setNombrePila(this.getNombrePila());
    	result.setApellido(this.getApellido());
    	result.setClave(this.getClave());
    	if(this.getEmail()!=null)
    		result.setEmail(this.getEmail().getDatatype());
    	if(this.getTelefonosContacto()!=null && conHijos){
	    	List<DataTelefono> temp = new ArrayList<DataTelefono>();
	    	this.getTelefonosContacto().stream().forEach((tel) -> {
	    		temp.add(tel.getDatatype());
	        });
	    	result.setTelefonosContacto(temp);
    	}
    	result.setFechaNacimiento(this.getFechaNacimiento());
    	result.setEliminado(this.getEliminado());
    	result.setIdEmpleadoLdap(this.getIdEmpleadoLdap());
    	if(this.getPerfil()!=null)
    		result.setPerfil(this.getPerfil().getDatatype(false));
    	return result;
    }

    public void setIdEmpleadoLdap(String val){
        this.idEmpleadoLdap = val;
    }
    
    public String getIdEmpleadoLdap(){
        return this.idEmpleadoLdap;
    }
    
    public void setPerfil(Perfil val){
        this.perfil = val;
    }
    
    public Perfil getPerfil(){
        return this.perfil;
    }
}