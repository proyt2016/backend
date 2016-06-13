package lcbs.shares;

import java.util.List;

public class DataRecorrido{
    
	private String id;
    
    private String nombre;
    private List<DataPuntoRecorrido> puntosDeDataRecorrido;
    private List<DataGrupoHorario> horarios;
    private List<DataPrecio> precios;
    private Boolean eliminado;
    
 

    public DataRecorrido() {}
    
    public DataRecorrido(String id, String nom, List<DataPuntoRecorrido> punRec, List<DataGrupoHorario> hor, List<DataPrecio> prec, Boolean elim) {
        this.id = id;
        this.nombre = nom;
        this.puntosDeDataRecorrido = punRec;
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

    public void setPuntosDeDataRecorrido(List<DataPuntoRecorrido> val){
        this.puntosDeDataRecorrido = val;
    }
    
    public List<DataPuntoRecorrido> getPuntosDeDataRecorrido(){
        return this.puntosDeDataRecorrido;
    }

    public void setHorarios(List<DataGrupoHorario> val){
        this.horarios = val;
    }
    
    public List<DataGrupoHorario> getHorarios(){
        return this.horarios;
    }

    public void setDataPrecios(List<DataPrecio> val){
        this.precios = val;
    }
    
    public List<DataPrecio> getDataPrecios(){
        return this.precios;
    }

    public void setEliminado(Boolean val){
        this.eliminado = val;
    }
    
    public Boolean getEliminado(){
        return this.eliminado;
    }
}