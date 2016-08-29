package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataPuntoRecorrido;
import lcbs.shares.DataTenant;
import lcbs.shares.DataTerminal;

@Local
public interface TerminalLocalApi {
	public List<DataTerminal> obtenerTerminals(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarTerminal(DataTerminal ter, DataTenant tenant);
	public DataTerminal getTerminal(String ter, DataTenant tenant);
	public DataTerminal crearTerminal(DataTerminal ter, DataTenant tenant);
	public void darBajaTerminal(String idTerminal, DataTenant tenant);
	public DataPuntoRecorrido getTerminalPorCoordenada(String coord, DataTenant tenant);
}
