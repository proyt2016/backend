package lcbs.shares;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class DataEncomiendaConvertor {
	
	private String id;
    private DataPuntoRecorridoConverter origen;
    private DataPuntoRecorridoConverter destino;
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
    private DataViaje viajeAsignado;
    private List<DataHistorialEstadosEncomienda> estados;
    private DataEstadosEncomienda estadoActual;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaIngreso;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaEntrega;
    private boolean retiraEnSucursal;
    private boolean eliminada;

 

    public DataEncomiendaConvertor() {}
    
    public DataEncomiendaConvertor(String id, DataPuntoRecorridoConverter orig, DataPuntoRecorridoConverter dest, DataUsuario emi, String ciEm, DataTelefono telEm, DataUsuario rec, String ciRec, DataTelefono telRec, String dirRec, DataReglaCobroEncomienda regCob, float mont, boolean pagaRec, DataViaje viajeAs, List<DataHistorialEstadosEncomienda> estds, DataEstadosEncomienda estAc, Date fecIng, Date fecEn, boolean retiraSuc, boolean elim) {
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
        this.eliminada = elim;
    }
    
    public DataEncomienda getDataEncomienda(){
    	DataEncomienda result = new DataEncomienda();
    	result.setId(this.getId());
    	if(this.getOrigen()!=null){
    		if(this.getOrigen().getTipo().equals("Terminal")){
    			result.setOrigen(new DataTerminal(this.getOrigen().getId(),this.getOrigen().getNombre(),this.getOrigen().getUbicacionMapa(),
    					this.getOrigen().getTelefonosContacto(),this.getOrigen().getMailsDeContacto(),this.getOrigen().getAceptaEncomiendas(),
    					this.getOrigen().getEliminado()));
    		}if(this.getOrigen().getTipo().equals("Parada")){
    			result.setOrigen(new DataParada(this.getOrigen().getId(),this.getOrigen().getNombre(),this.getOrigen().getUbicacionMapa(),
    					this.getOrigen().getEliminado()));
    	     }
    	}
    	if(this.getDestino()!=null){
    		if(this.getOrigen().getTipo().equals("Terminal")){
    			result.setOrigen(new DataTerminal(this.getOrigen().getId(),this.getOrigen().getNombre(),this.getOrigen().getUbicacionMapa(),
    					this.getOrigen().getTelefonosContacto(),this.getOrigen().getMailsDeContacto(),this.getOrigen().getAceptaEncomiendas(),
    					this.getOrigen().getEliminado()));
    		}if(this.getOrigen().getTipo().equals("Parada")){
    			result.setOrigen(new DataParada(this.getOrigen().getId(),this.getOrigen().getNombre(),this.getOrigen().getUbicacionMapa(),
    					this.getOrigen().getEliminado()));
    	     }
    	}
    	if(this.getEmisor()!=null){
    		result.setEmisor(this.getEmisor());}
    	if(this.getCiEmisor()!=null){
    		result.setCiEmisor(this.getCiEmisor());}
    	if(this.getTelEmisor()!=null){
    		result.setTelEmisor(this.getTelEmisor());    		
    	}
    	if(this.getReceptor()!=null){
    		result.setReceptor(this.getReceptor());
    	}
    	if(this.getCiReceptor()!=null){
    		result.setCiReceptor(this.getCiReceptor());
    	}
    	if(this.getTelReceptor()!=null){
    		result.setTelReceptor(this.getTelReceptor());
    	}
    	if(this.getDireccionReceptor()!=null){
    		result.setDireccionReceptor(this.getDireccionReceptor());
    	}
    	if(this.getReglaCobro()!=null){
    		result.setReglaCobro(this.getReglaCobro());
    	}
    	result.setMonto(this.getMonto());
    	result.setPagaReceptor(this.getPagaReceptor());
    	if(this.getViajeAsignado()!=null){
    		result.setViajeAsignado(this.getViajeAsignado());
    	}
    	if(this.getEstados()!=null){
    		result.setEstados(this.getEstados());
    	}
    	if(this.getEstadoActual()!=null){
    		result.setEstadoActual(this.getEstadoActual());
    	}
    	if(this.getFechaIngreso()!=null){
    		result.setFechaIngreso(this.getFechaIngreso());
    	}
    	if(this.getFechaEntrega()!=null){
    		result.setFechaEntrega(this.getFechaEntrega());
    	}
    	result.setRetiraEnSucursal(this.getRetiraEnSucursal());
    	result.setEliminada(this.getEliminada());
       	return result;
    }
    
    public void setId(String val){
        this.id = val;
    }
    
    public String getId(){
        return this.id;
    }

    public void setOrigen(DataPuntoRecorridoConverter val){
        this.origen = val;
    }
    
    public DataPuntoRecorridoConverter getOrigen(){
        return this.origen;
    }

    public void setDestino(DataPuntoRecorridoConverter val){
        this.destino = val;
    }
    
    public DataPuntoRecorridoConverter getDestino(){
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

    public void setViajeAsignado(DataViaje val){
        this.viajeAsignado = val;
    }
    
    public DataViaje getViajeAsignado(){
        return this.viajeAsignado;
    }

    public void setEstados(List<DataHistorialEstadosEncomienda> val){
        this.estados = val;
    }
    
    public List<DataHistorialEstadosEncomienda> getEstados(){
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
    
    public void setEliminada(boolean val){
        this.eliminada = val;
    }
    
    public boolean getEliminada(){
        return this.eliminada;
    }

}
