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
    
  

    public DataViaje() {}
    
    public DataViaje(String id, DataRecorrido rec, DataHorario hor, Date fecSalida, List<DataEmpleado> emp, DataVehiculo coche, List<DataEncomienda> enc) {
        this.id = id;
    	this.recorrido = rec;
        this.horario = hor;
        this.fechaSalida = fecSalida;
        this.empleados = emp;
        this.coche = coche;
        this.encomiendas = enc;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setDataRecorrido(DataRecorrido val){
        this.recorrido = val;
    }
    
    public DataRecorrido getDataRecorrido(){
        return this.recorrido;
    }

    public void setDataHorario(DataHorario val){
        this.horario = val;
    }
    
    public DataHorario getDataHorario(){
        return this.horario;
    }

    public void setFechaSalida(Date val){
        this.fechaSalida = val;
    }
    
    public Date getFechaSalida(){
        return this.fechaSalida;
    }

    public void setDataEmpleados(List<DataEmpleado> val){
        this.empleados = val;
    }
    
    public List<DataEmpleado> getDataEmpleados(){
        return this.empleados;
    }

    public void setCoche(DataVehiculo val){
        this.coche = val;
    }
    
    public DataVehiculo getCoche(){
        return this.coche;
    }
    
    public void setDataEncomiendas(List<DataEncomienda> val){
        this.encomiendas = val;
    }
    
    public List<DataEncomienda> getDataEncomiendas(){
        return this.encomiendas;
    }
}