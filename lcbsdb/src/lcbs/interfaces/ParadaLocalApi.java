package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataParada;
import lcbs.shares.DataPuntoRecorrido;
import lcbs.shares.DataTenant;

@Local
public interface ParadaLocalApi {
	public List<DataParada> obtenerParadas(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarParada(DataParada prd, DataTenant tenant);
	public DataParada getParada(String id, DataTenant tenant);
	public DataParada crearParada(DataParada prd, DataTenant tenant);
	public void borrarParada(String idParada, DataTenant tenant);
	public DataPuntoRecorrido getParadaPorCoordenada(String coord, DataTenant tenant);
}
