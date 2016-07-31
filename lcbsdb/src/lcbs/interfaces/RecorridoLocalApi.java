package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataRecorrido;

@Local
public interface RecorridoLocalApi {
	public List<DataRecorrido> obtenerRecorridos(Integer pagina, Integer elementosPagina);
	public void modificarRecorrido(DataRecorrido rec);
	public DataRecorrido getRecorrido(String rec);
	public DataRecorrido crearRecorrido(DataRecorrido rec);
	public void darBajaRecorrido(String idRecorrido);
	public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, Integer pagina, Integer elementosPagina);
}
