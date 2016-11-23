package lcbs.api.rest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
public class ViajeApi extends BaseApi{																														
	
	@EJB
	ViajeRepo repo;
	
	@GET
	@Path("/getterminales/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataTerminal> getTerminales(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getTerminales(pagina, elementosPagina, tenant);
	}
	
	@GET
	@Path("/gettotalpasajesvendidos/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}/{fecha}")
	public List<DataPasajeConvertor> obtenerTotalPasajesVendidos(@PathParam("fecha") final String fecha, @PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		//JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerTotalPasajesVendidos(fecha, pagina, elementosPagina, tenant);
	}

	
	
	@GET
	@Path("/getviajesxterminal/{idterminal}/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataViajeConvertor> viajesPorTerminal(@PathParam ("idterminal")String idterminal,@PathParam("pagina") final Integer pagina,@PathParam("elementosAMostrar") final Integer ElementosPagina){
		List<DataViajeConvertor> result = new ArrayList<DataViajeConvertor>();
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.viajesPorTerminal(idterminal,pagina,ElementosPagina, tenant).forEach((viaje)->{
			result.add(viaje.genConvertor());
		});
		return result;
		}
	
	@GET
	@Path("/getparadas/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataParada> getParadas(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getParadas(pagina, elementosPagina, tenant);
	}
	
	@POST
	@Path("/buscarviaje/{pagina}/{elementosPagina}")
	public List<DataViajeConvertor> BuscarViaje(DataViaje filtro, @PathParam("pagina") final Integer pagina, @PathParam("elementosPagina") final Integer ElementosPagina) throws ParseException{
		List<DataViajeConvertor> result = new ArrayList<DataViajeConvertor>();
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.BuscarViaje(filtro, null, pagina, ElementosPagina, tenant).forEach((viaje)->{
			result.add(viaje.genConvertor());
		});
		return result;
	}
	
