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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import javax.ws.rs.core.UriBuilder;

import lcbs.shares.*;
import lcbs.api.service.ViajeRepo;


@RequestScoped
@Path("/viajes/")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class ViajeApi {																														
	
	@EJB
	ViajeRepo repo;
	
	@GET
	@Path("/getterminales/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataTerminal> getTerminales(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		return repo.getTerminales(pagina, elementosPagina);
	}
	
	@POST
	@Path("/buscarviaje/{pagina}/{elementosPagina}")
	public Map<String, DataViaje> BuscarViaje(DataViaje filtro, @PathParam("pagina") final Integer pagina, @PathParam("elementosPagina") final Integer ElementosPagina){
		return repo.BuscarViaje(filtro, pagina, ElementosPagina);
	}
	
	@POST
	@Path("/comprarpasaje/")
	public DataPasaje ComprarPasaje(DataPasaje pasaje){
		return repo.ComprarPasaje(pasaje);
	}
	
	@POST
	@Path("/cambiarhorariopasaje/")
	public void CambiarHorarioPasaje(String data) {
		JSONObject obj = new JSONObject(data);
		repo.CambiarHorarioPasaje(obj.getString("idPasaje"), obj.getString("idViaje"));
	}
	
	@POST
	@Path("/reservapasaje/")
	public DataReserva ReservarPasaje(DataReserva reserva){
		return repo.ReservarPasaje(reserva);
	}
	
	@POST
	@Path("/transferirpasaje/")
	public void TransferirPasajeComprado(String data) {
		JSONObject obj = new JSONObject(data);
		repo.TransferirPasajeComprado(obj.getString("idPasaje"), obj.getString("idUsuario"));
	}
	
	@POST
	@Path("/cancelarreserva/")
	public void CancelarReserva(String idReserva){
		repo.CancelarReserva(idReserva);
	}
	
	@GET
	@Path("/listarreservas/")
	public Map<String, DataReserva> ListarReservas(String data) {
		JSONObject obj = new JSONObject(data);
		return repo.ListarReservas(obj.getString("idUsuario"));
	}
	
	@POST
	@Path("/procesarpasaje/")
	public void ProcesarPasajes(String idPasaje){
		repo.ProcesarPasajes(idPasaje);
	}
	
	//tested
	@POST
	@Path("/altaparada/")
	public DataParada AltaParadas(DataParada parada){
		return repo.AltaParadas(parada);
	}
	
	//tested
	@POST
	@Path("/altaterminal/")
	public DataTerminal AltaTerminal(DataTerminal terminal){
		return repo.AltaTerminal(terminal);
	}
	
	//tested
	@POST
	@Path("/editarparada/")
	public void EditarParada(DataParada parada){
		repo.EditarParada(parada);
	}
	
	//test
	@POST
	@Path("/editarterminal/")
	public void EditarTerminal(DataTerminal terminal){
		repo.EditarTerminal(terminal);
	}
	
	//tested
	@POST
	@Path("/crearrecorrido/")
	public DataRecorrido CrearRecorrido(DataRecorrido recorrido){
		return repo.CrearRecorrido(recorrido);
	}
	
	//tested
	@POST
	@Path("/editarrecorrido/")
	public void EditarRecorrido(DataRecorridoConvertor pseudoRecorrido){
		DataRecorrido recorrido = pseudoRecorrido.getRecorrido();
		repo.EditarRecorrido(recorrido);
	}
	
	//tested
	@GET
	@Path("/getrecorrido/{idRecorrido}")
	public DataRecorrido obtenerRecorrido(@PathParam("idRecorrido") final String idRecorrido){
		return repo.obtenerRecorrido(idRecorrido);
	}
	
	@POST
	@Path("/comprarpasajereservado/")
	public DataPasaje ComprarPasajeReservado(DataReserva reserva){
		return repo.ComprarPasajeReservado(reserva);
	}
	
	//tested
	@GET
	@Path("/getparada/{idParada}")
	public DataParada obtenerParada(@PathParam("idParada") final String IdParada){
		return repo.obtenerParada(IdParada);
	}
	
	@GET
	@Path("/listarhistorialpasajes/{idUsuario}")
	public Map<String, DataPasaje> obtenerHistorialPasajes(@PathParam("idUsuario") final String idUsuario){
		return repo.obtenerHistorialPasajes(idUsuario);
	}
	
	@POST
	@Path("/verdetallepasaje/")
	public DataPasaje verDetallePasaje(String idPasaje){
		return repo.verDetallePasaje(idPasaje);
	}
	
	//tested
	@GET
	@Path("/getterminal/{idTerminal}")
	public DataTerminal obtenerTerminal(@PathParam("idTerminal") final String IdTerminal){
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
