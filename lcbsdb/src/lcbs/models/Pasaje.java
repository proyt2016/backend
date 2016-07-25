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
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class Pasaje implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int codigoPasaje;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private Viaje viaje;
    @ManyToOne(fetch=FetchType.EAGER)
    private Precio precio;
    @ManyToOne(fetch=FetchType.EAGER)
    private PuntoRecorrido origen;
    @ManyToOne(fetch=FetchType.EAGER)
    private PuntoRecorrido destino;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @ManyToOne(fetch=FetchType.EAGER)
    private Usuario comprador;
    private String ciPersona;
    @ManyToOne(fetch=FetchType.EAGER)
    private Empleado vendedor;
    private Boolean usado;
    private Boolean pago;
    private Boolean eliminado;
    
    

    public Pasaje() {}
    
    public Pasaje(String id,int codigoPasaje, Viaje via, Precio prec, PuntoRecorrido orig, PuntoRecorrido des, Date fecVen, Usuario comp, String ciPer, Empleado vend, Boolean usd, Boolean pg, Boolean elim) {
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
        this.codigoPasaje = codigoPasaje;
    }
    
    public Pasaje(DataPasaje dt){
    	this.setId(dt.getId());
    	this.setCodigoPasaje(dt.getCodigoPasaje());
    	if(dt.getViaje() != null){
    		this.setViaje(new Viaje(dt.getViaje()));
    	}
    	if(dt.getPrecio() != null)
    		this.setPrecio(new Precio(dt.getPrecio()));
    	if(dt.getOrigen() != null){
	    	if(dt.getOrigen() instanceof DataTerminal){
	    		this.setOrigen(new Terminal((DataTerminal)dt.getOrigen()));
	    	}else{
	    		this.setOrigen(new Parada((DataParada)dt.getOrigen()));
	    	}
    	}
    	if(dt.getDestino() != null){
	    	if(dt.getDestino() instanceof DataTerminal){
	    		this.setDestino(new Terminal((DataTerminal)dt.getDestino()));
	    	}else{
	    		this.setDestino(new Parada((DataParada)dt.getDestino()));
	    	}
    	}
    	if(dt.getFechaCompra() != null)
    		this.setFechaCompra(dt.getFechaCompra());
    	if(dt.getComprador() != null){
    		this.setComprador(new Usuario(dt.getComprador()));
    	}
    	this.setCiPersona(dt.getCiPersona());
    	if(dt.getVendedor() != null){
    		this.setVendedor(new Empleado(dt.getVendedor(),true));
    	}
    	this.setUsado(dt.getUsado());
    	this.setPago(dt.getPago());
    	this.setEliminado(dt.getEliminado());
    }
    
    public DataPasaje getDatatype(){
    	DataPasaje result = new DataPasaje();
    	result.setId(this.getId());
    	result.setCodigoPasaje(this.getCodigoPasaje());
    	if(this.getViaje()!=null)
    		result.setViaje(this.getViaje().getDatatype(false));
    	if(this.getPrecio()!=null)
    		result.setPrecio(this.getPrecio().getDatatype());
    	if(this.getOrigen() instanceof Terminal){
    		if(this.getOrigen()!=null)
    			result.setOrigen(((Terminal)this.getOrigen()).getDatatype());
    	}else{
    		if(this.getOrigen()!=null)
    			result.setOrigen(((Parada)this.getOrigen()).getDatatype());
    	}
    	if(this.getDestino() instanceof Terminal){
    		if(this.getDestino()!=null)
    			result.setDestino(((Terminal)this.getDestino()).getDatatype());
    	}else{
    		if(this.getDestino()!=null)
    			result.setDestino(((Parada)this.getDestino()).getDatatype());
    	}
    	result.setFechaCompra(this.getFechaCompra());
    	if(this.getComprador()!=null)
    		result.setComprador(this.getComprador().getDatatype(false));
    	result.setCiPersona(this.getCiPersona());
    	if(this.getVendedor()!=null)
    		result.setVendedor(this.getVendedor().getDatatype(false));
    	result.setUsado(this.getUsado());
    	result.setPago(this.getPago());
    	result.setEliminado(this.getEliminado());
    	return result;
    }
    
    public void setCodigoPasaje(int cod){
    	this.codigoPasaje = cod;
    }
    
    public int getCodigoPasaje(){
    	return this.codigoPasaje;
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