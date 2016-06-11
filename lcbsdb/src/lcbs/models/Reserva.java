package lcbs.models;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@Entity
@XmlRootElement
public class Reserva implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="ReservaId")
    private Viaje viaje;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="ReservaId")
    private Precio precio;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="ReservaOrigId")
    private PuntoRecorrido origen;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="ReservaDestId")
    private PuntoRecorrido destino;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReserva;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="ReservaId")
    private Usuario usuarioReserva;
    private String ciPersona;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="ReservaId")
    private Empleado empleado;
    private boolean utilizada;
     
    public Reserva() {}
    
    public Reserva(String id, Viaje via, Precio prec, PuntoRecorrido orig, PuntoRecorrido des, Date fecRes, Usuario usRes, String ciPer, Empleado emp, boolean usa) {
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

	public boolean isUtilizada() {
		return utilizada;
	}

	public void setUtilizada(boolean utilizada) {
		this.utilizada = utilizada;
	}
    
    
}