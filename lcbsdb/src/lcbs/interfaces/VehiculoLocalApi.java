package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataVehiculo;

@Local
public interface VehiculoLocalApi {
	public Map<String,DataVehiculo> obtenerVehiculos(Integer pagina, Integer elementosPagina);
	public void modificarVehiculo(DataVehiculo veh);
	public DataVehiculo getVehiculo(String veh);
	public DataVehiculo crearVehiculo(DataVehiculo veh);
	public void darBajaVehiculo(String idVeiculo);
}
