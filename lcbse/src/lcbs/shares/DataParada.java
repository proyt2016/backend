package lcbs.shares;

public class DataParada extends DataPuntoRecorrido{
    
	public DataParada() {}
	 private String id;
	    
	    private String nombre;
	    private String ubicacionMapa;
	    private Boolean eliminado;    
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

	    public void setUbicacionMapa(String val){
	        this.ubicacionMapa = val;
	    }
	    
	    public String getUbicacionMapa(){
	        return this.ubicacionMapa;
	    }

	    public void setEliminado(Boolean val){
	        this.eliminado = val;
	    }
	    
	    public Boolean getEliminado(){
	        return this.eliminado;
	    }
    public DataParada(String id, String nom, String uMap, Boolean elim) {
        setId(id);
        setNombre(nom);
        setUbicacionMapa(uMap);
        setEliminado(elim);
    }
    
}