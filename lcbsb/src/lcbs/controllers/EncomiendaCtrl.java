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
public class EncomiendaCtrl implements IEncomienda{
	

	@EJB(lookup="java:app/lcbsdb/EncomiendaSrv!lcbs.interfaces.EncomiendaLocalApi")
	EncomiendaLocalApi srvEncomienda;
	@EJB(lookup="java:app/lcbsdb/TerminalSrv!lcbs.interfaces.TerminalLocalApi")
	TerminalLocalApi srvTerminal;
	@EJB(lookup="java:app/lcbsdb/UsuarioSrv!lcbs.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;
	@EJB(lookup="java:app/lcbsdb/VehiculoSrv!lcbs.interfaces.VehiculoLocalApi")
	VehiculoLocalApi srvVehiculo;
	@EJB(lookup="java:app/lcbsdb/ReglaCobroEncomiendaSrv!lcbs.interfaces.ReglaCobroEncomiendaLocalApi")
	ReglaCobroEncomiendaLocalApi srvReglaCobro;
	@EJB(lookup="java:app/lcbsdb/RecorridoSrv!lcbs.interfaces.RecorridoLocalApi")
	RecorridoLocalApi srvRecorrido;
	@EJB(lookup="java:app/lcbsdb/EstadosEncomiendaSrv!lcbs.interfaces.EstadosEncomiendaLocalApi")
	EstadosEncomiendaLocalApi srvEstadosEncomienda;
	@EJB(lookup="java:app/lcbsdb/ViajeSrv!lcbs.interfaces.ViajeLocalApi")
	ViajeLocalApi srvViaje;
	

	@Override
	public List<DataEncomiendaConvertor> ListarEncomiendas(Integer pagina, Integer elementosPagina) {
		return srvEncomienda.obtenerEncomiendas(pagina, elementosPagina);
		
	}
	@Override
	public List<DataEstadosEncomienda> getEstados(Integer pagina, Integer elementosPagina){
		return srvEstadosEncomienda.obtenerEstadosEncomienda(pagina, elementosPagina);
	}
	
	@Override
	public List<DataReglaCobroEncomienda> getReglasDeCobro(Integer pagina, Integer elementosPagina) {
		return srvReglaCobro.obtenerReglaCobroEncomiendas(pagina, elementosPagina);
	}
	
	@Override
	public void crearReglaDeCobro(DataReglaCobroEncomienda rdc){
		srvReglaCobro.crearReglaCobroEncomienda(rdc);
	}

	@Override
	public DataReglaCobroEncomienda getReglaDeCobro(String idEncomieda) {
		DataReglaCobroEncomienda dataReglaCobro = srvReglaCobro.getReglaCobroEncomienda(idEncomieda);
		return dataReglaCobro;
	}

	@Override
	public DataEncomienda AltaEncomienda(DataEncomienda encomienda) {
		return srvEncomienda.crearEncomienda(encomienda);
	}

	@Override
	public List<DataHistorialEstadosEncomienda> getHistorialEstado(String idEncomienda) {
		return srvEncomienda.getEncomienda(idEncomienda).getEstados();
		
	}

	@Override
	public DataEstadosEncomienda getUltimoEstado(String idEncomienda) {
		DataEncomienda dataEncomienda = srvEncomienda.getEncomienda(idEncomienda);
		return dataEncomienda.getEstadoActual();
	}

	@Override
	public DataEncomienda getEncomienda(String idEncomienda) {
		return srvEncomienda.getEncomienda(idEncomienda);
	}
	
	@Override
	public DataEstadosEncomienda crearEstadoEncomienda(DataEstadosEncomienda estado) {
		return srvEstadosEncomienda.crearEstadosEncomienda(estado);
	}
	
	@Override
	public List<DataEstadosEncomienda> listarEstadoEncomienda(Integer pagina, Integer elementosPagina) {
		return srvEstadosEncomienda.obtenerEstadosEncomienda(pagina, elementosPagina);
	}
	
	@Override
	public void borrarEstadoEncomienda(String idEstadoEncomienda) {
		srvEstadosEncomienda.borrarEstadosEncomienda(idEstadoEncomienda);
	}

	@Override
	public void setEstadoEncomienda(String idEncomienda, DataEstadosEncomienda dataEstado) {
		Date fecha = new Date();
		DataEncomienda encomienda = srvEncomienda.getEncomienda(idEncomienda);
		encomienda.setEstadoActual(dataEstado);
		DataHistorialEstadosEncomienda historialEstados = new DataHistorialEstadosEncomienda();
		historialEstados.setEstado(dataEstado);
		historialEstados.setFecha(fecha);
		encomienda.getEstados().add(historialEstados);
		srvEncomienda.modificarEncomienda(encomienda);
	}

	@Override
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idViaje) {
		DataViaje viaje = srvViaje.getViaje(idViaje);
		return viaje.getEncomiendas();
	}

	@Override
	public void AsignarEncomiendasVehiculo(String IdEncomienda,String idViaje, String idCoche) {
		DataViaje viaje = srvViaje.getViaje(idViaje);
		DataEncomienda encomienda = srvEncomienda.getEncomienda(IdEncomienda);
		DataVehiculo coche = srvVehiculo.getVehiculo(idCoche);
		List<DataEncomienda> encomiendas = viaje.getEncomiendas();
		encomiendas.add(encomienda);
		viaje.setEncomiendas(encomiendas);
		encomienda.setViajeAsignado(viaje);
		encomienda.setCocheAsignado(coche);
		srvEncomienda.modificarEncomienda(encomienda);
		srvViaje.modificarViaje(viaje);
	}

	@Override
	public List<DataHistorialEstadosEncomienda> VerEstadosEncomienda(String idEncomienda) {
		DataEncomienda encomienda = srvEncomienda.getEncomienda(idEncomienda);
		return encomienda.getEstados();
	}

	@Override
	public void editarEncomienda(DataEncomienda encomienda) {
		srvEncomienda.modificarEncomienda(encomienda);
	}
	
	@Override
	public void bajaEncomienda(String idEncomienda) {
		srvEncomienda.darBajaEncomienda(idEncomienda);
	}

	@Override
	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina) {
		return srvEncomienda.buscarEncomienda(filtro, pagina, ElementosPagina);
	}

	@Override
	public List<DataEncomienda> listarEncomiendasPorUsuario(String idUsuario, Integer pagina, Integer elementosPagina) {
		return srvEncomienda.listarEncomiendasPorUsuario(idUsuario, pagina, elementosPagina);
	}

	@Override
	public void EditarEstadoEncomienda(DataEstadosEncomienda dataEstado) {
		srvEstadosEncomienda.modificarEstadosEncomienda(dataEstado);
	}

	@Override
	public DataEstadosEncomienda getEstadoEncomienda(String idEstadoEncomienda) {
		return srvEstadosEncomienda.getEstadosEncomienda(idEstadoEncomienda);
	}
	
	@Override
	public DataEncomienda getEncomiendaXcodigo(int codigoEncomienda){
		return srvEncomienda.getEncomiendaXcodigo(codigoEncomienda);
	}
	@Override
	public DataReglaCobroEncomienda editarReglaCobroEncomienda(DataReglaCobroEncomienda dataRegla) {
		return srvReglaCobro.modificarReglaCobroEncomienda(dataRegla);
	}
	@Override
	public void borrarReglaCobroEncomienda(String idReglaCobro) {
		srvReglaCobro.borrarReglaCobroEncomienda(idReglaCobro);
	}
}
