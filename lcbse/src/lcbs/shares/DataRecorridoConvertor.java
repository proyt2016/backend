package lcbs.shares;

import java.util.ArrayList;
import java.util.List;

public class DataRecorridoConvertor{
    
	private String id;
    
    private String nombre;
    private List<DataPuntoRecorridoConverter> puntosDeRecorrido;
    private List<DataGrupoHorario> horarios;
    private String idOrigen;
    private String idDestino;
    private List<DataPrecio> precios;
    private Boolean eliminado;
    	
 

    public DataRecorridoConvertor() {}
    
    public DataRecorridoConvertor(String id, String nom, List<DataPuntoRecorridoConverter> punRec, List<DataGrupoHorario> hor, List<DataPrecio> prec, Boolean elim) {
        this.id = id;
        this.nombre = nom;
        this.puntosDeRecorrido = punRec;
        this.horarios = hor;
        this.precios = prec;
        this.eliminado = elim;
    }
    
    public DataRecorrido genRecorrido(){
    	DataRecorrido result = new DataRecorrido();
    	result.setEliminado(this.getEliminado());
    	result.setHorarios(this.getHorarios());
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setPrecios(this.getPrecios());
    	final List<DataPuntoRecorrido> puntos = new ArrayList<DataPuntoRecorrido>();
    	if(this.getPuntosDeRecorrido() != null){
    		this.getPuntosDeRecorrido().stream().forEach((pr) -> {
        		if(pr.getTipo().equals("Parada")){
        			puntos.add(pr.genDataParada());
        		}else{
        			puntos.add(pr.genDataTerminal());
        		}
            });
    	}
    	result.setPuntosDeRecorrido(puntos);
    	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setNombre(String val){
        this.nombre = val;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public void setPuntosDeRecorrido(List<DataPuntoRecorridoConverter> val){
        this.puntosDeRecorrido = val;
    }
    
    public List<DataPuntoRecorridoConverter> getPuntosDeRecorrido(){
        return this.puntosDeRecorrido;
    }

    public void setHorarios(List<DataGrupoHorario> val){
        this.horarios = val;
    }
    
    public List<DataGrupoHorario> getHorarios(){
        return this.horarios;
    }

    public void setPrecios(List<DataPrecio> val){
        this.precios = val;
    }
    
    public List<DataPrecio> getPrecios(){
        return this.precios;
    }

    public void setEliminado(Boolean val){
        this.eliminado = val;
    }
    
    public Boolean getEliminado(){
        return this.eliminado;
    }
    
    public String getIdOrigen(){
    	return idOrigen;
    }
    
    public void setIdOrigen(String val){
    	this.idOrigen = val;
    }
    
    public String getIdDestino(){
    	return idDestino;
    }
    
    public void setIdDestino(String val){
    	this.idDestino = val;
    }
}