package lcbs.shares;

import java.util.ArrayList;
import java.util.List;

public class DataRecorrido{
    
	private String id;
    
    private String nombre;
    private List<DataPuntoRecorrido> puntosDeRecorrido;
    private List<DataGrupoHorario> horarios;
    private String idOrigen;
    private String idDestino;
    private String tipoHorario;
    private List<DataPrecio> precios;
    private Boolean eliminado;
    
 

    public DataRecorrido() {}
    
    public DataRecorrido(String id, String nom, List<DataPuntoRecorrido> punRec, List<DataGrupoHorario> hor, List<DataPrecio> prec, Boolean elim, String tipoH) {
        this.id = id;
        this.nombre = nom;
        this.puntosDeRecorrido = punRec;
        this.horarios = hor;
        this.precios = prec;
        this.eliminado = elim;
        this.tipoHorario = tipoH;
    }
    
    public DataRecorridoConvertor genConvertor(){
    	DataRecorridoConvertor result = new DataRecorridoConvertor();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	if(this.getPuntosDeRecorrido() != null){
    		List<DataPuntoRecorridoConverter> puntos = new ArrayList<DataPuntoRecorridoConverter>();
    		this.getPuntosDeRecorrido().stream().forEach((pr) -> {
        		if(pr instanceof DataParada){
        			DataPuntoRecorridoConverter toAdd = pr.getConverter();
        			toAdd.setTipo("Parada");
        			puntos.add(toAdd);
        		}else{
        			DataPuntoRecorridoConverter toAdd = pr.getConverter();
        			toAdd.setAceptaEncomiendas(((DataTerminal)pr).getAceptaEncomiendas());
        			toAdd.setMailsDeContacto(((DataTerminal)pr).getMailsDeContacto());
        			toAdd.setTelefonosContacto(((DataTerminal)pr).getTelefonosContacto());
        			toAdd.setTipo("Terminal");
        			puntos.add(toAdd);
        		}
            });
        	result.setPuntosDeRecorrido(puntos);
    	}
    	result.setHorarios(this.getHorarios());
    	result.setPrecios(this.getPrecios());
    	result.setEliminado(this.getEliminado());
    	result.setTipoHorario(this.genTipoHorario());
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

    public void setPuntosDeRecorrido(List<DataPuntoRecorrido> val){
        this.puntosDeRecorrido = val;
    }
    
    public List<DataPuntoRecorrido> getPuntosDeRecorrido(){
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