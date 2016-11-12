package lcbs.shares;

import java.util.List;


public class DataPerfil{
    
	private String id;
    
    private String nombrePerfil;
    private Boolean gestionEncomiendas;
    private Boolean gestionPasajes;
    private Boolean ConfiguracionEmpresa;
    private Boolean gestionReportes;
    private Boolean mantenimientoFlota;
    private List<DataEmpleado> empleados;
    
     

    public DataPerfil() {}
    

    public DataPerfil(String id, String nom, Boolean gestionEncomiendas, Boolean gestionPasajes, Boolean ConfiguracionEmpresa, Boolean gestionResportes, Boolean mantenimientoFlota, List<DataEmpleado> emp) {
        this.id = id;
        this.nombrePerfil = nom;
        this.gestionEncomiendas = gestionEncomiendas;
        this.gestionPasajes = gestionPasajes;
        this.ConfiguracionEmpresa = ConfiguracionEmpresa;
        this.gestionReportes = gestionResportes;
        this.mantenimientoFlota = mantenimientoFlota;
        this.empleados = emp;
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
        this.ConfiguracionEmpresa = val;
    }
    
    public Boolean getConfigruacionEmpresa(){
        return this.ConfiguracionEmpresa;
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

    public void setEmpleados(List<DataEmpleado> val){
        this.empleados = val;
    }
      
    public List<DataEmpleado> getEmpleados(){
        return this.empleados;
    }
}