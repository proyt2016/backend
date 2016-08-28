package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataPasaje;
import lcbs.shares.DataPasajeConvertor;

@Local
public interface PasajeLocalApi {
	public List<DataPasajeConvertor> obtenerPasajes(Integer pagina, Integer elementosPagina);
	public void modificarPasaje(DataPasaje psj);
	public DataPasaje getPasaje(String id);
	public DataPasaje crearPasaje(DataPasaje psj);
	public void darBajaPasaje(String idPasaje);
	public DataPasaje getpasajeXcodigo(Integer codigoPasaje);
	public List<DataPasajeConvertor> obtenerPasajesPorPersona(String idUsuario, Integer pagina, Integer elementosPagina);
}
