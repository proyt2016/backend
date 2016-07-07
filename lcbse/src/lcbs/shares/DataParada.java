package lcbs.shares;

public class DataParada extends DataPuntoRecorrido{
    	
	public DataParada() {}
	
	public DataParada(String id, String nom, String uMap, Boolean elim) {
	    setId(id);
	    setNombre(nom);
	    setUbicacionMapa(uMap);
	    setEliminado(elim);
	}
	
	public String getTipo(){
	    return "Parada";
	}
	
	public void setTipo(String val){}
}