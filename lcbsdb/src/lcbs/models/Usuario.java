package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.IndexColumn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@XmlRootElement
public class Usuario extends Persona implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    
    private String redSocialUsada;
    private String idRedSocial;
    @OneToOne(cascade = CascadeType.ALL)
    private Cuponera cuponera;
    @OneToMany
    @IndexColumn(name="LIST_INDEX")
    private List<Encomienda> encomiendas;
    @Embedded
    @ElementCollection
    @IndexColumn(name="LIST_INDEX")
    private List<Notificacion> notificaciones;

 

    public Usuario() {}
    
    public Usuario(String id, String nm, String ape, Email mail, List<Telefono> tels, Date fecNac, Boolean elim, String clave, String redSoc, String idRedsoc, Cuponera cup, List<Encomienda> enc, List<Notificacion> not) {
        super(id,clave, nm, ape, mail, tels, fecNac, elim);
        this.redSocialUsada = redSoc;
        this.idRedSocial = idRedsoc;
        this.cuponera = cup;
        this.encomiendas = enc;
        this.notificaciones = not;
    }
    
    public Usuario(DataUsuario dt){
    	this.setId(dt.getId());
    	this.setNombrePila(dt.getNombrePila());
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
    	this.setClave(dt.getClave());
    	this.setRedSocialUsada(dt.getRedSocialUsada());
    	this.setIdRedSocial(dt.getIdRedSocial());
    	if(dt.getCuponera() != null){
    		this.setCuponera(new Cuponera(dt.getCuponera()));
    	}
    	if(dt.getEncomiendas() != null){
	    	List<Encomienda> auxEnc = new ArrayList<Encomienda>();
	    	dt.getEncomiendas().stream().forEach((enc) -> {
	    		auxEnc.add(new Encomienda(enc, true));
	        });
	    	this.setEncomiendas(auxEnc);
    	}
    }
    
    public DataUsuario getDatatype(Boolean conHijos){
    	DataUsuario result = new DataUsuario();
    	result.setId(this.getId());
    	result.setNombrePila(this.getNombrePila());
    	result.setApellido(this.getApellido());
    	if(this.getEmail()!=null)
    		result.setEmail(this.getEmail().getDatatype());
    	if(this.getTelefonosContacto()!=null && conHijos){
	    	List<DataTelefono> aux = new ArrayList<DataTelefono>();
	    	this.getTelefonosContacto().stream().forEach((tel) -> {
	    		aux.add(tel.getDatatype());
	        });
	    	result.setTelefonosContacto(aux);
    	}
    	result.setFechaNacimiento(this.getFechaNacimiento());
    	result.setEliminado(this.getEliminado());
    	result.setClave(this.getClave());
    	result.setRedSocialUsada(this.getRedSocialUsada());
    	this.setIdRedSocial(this.getIdRedSocial());
    	if(this.getCuponera()!=null)
    		result.setCuponera(this.getCuponera().getDatatype());
    	if(this.getEncomiendas()!=null && conHijos){
	    	List<DataEncomienda> auxEnc = new ArrayList<DataEncomienda>();
	    	this.getEncomiendas().stream().forEach((enc) -> {
	    		auxEnc.add(enc.getDatatype(false));
	        });
	    	result.setEncomiendas(auxEnc);
	    }
    	return result;
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
    
    public void setNotificaciones(List<Notificacion> val){
        this.notificaciones = val;
    }
    
    public List<Notificacion> getNotificaciones(){
        return this.notificaciones;
    }
}