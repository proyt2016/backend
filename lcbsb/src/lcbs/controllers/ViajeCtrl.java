package lcbs.controllers;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.stripe.Stripe;
import com.stripe.model.Charge;

import interfaces.IUsuario;
import interfaces.IViaje;
import lcbs.exceptions.UserException;
import lcbs.exceptions.ViajeException;
import lcbs.interfaces.ConfiguracionEmpresaLocalApi;
import lcbs.interfaces.CuponeraLocalApi;
import lcbs.interfaces.ParadaLocalApi;
import lcbs.interfaces.PasajeLocalApi;
import lcbs.interfaces.RecorridoLocalApi;
import lcbs.interfaces.ReservaLocalApi;
import lcbs.interfaces.TerminalLocalApi;
import lcbs.interfaces.UsuarioLocalApi;
import lcbs.interfaces.VehiculoLocalApi;
import lcbs.interfaces.ViajeLocalApi;
import lcbs.shares.DataConfiguracionEmpresa;
import lcbs.shares.DataCuponera;
import lcbs.shares.DataDiasSemana;
import lcbs.shares.DataGrupoHorario;
import lcbs.shares.DataHorario;
import lcbs.shares.DataParada;
import lcbs.shares.DataPasaje;
import lcbs.shares.DataPasajeConvertor;
import lcbs.shares.DataPrecio;
import lcbs.shares.DataPuntoRecorrido;
import lcbs.shares.DataRecorrido;
import lcbs.shares.DataReserva;
import lcbs.shares.DataTenant;
import lcbs.shares.DataTerminal;
import lcbs.shares.DataUsuario;
import lcbs.shares.DataVehiculo;
import lcbs.shares.DataViaje;
import lcbs.utils.NotificationHandler;

/**
 * Session Bean implementation class ViajeSrv
 */
@Stateless
public class ViajeCtrl implements IViaje {

	private static final Log log = LogFactory.getLog(ViajeCtrl.class);

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

	@EJB(lookup = "java:app/lcbsdb/ConfiguracionEmpresaSrv!lcbs.interfaces.ConfiguracionEmpresaLocalApi")
	ConfiguracionEmpresaLocalApi srvConfiguracion;

	@EJB(lookup = "java:app/lcbsdb/VehiculoSrv!lcbs.interfaces.VehiculoLocalApi")
	VehiculoLocalApi srvVehiculo;
	
	@EJB(lookup = "java:app/lcbsdb/CuponeraSrv!lcbs.interfaces.CuponeraLocalApi")
	CuponeraLocalApi srvCuponera;
	
	@EJB
	NotificationHandler nHandler;
	IUsuario usrController;
	

	@Override
	public List<DataPasajeConvertor> obtenerTotalPasajesVendidos(String fecha, Integer pagina, Integer elementosPagina,
			DataTenant tenant) {
		return srvPasaje.obtenerTotalPasajesVendidos(fecha, pagina, elementosPagina, tenant);
	}

	@Override
	public List<DataViaje> BuscarViaje(DataViaje filtro, Integer cantidadDias, Integer pagina, Integer ElementosPagina,
			DataTenant tenant) throws ViajeException {
		crearViajesParaRecorridos(tenant);
		return srvViaje.buscarViaje(filtro, cantidadDias, pagina, ElementosPagina, tenant);
	}

	@Override
	public DataPasaje ComprarPasaje(DataPasaje pasaje, DataTenant tenant) {
		DataViaje viaje = srvViaje.getViaje(pasaje.getViaje().getId(), tenant);

		DataPrecio precioPasaje = getPrecioDePasaje(pasaje.getOrigen().getId(), pasaje.getDestino().getId(), viaje.getRecorrido().getId(), tenant);
		pasaje.setPrecio(precioPasaje);

		DataPasaje nuevoPasaje = srvPasaje.crearPasaje(pasaje, tenant);
		if (nuevoPasaje.getComprador() != null) {
			nHandler.sendNotification(nuevoPasaje.getComprador(), "Pasajes", "compra",
					"Compra realizada con exito: " + nuevoPasaje.getDestino().getNombre(), tenant);
		}
		return nuevoPasaje;
	}
	
	@Override
	public DataPasaje comprarPasajeStripe(DataPasaje pasaje, DataTenant tenant) throws UserException{
		DataViaje viaje = srvViaje.getViaje(pasaje.getViaje().getId(), tenant);
		DataPrecio precioPasaje = getPrecioDePasaje(pasaje.getOrigen().getId(), pasaje.getDestino().getId(), viaje.getRecorrido().getId(), tenant);
		cargarTarjeta(pasaje.getComprador().getId(), precioPasaje.getMonto(), tenant);
		return ComprarPasaje(pasaje, tenant);
	}
	
