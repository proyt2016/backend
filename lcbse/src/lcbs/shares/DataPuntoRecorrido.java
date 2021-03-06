package lcbs.shares;

public abstract class DataPuntoRecorrido{
	  	private String id;
	    private String nombre;
	    private String ubicacionMapa;
	    private Boolean eliminado; 
	    
	    public DataPuntoRecorridoConverter genConverter(){
	    	DataPuntoRecorridoConverter result = new DataPuntoRecorridoConverter();
	    	result.setId(this.getId());
	    	result.setNombre(this.getNombre());
	    	result.setUbicacionMapa(this.getUbicacionMapa());
	    	result.setEliminado(this.getEliminado());
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

}