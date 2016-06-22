package lcbs.models;
import java.io.Serializable;
import lcbs.shares.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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



@Entity
@XmlRootElement
public class Encomienda implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private PuntoRecorrido origen;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private PuntoRecorrido destino;
    @ManyToOne
    private Usuario emisor;
    private String ciEmisor;
    /*@Embedded
    private Telefono telEmisor;
        */
    @Embedded
    private Telefono telReceptor;

    @ManyToOne
    private Usuario receptor;
    private String ciReceptor;
   
    private String direccionReceptor;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private ReglaCobroEncomienda reglaCobro;
    private float monto;
    private boolean pagaReceptor;
    @ManyToOne
    private Viaje viajeAsignado;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<HistorialEstadosEncomienda> estados;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private EstadosEncomienda estadoActual;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    private boolean retiraEnSucursal;

 

    public Encomienda() {}
    
    public Encomienda(String id, PuntoRecorrido orig, PuntoRecorrido dest, Usuario emi, String ciEm, Telefono telEm, Usuario rec, String ciRec, Telefono telRec, String dirRec, ReglaCobroEncomienda regCob, float mont, boolean pagaRec, Viaje viajeAs, List<HistorialEstadosEncomienda> estds, EstadosEncomienda estAc, Date fecIng, Date fecEn, boolean retiraSuc) {
        this.id = id;
        this.origen = orig;
        this.destino = dest;
        this.emisor = emi;
        this.ciEmisor = ciEm;
        this.receptor = rec;
        this.ciReceptor = ciRec;
        this.telReceptor = telEm;
        this.direccionReceptor = dirRec;
        this.reglaCobro = regCob;
        this.monto = mont;
        this.pagaReceptor = pagaRec;
        this.viajeAsignado = viajeAs;
        this.estados = estds;
        this.estadoActual = estAc;
        this.fechaIngreso = fecIng;
        this.fechaEntrega = fecEn;
        this.retiraEnSucursal = retiraSuc;
    }
    
    public Encomienda(DataEncomienda dt){
    	this.setId(dt.getId());
    	if(dt.getOrigen() != null){
	    	if(dt.getOrigen() instanceof DataTerminal){
	    		this.setOrigen(new Terminal((DataTerminal)dt.getOrigen()));
	    	}else{
	    		this.setOrigen(new Parada((DataParada)dt.getOrigen()));
	    	}
    	}
    	if(dt.getDestino() != null){
	    	if(this.getDestino() instanceof Terminal){
	    		this.setDestino(new Terminal((DataTerminal)dt.getDestino()));
	    	}else{
	    		this.setDestino(new Parada((DataParada)dt.getDestino()));
	    	}
    	}
    	if(dt.getEmisor() != null)
    		this.setEmisor(new Usuario(dt.getEmisor()));
    	this.setCiEmisor(dt.getCiEmisor());
    	if(dt.getReceptor() != null)
    		this.setReceptor(new Usuario(dt.getReceptor()));
    	this.setCiReceptor(dt.getCiReceptor());
    	if(dt.getTelReceptor() != null)
    		this.setTelReceptor(new Telefono(dt.getTelReceptor()));
    	this.setDireccionReceptor(dt.getDireccionReceptor());
    	if(dt.getReglaCobro() != null)
    		this.setReglaCobro(new ReglaCobroEncomienda(dt.getReglaCobro()));
    	this.setMonto(dt.getMonto());
    	this.setPagaReceptor(dt.getPagaReceptor());
    	if(dt.getViajeAsignado() != null)
    		this.setViajeAsignado(new Viaje(dt.getViajeAsignado()));
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
    }
    
    public DataEncomienda getDatatype(){
    	DataEncomienda result = new DataEncomienda();
    	result.setId(this.getId());
    	if(this.getOrigen()!=null)
    		if(this.getOrigen() instanceof Terminal){
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
    	if(this.getEmisor()!=null)
    		result.setEmisor(this.getEmisor().getDatatype());
    	result.setCiEmisor(this.getCiEmisor());
    	if(this.getReceptor()!=null)
    		result.setReceptor(this.getReceptor().getDatatype());
    	result.setCiReceptor(this.getCiReceptor());
    	if(this.getTelReceptor()!=null)
    		result.setTelReceptor(this.getTelReceptor().getDatatype());
    	result.setDireccionReceptor(this.getDireccionReceptor());
    	if(this.getReglaCobro()!=null)
    		result.setReglaCobro(this.getReglaCobro().getDatatype());
    	result.setMonto(this.getMonto());
    	result.setPagaReceptor(this.getPagaReceptor());
    	if(this.getViajeAsignado()!=null)
    		result.setViajeAsignado(this.getViajeAsignado().getDatatype());
    	if(this.getEstados()!=null){
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
    	return result;
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

    public void setMonto(float val){
        this.monto = val;
    }
    
    public float getMonto(){
        return this.monto;
    }

    public void setPagaReceptor(boolean val){
        this.pagaReceptor = val;
    }
    
    public boolean getPagaReceptor(){
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

    public void setRetiraEnSucursal(boolean val){
        this.retiraEnSucursal = val;
    }
    
    public boolean getRetiraEnSucursal(){
        return this.retiraEnSucursal;
    }
}