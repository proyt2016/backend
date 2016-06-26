package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import lcbs.shares.*;
public interface IVehiculo {
	
	public DataVehiculo altaVehiculo(DataVehiculo vehiculo);
	public void editarVehiculo(DataVehiculo vehiculo);
	public void bajaVehiculo(String idVehiculo);
	public List<DataMantenimientoVehiculo> mantenimientosPorVehiculo(String idVehiculo);

}
