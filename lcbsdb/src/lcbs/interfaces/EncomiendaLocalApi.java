package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.*;

@Local
public interface EncomiendaLocalApi {
	public List<DataEncomienda> obtenerEncomiendas(Integer pagina, Integer elementosPagina);
	public void modificarEncomienda(DataEncomienda enc);
	public DataEncomienda getEncomienda(String id);
	public DataEncomienda crearEncomienda(DataEncomienda enc);
	public void darBajaEncomienda(String idEncomienda);
	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina);
}
