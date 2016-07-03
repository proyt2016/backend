package lcbs.shares;

import java.util.Date;
import java.util.List;

public class DataViaje{
    private String id;
    private DataRecorrido recorrido;
    private DataHorario horario;
    private Date fechaSalida;
    private List<DataEmpleado> empleados;
    private DataVehiculo coche;
    private List<DataEncomienda> encomiendas;
    private List<DataReserva> reservas;
    
  

    public DataViaje() {}
    
    public DataViaje(String id, DataRecorrido rec, DataHorario hor, Date fecSalida, List<DataEmpleado> emp, DataVehiculo coche, List<DataEncomienda> enc, List<DataReserva> res) {
        this.id = id;
    	this.recorrido = rec;
        this.horario = hor;
        this.fechaSalida = fecSalida;
        this.empleados = emp;
        this.coche = coche;
        this.encomiendas = enc;
        this.reservas = res;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setRecorrido(DataRecorrido val){
        this.recorrido = val;
    }
    
    public DataRecorrido getRecorrido(){
        return this.recorrido;
    }

    public void setHorario(DataHorario val){
        this.horario = val;
    }
    
    public DataHorario getHorario(){
        return this.horario;
    }

    public void setFechaSalida(Date val){
        this.fechaSalida = val;
    }
    
    public Date getFechaSalida(){
        return this.fechaSalida;
    }

    public void setEmpleados(List<DataEmpleado> val){
        this.empleados = val;
    }
    
    public List<DataEmpleado> getEmpleados(){
        return this.empleados;
    }

    public void setCoche(DataVehiculo val){
        this.coche = val;
    }
    
    public DataVehiculo getCoche(){
        return this.coche;
    }
    
    public void setEncomiendas(List<DataEncomienda> val){
        this.encomiendas = val;
    }
    
    public List<DataEncomienda> getEncomiendas(){
        return this.encomiendas;
    }
    
    public void setReservas(List<DataReserva> val){
        this.reservas = val;
    }
    
    public List<DataReserva> getReservas(){
        return this.reservas;
    }
}