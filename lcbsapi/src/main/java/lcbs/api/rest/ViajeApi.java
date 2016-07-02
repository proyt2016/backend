package lcbs.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import lcbs.shares.*;
import lcbs.api.service.ViajeRepo;


@RequestScoped
@Path("/viajes/")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class ViajeApi {																														
	
	@EJB
	ViajeRepo repo;
	
	@POST
	@Path("/buscarviaje/")
	public Map<String, DataViaje> BuscarViaje(DataViaje filtro){
		return repo.BuscarViaje(filtro);
	}
	
	@POST
	@Path("/comprarpasaje/")
	public DataPasaje ComprarPasaje(DataPasaje pasaje){
		return repo.ComprarPasaje(pasaje);
	}
	
	@POST
	@Path("/cambiarhorariopasaje/{idPasaje}")
	public void CambiarHorarioPasaje(@QueryParam("idPasaje") final String idPasaje, String viaje){
		repo.CambiarHorarioPasaje(idPasaje, viaje);
	}
	
	@POST
	@Path("/reservapasaje/")
	public DataReserva ReservarPasaje(DataReserva reserva){
		return repo.ReservarPasaje(reserva);
	}
	
	@POST
	@Path("/transferirpasaje/{idPasaje}")
	public void TransferirPasajeComprado(@QueryParam("idPasaje") final String idPasaje, String idUsuario){
		repo.TransferirPasajeComprado(idPasaje, idUsuario);
	}
	
	@POST
	@Path("/cancelarreserva/")
	public void CancelarReserva(String idReserva){
		repo.CancelarReserva(idReserva);
	}
	
	@GET
	@Path("/listarreservas/{idUsuario}")
	public Map<String, DataReserva> ListarReservas(@QueryParam("idUsuario") final String idUsuario){
		return repo.ListarReservas(idUsuario);
	}
	
	@POST
	@Path("/procesarpasaje/")
	public void ProcesarPasajes(String idPasaje){
		repo.ProcesarPasajes(idPasaje);
	}
	
	@POST
	@Path("/altaparada/")
	public DataParada AltaParadas(DataParada parada){
		return repo.AltaParadas(parada);
	}
	
	@POST
	@Path("/altaterminal/")
	public void AltaTerminal(DataTerminal terminal){
		repo.AltaTerminal(terminal);
	}
	
	@POST
	@Path("/editarparada/")
	public void EditarParada(DataParada parada){
		repo.EditarParada(parada);
	}
	
	@POST
	@Path("/editarterminal/")
	public void EditarTerminal(DataTerminal terminal){
		repo.EditarTerminal(terminal);
	}
	
	@POST
	@Path("/crearrecorrido/")
	public void CrearRecorrido(DataRecorrido recorrido){
		repo.CrearRecorrido(recorrido);
	}
	
	@POST
	@Path("/editarrecorrido/")
	public void EditarRecorrido(DataRecorrido recorrido){
		repo.EditarRecorrido(recorrido);
	}
	
	@POST
	@Path("/comprarpasajereservado/")
	public DataPasaje ComprarPasajeReservado(DataReserva reserva){
		return repo.ComprarPasajeReservado(reserva);
	}
	
	@GET
	@Path("/getparada/{idParada}")
	public DataParada obtenerParada(@QueryParam("idParada") final String IdParada){
		return repo.obtenerParada(IdParada);
	}
	
	@GET
	@Path("/listarhistorialpasajes/{idUsuario}")
	public Map<String, DataPasaje> obtenerHistorialPasajes(@QueryParam("idUsuario") final String idUsuario){
		return repo.obtenerHistorialPasajes(idUsuario);
	}
	
	@POST
	@Path("/verdetallepasaje/")
	public DataPasaje verDetallePasaje(String idPasaje){
		return repo.verDetallePasaje(idPasaje);
	}
	
	@GET
	@Path("/getterminal/{idTerminal}")
	public DataTerminal obtenerTerminal(@QueryParam("idTerminal") final String IdTerminal){
		return repo.obtenerTerminal(IdTerminal);
	}
	
	@POST
	@Path("/altaviaje/")
	public void crearViaje(DataViaje viaje){
		repo.crearViaje(viaje);
	}
	
	@POST
	@Path("/editarviaje/")
	public void editarViaje(DataViaje viaje){
		repo.editarViaje(viaje);
	}
	
	@POST
	@Path("/eliminarviaje/")
	public void eliminarViaje(String idViaje){
		repo.eliminarViaje(idViaje);
	}
}
