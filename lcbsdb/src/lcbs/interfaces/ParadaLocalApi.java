package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataParada;

@Local
public interface ParadaLocalApi {
	public Map<String,DataParada> obtenerParadas();
	public void modificarParada(DataParada prd);
	public DataParada getParada(String id);
	public void crearParada(DataParada prd);
	public void borrarParada(DataParada prd);
}