	@Override
	public DataPasaje comprarPasajeCuponera(DataPasaje pasaje, DataTenant tenant) throws Exception{
		DataViaje viaje = srvViaje.getViaje(pasaje.getViaje().getId(), tenant);
		DataPrecio precioPasaje = getPrecioDePasaje(pasaje.getOrigen().getId(), pasaje.getDestino().getId(), viaje.getRecorrido().getId(), tenant);
		DataCuponera cuponera = srvUsuario.getUsuario(pasaje.getComprador().getId(), tenant).getCuponera();
		Float saldoActual = cuponera.getSaldo();
		if(saldoActual < precioPasaje.getMonto()){
			throw new Exception("El saldo de la cuponera no es suficiente");
		}
		cuponera.setSaldo(saldoActual - precioPasaje.getMonto());
		srvCuponera.modificarCuponera(cuponera, tenant);
		return ComprarPasaje(pasaje, tenant);
	}

	@Override
	public List<DataViaje> viajesPorTerminal(String idterminal, Integer pagina, Integer ElementosPagina,
			DataTenant tenant) {
		return srvViaje.viajesPorTerminal(idterminal, pagina, ElementosPagina, tenant);
	}

	@Override
	public List<DataPasajeConvertor> obtenerHistorialPasajes(String idUsuario, Integer pagina, Integer elementosPagina,
			DataTenant tenant) {
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
		if (pasajeAModificar.getComprador() != null) {
			nHandler.sendNotification(pasajeAModificar.getComprador(), "Pasajes", "cambio-horario",
					"Horario modificado con exito: " + pasajeAModificar.getDestino().getNombre(), tenant);
		}
	}

	@Override
	public DataReserva ReservarPasaje(DataReserva reserva, DataTenant tenant) {
		DataViaje viaje = srvViaje.getViaje(reserva.getViaje().getId(), tenant);
		DataPrecio precioPasae = getPrecioDePasaje(reserva.getOrigen().getId(), reserva.getDestino().getId(),
				viaje.getRecorrido().getId(), tenant);
		reserva.setPrecio(precioPasae);
		DataReserva nuevaReserva = srvReserva.crearReserva(reserva, tenant);
		
		if(nuevaReserva.getUsuarioReserva() != null){
			DataUsuario usuario = srvUsuario.getUsuario(nuevaReserva.getUsuarioReserva().getId(), tenant);
			nHandler.sendNotification(usuario, "Pasajes", "reserva", "Reserva realizada con exito: " + nuevaReserva.getDestino().getNombre(),
					tenant);
		}
		return nuevaReserva;
	}

	@Override
	public DataReserva PasajeOnlineAReserva(Integer codigoPasaje, String idUsuario, DataTenant tenant) {
		DataPasaje pasaje = srvPasaje.getpasajeXcodigo(codigoPasaje, tenant);
		DataUsuario usuario = srvUsuario.getUsuario(idUsuario, tenant);
		DataReserva nuevaReserva = new DataReserva();
		nuevaReserva.setOrigen(pasaje.getOrigen());
		nuevaReserva.setDestino(pasaje.getDestino());
		nuevaReserva.setEliminada(false);
		nuevaReserva.setFechaReserva(pasaje.getFechaCompra());
		nuevaReserva.setUsuarioReserva(usuario);
		nuevaReserva.setUtilizada(false);
		nuevaReserva.setViaje(pasaje.getViaje());
		srvPasaje.darBajaPasaje(pasaje.getId(), tenant);
		return srvReserva.crearReserva(nuevaReserva, tenant);

	}

	@Override
	public void TransferirPasajeComprado(String idPasaje, String idUsuario, DataTenant tenant) {
		DataPasaje pasajeAModificar = srvPasaje.getPasaje(idPasaje, tenant);
		DataUsuario usuarioAAsignar = srvUsuario.getUsuario(idUsuario, tenant);
		DataUsuario anterior = pasajeAModificar.getComprador();
		pasajeAModificar.setComprador(usuarioAAsignar);
		srvPasaje.modificarPasaje(pasajeAModificar, tenant);
		if (anterior != null) {
			nHandler.sendNotification(anterior, "Pasajes", "transferencia", "Uds transfirio el pasaje a: "
					+ pasajeAModificar.getDestino().getNombre() + " a " + usuarioAAsignar.getEmail(), tenant);
		}
		if (usuarioAAsignar != null) {
			nHandler.sendNotification(usuarioAAsignar, "Pasajes", "transferencia", "Uds recibio un pasaje a: "
					+ pasajeAModificar.getDestino().getNombre() + " de " + anterior.getEmail(), tenant);
		}
	}

