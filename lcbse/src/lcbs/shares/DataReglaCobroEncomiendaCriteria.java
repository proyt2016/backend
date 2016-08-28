package lcbs.shares;

public class DataReglaCobroEncomiendaCriteria {

    private String id;
    private String operador;
    private Integer valor;
    private Float precio;
    
public DataReglaCobroEncomiendaCriteria() {}
    
    public DataReglaCobroEncomiendaCriteria(String id, String oper, Integer val, Float prec) {
        this.id = id;
        this.operador = oper;
        this.valor = val;
        this.precio = prec;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setOperador(String val){
        this.operador = val;
    }
    
    public String getOperador(){
        return this.operador;
    }

    public void setValor(Integer val){
        this.valor = val;
    }
    
    public Integer getValor(){
        return this.valor;
    }

    public void setPrecio(Float val){
        this.precio = val;
    }
    
    public Float getPrecio(){
        return this.precio;
    }

}
