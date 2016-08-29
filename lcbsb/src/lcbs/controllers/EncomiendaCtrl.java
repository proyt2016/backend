package lcbs.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import interfaces.IEncomienda;
import lcbs.interfaces.*;
import lcbs.shares.*;

/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class EncomiendaCtrl implements IEncomienda {

	@EJB(lookup = "java:app/lcbsdb/EncomiendaSrv!lcbs.interfaces.EncomiendaLocalApi")
	EncomiendaLocalApi srvEncomienda;
	@EJB(lookup = "java:app/lcbsdb/TerminalSrv!lcbs.interfaces.TerminalLocalApi")
	TerminalLocalApi srvTerminal;
	@EJB(lookup = "java:app/lcbsdb/UsuarioSrv!lcbs.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;
	@EJB(lookup = "java:app/lcbsdb/VehiculoSrv!lcbs.interfaces.VehiculoLocalApi")
	VehiculoLocalApi srvVehiculo;
	@EJB(lookup = "java:app/lcbsdb/ReglaCobroEncomiendaSrv!lcbs.interfaces.ReglaCobroEncomiendaLocalApi")
	ReglaCobroEncomiendaLocalApi srvReglaCobro;
	@EJB(lookup = "java:app/lcbsdb/RecorridoSrv!lcbs.interfaces.RecorridoLocalApi")
	RecorridoLocalApi srvRecorrido;
	@EJB(lookup = "java:app/lcbsdb/EstadosEncomiendaSrv!lcbs.interfaces.EstadosEncomiendaLocalApi")
	EstadosEncomiendaLocalApi srvEstadosEncomienda;
	@EJB(lookup = "java:app/lcbsdb/ViajeSrv!lcbs.interfaces.ViajeLocalApi")
	ViajeLocalApi srvViaje;

	@Override
	public List<DataEncomiendaConvertor> ListarEncomiendas(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvEncomienda.obtenerEncomiendas(pagina, elementosPagina, tenant);

	}

	@Override
	public List<DataEstadosEncomienda> getEstados(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvEstadosEncomienda.obtenerEstadosEncomienda(pagina, elementosPagina, tenant);
	}

	@Override
	public List<DataReglaCobroEncomienda> getReglasDeCobro(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvReglaCobro.obtenerReglaCobroEncomiendas(pagina, elementosPagina, tenant);
	}

	@Override
	public void crearReglaDeCobro(DataReglaCobroEncomienda rdc, DataTenant tenant) {
		srvReglaCobro.crearReglaCobroEncomienda(rdc, tenant);
	}

	@Override
	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda, DataTenant tenant) {
		DataReglaCobroEncomienda dataReglaCobro = srvReglaCobro.getReglaCobroEncomienda(idEncomieda, tenant);
		return dataReglaCobro;
	}

	@Override
	public DataEncomienda AltaEncomienda(DataEncomienda encomienda, DataTenant tenant) {
		return srvEncomienda.crearEncomienda(encomienda, tenant);
	}

	@Override
	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda, DataTenant tenant) {
		return srvEncomienda.getEncomienda(idEncomienda, tenant).getEstados();

	}

	@Override
	public DataEstadosEncomienda getUltimoEstado(String idEncomienda, DataTenant tenant) {
		DataEncomienda dataEncomienda = srvEncomienda.getEncomienda(idEncomienda, tenant);
		return dataEncomienda.getEstadoActual();
	}

	@Override
	public DataEncomienda getEncomienda(String idEncomienda, DataTenant tenant) {
		return srvEncomienda.getEncomienda(idEncomienda, tenant);
	}

	@Override
	public DataEstadosEncomienda crearEstadoEncomienda(DataEstadosEncomienda estado, DataTenant tenant) {
		return srvEstadosEncomienda.crearEstadosEncomienda(estado, tenant);
	}

	@Override
	public List<DataEstadosEncomienda> listarEstadoEncomienda(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvEstadosEncomienda.obtenerEstadosEncomienda(pagina, elementosPagina, tenant);
	}

	@Override
	public void borrarEstadoEncomienda(String idEstadoEncomienda, DataTenant tenant) {
		srvEstadosEncomienda.borrarEstadosEncomienda(idEstadoEncomienda, tenant);
	}

	@Override
	public void setEstadoEncomienda(String idEncomienda, DataEstadosEncomienda dataEstado, DataTenant tenant) {
		Date fecha = new Date();
		DataEncomienda encomienda = srvEncomienda.getEncomienda(idEncomienda, tenant);
		encomienda.setEstadoActual(dataEstado);
		DataHistorialEstadosEncomienda historialEstados = new DataHistorialEstadosEncomienda();
		historialEstados.setEstado(dataEstado);
		historialEstados.setFecha(fecha);
		encomienda.getEstados().add(historialEstados);
		srvEncomienda.modificarEncomienda(encomienda, tenant);
	}

	@Override
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idCoche, DataTenant tenant) {
		DataVehiculo coche = srvVehiculo.getVehiculo(idCoche, tenant);
		return coche.getEncomiendas();
	}

	@Override
	public void AsignarEncomiendasVehiculo(String IdEncomienda, String idViaje, String idCoche, DataTenant tenant) {
		DataViaje viaje = srvViaje.getViaje(idViaje, tenant);
		DataEncomienda encomienda = srvEncomienda.getEncomienda(IdEncomienda, tenant);
		DataVehiculo coche = srvVehiculo.getVehiculo(idCoche, tenant);
		List<DataEncomienda> encomiendas = coche.getEncomiendas();
		encomiendas.add(encomienda);
		coche.setEncomiendas(encomiendas);
		encomienda.setViajeAsignado(viaje);
		encomienda.setCocheAsignado(coche);
		srvEncomienda.modificarEncomienda(encomienda, tenant);
		srvVehiculo.modificarVehiculo(coche, tenant);
	}

	@Override
	public List<DataHistorialEstadosEncomienda> VerEstadosEncomienda(String idEncomienda, DataTenant tenant) {
		DataEncomienda encomienda = srvEncomienda.getEncomienda(idEncomienda, tenant);
		return encomienda.getEstados();
	}

	@Override
	public void editarEncomienda(DataEncomienda encomienda, DataTenant tenant) {
		srvEncomienda.modificarEncomienda(encomienda, tenant);
	}

	@Override
	public void bajaEncomienda(String idEncomienda, DataTenant tenant) {
		srvEncomienda.darBajaEncomienda(idEncomienda, tenant);
	}

	@Override
	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina, DataTenant tenant) {
		return srvEncomienda.buscarEncomienda(filtro, pagina, ElementosPagina, tenant);
	}

	@Override
	public List<DataEncomienda> listarEncomiendasPorUsuario(String idUsuario, Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvEncomienda.listarEncomiendasPorUsuario(idUsuario, pagina, elementosPagina, tenant);
	}

	@Override
	public void EditarEstadoEncomienda(DataEstadosEncomienda dataEstado, DataTenant tenant) {
		srvEstadosEncomienda.modificarEstadosEncomienda(dataEstado, tenant);
	}

	@Override
	public DataEstadosEncomienda getEstadoEncomienda(String idEstadoEncomienda, DataTenant tenant) {
		return srvEstadosEncomienda.getEstadosEncomienda(idEstadoEncomienda, tenant);
	}

	@Override
	public DataEncomienda getEncomiendaXcodigo(Integer codigoEncomienda, DataTenant tenant) {
		return srvEncomienda.getEncomiendaXcodigo(codigoEncomienda, tenant);
	}

	@Override
	public DataReglaCobroEncomienda editarReglaCobroEncomienda(DataReglaCobroEncomienda dataRegla, DataTenant tenant) {
		return srvReglaCobro.modificarReglaCobroEncomienda(dataRegla, tenant);
	}

	@Override
	public void borrarReglaCobroEncomienda(String idReglaCobro, DataTenant tenant) {
		srvReglaCobro.borrarReglaCobroEncomienda(idReglaCobro, tenant);
	}

	@Override
	public Float getPrecioDeEncomienda(String codigoReglaCobro, Float monto, DataTenant tenant) {
		return srvReglaCobro.getPrecioDeEncomienda(codigoReglaCobro, monto, tenant);
	}
}
