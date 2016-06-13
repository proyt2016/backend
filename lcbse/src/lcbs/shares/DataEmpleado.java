package lcbs.shares;

public class DataEmpleado extends DataPersona{
    
    private String idEmpleadoLdap;
    private DataPerfil perfil;

 

    public DataEmpleado() {
        idEmpleadoLdap = "";
    }
    
    public DataEmpleado(String id, String nom, String ape, Email mail, List<Telefono> tels, Date fecNac, String idEmpLdap, DataPerfil perf) {
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

    public void setDataPerfil(DataPerfil val){
        this.perfil = val;
    }
    
    public DataPerfil getDataPerfil(){
        return this.perfil;
    }
}