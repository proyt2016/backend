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
public class VehiculoCtrl implements IVehiculo {
	@EJB(lookup = "java:app/lcbsdb/VehiculoSrv!lcbs.interfaces.VehiculoLocalApi")
	VehiculoLocalApi srvVehiculo;

	@Override
	public DataVehiculo altaVehiculo(DataVehiculo vehiculo, DataTenant tenant) {
		return srvVehiculo.crearVehiculo(vehiculo, tenant);
	}

	@Override
	public void editarVehiculo(DataVehiculo vehiculo, DataTenant tenant) {
		srvVehiculo.modificarVehiculo(vehiculo, tenant);
	}

	@Override
	public void bajaVehiculo(String idVehiculo, DataTenant tenant) {
		srvVehiculo.darBajaVehiculo(idVehiculo, tenant);
	}

	@Override
	public List<DataMantenimientoVehiculo> mantenimientosPorVehiculo(String idVehiculo, DataTenant tenant) {
		DataVehiculo vehiculo = srvVehiculo.getVehiculo(idVehiculo, tenant);
		return vehiculo.getMantenimientos();
	}

	@Override
	public DataVehiculo obtenerVehiculo(String idVehiculo, DataTenant tenant) {
		return srvVehiculo.getVehiculo(idVehiculo, tenant);
	}

	@Override
	public List<DataVehiculo> listarVehiculos(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvVehiculo.obtenerVehiculos(pagina, elementosPagina, tenant);
	}
	
	@Override
	public void altaMantenimiento(DataMantenimientoVehiculo mante,String id, DataTenant tenant){
		DataVehiculo ve = srvVehiculo.getVehiculo(id, tenant);
		List <DataMantenimientoVehiculo> lista = ve.getMantenimientos();
		lista.add(mante);
		ve.setMantenimientos(lista);
		ve.setEnMantenimiento(true);
		srvVehiculo.modificarVehiculo(ve, tenant);
	  
	}
	
	@Override
	public List<DataVehiculo> obtenerVehiculosEnMantenimiento(Integer pagina, Integer elementosPagina, DataTenant tenant){
		return srvVehiculo.obtenerVehiculosEnMantenimiento(pagina, elementosPagina, tenant);
	}
	
	@Override
	public int getMenorCantAsientos(Integer pagina, Integer elementosPagina, DataTenant tenant){
		return srvVehiculo.getMenorCantAsientos(pagina, elementosPagina, tenant);
	}


}
