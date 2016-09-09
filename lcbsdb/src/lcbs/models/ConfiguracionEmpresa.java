package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
    @Column(length = 65535,columnDefinition="Text")
    private String css;
    
    //UI de aplicaciones Android
    private String iconoEmpresa;
    private String colorFondosDePantalla;
    private String colorTitulo;
    private String colorTextoLista;
    private String colorFondoLista;
    private String colorBoton;
    private String colorLetras;

    public ConfiguracionEmpresa() {
       
    }
    
    public ConfiguracionEmpresa(String id, String nom, Boolean acCup, String urlAcc, List<Telefono> tels, List<Email> mails, String urlLdap, String usrLdap, String clLdap, Boolean act, Boolean pagOnCoche, Boolean resePas, Integer valResHrs, Boolean trasfPsjs, String css, String icEmp, String colFondPant, String colTit, String colTextLst, String colFondLst, String colBtn, String colorLetras) {
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
        this.iconoEmpresa = icEmp;
        this.colorFondosDePantalla = colFondPant;
        this.colorTitulo = colTit;
        this.colorTextoLista = colTextLst;
        this.colorFondoLista = colFondLst;
        this.colorBoton = colBtn;
        this.colorLetras = colorLetras;
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
        this.setIconoEmpresa(dt.getIconoEmpresa());
        this.setColorFondosDePantalla(dt.getColorFondosDePantalla());
        this.setColorTitulo(dt.getColorTitulo());
        this.setColorTextoLista(dt.getColorTextoLista());
        this.setColorFondoLista(dt.getColorFondoLista());
        this.setColorBoton(dt.getColorBoton());
        this.setColorLetras(dt.getColorLetras());
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
    	result.setIconoEmpresa(this.getIconoEmpresa());
    	result.setColorFondosDePantalla(this.getColorFondosDePantalla());
    	result.setColorTitulo(this.getColorTitulo());
    	result.setColorTextoLista(this.getColorTextoLista());
    	result.setColorFondoLista(this.getColorFondoLista());
    	result.setColorBoton(this.getColorBoton());
    	result.setColorLetras(this.getColorLetras());
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

    public void setIconoEmpresa(String val){
        this.iconoEmpresa = val;
    }
    
    public String getIconoEmpresa(){
        return this.iconoEmpresa;
    }
    
    public void setColorFondosDePantalla(String val){
        this.colorFondosDePantalla = val;
    }
    
    public String getColorFondosDePantalla(){
        return this.colorFondosDePantalla;
    }
    
    public void setColorTitulo(String val){
        this.colorTitulo = val;
    }
    
    public String getColorTitulo(){
        return this.colorTitulo;
    }
    
    public void setColorTextoLista(String val){
        this.colorTextoLista = val;
    }
    
    public String getColorTextoLista(){
        return this.colorTextoLista;
    }
    
    public void setColorFondoLista(String val){
        this.colorFondoLista = val;
    }
    
    public String getColorFondoLista(){
        return this.colorFondoLista;
    }
        
    public void setColorBoton(String val){
        this.colorBoton = val;
    }
    
    public String getColorBoton(){
        return this.colorBoton;
    }
    
	public void setColorLetras(String val){
	    this.colorLetras = val;
	}
	
	public String getColorLetras(){
	    return this.colorLetras;
	}
}