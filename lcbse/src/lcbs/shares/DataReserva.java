package lcbs.shares;

import java.util.Date;

public class DataReserva{
    
	private String id;
    private DataViaje viaje;
    private DataPrecio precio;
    private DataPuntoRecorrido origen;
    private DataPuntoRecorrido destino;
    private Date fechaReserva;
    private DataUsuario usuarioReserva;
    private String ciPersona;
    private DataEmpleado empleado;
    private boolean utilizada;
    private boolean eliminada;
     
    public DataReserva() {}
    
    public DataReserva(String id, DataViaje via, DataPrecio prec, DataPuntoRecorrido orig, DataPuntoRecorrido des, Date fecRes, DataUsuario usRes, String ciPer, DataEmpleado emp, boolean usa, boolean elim) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DataViaje getViaje() {
		return viaje;
	}

	public void setViaje(DataViaje viaje) {
		this.viaje = viaje;
	}

	public DataPrecio getPrecio() {
		return precio;
	}

	public void setPrecio(DataPrecio precio) {
		this.precio = precio;
	}

	public DataPuntoRecorrido getOrigen() {
		return origen;
	}

	public void setOrigen(DataPuntoRecorrido origen) {
		this.origen = origen;
	}

	public DataPuntoRecorrido getDestino() {
		return destino;
	}

	public void setDestino(DataPuntoRecorrido destino) {
		this.destino = destino;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public DataUsuario getUsuarioReserva() {
		return usuarioReserva;
	}

	public void setUsuarioReserva(DataUsuario usuarioReserva) {
		this.usuarioReserva = usuarioReserva;
	}

	public String getCiPersona() {
		return ciPersona;
	}

	public void setCiPersona(String ciPersona) {
		this.ciPersona = ciPersona;
	}

	public DataEmpleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(DataEmpleado empleado) {
		this.empleado = empleado;
	}

	public boolean getUtilizada() {
		return utilizada;
	}

	public void setUtilizada(boolean utilizada) {
		this.utilizada = utilizada;
	}
    
	public boolean getEliminada() {
		return eliminada;
	}

	public void setEliminada(boolean eliminada) {
		this.eliminada = eliminada;
	}
}