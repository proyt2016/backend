 package interfaces;

import java.util.List;

import javax.ejb.Local;

import java.util.Map;
import lcbs.shares.DataEncomienda;
import lcbs.shares.DataEstadosEncomienda;
import lcbs.shares.DataHistorialEstadosEncomienda;
import lcbs.shares.DataPuntoRecorrido;
import lcbs.shares.DataRecorrido;
import lcbs.shares.DataReglaCobroEncomienda;
import lcbs.shares.DataTerminal;
import lcbs.shares.DataUsuario;
import lcbs.shares.DataVehiculo;
@Local
public interface IEncomienda {
	
	public List<DataEncomienda> ListarEncomiendas(Integer pagina, Integer elementosPagina);
	public List<DataReglaCobroEncomienda> getReglasDeCobro(Integer pagina, Integer elementosPagina);
	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda);
	public DataEncomienda AltaEncomienda(DataEncomienda encomienda);
	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda);
	public DataEstadosEncomienda getUltimoEstado(String idEncomienda);
	public DataEncomienda getEncomienda(String idEncomienda);
	public void setEstadoEncomienda(String idEncomienda, DataEstadosEncomienda dataEstado);
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idViaje);
	public void AsignarEncomiendasVehiculo(String IdEncomienda, String idViaje);
	public List<DataHistorialEstadosEncomienda> VerEstadosEncomienda(String idEncomienda);
	public void crearReglaDeCobro(DataReglaCobroEncomienda rdc);
	public void editarEncomienda(DataEncomienda encomienda);
	public void bajaEncomienda(String idEncomienda);
	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina);
	public void borrarEstadoEncomienda(String idEstadoEncomienda);
	public List<DataEstadosEncomienda> listarEstadoEncomienda(Integer pagina, Integer elementosPagina);
	public DataEstadosEncomienda crearEstadoEncomienda(DataEstadosEncomienda estado);
}
