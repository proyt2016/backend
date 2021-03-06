package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.IndexColumn;


@Entity
@XmlRootElement
@IdClass(PuntoRecorrido.class)
public class Terminal extends PuntoRecorrido implements Serializable{
    private static final long serialVersionUID = 1L; 
    
    @Embedded
    @ElementCollection
    @IndexColumn(name="LIST_INDEX")
    private List<Telefono> telefonosContacto;
    @Embedded
    @ElementCollection
    @IndexColumn(name="LIST_INDEX")
    private List<Email> mailsDeContacto;
    private Boolean aceptaEncomiendas;

 

    public Terminal() {}
    
    public Terminal(String id, String nom, String uMap, List<Telefono> tels, List<Email> mails, Boolean acEnc, Boolean elim) {
        super(id, nom, uMap, elim);
        this.telefonosContacto = tels;
        this.mailsDeContacto = mails;
        this.aceptaEncomiendas = acEnc;
    }
    
    public Terminal(DataTerminal dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	this.setUbicacionMapa(dt.getUbicacionMapa());
    	this.setEliminado(dt.getEliminado());
    	if(dt.getTelefonosContacto() != null){
	    	List<Telefono> aux = new ArrayList<Telefono>();
	    	dt.getTelefonosContacto().stream().forEach((tel) -> {
	    		aux.add(new Telefono(tel));
	        });
	    	this.setTelefonosContacto(aux);
    	}
    	if(dt.getMailsDeContacto() != null){
	    	List<Email> auxEm = new ArrayList<Email>();
	    	dt.getMailsDeContacto().stream().forEach((em) -> {
	    		auxEm.add(new Email(em));
	        });
	    	this.setMailsDeContacto(auxEm);
    	}
    	this.setAceptaEncomiendas(dt.getAceptaEncomiendas());
    }
    
    public DataTerminal getDatatype(){
    	DataTerminal result = new DataTerminal();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setUbicacionMapa(this.getUbicacionMapa());
    	result.setEliminado(this.getEliminado());
    	if(this.getTelefonosContacto()!=null){
	    	List<DataTelefono> aux = new ArrayList<DataTelefono>();
	    	this.getTelefonosContacto().stream().forEach((tel) -> {
	    		aux.add(tel.getDatatype());
	        });
	    	result.setTelefonosContacto(aux);
    	}
    	if(this.getMailsDeContacto()!=null){
	    	List<DataEmail> auxEm = new ArrayList<DataEmail>();
	    	this.getMailsDeContacto().stream().forEach((em) -> {
	    		auxEm.add(em.getDatatype());
	        });
	    	result.setMailsDeContacto(auxEm);
    	}
    	result.setAceptaEncomiendas(this.getAceptaEncomiendas());
    	return result;
    }
    
    public void setTelefonosContacto(List<Telefono> val){
        this.telefonosContacto = val;
    }
    
    public List<Telefono> getTelefonosContacto(){
        return this.telefonosContacto;
    }

    public void setMailsDeContacto(List<Email> val){
        this.mailsDeContacto = val;
    }
    
    public List<Email> getMailsDeContacto(){
        return this.mailsDeContacto;
    }

    public void setAceptaEncomiendas(Boolean val){
        this.aceptaEncomiendas = val;
    }
    
    public Boolean getAceptaEncomiendas(){
        return this.aceptaEncomiendas;
    }

    
}