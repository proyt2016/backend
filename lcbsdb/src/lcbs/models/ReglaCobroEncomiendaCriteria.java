package lcbs.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import lcbs.shares.DataReglaCobroEncomiendaCriteria;

@Entity
@XmlRootElement
public class ReglaCobroEncomiendaCriteria  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String operador;
    private Integer valor;
    private float precio;
    
    public ReglaCobroEncomiendaCriteria() {}
    
    public ReglaCobroEncomiendaCriteria(String id, String oper, Integer val, float prec) {
        this.id = id;
        this.operador = oper;
        this.valor = val;
        this.precio = prec;
    }
    
    public ReglaCobroEncomiendaCriteria(DataReglaCobroEncomiendaCriteria dt){
        this.setId(dt.getId());
        this.setOperador(dt.getOperador());
        this.setPrecio(dt.getPrecio());
        this.setValor(dt.getValor());
    }
    
    public DataReglaCobroEncomiendaCriteria getDatatype(){
    	DataReglaCobroEncomiendaCriteria result = new DataReglaCobroEncomiendaCriteria();
    	result.setId(this.getId());
    	result.setOperador(this.getOperador());
    	result.setPrecio(this.getPrecio());
    	result.setValor(this.getValor());
    	return result;
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

    public void setPrecio(float val){
        this.precio = val;
    }
    
    public float getPrecio(){
        return this.precio;
    }

}