	@Override
	public void CancelarReserva(String idReserva, DataTenant tenant) {
		DataReserva recerva = srvReserva.getReserva(idReserva, tenant);
		srvReserva.darBajaReserva(idReserva, tenant);
		if (recerva.getUsuarioReserva() != null) {
			nHandler.sendNotification(recerva.getUsuarioReserva(), "Pasajes", "reserva",
					"Uds cancelo: " + recerva.getDestino().getNombre(), tenant);
		}

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
		if (pasajeAModificar.getComprador() != null) {
			nHandler.sendNotification(pasajeAModificar.getComprador(), "Pasajes", "uso",
					"Uds viajo: " + pasajeAModificar.getDestino().getNombre(), tenant);
		}
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
		pasajeACrear = srvPasaje.crearPasaje(pasajeACrear, tenant);
		if (pasajeACrear.getComprador() != null) {
			nHandler.sendNotification(pasajeACrear.getComprador(), "Pasajes", "reserva",
					"Uds compro el pasaje a : " + pasajeACrear.getDestino().getNombre(), tenant);
		}
		return pasajeACrear;
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
	public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, Integer pagina, Integer elementosPagina,
			DataTenant tenant) {
		return srvRecorrido.BuscarRecorrido(filtro, pagina, elementosPagina, tenant);
	}

	@Override
	public DataReserva obtenerReserva(String idReserva, DataTenant tenant) {
		return srvReserva.getReserva(idReserva, tenant);
	}

	@Override
	public void crearHorarioRecorrido(DataGrupoHorario horario, String idRecorrido, DataTenant tenant) {
		DataRecorrido rec = srvRecorrido.getRecorrido(idRecorrido, tenant);
		List<DataGrupoHorario> horarioList = rec.getHorarios();
		horarioList.add(horario);
		rec.setHorarios(horarioList);
		srvRecorrido.modificarRecorrido(rec, tenant);
	}

	@Override
	public void editarHorarioRecorrido(DataGrupoHorario horario, String idRecorrido, DataTenant tenant) {
		DataRecorrido rec = srvRecorrido.getRecorrido(idRecorrido, tenant);
		List<DataGrupoHorario> horarioList = rec.getHorarios();
		Integer aux = 0;
		for (Integer i = 0; i < horarioList.size(); i++) {
			if (horario.getId() == horarioList.get(i).getId()) {
				aux = i;
			}
		}
		horarioList.set(aux, horario);
		rec.setHorarios(horarioList);
		srvRecorrido.modificarRecorrido(rec, tenant);
	}

	@Override
	public Integer cantidadAsientosDisponibles(String idViaje, String idOrigen, String idDestino, DataTenant tenant) {
		DataViaje viaje = srvViaje.getViaje(idViaje, tenant);
		Integer cant = 0;
		List<DataVehiculo> vehiculos = viaje.getCoches();
		if (vehiculos.size() == 0) {
			cant = srvVehiculo.getMenorCantAsientos(tenant);
		} else {
			for (Integer i = 0; i < vehiculos.size(); i++) {
				cant += vehiculos.get(i).getCantidadAsientos();
			}
		}
		List<DataPuntoRecorrido> puntos = viaje.getRecorrido().getPuntosDeRecorrido();
		Map<String, Integer> indiceDePunto = new HashMap<String, Integer>();
		for (Integer i = 0; i < puntos.size(); i++) {
			indiceDePunto.put(puntos.get(i).getId(), i);
		}
		List<DataPasajeConvertor> pasajesEnViaje = srvPasaje.obtenerPasajesPorViaje(idViaje, tenant);
		for (Integer i = 0; i < pasajesEnViaje.size(); i++) {
			if (indiceDePunto.get(pasajesEnViaje.get(i).getDestino().getId()) > indiceDePunto.get(idOrigen)
					&& indiceDePunto.get(pasajesEnViaje.get(i).getDestino().getId()) <= indiceDePunto.get(idDestino)
					|| indiceDePunto.get(pasajesEnViaje.get(i).getOrigen().getId()) >= indiceDePunto.get(idOrigen)
							&& indiceDePunto.get(pasajesEnViaje.get(i).getOrigen().getId()) < indiceDePunto
									.get(idDestino)) {
				cant = cant - 1;
			}
		}
		return cant;
	}

