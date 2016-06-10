package lcbs.models;

import java.io.Serializable;

import javax.persistence.Id;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class ReglaCobroEncomienda implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private String nombre;
    private String operador;
    private Integer valor;
    private float precio;
    
 

    public ReglaCobroEncomienda() {
        id = "";
        nombre = "";
        operador = "";
        valor = 0;
        precio = 0.0f;
    }
    
    public ReglaCobroEncomienda(String id, String nom, String oper, Integer val, float prec) {
        this.id = id;
        this.nombre = nom;
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