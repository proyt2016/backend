package lcbs.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataPasaje;
import lcbs.shares.DataPasajeConvertor;
import lcbs.shares.DataTenant;

@Local
public interface PasajeLocalApi {
	public List<DataPasajeConvertor> obtenerPasajes(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarPasaje(DataPasaje psj, DataTenant tenant);
	public DataPasaje getPasaje(String id, DataTenant tenant);
	public DataPasaje crearPasaje(DataPasaje psj, DataTenant tenant);
	public void darBajaPasaje(String idPasaje, DataTenant tenant);
	public DataPasaje getpasajeXcodigo(Integer codigoPasaje, DataTenant tenant);
	public List<DataPasajeConvertor> obtenerPasajesPorPersona(String idUsuario, Integer pagina, Integer elementosPagina, DataTenant tenant);
	public List<DataPasajeConvertor> obtenerTotalPasajesVendidos(String fecha, Integer pagina, Integer elementosPagina, DataTenant tenant);

}
