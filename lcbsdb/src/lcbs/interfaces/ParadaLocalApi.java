package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataParada;

@Local
public interface ParadaLocalApi {
	public List<DataParada> obtenerParadas(Integer pagina, Integer elementosPagina);
	public void modificarParada(DataParada prd);
	public DataParada getParada(String id);
	public DataParada crearParada(DataParada prd);
	public void borrarParada(String idParada);
}
