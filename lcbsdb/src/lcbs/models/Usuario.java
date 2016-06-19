package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@XmlRootElement
public class Usuario extends Persona implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    private String nombreAMostrar;
    private String clave;
    private String redSocialUsada;
    private String idRedSocial;
    @OneToOne(cascade = CascadeType.ALL)
    private Cuponera cuponera;
    @OneToMany
    private List<Encomienda> encomiendas;

 

    public Usuario() {}
    
    public Usuario(String id, String ape, Email mail, List<Telefono> tels, Date fecNac, Boolean elim, String nomMos, String clave, String redSoc, String idRedsoc, Cuponera cup, List<Encomienda> enc) {
        super(id, ape, mail, tels, fecNac, elim);
        this.nombreAMostrar = nomMos;
        this.clave = clave;
        this.redSocialUsada = redSoc;
        this.idRedSocial = idRedsoc;
        this.cuponera = cup;
        this.encomiendas = enc;
    }
    
    public Usuario(DataUsuario dt){
    	this.setId(dt.getId());
    	this.setApellido(dt.getApellido());
    	if(dt.getEmail() != null){
    		this.setEmail(new Email(dt.getEmail()));
    	}
    	if(dt.getTelefonosContacto() != null){
	    	List<Telefono> aux = new ArrayList<Telefono>();
	    	dt.getTelefonosContacto().stream().forEach((tel) -> {
	    		aux.add(new Telefono(tel));
	        });
	    	this.setTelefonosContacto(aux);
    	}
    	this.setFechaNacimiento(dt.getFechaNacimiento());
    	this.setEliminado(dt.getEliminado());
    	this.setNombreAMostrar(dt.getNombreAMostrar());
    	this.setClave(dt.getClave());
    	this.setRedSocialUsada(dt.getRedSocialUsada());
    	this.setIdRedSocial(dt.getIdRedSocial());
    	if(dt.getCuponera() != null){
    		this.setCuponera(new Cuponera(dt.getCuponera()));
    	}
    	if(dt.getEncomiendas() != null){
	    	List<Encomienda> auxEnc = new ArrayList<Encomienda>();
	    	dt.getEncomiendas().stream().forEach((enc) -> {
	    		auxEnc.add(new Encomienda(enc));
	        });
	    	this.setEncomiendas(auxEnc);
    	}
    }
    
    public DataUsuario getDatatype(){
    	DataUsuario result = new DataUsuario();
    	result.setId(this.getId());
    	result.setApellido(this.getApellido());
    	result.setEmail(this.getEmail().getDatatype());
    	List<DataTelefono> aux = new ArrayList<DataTelefono>();
    	this.getTelefonosContacto().stream().forEach((tel) -> {
    		aux.add(tel.getDatatype());
        });
    	result.setTelefonosContacto(aux);
    	result.setFechaNacimiento(this.getFechaNacimiento());
    	result.setEliminado(this.getEliminado());
    	result.setNombreAMostrar(this.getNombreAMostrar());
    	result.setClave(this.getClave());
    	result.setRedSocialUsada(this.getRedSocialUsada());
    	this.setIdRedSocial(this.getIdRedSocial());
    	result.setCuponera(this.getCuponera().getDatatype());
    	List<DataEncomienda> auxEnc = new ArrayList<DataEncomienda>();
    	this.getEncomiendas().stream().forEach((enc) -> {
    		auxEnc.add(enc.getDatatype());
        });
    	result.setEncomiendas(auxEnc);
    	return result;
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

    public void setCuponera(Cuponera val){
        this.cuponera = val;
    }
    
    public Cuponera getCuponera(){
        return this.cuponera;
    }
    
    public void setEncomiendas(List<Encomienda> val){
        this.encomiendas = val;
    }
    
    public List<Encomienda> getEncomiendas(){
        return this.encomiendas;
    }
}