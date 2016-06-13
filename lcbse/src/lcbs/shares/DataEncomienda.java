package lcbs.shares;

import java.util.Date;
import java.util.List;

public class DataEncomienda {

	
	private String id;
    private DataPuntoRecorrido origen;
    private DataPuntoRecorrido destino;
    private DataUsuario emisor;
    private String ciEmisor;
    private DataTelefono telEmisor;
    private DataUsuario receptor;
    private String ciReceptor;
    private DataTelefono telReceptor;
    private String direccionReceptor;
    private DataReglaCobroEncomienda reglaCobro;
    private float monto;
    private boolean pagaReceptor;
    private Viaje viajeAsignado;
    private List<DataHistorialDataEstadosEncomienda> estados;
    private DataEstadosEncomienda estadoActual;
    private Date fechaIngreso;
    private Date fechaEntrega;
    private boolean retiraEnSucursal;

 

    public DataEncomienda() {}
    
    public DataEncomienda(String id, DataPuntoRecorrido orig, DataPuntoRecorrido dest, DataUsuario emi, String ciEm, DataTelefono telEm, DataUsuario rec, String ciRec, DataTelefono telRec, String dirRec, DataReglaCobroEncomienda regCob, float mont, boolean pagaRec, Viaje viajeAs, List<DataHistorialDataEstadosEncomienda> estds, DataEstadosEncomienda estAc, Date fecIng, Date fecEn, boolean retiraSuc) {
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

    public void setOrigen(DataPuntoRecorrido val){
        this.origen = val;
    }
    
    public DataPuntoRecorrido getOrigen(){
        return this.origen;
    }

    public void setDestino(DataPuntoRecorrido val){
        this.destino = val;
    }
    
    public DataPuntoRecorrido getDestino(){
        return this.destino;
    }

    public void setEmisor(DataUsuario val){
        this.emisor = val;
    }
    
    public DataUsuario getEmisor(){
        return this.emisor;
    }

    public void setCiEmisor(String val){
        this.ciEmisor = val;
    }
    
    public String getCiEmisor(){
        return this.ciEmisor;
    }

    public void setTelEmisor(DataTelefono val){
        this.telEmisor = val;
    }
    
    public DataTelefono getTelEmisor(){
        return this.telEmisor;
    }
    
    public void setReceptor(DataUsuario val){
        this.receptor = val;
    }
    
    public DataUsuario getReceptor(){
        return this.receptor;
    }
    
    public void setCiReceptor(String val){
        this.ciReceptor = val;
    }
    
    public String getCiReceptor(){
        return this.ciReceptor;
    }
    
    public void setTelReceptor(DataTelefono val){
        this.telReceptor = val;
    }
    
    public DataTelefono getTelReceptor(){
        return this.telReceptor;
    }

    public void setDireccionReceptor(String val){
        this.direccionReceptor = val;
    }
    
    public String getDireccionReceptor(){
        return this.direccionReceptor;
    }

    public void setReglaCobro(DataReglaCobroEncomienda val){
        this.reglaCobro = val;
    }
    
    public DataReglaCobroEncomienda getReglaCobro(){
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

    public void setEstados(List<DataHistorialDataEstadosEncomienda> val){
        this.estados = val;
    }
    
    public List<DataHistorialDataEstadosEncomienda> getEstados(){
        return this.estados;
    }

    public void setEstadoActual(DataEstadosEncomienda val){
        this.estadoActual = val;
    }
    
    public DataEstadosEncomienda getEstadoActual(){
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