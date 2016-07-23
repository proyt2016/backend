package lcbs.shares;

import java.util.List;

public class DataTerminal extends DataPuntoRecorrido{
    
	private List<DataTelefono> telefonosContacto;
    private List<DataEmail> mailsDeContacto;
    private Boolean aceptaEncomiendas;

  
    public DataTerminal() {}
    
    public DataTerminal(String id, String nom, String uMap, List<DataTelefono> tels, List<DataEmail> mails, Boolean acEnc, Boolean elim) {
        setId(id);
        setNombre(nom);
        setUbicacionMapa(uMap);
        setEliminado(elim);
        
        this.telefonosContacto = tels;
        this.mailsDeContacto = mails;
        this.aceptaEncomiendas = acEnc;
    }
    
    public DataPuntoRecorridoConverter getConvertor(){
		DataPuntoRecorridoConverter result = new DataPuntoRecorridoConverter();
		result.setId(this.getId());
		result.setNombre(this.getNombre());
		result.setUbicacionMapa(this.getUbicacionMapa());
		result.setEliminado(this.getEliminado());
		result.setAceptaEncomiendas(this.getAceptaEncomiendas());
		result.setMailsDeContacto(this.getMailsDeContacto());
		result.setTelefonosContacto(this.getTelefonosContacto());
		return result;
	}
    
    
    public void setTelefonosContacto(List<DataTelefono> val){
        this.telefonosContacto = val;
    }
    
    public List<DataTelefono> getTelefonosContacto(){
        return this.telefonosContacto;
    }

    public void setMailsDeContacto(List<DataEmail> val){
        this.mailsDeContacto = val;
    }
    
    public List<DataEmail> getMailsDeContacto(){
        return this.mailsDeContacto;
    }

    public void setAceptaEncomiendas(Boolean val){
        this.aceptaEncomiendas = val;
    }
    
    public Boolean getAceptaEncomiendas(){
        return this.aceptaEncomiendas;
    }

    public String getTipo(){
        return "Terminal";
    }
    
    public void setTipo(String val){}
    
}