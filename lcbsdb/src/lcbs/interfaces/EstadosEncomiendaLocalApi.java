package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataEstadosEncomienda;

@Local
public interface EstadosEncomiendaLocalApi {
	public Map<String,DataEstadosEncomienda> obtenerEstadosEncomienda();
	public void modificarEstadosEncomienda(DataEstadosEncomienda estEnc);
	public void crearEstadosEncomienda(DataEstadosEncomienda est);
	public void borrarEstadosEncomienda(DataEstadosEncomienda emp);
}
