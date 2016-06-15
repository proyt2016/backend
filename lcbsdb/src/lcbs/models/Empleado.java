package lcbs.models;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    
    public Empleado(String id, String ape, Email mail, List<Telefono> tels, Date fecNac, String idEmpLdap, Perfil perf, Boolean elim) {
        super(id, ape, mail, tels, fecNac, elim);
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