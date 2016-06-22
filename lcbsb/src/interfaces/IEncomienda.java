 package interfaces;

import java.util.List;

import javax.ejb.Local;

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
	
	public List<DataEncomienda> ListarEncomiendas();
	public List<DataTerminal> ListarTerminales();
	public List<DataVehiculo> ListarVehiculos();
	public List<DataUsuario> ListarUsuarios();
	public List<DataReglaCobroEncomienda> getReglasDeCobro();
	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda);
	public void AltaEncomienda(DataEncomienda encomienda);
	public List<DataRecorrido> getRecorridos();
	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda);
	public DataEstadosEncomienda getUltimoEstado(String idEncomienda);
	public DataVehiculo getVehiculo(String idVehiculo);
	public DataEncomienda getEncomienda(String idEncomienda);
	public DataTerminal getTerminal(String idTerminal);
	public void setEstadoEncomienda(String idEncomienda, DataEstadosEncomienda dataEstado);
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idVehiculo, String idViaje);
	public void AsignarEncomiendasVehiculo(String idVehiculo,String IdEncomienda, String idViaje);
	public List<DataHistorialEstadosEncomienda> VerEstadosEncomienda(String idEncomienda);
	public void SetPrecioEncomienda(String idEncomienda, DataReglaCobroEncomienda dataCobro);
}
