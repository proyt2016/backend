package lcbs.models;

import java.io.Serializable;
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

@Entity
@XmlRootElement
public class Viaje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "VIAJE_ID")
	private Recorrido recorrido;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "VIAJE_ID")
	private Horario horario;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "VIAJE_ID")
	private List<Empleado> empleados;
	private Vehiculo coche;
	@OneToMany
	private List<Encomienda> encomiendas;
	@OneToMany
	private List<Reserva> reservas;

	public Viaje() {
	}

	public Viaje(String id, Recorrido rec, Horario hor, Date fecSalida, List<Empleado> emp, Vehiculo coche,
			List<Encomienda> enc) {
		this.id = id;
		this.recorrido = rec;
		this.horario = hor;
		this.fechaSalida = fecSalida;
		this.empleados = emp;
		this.coche = coche;
		this.encomiendas = enc;
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
}