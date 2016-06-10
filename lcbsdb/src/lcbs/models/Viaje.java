package lcbs.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Viaje implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    private Recorrido recorrido;
    private Horario horario;
    private Date fechaSalida;
    @ElementCollection
    @OneToMany 
    private ArrayList<Empleado> empleados;
    private Vehiculo coche;
    
  

    public Viaje() {
        recorrido = new Recorrido();
        horario = new Horario();
        fechaSalida = new Date();
        empleados = new ArrayList<Empleado>();
        coche = new Vehiculo();
    }
    
    public Viaje(Recorrido rec, Horario hor, Date fecSalida, List<Empleado> emp, Vehiculo coche) {
        this.recorrido = rec;
        this.horario = hor;
        this.fechaSalida = fecSalida;
        this.empleados = emp;
        this.coche = coche;
    }
    
    public void setRecorrido(Recorrido val){
        this.recorrido = val;
    }
    
    public Recorrido getRecorrido(){
        return this.recorrido;
    }

    public void setHorario(Horario val){
        this.horario = val;
    }
    
    public Horario getHorario(){
        return this.horario;
    }

    public void setFechaSalida(Date val){
        this.fechaSalida = val;
    }
    
    public Date getFechaSalida(){
        return this.fechaSalida;
    }

    public void setEmpleados(List<Empleado> val){
        this.empleados = val;
    }
    
    public List<Empleado> getEmpleados(){
        return this.empleados;
    }

    public void setCoche(Vehiculo val){
        this.coche = val;
    }
    
    public Vehiculo getCoche(){
        return this.coche;
    }
}