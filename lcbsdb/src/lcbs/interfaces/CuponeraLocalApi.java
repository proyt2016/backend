package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataCuponera;

@Local
public interface CuponeraLocalApi {
	public List<DataCuponera> obtenerCuponera(Integer pagina, Integer elementosPagina);
	public void modificarCuponera(DataCuponera cup);
	public DataCuponera getCuponera(String id);
}
