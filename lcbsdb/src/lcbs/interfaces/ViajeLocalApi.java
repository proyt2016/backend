package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.models.Viaje;
import lcbs.shares.DataTenant;
import lcbs.shares.DataViaje;

@Local
public interface ViajeLocalApi {
	public List<DataViaje> obtenerViajes(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarViaje(DataViaje via, DataTenant tenant);
	public List<DataViaje> viajesPorTerminal(String idterminal, Integer pagina, Integer ElementosPagina, DataTenant tenant);
	
	public DataViaje getViaje(String via, DataTenant tenant);
	public DataViaje crearViaje(DataViaje via, DataTenant tenant);
	public void borrarViaje(String idViaje, DataTenant tenant);
	public List<DataViaje> buscarViaje(DataViaje filtro, Integer cantidadDias,Integer pagina, Integer ElementosPagina, DataTenant tenant);
	public void crearViajes(List<DataViaje> viajes, DataTenant tenant);
}
