package lcbs.shares;

public class DataParada extends DataPuntoRecorrido{
    	
	public DataParada() {}
	
	public DataParada(String id, String nom, String uMap, Boolean elim) {
	    setId(id);
	    setNombre(nom);
	    setUbicacionMapa(uMap);
	    setEliminado(elim);
	}
	
	public DataPuntoRecorridoConverter genConvertor(){
		DataPuntoRecorridoConverter result = new DataPuntoRecorridoConverter();
		result.setId(this.getId());
		result.setNombre(this.getNombre());
		result.setUbicacionMapa(this.getUbicacionMapa());
		result.setEliminado(this.getEliminado());
		return result;
	}
	
	public String getTipo(){
	    return "Parada";
	}
	
	public void setTipo(String val){}
}