package lcbs.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import javax.persistence.Embedded;

@Entity
@XmlRootElement
public class ConfiguracionEmpresa implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String nombre;
    private boolean aceptaCuponera;
    private String urlAcceso;
    @Embedded
    private List<Telefono> telefonos;
    @Embedded
    private List<Email> emails;
    private String urlLdap;
    private String usuarioLdap;
    private String claveLdap;
    private boolean activo;
    private boolean pagoOnlineCoche;
    private boolean reservaPasajes;
    private Integer validesReservasHoras;
    private boolean trasferirPasajes;

    public ConfiguracionEmpresa() {
       
    }
    
    public ConfiguracionEmpresa(String id, String nom, boolean acCup, String urlAcc, List<Telefono> tels, List<Email> mails, String urlLdap, String usrLdap, String clLdap, boolean act, boolean pagOnCoche, boolean resePas, Integer valResHrs, boolean trasfPsjs) {
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

    public void setAceptaCuponera(boolean val){
        this.aceptaCuponera = val;
    }
    
    public boolean getAceptaCuponera(){
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

    public void setActivo(boolean val){
        this.activo = val;
    }
    
    public boolean getActivo(){
        return this.activo;
    }

    public void setPagoOnlineCoche(boolean val){
        this.pagoOnlineCoche = val;
    }
    
    public boolean getPagoOnlineCoche(){
        return this.pagoOnlineCoche;
    }

    public void setReservaPasajes(boolean val){
        this.reservaPasajes = val;
    }
    
    public boolean getReservaPasajes(){
        return this.reservaPasajes;
    }

    public void setValidesReservasHoras(Integer val){
        this.validesReservasHoras = val;
    }
    
    public Integer getValidesReservasHoras(){
        return this.validesReservasHoras;
    }

    public void setTrasferirPasajes(boolean val){
        this.trasferirPasajes = val;
    }
    
    public boolean getTrasferirPasajes(){
        return this.trasferirPasajes;
    }
}