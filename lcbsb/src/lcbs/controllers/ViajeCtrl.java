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
public class ViajeCtrl implements IViaje {

	@EJB(lookup = "java:app/lcbsdb/ViajeSrv!lcbs.interfaces.ViajeLocalApi")
	ViajeLocalApi srvViaje;

	@EJB(lookup = "java:app/lcbsdb/PasajeSrv!lcbs.interfaces.PasajeLocalApi")
	PasajeLocalApi srvPasaje;

	@EJB(lookup = "java:app/lcbsdb/ReservaSrv!lcbs.interfaces.ReservaLocalApi")
	ReservaLocalApi srvReserva;

	@EJB(lookup = "java:app/lcbsdb/UsuarioSrv!lcbs.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;

	@EJB(lookup = "java:app/lcbsdb/ParadaSrv!lcbs.interfaces.ParadaLocalApi")
	ParadaLocalApi srvParada;

	@EJB(lookup = "java:app/lcbsdb/TerminalSrv!lcbs.interfaces.TerminalLocalApi")
	TerminalLocalApi srvTerminal;

	@EJB(lookup = "java:app/lcbsdb/RecorridoSrv!lcbs.interfaces.RecorridoLocalApi")
	RecorridoLocalApi srvRecorrido;

	@Override
	public List<DataViaje> BuscarViaje(DataViaje filtro, Integer pagina, Integer ElementosPagina, DataTenant tenant) {
		return srvViaje.buscarViaje(filtro, pagina, ElementosPagina, tenant);
	}

	@Override
	public DataPasaje ComprarPasaje(DataPasaje pasaje, DataTenant tenant) {
		DataPasaje nuevoPasaje = srvPasaje.crearPasaje(pasaje, tenant);
		return nuevoPasaje;

	}

	@Override
	public List<DataViaje> viajesPorTerminal(String idterminal, Integer pagina, Integer ElementosPagina, DataTenant tenant) {
		return srvViaje.viajesPorTerminal(idterminal, pagina, ElementosPagina, tenant);
	}

	@Override
	public List<DataPasajeConvertor> obtenerHistorialPasajes(String idUsuario, Integer pagina,
			Integer elementosPagina, DataTenant tenant) {
		return srvPasaje.obtenerPasajesPorPersona(idUsuario, pagina, elementosPagina, tenant);
	}

	@Override
	public DataPasaje verDetallePasaje(String idPasaje, DataTenant tenant) {
		return srvPasaje.getPasaje(idPasaje, tenant);
	}

	@Override
	public void CambiarHorarioPasaje(String idPasaje, String viaje, DataTenant tenant) {
		DataViaje viajeAAsignar = srvViaje.getViaje(viaje, tenant);
		DataPasaje pasajeAModificar = srvPasaje.getPasaje(idPasaje, tenant);
		pasajeAModificar.setViaje(viajeAAsignar);
		srvPasaje.modificarPasaje(pasajeAModificar, tenant);

	}

	@Override
	public DataReserva ReservarPasaje(DataReserva reserva, DataTenant tenant) {
		DataReserva nuevaReserva = srvReserva.crearReserva(reserva, tenant);
		return nuevaReserva;

	}

	@Override
	public void TransferirPasajeComprado(String idPasaje, String idUsuario, DataTenant tenant) {
		DataPasaje pasajeAModificar = srvPasaje.getPasaje(idPasaje, tenant);
		DataUsuario usuarioAAsignar = srvUsuario.getUsuario(idUsuario, tenant);
		pasajeAModificar.setComprador(usuarioAAsignar);
		srvPasaje.modificarPasaje(pasajeAModificar, tenant);
	}

	@Override
	public void CancelarReserva(String idReserva, DataTenant tenant) {
		srvReserva.darBajaReserva(idReserva, tenant);
	}

	@Override
	public List<DataReserva> ListarReservas(String idUsuario, DataTenant tenant) {
		return srvReserva.listarReservasPorUsuario(idUsuario, tenant);
	}

	@Override
	public void ProcesarPasajes(String idPasaje, DataTenant tenant) {
		DataPasaje pasajeAModificar = srvPasaje.getPasaje(idPasaje, tenant);
		pasajeAModificar.setUsado(true);
		pasajeAModificar.setPago(true);
		srvPasaje.modificarPasaje(pasajeAModificar, tenant);
	}

	@Override
	public List<DataPasajeConvertor> getPasajes(Integer pagina, Integer ElementosPagina, DataTenant tenant) {
		return srvPasaje.obtenerPasajes(pagina, ElementosPagina, tenant);
	}

	@Override
	public DataParada AltaParadas(DataParada parada, DataTenant tenant) {
		return srvParada.crearParada(parada, tenant);
	}

	@Override
	public void EditarParada(DataParada parada, DataTenant tenant) {
		srvParada.modificarParada(parada, tenant);

	}

	@Override
	public DataParada obtenerParada(String IdParada, DataTenant tenant) {
		return srvParada.getParada(IdParada, tenant);

	}

	@Override
	public DataTerminal AltaTerminal(DataTerminal terminal, DataTenant tenant) {
		return srvTerminal.crearTerminal(terminal, tenant);

	}

	@Override
	public void EditarTerminal(DataTerminal terminal, DataTenant tenant) {
		srvTerminal.modificarTerminal(terminal, tenant);

	}

	@Override
	public DataTerminal obtenerTerminal(String IdTerminal, DataTenant tenant) {
		return srvTerminal.getTerminal(IdTerminal, tenant);

	}

	@Override
	public List<DataTerminal> getTerminales(Integer pagina, Integer elementos, DataTenant tenant) {
		return srvTerminal.obtenerTerminals(pagina, elementos, tenant);

	}

	@Override
	public DataRecorrido CrearRecorrido(DataRecorrido recorrido, DataTenant tenant) {
		return srvRecorrido.crearRecorrido(recorrido, tenant);

	}

	@Override
	public void EditarRecorrido(DataRecorrido recorrido, DataTenant tenant) {
		srvRecorrido.modificarRecorrido(recorrido, tenant);

	}

	@Override
	public DataRecorrido obtenerRecorrido(String idRecorrido, DataTenant tenant) {
		return srvRecorrido.getRecorrido(idRecorrido, tenant);

	}

	@Override
	public List<DataRecorrido> listarRecorridos(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvRecorrido.obtenerRecorridos(pagina, elementosPagina, tenant);
	}

	@Override
	public DataPasaje ComprarPasajeReservado(DataReserva filtro, DataTenant tenant) {
		DataReserva reserva = srvReserva.getReserva(filtro.getId(), tenant);
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
		reserva.setUtilizada(true);
		srvReserva.modificarReserva(reserva, tenant);
		return srvPasaje.crearPasaje(pasajeACrear, tenant);
	}

	@Override
	public DataViaje crearViaje(DataViaje viaje, DataTenant tenant) {
		return srvViaje.crearViaje(viaje, tenant);
	}

	@Override
	public void editarViaje(DataViaje viaje, DataTenant tenant) {
		srvViaje.modificarViaje(viaje, tenant);
	}

	@Override
	public void eliminarViaje(String idViaje, DataTenant tenant) {
		srvViaje.borrarViaje(idViaje, tenant);
	}

	@Override
	public void BajaRecorrido(String idRecorrido, DataTenant tenant) {
		srvRecorrido.darBajaRecorrido(idRecorrido, tenant);

	}

	@Override
	public List<DataParada> getParadas(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvParada.obtenerParadas(pagina, elementosPagina, tenant);
	}

	@Override
	public DataPuntoRecorrido obtenerPuntoRecorrido(String idPunto, DataTenant tenant) {
		DataPuntoRecorrido terminal = obtenerTerminal(idPunto, tenant);
		if (terminal != null) {
			return terminal;
		}
		DataPuntoRecorrido parada = obtenerParada(idPunto, tenant);
		if (parada != null) {
			return parada;
		}
		return null;
	}

	@Override
	public DataPuntoRecorrido obtenerPuntoPorCoordenada(String coord, DataTenant tenant) {
		DataPuntoRecorrido terminal = srvTerminal.getTerminalPorCoordenada(coord, tenant);
		if (terminal != null) {
			return terminal;
		}
		DataPuntoRecorrido parada = srvParada.getParadaPorCoordenada(coord, tenant);
		if (parada != null) {
			return parada;
		}
		return null;
	}

	@Override
	public DataViaje getViaje(String idViaje, DataTenant tenant) {
		return srvViaje.getViaje(idViaje, tenant);
	}

	public DataPasaje getPasajeXCodigo(Integer codigoPasaje, DataTenant tenant) {
		return srvPasaje.getpasajeXcodigo(codigoPasaje, tenant);
	}

	@Override
	public List<DataViaje> getViajes(Integer pagina, Integer elementos, DataTenant tenant) {
		return srvViaje.obtenerViajes(pagina, elementos, tenant);
	}

	@Override
	public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvRecorrido.BuscarRecorrido(filtro, pagina, elementosPagina, tenant);
	}

	@Override
	public DataReserva obtenerReserva(String idReserva, DataTenant tenant) {
		return srvReserva.getReserva(idReserva, tenant);
	}

}