	@Override
	public void borrarHorarioRecorrido(String idRecorrido, String idHorario, DataTenant tenant) {
		DataRecorrido rec = srvRecorrido.getRecorrido(idRecorrido, tenant);
		List<DataGrupoHorario> horarioList = rec.getHorarios();
		List<DataGrupoHorario> result = new ArrayList<DataGrupoHorario>();
		for (Integer i = 0; i < horarioList.size(); i++) {
			if (!idHorario.equals(horarioList.get(i).getId())) {
				result.add(horarioList.get(i));
			}
		}
		rec.setHorarios(result);
		srvRecorrido.modificarRecorrido(rec, tenant);
	}

	@Override
	public DataPrecio getPrecioDePasaje(String codigoOrigen, String codigoDestino, String codigoRecorrido,
			DataTenant tenant) {
		DataRecorrido rec = srvRecorrido.getRecorrido(codigoRecorrido, tenant);
		List<DataPrecio> precios = rec.getPrecios();
		Map<String, Integer> puntosMap = new HashMap<String, Integer>();
		for (Integer i = 0; i < rec.getPuntosDeRecorrido().size(); i++) {
			puntosMap.put(rec.getPuntosDeRecorrido().get(i).getId(), i);
		}
		List<DataPrecio> aux = new ArrayList<DataPrecio>();
		DataPrecio elegido = new DataPrecio();
		precios.stream().forEach((prc) -> {
			if (puntosMap.get(prc.getOrigen().getId()) <= puntosMap.get(codigoOrigen)
					&& puntosMap.get(prc.getDestino().getId()) >= puntosMap.get(codigoDestino)) {
				aux.add(prc);
			}
		});
		for (Integer i = 0; i < aux.size(); i++) {
			if (elegido.getMonto() == null || elegido.getMonto() > aux.get(i).getMonto()) {
				elegido = aux.get(i);
			}
		}
		return elegido;
	}

