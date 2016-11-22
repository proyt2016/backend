package lcbs.shares;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataReservaConvertor{
    
	private String id;
    private DataViajeConvertor viaje;
    private DataPrecio precio;
    private DataPuntoRecorridoConverter origen;
    private DataPuntoRecorridoConverter destino;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaReserva;
    private DataUsuario usuarioReserva;
    private String ciPersona;
    private DataEmpleado empleado;
    private Boolean utilizada;
    private Boolean eliminada;
     
    public DataReservaConvertor() {}
    
    public DataReservaConvertor(String id, DataViajeConvertor via, DataPrecio prec, DataPuntoRecorridoConverter orig, DataPuntoRecorridoConverter des, Date fecRes, DataUsuario usRes, String ciPer, DataEmpleado emp, Boolean usa, Boolean elim) {
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
    
    public DataReserva genDataReserva(){
    	DataReserva result = new DataReserva();
    	result.setId(this.getId());
    	if(this.getViaje() != null)
    		result.setViaje(this.getViaje().genDataViaje());
    	result.setPrecio(this.getPrecio());
    	if(this.getOrigen() != null){
			if(this.getOrigen().getTipo().equals("Parada")){
				result.setOrigen(this.getOrigen().genDataParada());
			}else{
				result.setOrigen(this.getOrigen().genDataTerminal());
			}
    	}
    	if(this.getDestino() != null){
			if(this.getDestino().getTipo().equals("Parada")){
				result.setDestino(this.getDestino().genDataParada());
			}else{
				result.setDestino(this.getDestino().genDataTerminal());
			}
    	}
    	result.setFechaReserva(this.getFechaReserva());
    	result.setUsuarioReserva(this.getUsuarioReserva());
    	result.setCiPersona(this.getCiPersona());
    	result.setEmpleado(this.getEmpleado());
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

	public DataViajeConvertor getViaje() {
		return viaje;
	}

	public void setViaje(DataViajeConvertor viaje) {
		this.viaje = viaje;
	}

	public DataPrecio getPrecio() {
		return precio;
	}

	public void setPrecio(DataPrecio precio) {
		this.precio = precio;
	}

	public DataPuntoRecorridoConverter getOrigen() {
		return origen;
	}

	public void setOrigen(DataPuntoRecorridoConverter origen) {
		this.origen = origen;
	}

	public DataPuntoRecorridoConverter getDestino() {
		return destino;
	}

	public void setDestino(DataPuntoRecorridoConverter destino) {
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