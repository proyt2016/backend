package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import lcbs.shares.*;
public interface IVehiculo {
	
	public DataVehiculo altaVehiculo(DataVehiculo vehiculo, DataTenant tenant);
	public void editarVehiculo(DataVehiculo vehiculo, DataTenant tenant);
	public void bajaVehiculo(String idVehiculo, DataTenant tenant);
	public List<DataVehiculo> listarVehiculos(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public List<DataMantenimientoVehiculo> mantenimientosPorVehiculo(String idVehiculo, DataTenant tenant);
	public DataVehiculo obtenerVehiculo(String idVehiculo, DataTenant tenant);
	public void altaMantenimiento(DataMantenimientoVehiculo mante,String id, DataTenant tenant);
	public List<DataVehiculo> obtenerVehiculosEnMantenimiento(Integer pagina, Integer elementosPagina, DataTenant tenant);
	public int getMenorCantAsientos(Integer pagina, Integer elementosPagina, DataTenant tenant);


}
