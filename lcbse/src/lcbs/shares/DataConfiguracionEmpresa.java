package lcbs.shares;

import java.util.Date;
import java.util.List;

public class DataConfiguracionEmpresa{

    private String id;
    private String nombre;
    private Boolean aceptaCuponera;
    private String urlAcceso;
    private List<DataTelefono> telefonos;
    private List<DataEmail> emails;
    private String urlLdap;
    private String baseLdap;
    private Boolean activo;
    private Boolean pagoOnlineCoche;
    private Boolean reservaPasajes;
    private Integer validesReservasHoras;
    private Boolean trasferirPasajes;
    private String css;
    
    //UI de aplicaciones Android
    private String iconoEmpresa;
    private String colorFondosDePantalla;
    private String colorTitulo;
    private String colorTextoLista;
    private String colorFondoLista;
    private String colorBoton;
    private String colorLetras;
    private Integer diasCreacionViaje;
    private Date ultimaCreacionDeViajes;
    private String stripePrivateKey;
    private String stripePublicKey;

    public DataConfiguracionEmpresa() {
       
    }
    
    public DataConfiguracionEmpresa(String id, String nom, Boolean acCup, String urlAcc, List<DataTelefono> tels, List<DataEmail> mails, String urlLdap, String usrLdap, String clLdap, Boolean act, Boolean pagOnCoche, Boolean resePas, Integer valResHrs, Boolean trasfPsjs, String css, String icEmp, String colFondPant, String colTit, String colTextLst, String colFondLst, String colBtn, String colLetra, Integer diasC, Date ultCreaViaje, String spk, String spubk) {
        this.id = id;
        this.nombre = nom;
        this.aceptaCuponera = acCup;
        this.urlAcceso = urlAcc;
        this.telefonos = tels;
        this.emails = mails;
        this.urlLdap = urlLdap;
        this.baseLdap = clLdap;
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
        this.colorLetras = colLetra;
        this.diasCreacionViaje = diasC;
        this.ultimaCreacionDeViajes = ultCreaViaje;
        this.stripePrivateKey = spk;
        this.stripePublicKey = spubk;
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

    public void setTelefonos(List<DataTelefono> val){
        this.telefonos = val;
    }
    
    public List<DataTelefono> getTelefonos(){
        return this.telefonos;
    }

    public void setEmails(List<DataEmail> val){
        this.emails = val;
    }
    
    public List<DataEmail> getEmails(){
        return this.emails;
    }

    public void setUrlLdap(String val){
        this.urlLdap = val;
    }
    
    public String getUrlLdap(){
        return this.urlLdap;
    }

    public void setBaseLdap(String val) {
		this.baseLdap = val;	
	}
    
    public String getBaseLdap(){
        return this.baseLdap;
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
	    this.colorLetras= val;
	}
	
	public String getColorLetras(){
	    return this.colorLetras;
	}
	
	public void setDiasCreacionViaje(Integer val){
	    this.diasCreacionViaje = val;
	}
	
	public Integer getDiasCreacionViaje(){
	    return this.diasCreacionViaje;
	}
	
	public void setUltimaCreacionDeViajes(Date val){
	    this.ultimaCreacionDeViajes = val;
	}
	
	public Date getUltimaCreacionDeViajes(){
	    return this.ultimaCreacionDeViajes;
	}
	
	public void setStripePrivateKey(String val){
	    this.stripePrivateKey = val;
	}
	
	public String genStripePrivateKey(){
	    return this.stripePrivateKey;
	}
	
	public void setStripePublicKey(String val){
	    this.stripePublicKey = val;
	}
	
	public String getStripePublicKey(){
		return this.stripePublicKey;
	}
}