package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataRecorrido;

@Local
public interface RecorridoLocalApi {
	public Map<String,DataRecorrido> obtenerRecorridos();
	public void modificarRecorrido(DataRecorrido rec);
	public DataRecorrido getRecorrido(String rec);
	public DataRecorrido crearRecorrido(DataRecorrido rec);
	public void darBajaRecorrido(DataRecorrido rec);
}