	@POST
	@Path("/buscarrecorrido/{pagina}/{elementosPagina}")
	public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, @PathParam("pagina") final Integer pagina, @PathParam("elementosPagina") final Integer ElementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.BuscarRecorrido(filtro, pagina, ElementosPagina, tenant);
	}
	
	@POST
	@Path("/comprarpasaje/")
	public DataPasajeConvertor ComprarPasaje(DataPasajeConvertor pseudoPasaje){
		DataPasaje pasaje = pseudoPasaje.genDataPasaje();
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.ComprarPasaje(pasaje, tenant).genConvertor();
	}
	
	@POST
	@Path("/cambiarhorariopasaje/")
	public void CambiarHorarioPasaje(String data) {
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.CambiarHorarioPasaje(obj.getString("idPasaje"), obj.getString("idViaje"), tenant);
	}
	
	@POST
	@Path("/reservapasaje/")
	public DataReserva ReservarPasaje(DataReservaConvertor pseudoReserva){
		DataReserva reserva = pseudoReserva.genDataReserva();
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.ReservarPasaje(reserva, tenant);
	}
	
	@POST
	@Path("/transferirpasaje/")
	public void TransferirPasajeComprado(String data) {
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.TransferirPasajeComprado(obj.getString("idPasaje"), obj.getString("idUsuario"), tenant);
	}
	
	@DELETE
	@Path("/cancelarreserva/{idReserva}")
	public void CancelarReserva(@PathParam("idReserva") final String idReserva){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.CancelarReserva(idReserva, tenant);
	}
	
	@GET
	@Path("/listarreservas/{idUsuario}")
	public List<DataReserva> ListarReservas(@PathParam("idUsuario") final String idUsuario) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.ListarReservas(idUsuario, tenant);
	}
	
	@GET
	@Path("/getreserva/{idReserva}")
	public DataReserva obtenerReserva(@PathParam("idReserva") final String idReserva){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerReserva(idReserva, tenant);
	}
	
	@POST	
	@Path("/procesarpasaje/{idPasaje}")
	public void ProcesarPasajes(@PathParam("idPasaje") final String idPasaje){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.ProcesarPasajes(idPasaje, tenant);
	}
	
	//tested
	@POST
	@Path("/altaparada/")
	public DataParada AltaParadas(DataParada parada){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.AltaParadas(parada, tenant);
	}
	
	//tested
	@POST
	@Path("/altaterminal/")
	public DataTerminal AltaTerminal(DataTerminal terminal){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.AltaTerminal(terminal, tenant);
	}
	
	//tested
	@POST
	@Path("/editarparada/")
	public void EditarParada(DataParada parada){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.EditarParada(parada, tenant);
	}
	
	//test
	@POST
	@Path("/editarterminal/")
	public void EditarTerminal(DataTerminal terminal){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.EditarTerminal(terminal, tenant);
	}
	
	//tested
	@POST
	@Path("/crearrecorrido/")
	public DataRecorrido CrearRecorrido(DataRecorridoConvertor pseudoRecorrido){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		DataRecorrido recorrido = pseudoRecorrido.genRecorrido();
		return repo.CrearRecorrido(recorrido, tenant);
	}
	
	//tested
	@POST
	@Path("/editarrecorrido/")
	public void EditarRecorrido(DataRecorridoConvertor pseudoRecorrido){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		DataRecorrido recorrido = pseudoRecorrido.genRecorrido();
		repo.EditarRecorrido(recorrido, tenant);
	}
	
	@POST
	@Path("/bajarecorrido/")
	public void BajaRecorrido(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.BajaRecorrido(obj.getString("idRecorrido"), tenant);
	}
	
	//tested
	@GET
	@Path("/getrecorrido/{idRecorrido}")
	public DataRecorridoConvertor obtenerRecorrido(@PathParam("idRecorrido") final String idRecorrido){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerRecorrido(idRecorrido, tenant).genConvertor();
	}
	
	@GET
	@Path("/listarrecorridos/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataRecorrido> listarRecorridos(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar") final Integer elementosPagina) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.listarRecorridos(pagina, elementosPagina, tenant);
	}
	
	@POST
	@Path("/comprarpasajereservado/")
	public DataPasaje ComprarPasajeReservado(DataReservaConvertor pseudoReserva){
		DataReserva reserva = pseudoReserva.genDataReserva();
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.ComprarPasajeReservado(reserva, tenant);
	}
	
	//tested
	@GET
	@Path("/getparada/{idParada}")
	public DataParada obtenerParada(@PathParam("idParada") final String IdParada){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerParada(IdParada, tenant);
	}
	
	@GET
	@Path("/listarhistorialpasajes/{idUsuario}/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataPasajeConvertor> obtenerHistorialPasajes(@PathParam("idUsuario") final String idUsuario, @PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar") final Integer elementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerHistorialPasajes(idUsuario, pagina, elementosPagina, tenant);
	}
	
	@POST
	@Path("/verdetallepasaje/")
	public DataPasaje verDetallePasaje(String idPasaje){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.verDetallePasaje(idPasaje, tenant);
	}
	
	
	//tested
	@GET
	@Path("/getterminal/{idTerminal}")
	public DataTerminal obtenerTerminal(@PathParam("idTerminal") final String IdTerminal){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerTerminal(IdTerminal, tenant);
	}
	
	@GET
	@Path("/getpuntorecorrido/{idPunto}")
	public DataPuntoRecorrido obtenerPuntoRecorrido(@PathParam("idPunto") final String idPunto){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerPuntoRecorrido(idPunto, tenant);
	}
	
	@GET
	@Path("/getpuntoporcoordenada/{coord}")
	public DataPuntoRecorrido getPuntoPorCoordenada(@PathParam("coord") final String coord){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerPuntoPorCoordenada(coord, tenant);
	}
	
	@GET
	@Path("/getviaje/{idViaje}")
	public DataViaje getViaje(@PathParam("idViaje") final String idViaje){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getViaje(idViaje, tenant);
	}
	
	@POST
	@Path("/altaviaje/")
	public void crearViaje(DataViaje viaje){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.crearViaje(viaje, tenant);
	}
	
	@POST
	@Path("/editarviaje/")
	public void editarViaje(DataViaje viaje){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.editarViaje(viaje, tenant);
	}
		
	@POST
	@Path("/eliminarviaje/")
	public void eliminarViaje(String idViaje){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.eliminarViaje(idViaje, tenant);
	}
	
	@GET
	@Path("/getviajes/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataViaje> getViajes(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar") final Integer elementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getViajes(pagina, elementosPagina, tenant);
	}
	
	@GET
	@Path("/getpasajes/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataPasajeConvertor> getPasajes(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar") final Integer ElementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getPasajes(pagina, ElementosPagina, tenant);
	}
	
	@GET
	@Path("/getpasajeporcodigo/{codigoPasaje}")
	public DataPasaje getPasajeXcodigo(@PathParam("codigoPasaje") final Integer codigo){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getPasajeXcodigo(codigo, tenant);
	}
	
	@POST
	@Path("/crearhorarioenrecorrido/{idRecorrido}")
	public void crearHorarioRecorrido(DataGrupoHorario horario, @PathParam("idRecorrido") final String idRecorrido){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.crearHorarioRecorrido(horario, idRecorrido, tenant);
	}

	@POST
	@Path("/editarhorarioenrecorrido/{idRecorrido}")
	public void editarHorarioRecorrido(DataGrupoHorario horario, @PathParam("idRecorrido") final String idRecorrido){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.editarHorarioRecorrido(horario, idRecorrido, tenant);
	}

	@POST
	@Path("/borrarhorarioenrecorrido/{idRecorrido}")
	public void borrarHorarioRecorrido(String data, @PathParam("idRecorrido") final String idRecorrido){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.borrarHorarioRecorrido(idRecorrido, obj.getString("idHorarioRecorrido"), tenant);
	}
	
	@GET
	@Path("/getpreciodepasaje/{codigoOrigen}/{codigoDestino}/{codigoRecorrido}")
	public Float getPrecioDePasaje(@PathParam("codigoOrigen") final String codigoOrigen, @PathParam("codigoDestino") final String codigoDestino, @PathParam("codigoRecorrido") final String codigoRecorrido){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getPrecioDePasaje(codigoOrigen, codigoDestino, codigoRecorrido,  tenant).getMonto();
	}
	
	@GET
	@Path("/crearViajesNuevoRecorrido/{recorridoId}")
	public void crearViajesNuevoRecorrido(@PathParam("recorridoId") final String recorridoId){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.crearViajesNuevoRecorrido(recorridoId, tenant);
	}
	
	@GET
	@Path("/crearviajespararecorridos/")
	public void crearViajesParaRecorridos() throws ParseException{
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.crearViajesParaRecorridos(tenant);
	}
	
	@GET
	@Path("/getcantidaddepasaesdisponibles/{idViaje}/{idOrigen}/{idDestino}")
	public Integer cantidadAsientosDisponibles(@PathParam("idViaje") final String idViaje, @PathParam("idOrigen") final String idOrigen, @PathParam("idDestino") final String idDestino){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.cantidadAsientosDisponibles(idViaje, idOrigen, idDestino, tenant);
	}
	
	
	@POST
	@Path("/pasajeonlineareserva/")
	public DataReserva PasajeOnlineAReserva(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.PasajeOnlineAReserva(Integer.valueOf(obj.getString("codigoPasaje")), obj.getString("idUsuario"), tenant);
	}
	
	@GET
	@Path("/listarviajescambiohorario/{idPasaje}")
	public List<DataViaje> listarViajesPasajesCambioHorario(@PathParam("idPasaje") final String idPasaje){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.listarViajesCambioHorario(idPasaje, tenant);
	}
	
	
	@POST
	@Path("/comprarpasajestripe/")
	public DataPasaje comprarPasajeStripe(DataPasajeConvertor pseudoPasaje){
		DataPasaje pasaje = pseudoPasaje.genDataPasaje();
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.comprarPasajeStripe(pasaje, tenant);
	}
	
	@POST
	@Path("/comparapasajecuponera/")
	public DataPasaje comprarPasajeCuponera(DataPasajeConvertor pseudoPasaje) throws Exception{
		DataPasaje pasaje = pseudoPasaje.genDataPasaje();
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.comprarPasajeCuponera(pasaje, tenant);
	}
	
	@POST
	@Path("/buscarreservas/")
	public List<DataReserva> buscarReservas(DataReserva filtro){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.buscarReservas(filtro, tenant);
	}
	
}
