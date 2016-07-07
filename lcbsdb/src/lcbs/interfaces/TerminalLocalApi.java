package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataPuntoRecorrido;
import lcbs.shares.DataTerminal;

@Local
public interface TerminalLocalApi {
	public List<DataTerminal> obtenerTerminals(Integer pagina, Integer elementosPagina);
	public void modificarTerminal(DataTerminal ter);
	public DataTerminal getTerminal(String ter);
	public DataTerminal crearTerminal(DataTerminal ter);
	public void darBajaTerminal(String idTerminal);
	public DataPuntoRecorrido getTerminalPorCoordenada(String coord);
}
