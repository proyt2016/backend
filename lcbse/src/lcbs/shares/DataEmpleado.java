package lcbs.shares;

import java.util.Date;
import java.util.List;

public class DataEmpleado extends DataPersona{
    
    private String idEmpleadoLdap;
    private DataPerfil perfil;

 

    public DataEmpleado() {
        idEmpleadoLdap = "";
    }
    
    public DataEmpleado(String id, String nom, String ape, DataEmail mail, List<DataTelefono> tels, Date fecNac, String idEmpLdap, DataPerfil perf, Boolean elim) {
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

    public void setPerfil(DataPerfil val){
        this.perfil = val;
    }
    
    public DataPerfil getPerfil(){
        return this.perfil;
    }
}