package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataViaje;

@Local
public interface ViajeLocalApi {
	public Map<String,DataViaje> obtenerViajes();
	public void modificarViaje(DataViaje via);
	public DataViaje getViaje(String via);
	public DataViaje crearViaje(DataViaje via);
	public void borrarViaje(DataViaje via);
	public Map<String, DataViaje> buscarViaje(DataViaje filtro);
}
