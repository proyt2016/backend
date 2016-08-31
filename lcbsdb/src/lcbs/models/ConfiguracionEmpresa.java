package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

@Entity
@XmlRootElement
public class ConfiguracionEmpresa implements Serializable{
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private Boolean aceptaCuponera;
    private String urlAcceso;
    @Embedded
    @ElementCollection
    @IndexColumn(name="LIST_INDEX")
    private List<Telefono> telefonos;
    
    @Embedded
    @ElementCollection
    @IndexColumn(name="LIST_INDEX")
    private List<Email> emails;
    private String urlLdap;
    private String usuarioLdap;
    private String claveLdap;
    private Boolean activo;
    private Boolean pagoOnlineCoche;
    private Boolean reservaPasajes;
    private Integer validesReservasHoras;
    private Boolean trasferirPasajes;
    private String css;

    public ConfiguracionEmpresa() {
       
    }
    
    public ConfiguracionEmpresa(String id, String nom, Boolean acCup, String urlAcc, List<Telefono> tels, List<Email> mails, String urlLdap, String usrLdap, String clLdap, Boolean act, Boolean pagOnCoche, Boolean resePas, Integer valResHrs, Boolean trasfPsjs, String css) {
        this.id = id;
        this.nombre = nom;
        this.aceptaCuponera = acCup;
        this.urlAcceso = urlAcc;
        this.telefonos = tels;
        this.emails = mails;
        this.urlLdap = urlLdap;
        this.usuarioLdap = usrLdap;
        this.claveLdap = clLdap;
        this.activo = act;
        this.pagoOnlineCoche = pagOnCoche;
        this.reservaPasajes = resePas;
        this.validesReservasHoras = valResHrs;
        this.trasferirPasajes = trasfPsjs;
        this.css = css;
    }
    
    public ConfiguracionEmpresa(DataConfiguracionEmpresa dt){
        this.setId(dt.getId());
        this.setNombre(dt.getNombre());
        this.setAceptaCuponera(dt.getAceptaCuponera());
        this.setUrlAcceso(dt.getUrlAcceso());
        this.telefonos = new ArrayList<Telefono>();
        if(dt.getTelefonos() != null){
	        List<Telefono> aux = new ArrayList<Telefono>();
	        dt.getTelefonos().stream().forEach((tel) -> {
	        	aux.add(new Telefono(tel));
	        });
	        this.setTelefonos(aux);
        }
        if(dt.getEmails() != null){
	        List<Email> auxEm = new ArrayList<Email>();
	        dt.getEmails().stream().forEach((em) -> {
	        	auxEm.add(new Email(em));
	        });
	        this.setEmails(auxEm);
        }
        this.setUrlLdap(dt.getUrlLdap());
        this.setUsuarioLdap(dt.getUsuarioLdap());
        this.setClaveLdap(dt.getClaveLdap());
        this.setActivo(dt.getActivo());
        this.setPagoOnlineCoche(dt.getPagoOnlineCoche());
        this.setReservaPasajes(dt.getReservaPasajes());
        this.setValidesReservasHoras(dt.getValidesReservasHoras());
        this.setTrasferirPasajes(dt.getTrasferirPasajes());
        this.setCss(dt.getCss());
    }
    
    public DataConfiguracionEmpresa getDatatype(){
    	DataConfiguracionEmpresa result = new DataConfiguracionEmpresa();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setAceptaCuponera(this.getAceptaCuponera());
    	result.setUrlAcceso(this.getUrlAcceso());
    	if(this.telefonos != null){
	    	List<DataTelefono> aux = new ArrayList<DataTelefono>();
	    	this.telefonos.stream().forEach((tel) -> {
	    		aux.add(tel.getDatatype());
	        });
	    	result.setTelefonos(aux);
    	}
    	if(this.emails != null){    	
	    	List<DataEmail> auxEm = new ArrayList<DataEmail>();
	    	this.emails.stream().forEach((em) -> {
	    		auxEm.add(em.getDatatype());
	        });
	    	result.setEmails(auxEm);
    	}
    	result.setUrlLdap(this.getUrlLdap());
    	result.setUsuarioLdap(this.getUsuarioLdap());
    	result.setClaveLdap(this.getClaveLdap());
    	result.setActivo(this.getActivo());
    	result.setPagoOnlineCoche(this.getPagoOnlineCoche());
    	result.setReservaPasajes(this.getReservaPasajes());
    	result.setValidesReservasHoras(this.getValidesReservasHoras());
    	result.setTrasferirPasajes(this.getTrasferirPasajes());
    	result.setCss(this.getCss());
    	return result;
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

    public void setAceptaCuponera(Boolean val){
        this.aceptaCuponera = val;
    }
    
    public Boolean getAceptaCuponera(){
        return this.aceptaCuponera;
    }

    public void setUrlAcceso(String val){
        this.urlAcceso = val;
    }
    
    public String getUrlAcceso(){
        return this.urlAcceso;
    }

    public void setTelefonos(List<Telefono> val){
        this.telefonos = val;
    }
    
    public List<Telefono> getTelefonos(){
        return this.telefonos;
    }

    public void setEmails(List<Email> val){
        this.emails = val;
    }
    
    public List<Email> getEmails(){
        return this.emails;
    }

    public void setUrlLdap(String val){
        this.urlLdap = val;
    }
    
    public String getUrlLdap(){
        return this.urlLdap;
    }

    public void setUsuarioLdap(String val){
        this.usuarioLdap = val;
    }
    
    public String getUsuarioLdap(){
        return this.usuarioLdap;
    }

    public void setClaveLdap(String val){
        this.claveLdap = val;
    }
    
    public String getClaveLdap(){
        return this.claveLdap;
    }

    public void setActivo(Boolean val){
        this.activo = val;
    }
    
    public Boolean getActivo(){
        return this.activo;
    }

    public void setPagoOnlineCoche(Boolean val){
        this.pagoOnlineCoche = val;
    }
    
    public Boolean getPagoOnlineCoche(){
        return this.pagoOnlineCoche;
    }

    public void setReservaPasajes(Boolean val){
        this.reservaPasajes = val;
    }
    
    public Boolean getReservaPasajes(){
        return this.reservaPasajes;
    }

    public void setValidesReservasHoras(Integer val){
        this.validesReservasHoras = val;
    }
    
    public Integer getValidesReservasHoras(){
        return this.validesReservasHoras;
    }

    public void setTrasferirPasajes(Boolean val){
        this.trasferirPasajes = val;
    }
    
    public Boolean getTrasferirPasajes(){
        return this.trasferirPasajes;
    }
    
    public void setCss(String val){
        this.css = val;
    }
    
    public String getCss(){
        return this.css;
    }
}