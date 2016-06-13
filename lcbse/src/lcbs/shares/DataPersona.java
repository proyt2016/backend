package lcbs.shares;

import java.util.Date;
import java.util.List;

public abstract class DataPersona{
    
	private String id;
    
    private String nombre;
    private String apellido;
    private DataEmail email;
    private List<DataTelefono> telefonosContacto;
    private Date fechaNacimiento;
    private boolean eliminado;
     

    public DataPersona() {}
    
    public DataPersona(String id, String nom, String ape, DataEmail mail, List<DataTelefono> tels, Date fecNac, Boolean elim) {
        this.id = id;
        this.nombre = nom;
        this.apellido = ape;
        this.email = mail;
        this.telefonosContacto = tels;
        this.fechaNacimiento = fecNac;
        this.eliminado = elim;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setApellido(String val){
        this.apellido = val;
    }
    
    public String getApellido(){
        return this.apellido;
    }

    public void setDataEmail(DataEmail val){
        this.email = val;
    }
    
    public DataEmail getDataEmail(){
        return this.email;
    }

    public void setDataTelefonosContacto(List<DataTelefono> val){
        this.telefonosContacto = val;
    }
    
    public List<DataTelefono> getDataTelefonosContacto(){
        return this.telefonosContacto;
    }

    public void setFechaNacimiento(Date val){
        this.fechaNacimiento = val;
    }
    
    public Date getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    

    public void setEliminado(Boolean val){
    	this.eliminado = val;
    }
    
    public Boolean getEliminado(){
    	return this.eliminado;
    }
}