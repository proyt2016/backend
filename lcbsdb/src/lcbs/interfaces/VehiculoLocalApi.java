package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataVehiculo;

@Local
public interface VehiculoLocalApi {
	public Map<String,DataVehiculo> obtenerVehiculos();
	public void modificarVehiculo(DataVehiculo veh);
	public DataVehiculo getVehiculo(String veh);
	public void crearVehiculo(DataVehiculo veh);
	public void darBajaVehiculo(DataVehiculo veh);
}
