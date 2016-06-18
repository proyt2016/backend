package lcbs.models;

import java.io.Serializable;
import lcbs.shares.*;

import javax.persistence.Id;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class ReglaCobroEncomienda implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String nombre;
    private String operador;
    private Integer valor;
    private float precio;
    
 

    public ReglaCobroEncomienda() {}
    
    public ReglaCobroEncomienda(String id, String nom, String oper, Integer val, float prec) {
        this.id = id;
        this.nombre = nom;
        this.operador = oper;
        this.valor = val;
        this.precio = prec;
    }
    
    public ReglaCobroEncomienda(DataReglaCobroEncomienda dt){
    	this.setId(dt.getId());
    	this.setNombre(dt.getNombre());
    	this.setOperador(dt.getOperador());
    	this.setValor(dt.getValor());
    	this.setPrecio(dt.getPrecio());
    }
    
    public DataReglaCobroEncomienda getDatatype(){
    	DataReglaCobroEncomienda result = new DataReglaCobroEncomienda();
    	result.setId(this.getId());
    	result.setNombre(this.getNombre());
    	result.setOperador(this.getOperador());
    	result.setValor(this.getValor());
    	result.setPrecio(this.getPrecio());
    	return result;
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