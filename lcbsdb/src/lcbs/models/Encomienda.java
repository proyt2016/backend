package lcbs.models;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@XmlRootElement
public class Encomienda implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private PuntoRecorrido origen;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private PuntoRecorrido destino;
    @ManyToOne
    private Usuario emisor;
    private String ciEmisor;
    /*@Embedded
    private Telefono telEmisor;
        */
    @Embedded
    private Telefono telReceptor;

    @ManyToOne
    private Usuario receptor;
    private String ciReceptor;
   
    private String direccionReceptor;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private ReglaCobroEncomienda reglaCobro;
    private float monto;
    private boolean pagaReceptor;
    @ManyToOne
    private Viaje viajeAsignado;
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<HistorialEstadosEncomienda> estados;
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private EstadosEncomienda estadoActual;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrega;
    private boolean retiraEnSucursal;

 

    public Encomienda() {}
    
    public Encomienda(String id, PuntoRecorrido orig, PuntoRecorrido dest, Usuario emi, String ciEm, Telefono telEm, Usuario rec, String ciRec, Telefono telRec, String dirRec, ReglaCobroEncomienda regCob, float mont, boolean pagaRec, Viaje viajeAs, List<HistorialEstadosEncomienda> estds, EstadosEncomienda estAc, Date fecIng, Date fecEn, boolean retiraSuc) {
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
    
    public void setReceptor(Usuario val){
        this.receptor = val;
    }
    
    public Usuario getReceptor(){
        return this.receptor;
    }
    
    public void setCiReceptor(String val){
        this.ciReceptor = val;
    }
    
    public String getCiReceptor(){
        return this.ciReceptor;
    }
    
    public void setTelReceptor(Telefono val){
        this.telReceptor = val;
    }
    
    public Telefono getTelReceptor(){
        return this.telReceptor;
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

    public void setEstados(List<HistorialEstadosEncomienda> val){
        this.estados = val;
    }
    
    public List<HistorialEstadosEncomienda> getEstados(){
        return this.estados;
    }

    public void setEstadoActual(EstadosEncomienda val){
        this.estadoActual = val;
    }
    
    public EstadosEncomienda getEstadoActual(){
        return this.estadoActual;
    }

    public void setFechaIngreso(Date val){
        this.fechaIngreso = val;
    }
    
    public Date getFechaIngreso(){
        return this.fechaIngreso;
    }

    public void setFechaEntrega(Date val){
        this.fechaEntrega = val;
    }
    
    public Date getFechaEntrega(){
        return this.fechaEntrega;
    }

    public void setRetiraEnSucursal(boolean val){
        this.retiraEnSucursal = val;
    }
    
    public boolean getRetiraEnSucursal(){
        return this.retiraEnSucursal;
    }
}