	@Override
	public void crearViajesParaRecorridos(DataTenant tenant) throws ViajeException {
		List<DataRecorrido> recs = srvRecorrido.obtenerRecorridos(1, 99999, tenant);
		DataConfiguracionEmpresa conf = srvConfiguracion.getConfiguracionEmpresa(tenant);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if (conf.getUltimaCreacionDeViajes() != null
					&& formatter.parse(formatter.format(conf.getUltimaCreacionDeViajes()))
							.equals(formatter.parse(formatter.format(new Date()))))
				return;
			
			recs.stream().forEach((rec) -> {
				crearViajesNuevoRecorrido(rec.getId(), tenant);
			});
			conf.setUltimaCreacionDeViajes(formatter.parse(formatter.format(new Date())));
			srvConfiguracion.editarConfiguracionEmpresa(conf, tenant);
		} catch (Exception e) {
			throw new ViajeException("Imposible crear viajes para el recorrido", 0);
		}
	}

	@Override
	public List<DataViaje> listarViajesCambioHorario(String idPasaje, DataTenant tenant) {
		DataPasaje pasaje = srvPasaje.getPasaje(idPasaje, tenant);
		DataViaje filtro = new DataViaje();
		filtro.setFechaSalida(pasaje.getViaje().getFechaSalida());
		filtro.setRecorrido(pasaje.getViaje().getRecorrido());
		List<DataViaje> viajes = srvViaje.buscarViaje(filtro, null, 1, 1000, tenant);
		List<DataViaje> result = new ArrayList<DataViaje>();
		viajes.stream().forEach((viaje) -> {
			if (viaje.getId() != pasaje.getViaje().getId())
				result.add(viaje);
		});
		return result;
	}

	@Override
	public void crearViajesNuevoRecorrido(String recorridoId, DataTenant tenant) {
		List<DataViaje> aInsertar = new ArrayList<DataViaje>();
		DataRecorrido rec = srvRecorrido.getRecorrido(recorridoId, tenant);
		DataConfiguracionEmpresa conf = srvConfiguracion.getConfiguracionEmpresa(tenant);
		Integer diasACrear = conf.getDiasCreacionViaje();
		Date fechaDesde = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(fechaDesde);
		c.add(Calendar.DATE, diasACrear);
		Date fechaHasta = c.getTime();
		List<Date> listaDias = getDaysBetweenDates(fechaDesde, fechaHasta);
		List<Date> yaAgregados = new ArrayList<Date>();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		DataViaje filtro = new DataViaje();
		filtro.setRecorrido(rec);
		filtro.setFechaSalida(fechaDesde);
		List<DataViaje> viajesExistentes = srvViaje.buscarViaje(filtro, diasACrear, 1, 99999, tenant);
		viajesExistentes.stream().forEach((day) -> {
			try {
				if (!yaAgregados.contains(formatter.parse(formatter.format(day.getFechaSalida())))) {
					yaAgregados.add(formatter.parse(formatter.format(day.getFechaSalida())));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		rec.getHorarios().stream().forEach((hor) -> {
			listaDias.stream().forEach((day) -> {
				if (hor.getDiasEspecificos().size() > 0) {
					hor.getDiasEspecificos().stream().forEach((dEsp) -> {
						try {
							if (formatter.parse(formatter.format(day)).equals(formatter.parse(formatter.format(dEsp)))
									&& !yaAgregados.contains(formatter.parse(formatter.format(day)))) {
								aInsertar.addAll(
										generarViajes(rec, formatter.parse(formatter.format(day)), hor.getHorarios()));
								yaAgregados.add(formatter.parse(formatter.format(day)));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
			});
			listaDias.stream().forEach((day) -> {
				try {
					if (hor.getDiasSemana().size() > 0
							&& !yaAgregados.contains(formatter.parse(formatter.format(day)))) {
						if ((hor.getDiasSemana().contains(DataDiasSemana.Lunes) && getDayOfTheWeek(day) == 2)
								|| (hor.getDiasSemana().contains(DataDiasSemana.Martes) && getDayOfTheWeek(day) == 3)
								|| (hor.getDiasSemana().contains(DataDiasSemana.Miercoles) && getDayOfTheWeek(day) == 4)
								|| (hor.getDiasSemana().contains(DataDiasSemana.Jueves) && getDayOfTheWeek(day) == 5)
								|| (hor.getDiasSemana().contains(DataDiasSemana.Viernes) && getDayOfTheWeek(day) == 6)
								|| (hor.getDiasSemana().contains(DataDiasSemana.Sabado) && getDayOfTheWeek(day) == 7)
								|| (hor.getDiasSemana().contains(DataDiasSemana.Domingo)
										&& getDayOfTheWeek(day) == 1)) {
							aInsertar.addAll(
									generarViajes(rec, formatter.parse(formatter.format(day)), hor.getHorarios()));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		});
		srvViaje.crearViajes(aInsertar, tenant);
	}

	public static List<DataViaje> generarViajes(DataRecorrido rec, Date fecha, List<DataHorario> hor) {
		List<DataViaje> result = new ArrayList<DataViaje>();
		hor.stream().forEach((horario) -> {
			DataViaje nuevoViaje = new DataViaje();
			nuevoViaje.setFechaSalida(fecha);
			nuevoViaje.setHorario(horario);
			nuevoViaje.setRecorrido(rec);
			result.add(nuevoViaje);
		});
		return result;
	}

	public static Integer getDayOfTheWeek(Date fecha) {
		Calendar c = Calendar.getInstance();
		c.setTime(fecha);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startdate);

		while (calendar.getTime().before(enddate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}


	@Override
	public List<DataReserva> buscarReservas(DataReserva filtro, DataTenant tenant) {
		return srvReserva.buscarReserva(filtro, tenant);
	}
	
	@Override
	public void cargarTarjeta(String idUsuario, Float cargo, DataTenant tenant) {
		DataConfiguracionEmpresa conf = srvConfiguracion.getConfiguracionEmpresa(tenant);
		try {
			Stripe.apiKey = Desencriptar(conf.genStripePrivateKey());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// Get the credit card details submitted by the form
		// String token = request.getParameter("stripeToken");
		DataUsuario usu = srvUsuario.getUsuario(idUsuario, tenant);
		String cusId = usu.getStripeCustomerId();
		String emailUsuario = usu.getEmail().getEmail();

		// Create a charge: this will charge the user's card
		try {
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("amount", Math.round(cargo * 100)); // Amount in
																	// cents
			chargeParams.put("currency", "usd");
			chargeParams.put("customer", cusId);
			chargeParams.put("description", "Cargo para: " + emailUsuario);

			// Charge charge = Charge.create(chargeParams);
			Charge.create(chargeParams);

		} catch (Exception e) {
			
		}

	}
	
	public static String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "ck4VC453FDGDFgdgdf";
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
	}

}
