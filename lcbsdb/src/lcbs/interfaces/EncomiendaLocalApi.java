package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.*;

@Local
public interface EncomiendaLocalApi {
	public Map<String,DataEncomienda> obtenerEncomiendas();
	public void modificarEncomienda(DataEncomienda enc);
	public DataEncomienda getEncomienda(String id);
	public void crearEncomienda(DataEncomienda enc);
}
