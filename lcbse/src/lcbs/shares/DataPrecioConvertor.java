package lcbs.shares;

import java.util.ArrayList;
import java.util.List;

public class DataPrecioConvertor{
    
	private String id;
    private DataPuntoRecorridoConverter origen;
    private DataPuntoRecorridoConverter destino;
    private Float monto;
     
    public DataPrecioConvertor() {
      
    }
    
    public DataPrecioConvertor(DataPuntoRecorridoConverter orig, DataPuntoRecorridoConverter dest, Float monto) {
        this.origen = orig;
        this.destino = dest;
        this.monto = monto;
    }
    
    public DataPrecio genPrecio(){
    	DataPrecio result = new DataPrecio();
    	result.setId(this.getId());
    	result.setMonto(this.getMonto());
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
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
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

    public void setMonto(Float val){
        this.monto = val;
    }
    
    public Float getMonto(){
        return this.monto;
    }

    
}