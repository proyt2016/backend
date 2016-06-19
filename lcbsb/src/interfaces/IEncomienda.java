package interfaces;

import java.util.List;

import lcbs.shares.*;

public interface IEncomienda {
	
	public List<DataEncomienda> ListarEncomiendas();
	public List<DataTerminal> ListarTerminales();
	public List<DataVehiculo> getVehiculos();
	public DataVehiculo getVehiculo(String idVehiculo);
	public DataEncomienda getEncomienda(String idEncomienda);
	public DataTerminal getTerminal(String idTerminal);
	public boolean setEstadoEncomiendaIndividual(String idEncomienda, DataEstadosEncomienda dataEstado);
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idVehiculo);
	public boolean setEstadoEncomiendaPorVehiculo(String idEncomienda, DataEstadosEncomienda estado);
	
	
	
	
	
	//ver estas operaciones quede en - Cambios de estados de encomiendas de forma masiva por coche.(ENC)

	
	
	public List<DataHistorialEstadosEncomienda> VerEstadoEncomienda(String idEncomienda);
	public boolean CambiarEstadoEncomienda(String idEncomienda, DataEstadosEncomienda dataEstado);
	public boolean CambioDeEstadoMasivoPorCoche(String idCoche,String idEncomienda, List<DataEstadosEncomienda> listaEstados);
	public void AsignarEncomiendasCoche(String idCoche, DataEncomienda encomienda);
	public void CambiarEstadoEncomiendasGeneral(String idEncomienda, DataEstadosEncomienda estadoNuevo);
	public void SetPrecioEncomienda(String idEncomienda, DataReglaCobroEncomienda dataCobro);
}
