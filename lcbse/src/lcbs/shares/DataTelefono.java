package lcbs.shares;

public class DataTelefono{
    
	private String telefono;
    
 

    public DataTelefono() {}
    
    public DataTelefono(String tel) {
        this.telefono = tel;
    }

    public void setTelefono(String val){
        this.telefono = val;
    }
    
    public String getTelefono(){
        return this.telefono;
    }
}