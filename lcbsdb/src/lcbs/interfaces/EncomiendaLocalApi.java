package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.*;

@Local
public interface EncomiendaLocalApi {
	public List<DataEncomiendaConvertor> obtenerEncomiendas(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarEncomienda(DataEncomienda enc, DataTenant tenant);
	public DataEncomienda getEncomienda(String id, DataTenant tenant);
	public DataEncomienda crearEncomienda(DataEncomienda enc, DataTenant tenant);
	public void darBajaEncomienda(String idEncomienda, DataTenant tenant);
	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina, DataTenant tenant);
	public List<DataEncomienda> listarEncomiendasPorUsuario(String idUsuario, Integer pagina, Integer elementosPagina, DataTenant tenant);
	public DataEncomienda getEncomiendaXcodigo(Integer codigoEncomienda, DataTenant tenant);
}
