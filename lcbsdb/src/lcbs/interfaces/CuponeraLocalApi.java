package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataCuponera;
import lcbs.shares.DataTenant;

@Local
public interface CuponeraLocalApi {
	public List<DataCuponera> obtenerCuponera(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarCuponera(DataCuponera cup, DataTenant tenant);
	public DataCuponera getCuponera(String id, DataTenant tenant);
}
