package lcbs.models;
import java.io.Serializable;

import javax.persistence.Id;

import org.apache.james.mime4j.field.datetime.DateTime;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Pasaje implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    private Viaje viaje;
    private Precio precio;
    private PuntoRecorrido origen;
    private PuntoRecorrido destino;
    private DateTime fechaCompra;
    private Usuario comprador;
    private String ciPersona;
    private Empleado vendedor;
    
    

    public Pasaje() {
        id = "";
        viaje = new Viaje();
        precio = new Precio();
        origen = new PuntoRecorrido();
        destino = new PuntoRecorrido();
        fechaCompra = new DateTime();
        comprador = new Usuario();
        ciPersona = "";
        vendedor = new Empleado();
    }
    
    public Pasaje(String id, Viaje via, Precio prec, PuntoRecorrido orig, PuntoRecorrido des, DateTime fecVen, Usuario comp, String ciPer, Empleado vend) {
        this.id = id;
        this.viaje = via;
        this.precio = prec;
        this.origen = orig;
        this.destino = des;
        this.fechaCompra = fecVen;
        this.comprador = comp;
        this.ciPersona = ciPer;
        this.vendedor = vend;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setViaje(Viaje val){
        this.viaje = val;
    }
    
    public Viaje getViaje(){
        return this.viaje;
    }

    public void setPrecio(Precio val){
        this.precio = val;
    }
    
    public Precio getPrecio(){
        return this.precio;
    }

    public void setOrigen(PuntoRecorrido val){
        this.origen = val;
    }
    
    public PuntoRecorrido getOrigen(){
        return this.origen;
    }

    public void setDestino(PuntoRecorrido val){
        this.destino = val;
    }
    
    public PuntoRecorrido getDestino(){
        return this.destino;
    }

    public void setFechaCompra(DateTime val){
        this.fechaCompra = val;
    }
    
    public DateTime getFechaCompra(){
        return this.fechaCompra;
    }

    public void setComprador(Usuario val){
        this.comprador = val;
    }
    
    public Usuario getComprador(){
        return this.comprador;
    }

    public void setCiPersona(String val){
        this.ciPersona = val;
    }
    
    public String getCiPersona(){
        return this.ciPersona;
    }

    public void setVendedor(Empleado val){
        this.vendedor = val;
    }
    
    public Empleado getVendedor(){
        return this.vendedor;
    }
}