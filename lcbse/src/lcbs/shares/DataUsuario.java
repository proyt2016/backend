package lcbs.shares;

import java.util.Date;
import java.util.List;

public class DataUsuario extends DataPersona{
    private String redSocialUsada;
    private String idRedSocial;
    private DataCuponera cuponera;
    private List<DataEncomienda> encomiendas;
    private List<DataNotificacion> notificaciones;

 

    public DataUsuario() {}
    
    public DataUsuario(String id, String nm, String ape, DataEmail mail, List<DataTelefono> tels, Date fecNac, Boolean elim, String nomMos, String redSoc, String idRedsoc, DataCuponera cup, List<DataEncomienda> enc, List<DataNotificacion> not) {
    	super.setId(id);
        super.setNombrePila(nm);
        super.setApellido(ape);
        super.setEmail(mail);
        super.setTelefonosContacto(tels);
        super.setFechaNacimiento(fecNac);
        super.setEliminado(elim);
        this.redSocialUsada = redSoc;
        this.idRedSocial = idRedsoc;
        this.cuponera = cup;
        this.encomiendas = enc;
        this.notificaciones = not;
    }

    public void setRedSocialUsada(String val){
        this.redSocialUsada = val;
    }
    
    public String getRedSocialUsada(){
        return this.redSocialUsada;
    }

    public void setIdRedSocial(String val){
        this.idRedSocial = val;
    }
    
    public String getIdRedSocial(){
        return this.idRedSocial;
    }

    public void setCuponera(DataCuponera val){
        this.cuponera = val;
    }
    
    public DataCuponera getCuponera(){
        return this.cuponera;
    }
    
    public void setEncomiendas(List<DataEncomienda> val){
        this.encomiendas = val;
    }
    
    public List<DataEncomienda> getEncomiendas(){
        return this.encomiendas;
    }
    
    public void setNotificaciones(List<DataNotificacion> val){
        this.notificaciones = val;
    }
    
    public List<DataNotificacion> getNotificaciones(){
        return this.notificaciones;
    }
}