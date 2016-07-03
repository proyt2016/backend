package lcbs.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import interfaces.IViaje;
import lcbs.interfaces.*;
import lcbs.shares.*;

import javax.ejb.Stateless;




/**
 * Session Bean implementation class ViajeSrv
 */
@Stateless
public class ViajeCtrl implements IViaje{

	@EJB(lookup="java:app/lcbsdb/ViajeSrv!lcbs.interfaces.ViajeLocalApi")
	ViajeLocalApi srvViaje;
	
	@EJB(lookup="java:app/lcbsdb/PasajeSrv!lcbs.interfaces.PasajeLocalApi")
	PasajeLocalApi srvPasaje;
	
	@EJB(lookup="java:app/lcbsdb/ReservaSrv!lcbs.interfaces.ReservaLocalApi")
	ReservaLocalApi srvReserva;
	
	@EJB(lookup="java:app/lcbsdb/UsuarioSrv!lcbs.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;
	
	@EJB(lookup="java:app/lcbsdb/ParadaSrv!lcbs.interfaces.ParadaLocalApi")
	ParadaLocalApi srvParada;
	
	@EJB(lookup="java:app/lcbsdb/TerminalSrv!lcbs.interfaces.TerminalLocalApi")
	TerminalLocalApi srvTerminal;
	
	@EJB(lookup="java:app/lcbsdb/RecorridoSrv!lcbs.interfaces.RecorridoLocalApi")
	RecorridoLocalApi srvRecorrido;
	
	@Override
	public Map<String, DataViaje> BuscarViaje(DataViaje filtro, Integer pagina, Integer ElementosPagina) {
		Map<String, DataViaje> listaViajes = srvViaje.buscarViaje(filtro, pagina, ElementosPagina);
		return listaViajes;
	}

	@Override
	public DataPasaje ComprarPasaje(DataPasaje pasaje) {
		DataPasaje nuevoPasaje = srvPasaje.crearPasaje(pasaje);
		return nuevoPasaje;
		
	}
	
	@Override
	public Map<String, DataPasaje> obtenerHistorialPasajes(String idUsuario) {
		return srvPasaje.obtenerPasajesPorPersona(idUsuario);
	}
	
	@Override
	public DataPasaje verDetallePasaje(String idPasaje){
		return srvPasaje.getPasaje(idPasaje);
	}

	@Override
	public void CambiarHorarioPasaje(String idPasaje, String viaje) {
		DataViaje viajeAAsignar = srvViaje.getViaje(viaje);
		DataPasaje pasajeAModificar = srvPasaje.getPasaje(idPasaje);
		pasajeAModificar.setViaje(viajeAAsignar);
		srvPasaje.modificarPasaje(pasajeAModificar);
		
	}

	@Override
	public DataReserva ReservarPasaje(DataReserva reserva) {
		DataReserva nuevaReserva = srvReserva.crearReserva(reserva);
		return nuevaReserva;
		
	}

	@Override
	public void TransferirPasajeComprado(String idPasaje, String idUsuario) {
		DataPasaje pasajeAModificar = srvPasaje.getPasaje(idPasaje);
		DataUsuario usuarioAAsignar = srvUsuario.getUsuario(idUsuario);
		pasajeAModificar.setComprador(usuarioAAsignar);
		srvPasaje.modificarPasaje(pasajeAModificar);
	}

	@Override
	public void CancelarReserva(String idReserva) {
		srvReserva.darBajaReserva(idReserva);
	}

	@Override
	public Map<String, DataReserva> ListarReservas(String idUsuario) {
		return srvReserva.listarReservasPorUsuario(idUsuario);
	}

	@Override
	public void ProcesarPasajes(String idPasaje) {
		DataPasaje pasajeAModificar = srvPasaje.getPasaje(idPasaje);
		pasajeAModificar.setUsado(true);
		srvPasaje.modificarPasaje(pasajeAModificar);
	}

	@Override
	public DataParada AltaParadas(DataParada parada) {
		return srvParada.crearParada(parada);
	}
	
	@Override
	public void EditarParada(DataParada parada) {
		srvParada.modificarParada(parada);
		
	}
	
	@Override
	public DataParada obtenerParada(String IdParada) {
		return srvParada.getParada(IdParada);
		
	}

	@Override
	public DataTerminal AltaTerminal(DataTerminal terminal) {
		return srvTerminal.crearTerminal(terminal);
		
	}

	@Override
	public void EditarTerminal(DataTerminal terminal) {
		srvTerminal.modificarTerminal(terminal);
		
	}
	
	@Override
	public DataTerminal obtenerTerminal(String IdTerminal) {
		return srvTerminal.getTerminal(IdTerminal);
		
	}
	
	@Override
	public List<DataTerminal> getTerminales(int pagina, int elementos) {
		 List<DataTerminal> listaTerminales = new ArrayList(srvTerminal.obtenerTerminals(pagina, elementos).values());
		return listaTerminales;
		
	}
	
	

	@Override
	public DataRecorrido CrearRecorrido(DataRecorrido recorrido) {
		return srvRecorrido.crearRecorrido(recorrido);
		
	}

	@Override
	public void EditarRecorrido(DataRecorrido recorrido) {
		srvRecorrido.modificarRecorrido(recorrido);
		
	}
	
	@Override
	public DataRecorrido obtenerRecorrido(String idRecorrido) {
		return srvRecorrido.getRecorrido(idRecorrido);
		
	}

	@Override
	public DataPasaje ComprarPasajeReservado(DataReserva reserva) {
		DataPasaje pasajeACrear = new DataPasaje();
		pasajeACrear.setCiPersona(reserva.getCiPersona());
		pasajeACrear.setComprador(reserva.getUsuarioReserva());
		pasajeACrear.setOrigen(reserva.getOrigen());
		pasajeACrear.setDestino(reserva.getDestino());
		pasajeACrear.setFechaCompra(new Date());
		pasajeACrear.setPago(true);
		pasajeACrear.setPrecio(reserva.getPrecio());
		pasajeACrear.setUsado(false);
		pasajeACrear.setVendedor(reserva.getEmpleado());
		pasajeACrear.setViaje(reserva.getViaje());
		return srvPasaje.crearPasaje(pasajeACrear);
	}

	@Override
	public DataViaje crearViaje(DataViaje viaje) {
		return srvViaje.crearViaje(viaje);
	}

	@Override
	public void editarViaje(DataViaje viaje) {
		srvViaje.modificarViaje(viaje);
	}

	@Override
	public void eliminarViaje(String idViaje) {
		srvViaje.borrarViaje(idViaje);
	}

}
