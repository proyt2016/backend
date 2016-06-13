package lcbs.shares;

public class DataTelefono{
    
	private String id;
    
    private String telefono;
    
 

    public DataTelefono() {}
    
    public DataTelefono(String id, String nom) {
        this.id = id;
        this.telefono = nom;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setDataTelefono(String val){
        this.telefono = val;
    }
    
    public String getDataTelefono(){
        return this.telefono;
    }
}