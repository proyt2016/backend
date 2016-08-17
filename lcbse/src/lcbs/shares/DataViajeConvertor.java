package lcbs.shares;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataViajeConvertor{
    private String id;
    private DataRecorridoConvertor recorrido;
    private DataHorario horario;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaSalida;
    private List<DataEmpleado> empleados;
    private List<DataVehiculo> coches;
    private List<DataEncomiendaConvertor> encomiendas;
    private List<DataReservaConvertor> reservas;
    private String idOrigen;
    private String idDestino;
    private String tipoHorario;
    
  

    public DataViajeConvertor() {}
    
    public DataViajeConvertor(String id, DataRecorridoConvertor rec, DataHorario hor, Date fecSalida, List<DataEmpleado> emp, List<DataVehiculo> coches, List<DataEncomiendaConvertor> enc, List<DataReservaConvertor> res, String idOr, String idDest, String tipoHor) {
        this.id = id;
    	this.recorrido = rec;
        this.horario = hor;
        this.fechaSalida = fecSalida;
        this.empleados = emp;
        this.coches = coches;
        this.encomiendas = enc;
        this.reservas = res;
        this.idOrigen = idOr;
        this.idDestino = idDest;
        this.tipoHorario = tipoHor;
    }
    
    public DataViaje genDataViaje(){
    	DataViaje result = new DataViaje();
    	result.setId(this.getId());
    	if(this.getRecorrido() != null)
    		result.setRecorrido(this.getRecorrido().genRecorrido());
    	result.setHorario(this.getHorario());
    	result.setFechaSalida(this.getFechaSalida());
    	result.setEmpleados(this.getEmpleados());
    	result.setCoches(this.getCoches());
    	if(this.getEncomiendas() != null){
    		List<DataEncomienda> encToAdd = new ArrayList<DataEncomienda>();
    		this.getEncomiendas().stream().forEach((enc) -> {
    			encToAdd.add(enc.getDataEncomienda());
    		});
    		result.setEncomiendas(encToAdd);
    	}
    	if(this.getReservas() != null){
    		List<DataReserva> resToAdd = new ArrayList<DataReserva>();
    		this.getReservas().stream().forEach((res) -> {
    			resToAdd.add(res.genDataReserva());
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
    
    public void setRecorrido(DataRecorridoConvertor val){
        this.recorrido = val;
    }
    
    public DataRecorridoConvertor getRecorrido(){
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

    public void setCoches(List<DataVehiculo> val){
        this.coches = val;
    }
    
    public List<DataVehiculo> getCoches(){
        return this.coches;
    }
    
    public void setEncomiendas(List<DataEncomiendaConvertor> val){
        this.encomiendas = val;
    }
    
    public List<DataEncomiendaConvertor> getEncomiendas(){
        return this.encomiendas;
    }
    
    public void setReservas(List<DataReservaConvertor> val){
        this.reservas = val;
    }
    
    public List<DataReservaConvertor> getReservas(){
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