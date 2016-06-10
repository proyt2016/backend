package lcbs.models;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.james.mime4j.field.datetime.DateTime;
import org.hibernate.mapping.List;

@Entity
@XmlRootElement
public class Encomienda implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
	private String id;

    private PuntoRecorrido origen;
    private PuntoRecorrido destino;
    private Usuario emisor;
    private String ciEmisor;
    private Telefono telEmisor;
    private Usuario receptor;
    private String ciReceptor;
    private Telefono telReceptor;
    private String direccionReceptor;
    private ReglaCobroEncomienda reglaCobro;
    private float monto;
    private boolean pagaReceptor;
    private Viaje viajeAsignado;
    private List<EstadoEncomienda> estados;
    private EstadoEncomienda estadoActual;
    private DateTime fechaIngreso;
    private DateTime fechaEntrega;
    private boolean retiraEnSucursal;

 

    public Encomienda() {
        id = "";
        origen = new PuntoRecorrido();
        destino = new PuntoRecorrido();
        emisor = new Usuario();
        ciEmisor = "";
        telEmisor = new Telefono();
        receptor = new Usuario();
        ciReceptor = "";
        telReceptor = new Telefono();
        direccionReceptor = "";
        reglaCobro = new ReglaCobroEncomienda();
        monto = 0.0f;
        pagaReceptor = false;
        viajeAsignado = new Viaje();
        estados = new List<EstadoEncomienda>();
        estadoActual = new EstadoEncomienda();
        fechaIngreso = new DateTime();
        fechaEntrega = new DateTime();
        retiraEnSucursal = false;

    }
    
    public Encomienda(String id, PuntoRecorrido orig, PuntoRecorrido dest, Usuario emi, String ciEm, Telefono telEm, Usuario rec, String ciRec, Telefono telRec, String dirRec, ReglaCobroEncomienda regCob, float mont, boolean pagaRec, Viaje viajeAs, List<EstadoEncomienda> estds, EstadoEncomienda estAc, DateTime fecIng, DateTime fecEn, boolean retiraSuc) {
        this.id = id;
        this.origen = orig;
        this.destino = dest;
        this.emisor = emi;
        this.ciEmisor = ciEm;
        this.telEmisor = telEm;
        this.receptor = rec;
        this.ciReceptor = ciRec;
        this.telReceptor = telEm;
        this.direccionReceptor = dirRec;
        this.reglaCobro = regCob;
        this.monto = mont;
        this.pagaReceptor = pagaRec;
        this.viajeAsignado = viajeAs;
        this.estados = estds;
        this.estadoActual = estAc;
        this.fechaIngreso = fecIng;
        this.fechaEntrega = fecEn;
        this.retiraEnSucursal = retiraSuc;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
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

    public void setEmisor(Usuario val){
        this.emisor = val;
    }
    
    public Usuario getEmisor(){
        return this.emisor;
    }

    public void setCiEmisor(String val){
        this.ciEmisor = val;
    }
    
    public String getCiEmisor(){
        return this.ciEmisor;
    }

    public void setTelEmisor(Telefono val){
        this.telEmisor = val;
    }
    
    public Telefono getTelEmisor(){
        return this.telEmisor;
    }

    public void setDireccionReceptor(String val){
        this.direccionReceptor = val;
    }
    
    public String getDireccionReceptor(){
        return this.direccionReceptor;
    }

    public void setReglaCobro(ReglaCobroEncomienda val){
        this.reglaCobro = val;
    }
    
    public ReglaCobroEncomienda getReglaCobro(){
        return this.reglaCobro;
    }

    public void setMonto(float val){
        this.monto = val;
    }
    
    public float getMonto(){
        return this.monto;
    }

    public void setPagaReceptor(boolean val){
        this.pagaReceptor = val;
    }
    
    public boolean getPagaReceptor(){
        return this.pagaReceptor;
    }

    public void setViajeAsignado(Viaje val){
        this.viajeAsignado = val;
    }
    
    public Viaje getViajeAsignado(){
        return this.viajeAsignado;
    }

    public void setEstados(List<EstadoEncomienda> val){
        this.estados = val;
    }
    
    public List<EstadoEncomienda> getEstados(){
        return this.estados;
    }

    public void setEstadoActual(EstadoEncomienda val){
        this.estadoActual = val;
    }
    
    public EstadoEncomienda getEstadoActual(){
        return this.estadoActual;
    }

    public void setFechaIngreso(DateTime val){
        this.fechaIngreso = val;
    }
    
    public DateTime getFechaIngreso(){
        return this.fechaIngreso;
    }

    public void setFechaEntrega(DateTime val){
        this.fechaEntrega = val;
    }
    
    public DateTime getFechaEntrega(){
        return this.fechaEntrega;
    }

    public void setRetiraEnSucursal(boolean val){
        this.retiraEnSucursal = val;
    }
    
    public boolean getRetiraEnSucursal(){
        return this.retiraEnSucursal;
    }
}