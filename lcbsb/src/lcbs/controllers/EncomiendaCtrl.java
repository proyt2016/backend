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
	public List<DataEncomienda> ListarEncomiendas(Integer pagina, Integer elementosPagina) {
		List<DataEncomienda> listaEncomiendas = new ArrayList( srvEncomienda.obtenerEncomiendas(pagina, elementosPagina).values());
		return listaEncomiendas;
		
	}

	@Override
	public List<DataReglaCobroEncomienda> getReglasDeCobro(Integer pagina, Integer elementosPagina) {
		@SuppressWarnings("unchecked")
		List<DataReglaCobroEncomienda> listaReglaCobro = new ArrayList(srvReglaCobro.obtenerReglaCobroEncomiendas(pagina, elementosPagina).values());
		return listaReglaCobro;
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
	public void AltaEncomienda(DataEncomienda encomienda) {
		srvEncomienda.crearEncomienda(encomienda);
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
	public Map<String, DataEstadosEncomienda> listarEstadoEncomienda(Integer pagina, Integer elementosPagina) {
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
	public void AsignarEncomiendasVehiculo(String IdEncomienda,String idViaje) {
		DataViaje viaje = srvViaje.getViaje(idViaje);
		DataEncomienda encomienda = srvEncomienda.getEncomienda(IdEncomienda);
		List<DataEncomienda> encomiendas = viaje.getEncomiendas();
		encomiendas.add(encomienda);
		viaje.setEncomiendas(encomiendas);
		srvViaje.modificarViaje(viaje);;
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
	public Map<String, DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina) {
		return srvEncomienda.buscarEncomienda(filtro, pagina, ElementosPagina);
	}
}
