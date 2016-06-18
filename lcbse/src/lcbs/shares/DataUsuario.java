package lcbs.shares;

import java.util.Date;
import java.util.List;

public class DataUsuario extends DataPersona{
    
    private String nombreAMostrar;
    private String clave;
    private String redSocialUsada;
    private String idRedSocial;
    private DataCuponera cuponera;
    private List<DataEncomienda> encomiendas;

 

    public DataUsuario() {}
    
    public DataUsuario(String id, String nom, String ape, DataEmail mail, List<DataTelefono> tels, Date fecNac, Boolean elim, String nomMos, String clave, String redSoc, String idRedsoc, DataCuponera cup, List<DataEncomienda> enc) {
        super(id, ape, mail, tels, fecNac, elim);
        this.nombreAMostrar = nomMos;
        this.clave = clave;
        this.redSocialUsada = redSoc;
        this.idRedSocial = idRedsoc;
        this.cuponera = cup;
        this.encomiendas = enc;
    }
    
    public void setNombreAMostrar(String val){
        this.nombreAMostrar = val;
    }
    
    public String getNombreAMostrar(){
        return this.nombreAMostrar;
    }

    public void setClave(String val){
        this.clave = val;
    }
    
    public String getClave(){
        return this.clave;
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
}