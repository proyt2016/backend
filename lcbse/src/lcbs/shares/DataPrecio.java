package lcbs.shares;

import java.util.ArrayList;
import java.util.List;

public class DataPrecio{
    
	private String id;
    private DataPuntoRecorrido origen;
    private DataPuntoRecorrido destino;
    private Float monto;
     
    public DataPrecio() {
      
    }
    
    public DataPrecio(DataPuntoRecorrido orig, DataPuntoRecorrido dest, Float monto) {
        this.origen = orig;
        this.destino = dest;
        this.monto = monto;
    }
    
    public DataPrecioConvertor genConvertor(){
    	DataPrecioConvertor result = new DataPrecioConvertor();
    	result.setId(this.getId());
    	if(this.getOrigen() != null)
    		result.setOrigen(this.getOrigen().genConverter());
    	if(this.getDestino() != null)
    		result.setDestino(this.getDestino().genConverter());
    	result.setMonto(this.getMonto());
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setOrigen(DataPuntoRecorrido val){
        this.origen = val;
    }
    
    public DataPuntoRecorrido getOrigen(){
        return this.origen;
    }

    public void setDestino(DataPuntoRecorrido val){
        this.destino = val;
    }
    
    public DataPuntoRecorrido getDestino(){
        return this.destino;
    }

    public void setMonto(Float val){
        this.monto = val;
    }
    
    public Float getMonto(){
        return this.monto;
    }

    
}