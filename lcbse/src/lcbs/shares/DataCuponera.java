package lcbs.shares;

public class DataCuponera{
    
    private String id;
    private Float saldo;
 

    public DataCuponera() {}
    
    public DataCuponera(String id, Float saldo) {
        this.id = id;
        this.saldo = saldo;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setSaldo(Float val){
        this.saldo = val;
    }
    
    public Float getSaldo(){
        return this.saldo;
    }
}