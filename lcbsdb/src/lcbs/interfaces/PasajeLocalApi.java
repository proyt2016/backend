package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataPasaje;

@Local
public interface PasajeLocalApi {
	public Map<String,DataPasaje> obtenerPasajes();
	public void modificarPasaje(DataPasaje psj);
	public DataPasaje getPasaje(String id);
	public void crearPasaje(DataPasaje psj);
	public void darBajaPasaje(DataPasaje psj);
}
