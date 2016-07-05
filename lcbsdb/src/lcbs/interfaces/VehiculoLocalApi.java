package lcbs.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataVehiculo;

@Local
public interface VehiculoLocalApi {
	public List<DataVehiculo> obtenerVehiculos(Integer pagina, Integer elementosPagina);
	public void modificarVehiculo(DataVehiculo veh);
	public DataVehiculo getVehiculo(String veh);
	public DataVehiculo crearVehiculo(DataVehiculo veh);
	public void darBajaVehiculo(String idVeiculo);
}
