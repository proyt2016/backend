 package interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataEncomienda;
import lcbs.shares.DataEncomiendaConvertor;
import lcbs.shares.DataEstadosEncomienda;
import lcbs.shares.DataHistorialEstadosEncomienda;
import lcbs.shares.DataReglaCobroEncomienda;
import lcbs.shares.DataTenant;
@Local
public interface IEncomienda {
	
	public List<DataEncomiendaConvertor> ListarEncomiendas(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public List<DataReglaCobroEncomienda> getReglasDeCobro(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public List<DataEstadosEncomienda> getEstados(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda, DataTenant tenant);
	public DataEncomienda AltaEncomienda(DataEncomienda encomienda, DataTenant tenant);
	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda, DataTenant tenant);
	public DataEstadosEncomienda getUltimoEstado(String idEncomienda, DataTenant tenant);
	public DataEncomienda getEncomienda(String idEncomienda, DataTenant tenant);
	public void setEstadoEncomienda(String idEncomienda, DataEstadosEncomienda dataEstado, DataTenant tenant);
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idVehiculo, DataTenant tenant);
	public void AsignarEncomiendasVehiculo(String IdEncomienda, String idViaje, String idCoche, DataTenant tenant);
	public List<DataHistorialEstadosEncomienda> VerEstadosEncomienda(String idEncomienda, DataTenant tenant);
	public void crearReglaDeCobro(DataReglaCobroEncomienda rdc, DataTenant tenant);
	public void editarEncomienda(DataEncomienda encomienda, DataTenant tenant);
	public void bajaEncomienda(String idEncomienda, DataTenant tenant);
	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina, DataTenant tenant);
	public void borrarEstadoEncomienda(String idEstadoEncomienda, DataTenant tenant);
	public List<DataEstadosEncomienda> listarEstadoEncomienda(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataEstadosEncomienda crearEstadoEncomienda(DataEstadosEncomienda estado, DataTenant tenant);
	public List<DataEncomienda> listarEncomiendasPorUsuario(String idUsuario, Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void EditarEstadoEncomienda(DataEstadosEncomienda dataEstado, DataTenant tenant);
	public DataEstadosEncomienda getEstadoEncomienda(String idEstadoEncomienda, DataTenant tenant);
	public DataEncomienda getEncomiendaXcodigo(Integer codigoEncomienda, DataTenant tenant);
	public DataReglaCobroEncomienda editarReglaCobroEncomienda(DataReglaCobroEncomienda dataRegla, DataTenant tenant);
	public void borrarReglaCobroEncomienda(String idReglaCobro, DataTenant tenant);
	public Float getPrecioDeEncomienda(String codigoReglaCobro, Float monto, DataTenant tenant);
}
