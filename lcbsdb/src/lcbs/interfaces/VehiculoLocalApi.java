package lcbs.interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.shares.DataTenant;
import lcbs.shares.DataVehiculo;

@Local
public interface VehiculoLocalApi {
	public List<DataVehiculo> obtenerVehiculos(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public void modificarVehiculo(DataVehiculo veh, DataTenant tenant);
	public DataVehiculo getVehiculo(String veh, DataTenant tenant);
	public DataVehiculo crearVehiculo(DataVehiculo veh, DataTenant tenant);
	public void darBajaVehiculo(String idVeiculo, DataTenant tenant);
}
