package lcbs.shares;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class DataPasajeConvertor{
    
	private String id;
    private DataViaje viaje;
    private DataPrecio precio;
    private DataPuntoRecorridoConverter origen;
    private DataPuntoRecorridoConverter destino;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaCompra;
    private DataUsuario comprador;
    private String ciPersona;
    private DataEmpleado vendedor;
    private int codigoPasaje;
    private Boolean usado;
    private Boolean pago;
    private Boolean eliminado;
    
    

    public DataPasajeConvertor() {}
    
    public DataPasajeConvertor(String id, int codigoPasaje, DataViaje via, DataPrecio prec, DataPuntoRecorridoConverter orig, 
    		DataPuntoRecorridoConverter des,	Date fecVen, DataUsuario comp, String ciPer, 
    		DataEmpleado vend, Boolean usd, Boolean pg, Boolean elim) {
        this.id = id;
        this.codigoPasaje = codigoPasaje;
        this.viaje = via;
        this.precio = prec;
        this.origen = orig;
        this.destino = des;
        this.fechaCompra = fecVen;
        this.comprador = comp;
        this.ciPersona = ciPer;
        this.vendedor = vend;
        this.usado = usd;
        this.pago = pg;
        this.eliminado = elim;
    }
    
    public DataPasaje genDataPasaje(){
    	DataPasaje result = new DataPasaje();
    	result.setId(this.getId());
    	result.setCodigoPasaje(this.getCodigoPasaje());
    	result.setViaje(this.getViaje());
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
    	result.setFechaCompra(this.getFechaCompra());
    	result.setComprador(this.getComprador());
    	result.setCiPersona(this.getCiPersona());
    	result.setVendedor(this.getVendedor());
    	result.setUsado(this.getUsado());
    	result.setPago(this.getPago());
    	result.setEliminado(this.getEliminado());
    	return result;
    }
    
    public void setCodigoPasaje(int cod){
    	this.codigoPasaje = cod;
    }
    
    public int getCodigoPasaje(){
    	return this.codigoPasaje;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setViaje(DataViaje val){
        this.viaje = val;
    }
    
    public DataViaje getViaje(){
    	DataViaje sinPuntos = new DataViaje();
    	sinPuntos = this.viaje;
    	DataRecorrido recOverr = sinPuntos.getRecorrido();
    	if(recOverr != null){
	    	recOverr.setPuntosDeRecorrido(null);
	    	sinPuntos.setRecorrido(recOverr);
    	}
        return sinPuntos;
    }

    public void setPrecio(DataPrecio val){
        this.precio = val;
    }
    
    public DataPrecio getPrecio(){
        return this.precio;
    }

    public void setOrigen(DataPuntoRecorridoConverter val){
        this.origen = val;
    }
    
    public DataPuntoRecorridoConverter getOrigen(){
        return this.origen;
    }

    public void setDestino(DataPuntoRecorridoConverter val){
        this.destino = val;
    }
    
    public DataPuntoRecorridoConverter getDestino(){
        return this.destino;
    }

    public void setFechaCompra(Date val){
        this.fechaCompra = val;
    }
    
    public Date getFechaCompra(){
        return this.fechaCompra;
    }

    public void setComprador(DataUsuario val){
        this.comprador = val;
    }
    
    public DataUsuario getComprador(){
        return this.comprador;
    }

    public void setCiPersona(String val){
        this.ciPersona = val;
    }
    
    public String getCiPersona(){
        return this.ciPersona;
    }

    public void setVendedor(DataEmpleado val){
        this.vendedor = val;
    }
    
    public DataEmpleado getVendedor(){
        return this.vendedor;
    }
    
    public void setUsado(Boolean val){
        this.usado = val;
    }
    
    public Boolean getUsado(){
        return this.usado;
    }
    
    public void setPago(Boolean val){
        this.pago = val;
    }
    
    public Boolean getPago(){
        return this.pago;
    }
    
    public void setEliminado(Boolean val){
        this.eliminado = val;
    }
    
    public Boolean getEliminado(){
        return this.eliminado;
    }
}