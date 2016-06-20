package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataTerminal;

@Local
public interface TerminalLocalApi {
	public Map<String,DataTerminal> obtenerTerminals();
	public void modificarTerminal(DataTerminal ter);
	public DataTerminal getTerminal(String ter);
	public DataTerminal crearTerminal(DataTerminal ter);
	public void darBajaTerminal(DataTerminal ter);
}
