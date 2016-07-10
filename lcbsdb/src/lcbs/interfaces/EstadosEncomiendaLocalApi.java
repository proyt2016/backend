package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataEstadosEncomienda;

@Local
public interface EstadosEncomiendaLocalApi {
	public List<DataEstadosEncomienda> obtenerEstadosEncomienda(Integer pagina, Integer elementosPagina);
	public void modificarEstadosEncomienda(DataEstadosEncomienda estEnc);
	public DataEstadosEncomienda getEstadosEncomienda(String id);
	public DataEstadosEncomienda crearEstadosEncomienda(DataEstadosEncomienda est);
	public void borrarEstadosEncomienda(String idEmpleado);
}
