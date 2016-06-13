package lcbs.shares;

import java.util.Date;

public class DataReserva{
    
	private String id;
    
    private DataViaje viaje;
    private DataPrecio precio;
    private DataPuntoRecorrido origen;
    private DataPuntoRecorrido destino;
    private Date fechaDataReserva;
    private Usuario usuarioDataReserva;
    private String ciPersona;
    private DataEmpleado empleado;
    private boolean utilizada;
    private boolean eliminada;
     
    public DataReserva() {}
    
    public DataReserva(String id, Viaje via, DataPrecio prec, DataPuntoRecorrido orig, DataPuntoRecorrido des, Date fecRes, Usuario usRes, String ciPer, DataEmpleado emp, boolean usa, boolean elim) {
        this.id = id;
        this.viaje = via;
        this.precio = prec;
        this.origen = orig;
        this.destino = des;
        this.fechaDataReserva = fecRes;
        this.usuarioDataReserva = usRes;
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

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public DataPrecio getDataPrecio() {
		return precio;
	}

	public void setDataPrecio(DataPrecio precio) {
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

	public Date getFechaDataReserva() {
		return fechaDataReserva;
	}

	public void setFechaDataReserva(Date fechaDataReserva) {
		this.fechaDataReserva = fechaDataReserva;
	}

	public Usuario getUsuarioDataReserva() {
		return usuarioDataReserva;
	}

	public void setUsuarioDataReserva(Usuario usuarioDataReserva) {
		this.usuarioDataReserva = usuarioDataReserva;
	}

	public String getCiPersona() {
		return ciPersona;
	}

	public void setCiPersona(String ciPersona) {
		this.ciPersona = ciPersona;
	}

	public DataEmpleado getDataEmpleado() {
		return empleado;
	}

	public void setDataEmpleado(DataEmpleado empleado) {
		this.empleado = empleado;
	}

	public boolean isUtilizada() {
		return utilizada;
	}

	public void setUtilizada(boolean utilizada) {
		this.utilizada = utilizada;
	}
    
	public boolean isEliminada() {
		return eliminada;
	}

	public void setEliminada(boolean eliminada) {
		this.eliminada = eliminada;
	}
}