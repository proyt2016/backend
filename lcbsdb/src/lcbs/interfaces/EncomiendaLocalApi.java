package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.*;

@Local
public interface EncomiendaLocalApi {
	public Map<String,DataEncomienda> obtenerEncomiendas();
	public void modificarEncomienda(DataEncomienda enc);
	public DataEncomienda getEncomienda(String id);
	public DataEncomienda crearEncomienda(DataEncomienda enc);
	public void darBajaEncomienda(String idEncomienda);
	public Map<String, DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina);
}
