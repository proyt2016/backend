package lcbs.models;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.james.mime4j.field.datetime.DateTime;
import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Reserva implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private Viaje viaje;
    private Precio precio;
    private PuntoRecorrido origen;
    private PuntoRecorrido destino;
    private DateTime fechaReserva;
    private Usuario usuarioReserva;
    private String ciPersona;
    private Empleado empleado;
    private boolean utilizada;
     
    public Reserva() {
        id = "";
        viaje = new Viaje();
        precio = new Precio();
        origen = new PuntoRecorrido();
        destino = new PuntoRecorrido();
        fechaReserva = new DateTime();
        usuarioReserva = new Usuario();
        ciPersona = "";
        empleado = new Empleado();
        utilizada = false;
    }
    
    public Reserva(String id, Viaje via, Precio prec, PuntoRecorrido orig, PuntoRecorrido des, DateTime fecRes, Usuario usRes, String ciPer, Empleado emp, boolean usa) {
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

	public DateTime getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(DateTime fechaReserva) {
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