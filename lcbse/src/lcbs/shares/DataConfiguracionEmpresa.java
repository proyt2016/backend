package lcbs.shares;

import java.util.List;

public class DataConfiguracionEmpresa{

    private String id;
    private String nombre;
    private boolean aceptaCuponera;
    private String urlAcceso;
    private List<DataTelefono> telefonos;
    private List<DataEmail> emails;
    private String urlLdap;
    private String usuarioLdap;
    private String claveLdap;
    private boolean activo;
    private boolean pagoOnlineCoche;
    private boolean reservaPasajes;
    private Integer validesReservasHoras;
    private boolean trasferirPasajes;

    public DataConfiguracionEmpresa() {
       
    }
    
    public DataConfiguracionEmpresa(String id, String nom, boolean acCup, String urlAcc, List<DataTelefono> tels, List<DataEmail> mails, String urlLdap, String usrLdap, String clLdap, boolean act, boolean pagOnCoche, boolean resePas, Integer valResHrs, boolean trasfPsjs) {
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