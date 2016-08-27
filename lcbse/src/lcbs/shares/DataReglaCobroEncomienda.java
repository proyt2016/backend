package lcbs.shares;

import java.util.List;

public class DataReglaCobroEncomienda{
    
	private String id;
    
    private String nombre;
    private Boolean precioExactoOCalculo;
    private List<DataReglaCobroEncomiendaCriteria> criterias;
    
 

    public DataReglaCobroEncomienda() {}
    
    public DataReglaCobroEncomienda(String id, String nom, List<DataReglaCobroEncomiendaCriteria> crit, Boolean proc) {
        this.id = id;
        this.nombre = nom;
        this.criterias = crit;
        this.precioExactoOCalculo = proc;
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

    public void setCriterias(List<DataReglaCobroEncomiendaCriteria> val){
        this.criterias = val;
    }
    
    public List<DataReglaCobroEncomiendaCriteria> getCriterias(){
        return this.criterias;
    }
    
    public void setPrecioExactoOCalculo(Boolean val){
        this.precioExactoOCalculo = val;
    }
    
    public Boolean getPrecioExactoOCalculo(){
        return this.precioExactoOCalculo;
    } 
    
}