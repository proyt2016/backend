package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataRecorrido;
import lcbs.shares.DataTenant;

@Local
public interface RecorridoLocalApi {
	public List<DataRecorrido> obtenerRecorridos(Integer pagina, Integer elementosPagina, DataTenant tenant);

	public void modificarRecorrido(DataRecorrido rec, DataTenant tenant);

	public DataRecorrido getRecorrido(String rec, DataTenant tenant);

	public DataRecorrido crearRecorrido(DataRecorrido rec, DataTenant tenant);

	public void darBajaRecorrido(String idRecorrido, DataTenant tenant);

	public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, Integer pagina, Integer elementosPagina, DataTenant tenant);
}
