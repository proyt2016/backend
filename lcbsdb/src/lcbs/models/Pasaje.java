package lcbs.models;
import java.io.Serializable;
import lcbs.shares.*;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.james.mime4j.field.datetime.DateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class Pasaje implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private Viaje viaje;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private Precio precio;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private PuntoRecorrido origen;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private PuntoRecorrido destino;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private Usuario comprador;
    private String ciPersona;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private Empleado vendedor;
    private Boolean usado;
    private Boolean pago;
    private Boolean eliminado;
    
    

    public Pasaje() {}
    
    public Pasaje(String id, Viaje via, Precio prec, PuntoRecorrido orig, PuntoRecorrido des, Date fecVen, Usuario comp, String ciPer, Empleado vend, Boolean usd, Boolean pg, Boolean elim) {
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
    
    public Pasaje(DataPasaje dt){
    	this.setId(dt.getId());
    	this.setViaje(new Viaje(dt.getViaje()));
    	this.setPrecio(new Precio(dt.getPrecio()));
    	if(dt.getOrigen() instanceof DataTerminal){
    		this.setOrigen(new Terminal((DataTerminal)dt.getOrigen()));
    	}else{
    		this.setOrigen(new Parada((DataParada)dt.getOrigen()));
    	}
    	if(this.getDestino() instanceof Terminal){
    		this.setDestino(new Terminal((DataTerminal)dt.getDestino()));
    	}else{
    		this.setDestino(new Parada((DataParada)dt.getDestino()));
    	}
    	this.setFechaCompra(dt.getFechaCompra());
    	this.setComprador(new Usuario(dt.getComprador()));
    	this.setCiPersona(dt.getCiPersona());
    	this.setVendedor(new Empleado(dt.getVendedor()));
    	this.setUsado(dt.getUsado());
    	this.setPago(dt.getPago());
    	this.setEliminado(dt.getEliminado());
    }
    
    public DataPasaje getDatatype(){
    	DataPasaje result = new DataPasaje();
    	result.setId(this.getId());
    	result.setViaje(this.getViaje().getDatatype());
    	result.setPrecio(this.getPrecio().getDatatype());
    	if(this.getOrigen() instanceof Terminal){
    		result.setOrigen(((Terminal)this.getOrigen()).getDatatype());
    	}else{
    		result.setOrigen(((Parada)this.getOrigen()).getDatatype());
    	}
    	if(this.getDestino() instanceof Terminal){
    		result.setDestino(((Terminal)this.getDestino()).getDatatype());
    	}else{
    		result.setDestino(((Parada)this.getDestino()).getDatatype());
    	}
    	result.setFechaCompra(this.getFechaCompra());
    	result.setComprador(this.getComprador().getDatatype());
    	result.setCiPersona(this.getCiPersona());
    	result.setVendedor(this.getVendedor().getDatatype());
    	result.setUsado(this.getUsado());
    	result.setPago(this.getPago());
    	result.setEliminado(this.getEliminado());
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setViaje(Viaje val){
        this.viaje = val;
    }
    
    public Viaje getViaje(){
        return this.viaje;
    }

    public void setPrecio(Precio val){
        this.precio = val;
    }
    
    public Precio getPrecio(){
        return this.precio;
    }

    public void setOrigen(PuntoRecorrido val){
        this.origen = val;
    }
    
    public PuntoRecorrido getOrigen(){
        return this.origen;
    }

    public void setDestino(PuntoRecorrido val){
        this.destino = val;
    }
    
    public PuntoRecorrido getDestino(){
        return this.destino;
    }

    public void setFechaCompra(Date val){
        this.fechaCompra = val;
    }
    
    public Date getFechaCompra(){
        return this.fechaCompra;
    }

    public void setComprador(Usuario val){
        this.comprador = val;
    }
    
    public Usuario getComprador(){
        return this.comprador;
    }

    public void setCiPersona(String val){
        this.ciPersona = val;
    }
    
    public String getCiPersona(){
        return this.ciPersona;
    }

    public void setVendedor(Empleado val){
        this.vendedor = val;
    }
    
    public Empleado getVendedor(){
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