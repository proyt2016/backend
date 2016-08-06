package lcbs.shares;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataViaje{
    private String id;
    private DataRecorrido recorrido;
    private DataHorario horario;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaSalida;
    private List<DataEmpleado> empleados;
    private DataVehiculo coche;
    private List<DataEncomienda> encomiendas;
    private List<DataReserva> reservas;
    private String idOrigen;
    private String idDestino;
    private String tipoHorario;
    
  

    public DataViaje() {}
    
    public DataViaje(String id, DataRecorrido rec, DataHorario hor, Date fecSalida, List<DataEmpleado> emp, DataVehiculo coche, List<DataEncomienda> enc, List<DataReserva> res, String idOr, String idDest, String tipoHor) {
        this.id = id;
    	this.recorrido = rec;
        this.horario = hor;
        this.fechaSalida = fecSalida;
        this.empleados = emp;
        this.coche = coche;
        this.encomiendas = enc;
        this.reservas = res;
        this.idOrigen = idOr;
        this.idDestino = idDest;
        this.tipoHorario = tipoHor;
    }
    
    public DataViajeConvertor genConvertor(){
    	DataViajeConvertor result = new DataViajeConvertor();
    	result.setId(this.getId());
    	if(this.getRecorrido() != null)
    		result.setRecorrido(this.getRecorrido().genConvertor());
    	result.setHorario(this.getHorario());
    	result.setFechaSalida(this.getFechaSalida());
    	result.setEmpleados(this.getEmpleados());
    	result.setCoche(this.getCoche());
    	if(this.getEncomiendas() != null){
    		List<DataEncomiendaConvertor> encToAdd = new ArrayList<DataEncomiendaConvertor>();
    		this.getEncomiendas().stream().forEach((enc) -> {
    			encToAdd.add(enc.getConvertor());
    		});
    		result.setEncomiendas(encToAdd);
    	}
    	if(this.getReservas() != null){
    		List<DataReservaConvertor> resToAdd = new ArrayList<DataReservaConvertor>();
    		this.getReservas().stream().forEach((res) -> {
    			resToAdd.add(res.genConvertor());
    		});
    		result.setReservas(resToAdd);
    	}
    	result.setIdOrigen(this.genIdOrigen());
    	result.setIdDestino(this.genIdDestino());
    	result.setTipoHorario(this.genTipoHorario());
    	return result;
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
    
    public String genIdOrigen(){
    	return this.idOrigen;
    }
    
    public void setIdOrigen(String val){
    	this.idOrigen = val;
    }
    
    public String genIdDestino(){
    	return this.idDestino;
    }
    
    public void setIdDestino(String val){
    	this.idDestino = val;
    }
    
    public void setTipoHorario(String val){
        this.tipoHorario = val;
    }
    
    public String genTipoHorario(){
        return this.tipoHorario;
    }
}