package lcbs.models;
import java.io.Serializable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Empleado extends Persona implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    private String idEmpleadoLdap;
    private Perfil perfil;

 

    public Empleado() {
        idEmpleadoLdap = "";
    }
    
    public Empleado(String id, String nom, String ape, Email mail, List<Telefono> tels, Date fecNac, String idEmpLdap, Perfil perf) {
        super(id, nom, ape, mail, tels, fecNac);
        this.idEmpleadoLdap = idEmpLdap;
        this.perfil = perf;
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