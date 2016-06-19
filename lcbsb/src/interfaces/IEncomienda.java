 package interfaces;

import java.util.List;

import lcbs.shares.*;

public interface IEncomienda {
	
	public List<DataEncomienda> ListarEncomiendas();
	public List<DataTerminal> ListarTerminales();
	public List<DataVehiculo> ListarVehiculos();
	public List<DataUsuario> ListarUsuarios();
	public List<DataReglaCobroEncomienda> getReglasDeCobro();
	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda);
	public void AltaEncomienda(DataEncomienda encomienda);
	public List<DataPuntoRecorrido> getRecorridos();
	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda);
	public DataEstadosEncomienda getUltimoEstado(String idEncomienda);
	public DataVehiculo getVehiculo(String idVehiculo);
	public DataEncomienda getEncomienda(String idEncomienda);
	public DataTerminal getTerminal(String idTerminal);
	public boolean setEstadoEncomiendaIndividual(String idEncomienda, DataEstadosEncomienda dataEstado);
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idVehiculo);
	public boolean setEstadoEncomiendaPorVehiculo(String idEncomienda, DataEstadosEncomienda estado);
	public void AsignarEncomiendasVehiculo(DataVehiculo vehiculo,String IdEncomienda);
	public List<DataHistorialEstadosEncomienda> VerEstadoEncomienda(String idEncomienda);
	public void SetPrecioEncomienda(String idEncomienda, DataReglaCobroEncomienda dataCobro);
}
