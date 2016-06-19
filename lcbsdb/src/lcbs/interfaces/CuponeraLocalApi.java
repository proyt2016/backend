package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataCuponera;

@Local
public interface CuponeraLocalApi {
	public Map<String,DataCuponera> obtenerCuponera();
	public void modificarCuponera(DataCuponera cup);
	public DataCuponera getCuponera(String id);
}
