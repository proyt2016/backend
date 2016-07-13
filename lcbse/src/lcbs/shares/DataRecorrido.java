package lcbs.shares;

import java.util.List;

public class DataRecorrido{
    
	private String id;
    
    private String nombre;
    private List<DataPuntoRecorrido> puntosDeRecorrido;
    private List<DataGrupoHorario> horarios;
    private String idOrigen;
    private String idDestino;
    private List<DataPrecio> precios;
    private Boolean eliminado;
    
 

    public DataRecorrido() {}
    
    public DataRecorrido(String id, String nom, List<DataPuntoRecorrido> punRec, List<DataGrupoHorario> hor, List<DataPrecio> prec, Boolean elim) {
        this.id = id;
        this.nombre = nom;
        this.puntosDeRecorrido = punRec;
        this.horarios = hor;
        this.precios = prec;
        this.eliminado = elim;
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
    
    public String getIdOrigen(){
    	String result = null;
    	if(puntosDeRecorrido != null && puntosDeRecorrido.size() > 0)
    		result = puntosDeRecorrido.get(0).getId();
    	return result;
    }
    
    public void setIdOrigen(String val){
    	this.idOrigen = val;
    }
    
    public String getIdDestino(){
    	String result = null;
    	if(puntosDeRecorrido != null && puntosDeRecorrido.size() > 0)
    		result = puntosDeRecorrido.get(puntosDeRecorrido.size()-1).getId();
    	return result;
    }
    
    public void setIdDestino(String val){
    	this.idDestino = val;
    }
}