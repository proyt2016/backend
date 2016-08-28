package lcbs.models;
import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Entity
@XmlRootElement
public class Reserva implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne(fetch=FetchType.LAZY)
    private Viaje viaje;
    @ManyToOne(fetch=FetchType.LAZY)
    private Precio precio;
    @ManyToOne(fetch=FetchType.LAZY)
    private PuntoRecorrido origen;
    @ManyToOne(fetch=FetchType.LAZY)
    private PuntoRecorrido destino;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReserva;
    @ManyToOne(fetch=FetchType.LAZY)
    private Usuario usuarioReserva;
    private String ciPersona;
    @ManyToOne(fetch=FetchType.LAZY)
    private Empleado empleado;
    private Boolean utilizada;
    private Boolean eliminada;
     
    public Reserva() {}
    
    public Reserva(String id, Viaje via, Precio prec, PuntoRecorrido orig, PuntoRecorrido des, Date fecRes, Usuario usRes, String ciPer, Empleado emp, Boolean usa, Boolean elim) {
        this.id = id;
        this.viaje = via;
        this.precio = prec;
        this.origen = orig;
        this.destino = des;
        this.fechaReserva = fecRes;
        this.usuarioReserva = usRes;
        this.ciPersona = ciPer;
        this.empleado = emp;
        this.utilizada = usa;
        this.eliminada = elim;
    }
    
    public Reserva(DataReserva dt){
    	this.setId(dt.getId());
    	if(dt.getViaje() != null)
    		this.setViaje(new Viaje(dt.getViaje(),false));
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
	    	if(this.getDestino() instanceof Terminal){
	    		this.setDestino(new Terminal((DataTerminal)dt.getDestino()));
	    	}else{
	    		this.setDestino(new Parada((DataParada)dt.getDestino()));
	    	}
    	}
    	this.setFechaReserva(dt.getFechaReserva());
    	if(dt.getUsuarioReserva() != null){
    		this.setUsuarioReserva(new Usuario(dt.getUsuarioReserva()));
    	}
    	if(dt.getEmpleado() != null){
    		this.setEmpleado(new Empleado(dt.getEmpleado(),true));
    	}
    	this.setUtilizada(dt.getUtilizada());
    	this.setEliminada(dt.getEliminada());
    }
    
    public DataReserva getDatatype(){
    	DataReserva result = new DataReserva();
    	result.setId(this.getId());
    	if(this.getViaje()!=null)
    		result.setViaje(this.getViaje().getDatatype(false));
    	if(this.getPrecio()!=null)
    		result.setPrecio(this.getPrecio().getDatatype());
    	if(this.getOrigen()!=null){
    	if(this.getOrigen() instanceof Terminal){
    		if(this.getOrigen()!=null)
    			result.setOrigen(((Terminal)this.getOrigen()).getDatatype());
    	}else{
    		if(this.getOrigen()!=null)
    			result.setOrigen(((Parada)this.getOrigen()).getDatatype());
    	}
    	}
    	if(this.getDestino()!=null){
    	if(this.getDestino() instanceof Terminal){
    		if(this.getDestino()!=null)
    			result.setDestino(((Terminal)this.getDestino()).getDatatype());
    	}else{
    		if(this.getDestino()!=null)
    			result.setDestino(((Parada)this.getDestino()).getDatatype());
    	}
    	}
    	result.setFechaReserva(this.getFechaReserva());
    	if(this.getUsuarioReserva()!=null)
    		result.setUsuarioReserva(this.getUsuarioReserva().getDatatype(false));
    	result.setCiPersona(this.getCiPersona());
    	if(this.getEmpleado()!=null)
    		result.setEmpleado(this.getEmpleado().getDatatype(false));
    	result.setUtilizada(this.getUtilizada());
    	result.setEliminada(this.getEliminada());
    	return result;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public PuntoRecorrido getOrigen() {
		return origen;
	}

	public void setOrigen(PuntoRecorrido origen) {
		this.origen = origen;
	}

	public PuntoRecorrido getDestino() {
		return destino;
	}

	public void setDestino(PuntoRecorrido destino) {
		this.destino = destino;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public Usuario getUsuarioReserva() {
		return usuarioReserva;
	}

	public void setUsuarioReserva(Usuario usuarioReserva) {
		this.usuarioReserva = usuarioReserva;
	}

	public String getCiPersona() {
		return ciPersona;
	}

	public void setCiPersona(String ciPersona) {
		this.ciPersona = ciPersona;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Boolean getUtilizada() {
		return utilizada;
	}

	public void setUtilizada(Boolean utilizada) {
		this.utilizada = utilizada;
	}
    
	public Boolean getEliminada() {
		return eliminada;
	}

	public void setEliminada(Boolean eliminada) {
		this.eliminada = eliminada;
	}
}