package lcbs.shares;

public class DataHorario{
    
	private String id;
    
    private String nombre;
    
 

    public DataHorario() {
        id = "";
        nombre = "";
    }
    
    public DataHorario(String id, String nom) {
        this.id = id;
        this.nombre = nom;
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
}