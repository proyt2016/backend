package lcbs.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import interfaces.IVehiculo;
import lcbs.interfaces.*;
import lcbs.shares.*;

import javax.ejb.Stateless;




/**
 * Session Bean implementation class VehiculoCtrl
 */
@Stateless
public class VehiculoCtrl implements IVehiculo{
	@EJB(lookup="java:app/lcbsdb/VehiculoSrv!lcbs.interfaces.VehiculoLocalApi")
	VehiculoLocalApi srvVehiculo;

	@Override
	public DataVehiculo altaVehiculo(DataVehiculo vehiculo) {
		return srvVehiculo.crearVehiculo(vehiculo);
	}

	@Override
	public void editarVehiculo(DataVehiculo vehiculo) {
		srvVehiculo.modificarVehiculo(vehiculo);
	}

	@Override
	public void bajaVehiculo(String idVehiculo) {
		srvVehiculo.darBajaVehiculo(idVehiculo);
	}

	@Override
	public List<DataMantenimientoVehiculo> mantenimientosPorVehiculo(String idVehiculo) {
		DataVehiculo vehiculo = srvVehiculo.getVehiculo(idVehiculo);
		return vehiculo.getMantenimientos();
	}
	
	@Override
	public DataVehiculo obtenerVehiculo(String idVehiculo){
		return srvVehiculo.getVehiculo(idVehiculo);
	}
	
}
