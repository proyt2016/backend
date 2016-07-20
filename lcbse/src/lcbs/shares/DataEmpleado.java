package lcbs.shares;

import java.util.Date;
import java.util.List;

public class DataEmpleado extends DataPersona{
    
    private String idEmpleadoLdap;
    private DataPerfil perfil;
    private String clave;

 

    public DataEmpleado() {
        idEmpleadoLdap = "";
    }
    
    public DataEmpleado(String id, String clave,String nm, String ape, DataEmail mail, List<DataTelefono> tels, Date fecNac, String idEmpLdap, DataPerfil perf, Boolean elim) {
        super.setId(id);
        super.setNombrePila(nm);
        super.setApellido(ape);
        super.setEmail(mail);
        super.setTelefonosContacto(tels);
        super.setFechaNacimiento(fecNac);
        super.setEliminado(elim);
        this.clave = clave;
        this.idEmpleadoLdap = idEmpLdap;
        this.perfil = perf;
        
    }
 
    public void setIdEmpleadoLdap(String val){
        this.idEmpleadoLdap = val;
    }
    
    public String getIdEmpleadoLdap(){
        return this.idEmpleadoLdap;
    }

    public void setPerfil(DataPerfil val){
        this.perfil = val;
    }
    
    public String getClave(){
    	return this.clave;
    }
    
    public void setClave(String c){
    	this.clave = c;
    }
    
    public DataPerfil getPerfil(){
        return this.perfil;
    }
}