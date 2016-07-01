package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataEstadosEncomienda;

@Local
public interface EstadosEncomiendaLocalApi {
	public Map<String,DataEstadosEncomienda> obtenerEstadosEncomienda(Integer pagina, Integer elementosPagina);
	public void modificarEstadosEncomienda(DataEstadosEncomienda estEnc);
	public DataEstadosEncomienda crearEstadosEncomienda(DataEstadosEncomienda est);
	public void borrarEstadosEncomienda(String idEmpleado);
}
