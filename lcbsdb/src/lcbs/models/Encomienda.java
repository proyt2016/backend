package lcbs.models;
import java.io.Serializable;
import lcbs.shares.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;



@Entity
@XmlRootElement
public class Encomienda implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer codigoEncomienda;
    
    @ManyToOne
    private PuntoRecorrido origen;
    @ManyToOne
    private PuntoRecorrido destino;
    @ManyToOne
    private Usuario emisor;
    private String ciEmisor;
    @ManyToOne
    private Vehiculo cocheAsignado;
    @Embedded
    @AttributeOverrides( {
        @AttributeOverride(name="descripcion", column = @Column(name="descTelEmisor") ),
        @AttributeOverride(name="telefono", column = @Column(name="telEmisor") )
	} )
    private Telefono telEmisor;
    @Embedded
    @AttributeOverrides( {
        @AttributeOverride(name="descripcion", column = @Column(name="descTelReceptor") ),
        @AttributeOverride(name="telefono", column = @Column(name="telReceptor") )
	} )
    private Telefono telReceptor;
    @ManyToOne
    private Usuario receptor;
    private String ciReceptor;
    private String direccionReceptor;
    @ManyToOne(fetch=FetchType.LAZY)
    private ReglaCobroEncomienda reglaCobro;
    private Float monto;
    private Float precio;
    private Boolean pagaReceptor;
    @ManyToOne
    private Viaje viajeAsignado;
    @OneToMany(fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
    @IndexColumn(name="LIST_INDEX")
    private List<HistorialEstadosEncomienda> estados;
    @ManyToOne(fetch=FetchType.LAZY)
    private EstadosEncomienda estadoActual;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    private Boolean retiraEnSucursal;
    private Boolean eliminada;
    private Boolean paga;

 

    public Encomienda() {}
    
    public Encomienda(String id, PuntoRecorrido orig, PuntoRecorrido dest, Usuario emi, String ciEm, Telefono telEm, Usuario rec, String ciRec, Telefono telRec, String dirRec, ReglaCobroEncomienda regCob, Float mont, Float prec, Boolean pagaRec, Viaje viajeAs, List<HistorialEstadosEncomienda> estds, EstadosEncomienda estAc, Date fecIng, Date fecEn, Boolean retiraSuc, Boolean elim, Vehiculo cod_coche, Integer codEnco, Boolean paga) {
        this.id = id;
        this.origen = orig;
        this.destino = dest;
        this.emisor = emi;
        this.ciEmisor = ciEm;
        this.telEmisor = telEm;
        this.receptor = rec;
        this.ciReceptor = ciRec;
        this.telReceptor = telRec;
        this.direccionReceptor = dirRec;
        this.reglaCobro = regCob;
        this.monto = mont;
        this.precio = prec;
        this.pagaReceptor = pagaRec;
        this.viajeAsignado = viajeAs;
        this.estados = estds;
        this.estadoActual = estAc;
        this.fechaIngreso = fecIng;
        this.fechaEntrega = fecEn;
        this.retiraEnSucursal = retiraSuc;
        this.eliminada = elim;
        this.cocheAsignado = cod_coche;
        this.codigoEncomienda = codEnco;
        this.paga = paga;
    }
    
    public Encomienda(DataEncomienda dt, Boolean conHijos){
    	this.setId(dt.getId());
    	this.setCodigoEncomienda(dt.getCodigoEncomienda());
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
    	
    	if(dt.getCocheAsignado()!=null && conHijos)
    		this.setCocheAsignado(new Vehiculo(dt.getCocheAsignado()));
    
    	if(dt.getEmisor() != null)
    		this.setEmisor(new Usuario(dt.getEmisor()));
    	this.setCiEmisor(dt.getCiEmisor());
    	if(dt.getReceptor() != null)
    		this.setReceptor(new Usuario(dt.getReceptor()));
    	if(dt.getTelEmisor() != null)
    		this.setTelEmisor(new Telefono(dt.getTelEmisor()));
    	this.setCiReceptor(dt.getCiReceptor());
    	if(dt.getTelReceptor() != null)
    		this.setTelReceptor(new Telefono(dt.getTelReceptor()));
    	this.setDireccionReceptor(dt.getDireccionReceptor());
    	if(dt.getReglaCobro() != null)
    		this.setReglaCobro(new ReglaCobroEncomienda(dt.getReglaCobro()));
    	this.setMonto(dt.getMonto());
    	this.setPrecio(dt.getPrecio());
    	this.setPagaReceptor(dt.getPagaReceptor());
    	if(dt.getViajeAsignado() != null)
    		this.setViajeAsignado(new Viaje(dt.getViajeAsignado(),false));
    	if(dt.getEstados()!=null){
	    	List<HistorialEstadosEncomienda> temp = new ArrayList<HistorialEstadosEncomienda>();
	    	dt.getEstados().stream().forEach((est) -> {
	    		temp.add(new HistorialEstadosEncomienda(est));
	        });
	    	this.setEstados(temp);
    	}
    	if(dt.getEstadoActual() != null)
    		this.setEstadoActual(new EstadosEncomienda(dt.getEstadoActual()));
    	this.setFechaIngreso(dt.getFechaIngreso());
    	this.setFechaEntrega(dt.getFechaEntrega());
    	this.setRetiraEnSucursal(dt.getRetiraEnSucursal());	
    	this.setEliminada(dt.getEliminada());
    	this.setPaga(dt.getPaga());
    	
    }
    
    public DataEncomienda getDatatype(Boolean conHijos){
    	DataEncomienda result = new DataEncomienda();
    	result.setId(this.getId());
    	result.setCodigoEncomienda(this.getCodigoEncomienda());
    	if(this.getOrigen()!=null)
    		if(this.getOrigen() instanceof Terminal){
    			result.setOrigen(((Terminal)this.getOrigen()).getDatatype());
    		}else{
    			result.setOrigen(((Parada)this.getOrigen()).getDatatype());
    		}
    	if(this.getDestino()!=null){
    		if(this.getDestino() instanceof Terminal){
    			result.setDestino(((Terminal)this.getDestino()).getDatatype());
    		}else{
    			result.setDestino(((Parada)this.getDestino()).getDatatype());
    		}
    	}
    	
    	if(this.getCocheAsignado()!=null)
    		result.setCocheAsignado(this.getCocheAsignado().getDatatype(false));    		    	
    	if(this.getEmisor()!=null)
    		result.setEmisor(this.getEmisor().getDatatype(false));
    	result.setCiEmisor(this.getCiEmisor());
    	if(this.getTelEmisor()!=null)
    		result.setTelEmisor(this.getTelEmisor().getDatatype());
    	if(this.getReceptor()!=null)
    		result.setReceptor(this.getReceptor().getDatatype(false));
    	result.setCiReceptor(this.getCiReceptor());
    	if(this.getTelReceptor()!=null)
    		result.setTelReceptor(this.getTelReceptor().getDatatype());
    	result.setDireccionReceptor(this.getDireccionReceptor());
    	if(this.getReglaCobro()!=null)
    		result.setReglaCobro(this.getReglaCobro().getDatatype());
    	result.setMonto(this.getMonto());
    	result.setPrecio(this.getPrecio());
    	result.setPagaReceptor(this.getPagaReceptor());
    	if(this.getViajeAsignado()!=null)
    		result.setViajeAsignado(this.getViajeAsignado().getDatatype(false));
    	if(this.getEstados()!=null && conHijos){
	    	List<DataHistorialEstadosEncomienda> temp = new ArrayList<DataHistorialEstadosEncomienda>();
	    	this.getEstados().stream().forEach((est) -> {
	    		temp.add(est.getDatatype());
	        });
	    	result.setEstados(temp);
    	}
    	if(this.getEstadoActual()!=null)
    		result.setEstadoActual(this.getEstadoActual().getDatatype());
    	result.setFechaIngreso(this.getFechaIngreso());
    	result.setFechaEntrega(this.getFechaEntrega());
    	result.setRetiraEnSucursal(this.getRetiraEnSucursal());
    	result.setEliminada(this.getEliminada());
    	result.setPaga(this.getPaga());
    
    	return result;
    }
    
    public void setCodigoEncomienda(Integer codigoEnco){
    	this.codigoEncomienda = codigoEnco;
    }
    
    public Integer getCodigoEncomienda(){
    	return this.codigoEncomienda;
    }
    
    public void setCocheAsignado(Vehiculo val){
    	this.cocheAsignado = val;
    }
    
    public Vehiculo getCocheAsignado(){
    	return this.cocheAsignado;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
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

    public void setEmisor(Usuario val){
        this.emisor = val;
    }
    
    public Usuario getEmisor(){
        return this.emisor;
    }

    public void setCiEmisor(String val){
        this.ciEmisor = val;
    }
    
    public String getCiEmisor(){
        return this.ciEmisor;
    }
    
    public void setTelEmisor(Telefono val){
        this.telEmisor = val;
    }
    
    public Telefono getTelEmisor(){
        return this.telEmisor;
    }
    
    public void setReceptor(Usuario val){
        this.receptor = val;
    }
    
    public Usuario getReceptor(){
        return this.receptor;
    }
    
    public void setCiReceptor(String val){
        this.ciReceptor = val;
    }
    
    public String getCiReceptor(){
        return this.ciReceptor;
    }
    
    public void setTelReceptor(Telefono val){
        this.telReceptor = val;
    }
    
    public Telefono getTelReceptor(){
        return this.telReceptor;
    }

    public void setDireccionReceptor(String val){
        this.direccionReceptor = val;
    }
    
    public String getDireccionReceptor(){
        return this.direccionReceptor;
    }

    public void setReglaCobro(ReglaCobroEncomienda val){
        this.reglaCobro = val;
    }
    
    public ReglaCobroEncomienda getReglaCobro(){
        return this.reglaCobro;
    }

    public void setMonto(Float val){
        this.monto = val;
    }
    
    public Float getMonto(){
        return this.monto;
    }
 
    public void setPrecio(Float val){
        this.precio = val;
    }
    
    public Float getPrecio(){
        return this.precio;
    }

    public void setPagaReceptor(Boolean val){
        this.pagaReceptor = val;
    }
    
    public Boolean getPagaReceptor(){
        return this.pagaReceptor;
    }

    public void setViajeAsignado(Viaje val){
        this.viajeAsignado = val;
    }
    
    public Viaje getViajeAsignado(){
        return this.viajeAsignado;
    }

    public void setEstados(List<HistorialEstadosEncomienda> val){
        this.estados = val;
    }
    
    public List<HistorialEstadosEncomienda> getEstados(){
        return this.estados;
    }

    public void setEstadoActual(EstadosEncomienda val){
        this.estadoActual = val;
    }
    
    public EstadosEncomienda getEstadoActual(){
        return this.estadoActual;
    }

    public void setFechaIngreso(Date val){
        this.fechaIngreso = val;
    }
    
    public Date getFechaIngreso(){
        return this.fechaIngreso;
    }

    public void setFechaEntrega(Date val){
        this.fechaEntrega = val;
    }
    
    public Date getFechaEntrega(){
        return this.fechaEntrega;
    }

    public void setRetiraEnSucursal(Boolean val){
        this.retiraEnSucursal = val;
    }
    
    public Boolean getRetiraEnSucursal(){
        return this.retiraEnSucursal;
    }
    
    public void setEliminada(Boolean val){
        this.eliminada = val;
    }
    
    public Boolean getEliminada(){
        return this.eliminada;
    }
    
    public void setPaga(Boolean val){
        this.paga = val;
    }
    
    public Boolean getPaga(){
        return this.paga;
    }
}