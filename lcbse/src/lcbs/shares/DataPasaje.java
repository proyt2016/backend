package lcbs.shares;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataPasaje{
    
	private String id;
    private DataViaje viaje;
    private DataPrecio precio;
    private DataPuntoRecorrido origen;
    private DataPuntoRecorrido destino;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaCompra;
    private DataUsuario comprador;
    private String ciPersona;
    private DataEmpleado vendedor;
    private Boolean usado;
    private Boolean pago;
    private Boolean eliminado;
    
    

    public DataPasaje() {}
    
    public DataPasaje(String id, DataViaje via, DataPrecio prec, DataPuntoRecorrido orig, 
    		DataPuntoRecorrido des,	Date fecVen, DataUsuario comp, String ciPer, 
    		DataEmpleado vend, Boolean usd, Boolean pg, Boolean elim) {
        this.id = id;
        this.viaje = via;
        this.precio = prec;
        this.origen = orig;
        this.destino = des;
        this.fechaCompra = fecVen;
        this.comprador = comp;
        this.ciPersona = ciPer;
        this.vendedor = vend;
        this.usado = usd;
        this.pago = pg;
        this.eliminado = elim;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setViaje(DataViaje val){
        this.viaje = val;
    }
    
    public DataViaje getViaje(){
        return this.viaje;
    }

    public void setPrecio(DataPrecio val){
        this.precio = val;
    }
    
    public DataPrecio getPrecio(){
        return this.precio;
    }

    public void setOrigen(DataPuntoRecorrido val){
        this.origen = val;
    }
    
    public DataPuntoRecorrido getOrigen(){
        return this.origen;
    }

    public void setDestino(DataPuntoRecorrido val){
        this.destino = val;
    }
    
    public DataPuntoRecorrido getDestino(){
        return this.destino;
    }

    public void setFechaCompra(Date val){
        this.fechaCompra = val;
    }
    
    public Date getFechaCompra(){
        return this.fechaCompra;
    }

    public void setComprador(DataUsuario val){
        this.comprador = val;
    }
    
    public DataUsuario getComprador(){
        return this.comprador;
    }

    public void setCiPersona(String val){
        this.ciPersona = val;
    }
    
    public String getCiPersona(){
        return this.ciPersona;
    }

    public void setVendedor(DataEmpleado val){
        this.vendedor = val;
    }
    
    public DataEmpleado getVendedor(){
        return this.vendedor;
    }
    
    public void setUsado(Boolean val){
        this.usado = val;
    }
    
    public Boolean getUsado(){
        return this.usado;
    }
    
    public void setPago(Boolean val){
        this.pago = val;
    }
    
    public Boolean getPago(){
        return this.pago;
    }
    
    public void setEliminado(Boolean val){
        this.eliminado = val;
    }
    
    public Boolean getEliminado(){
        return this.eliminado;
    }
}