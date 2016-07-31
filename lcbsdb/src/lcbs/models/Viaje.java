package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Viaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@ManyToOne(fetch=FetchType.LAZY)
	private Recorrido recorrido;
	@ManyToOne(fetch=FetchType.LAZY)
	private Horario horario;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;
	@OneToMany(fetch=FetchType.LAZY)
	@IndexColumn(name="LIST_INDEX")
	private List<Empleado> empleados;
	private Vehiculo coche;
	@OneToMany
	@IndexColumn(name="LIST_INDEX")
	private List<Encomienda> encomiendas;
	@OneToMany
	@IndexColumn(name="LIST_INDEX")
	private List<Reserva> reservas;

	public Viaje() {
	}

	public Viaje(String id, Recorrido rec, Horario hor, Date fecSalida, List<Empleado> emp, Vehiculo coche, List<Encomienda> enc, List<Reserva> res) {
		this.id = id;
		this.recorrido = rec;
		this.horario = hor;
		this.fechaSalida = fecSalida;
		this.empleados = emp;
		this.coche = coche;
		this.encomiendas = enc;
		this.reservas = res;
	}
	
	public Viaje(DataViaje dt){
		this.setId(dt.getId());
		if(dt.getRecorrido() != null){
			this.setRecorrido(new Recorrido(dt.getRecorrido()));
		}
		if(dt.getHorario() != null){
			this.setHorario(new Horario(dt.getHorario()));
		}
		this.setFechaSalida(dt.getFechaSalida());
		if(dt.getEmpleados() != null){
			List<Empleado> aux = new ArrayList<Empleado>();
	    	dt.getEmpleados().stream().forEach((emp) -> {
	    		aux.add(new Empleado(emp,true));
	        });
	    	this.setEmpleados(aux);
		}
		if(dt.getCoche() != null){
			this.setCoche(new Vehiculo(dt.getCoche()));
		}
		if(dt.getEncomiendas() != null){
			List<Encomienda> auxEnc = new ArrayList<Encomienda>();
	    	dt.getEncomiendas().stream().forEach((enc) -> {
	    		auxEnc.add(new Encomienda(enc));
	        });
	    	this.setEncomiendas(auxEnc);
		}
		if(dt.getReservas() != null){
			List<Reserva> auxRes = new ArrayList<Reserva>();
	    	dt.getReservas().stream().forEach((res) -> {
	    		auxRes.add(new Reserva(res));
	        });
	    	this.setReservas(auxRes);
		}
	}
	
	public DataViaje getDatatype(Boolean conHijos){
		DataViaje result = new DataViaje();
		result.setId(this.getId());
		if(this.getRecorrido()!=null)
			result.setRecorrido(this.getRecorrido().getDatatype(true));
		if(this.getHorario()!=null)
			result.setHorario(this.getHorario().getDatatype());
		result.setFechaSalida(this.getFechaSalida());
		if(this.getEmpleados()!=null && conHijos){
			List<DataEmpleado> aux = new ArrayList<DataEmpleado>();
			this.getEmpleados().stream().forEach((emp) -> {
	    		aux.add(emp.getDatatype(false));
	        });
	    	result.setEmpleados(aux);
    	}
		if(this.getCoche()!=null)
			result.setCoche(this.getCoche().getDatatype(false));
		if(this.getEncomiendas()!=null && conHijos){
			List<DataEncomienda> auxEnc = new ArrayList<DataEncomienda>();
			this.getEncomiendas().stream().forEach((enc) -> {
	    		auxEnc.add(enc.getDatatype(false));
	        });
	    	result.setEncomiendas(auxEnc);
    	}
		if(this.getReservas()!=null && conHijos){
			List<DataReserva> auxRes = new ArrayList<DataReserva>();
			this.getReservas().stream().forEach((res) -> {
	    		auxRes.add(res.getDatatype());
	        });
	    	result.setReservas(auxRes);
    	}
		return result;
	}

	public void setId(String val) {
		this.id = val;
	}

	public String getId() {
		return this.id;
	}

	public void setRecorrido(Recorrido val) {
		this.recorrido = val;
	}

	public Recorrido getRecorrido() {
		return this.recorrido;
	}

	public void setHorario(Horario val) {
		this.horario = val;
	}

	public Horario getHorario() {
		return this.horario;
	}

	public void setFechaSalida(Date val) {
		this.fechaSalida = val;
	}

	public Date getFechaSalida() {
		return this.fechaSalida;
	}

	public void setEmpleados(List<Empleado> val) {
		this.empleados = val;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setCoche(Vehiculo val) {
		this.coche = val;
	}

	public Vehiculo getCoche() {
		return this.coche;
	}

	public void setEncomiendas(List<Encomienda> val) {
		this.encomiendas = val;
	}

	public List<Encomienda> getEncomiendas() {
		return this.encomiendas;
	}
	
	public void setReservas(List<Reserva> val) {
		this.reservas = val;
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}
}