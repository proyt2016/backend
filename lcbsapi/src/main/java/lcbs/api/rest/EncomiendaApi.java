/**
 * 
 */
package lcbs.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import javax.ws.rs.core.UriBuilder;

import lcbs.shares.*;
import lcbs.api.service.EncomiendaRepo;
import lcbs.controllers.EncomiendaCtrl;

/**
 * @author rodrigo
 *
 */
@RequestScoped
@Path("/encomiendas")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class EncomiendaApi {

	
	@EJB
	EncomiendaRepo repo;
		
	
	@POST
	@Path("/altaencomienda/")
	public DataEncomienda AltaEncomienda(DataEncomienda enc){
		return repo.AltaEncomienda(enc);
	}
	
	@POST
	@Path("/borrarestadoencomienda/")
	public void borrarEstadoEncomienda(String idEstadoEncomienda){
		repo.borrarEstadoEncomienda(idEstadoEncomienda);
	}
	
	@POST
	@Path("/setestadoencomienda/{idEncomienda}/")
	public void setEstadoEncomienda(@PathParam("idEncomienda")final String idEncomienda,final DataEstadosEncomienda dataEstado){
		repo.setEstadoEncomienda(idEncomienda, dataEstado);
	}
	
	@POST
	@Path("/asignarencomiendavehiculo/")
	public void AsignarEncomiendaVehiculo(String data) {
		JSONObject obj = new JSONObject(data);
		repo.AsignarEncomiendasVehiculo(obj.getString("IdEncomienda"), obj.getString("idViaje"));
	}
	
		
	@POST
	@Path("/altaestadoencomienda/")
	public void AltaEstadoEncomienda(DataEstadosEncomienda dataEstado){
		repo.crearEstadoEncomienda(dataEstado);
	}
	
	@POST
	@Path("/altareglacobro/")
	public void AltaReglaCobro(DataReglaCobroEncomienda dataRegla){
		repo.AltaReglaCobro(dataRegla);
	}
	
	@POST
	@Path("/editarencomienda/")
	public void editarEncomienda(DataEncomienda encomienda){
		repo.editarEncomienda(encomienda);
	}
	
	@POST
	@Path("/borrarencomienda/")
	public void borrarEncomienda(String idEncomienda){
		 repo.bajaEncomienda(idEncomienda);
	}
	
	@GET
	@Path("/getencomiendasporvehiculo/")
	public List<DataEncomienda> getEncomiendasPorVehiculo(String idViaje){
		return repo.getEncomiendasPorVehiculo(idViaje);
	}
	
	@GET
	@Path("/buscarencomienda/{pagina:[0-9][0-9]*}/{elementosPagina:[0-9][0-9]*}")
	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, @PathParam("pagina") final Integer pagina,@PathParam("elementosPagina") final Integer ElementosPagina){
		return repo.buscarEncomienda(filtro, pagina, ElementosPagina);
	}
	
	
	@GET
	@Path("/getencomienda/{idEncomienda}")
	public DataEncomienda getEncomienda(@PathParam("idEncomienda") final String idEncomienda){
		return repo.getEncomienda(idEncomienda);
	}
	
	@GET
	@Path("/getreglacobro/{idEncomienda}")
	public DataReglaCobroEncomienda getReglaCobro(@PathParam("idEncomienda") final String idEncomieda){
		return repo.getReglaDeCobro(idEncomieda);
	}
	
	@GET
	@Path("/getencomiendas/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEncomienda> getEncomiendas(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		return repo.ListarEncomiendas(pagina, elementosPagina);
	}
	
	@GET
	@Path("/getreglascobro/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataReglaCobroEncomienda> getReglasCobro(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		return repo.getReglasDeCobro(pagina, elementosPagina);
	}
	
	@GET
	@Path("/gethistorialestados/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}/{idEncomienda}")
	public List<DataHistorialEstadosEncomienda> getHistorialEstadosEncomienda(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina, @PathParam("idEncomienda") final String idEncomienda){
		return repo.getHistorialEstado(idEncomienda);
	}
	
	@GET
	@Path("/getreglacobro/{idEncomienda}")
	public DataEstadosEncomienda getUltimoEstado(@PathParam("idEncomienda")final String idEncomienda){
		return repo.getUltimoEstado(idEncomienda);
	}
	
	@GET
	@Path("/listarestadosencomienda/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEstadosEncomienda> listarEstadoEncomienda(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		return repo.listarEstadoEncomienda(pagina, elementosPagina);
	}
	
	
	
	
	
	
	

}
