package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataEstadosEncomienda;
import lcbs.shares.DataTenant;

@Local
public interface EstadosEncomiendaLocalApi {
	public List<DataEstadosEncomienda> obtenerEstadosEncomienda(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarEstadosEncomienda(DataEstadosEncomienda estEnc, DataTenant tenant);
	public DataEstadosEncomienda getEstadosEncomienda(String id, DataTenant tenant);
	public DataEstadosEncomienda crearEstadosEncomienda(DataEstadosEncomienda est, DataTenant tenant);
	public void borrarEstadosEncomienda(String idEmpleado, DataTenant tenant);
}
