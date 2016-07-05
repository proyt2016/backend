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
import lcbs.api.service.VehiculoRepo;
import lcbs.controllers.VehiculoCtrl;

/**
 * @author rodrigo
 *
 */
@RequestScoped
@Path("/vehiculos")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class VehiculoApi {

	
	@EJB
	VehiculoRepo repo;
	
	//tested
	@POST
	@Path("/altavehiculo/")
	public DataVehiculo AltaVehiculo(DataVehiculo dataVehiculo){
		return repo.altaVehiculo(dataVehiculo);
	}
	
	//tested
	@POST
	@Path("/editarvehiculo/")
	public void editarVehiculo(DataVehiculo vehiculo){
		 repo.editarVehiculo(vehiculo);
	}
	
	//tested
	@POST
	@Path("/bajavehiculo/")
	public void bajaVehiculo(String data){
		 JSONObject obj = new JSONObject(data);
		 repo.bajaVehiculo(obj.getString("idVehiculo"));
	}
	
	@GET
	@Path("/listarvehiculos/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataVehiculo> listarVehiculos(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar") final Integer elementosPagina) {
		return repo.listarVehiculos(pagina, elementosPagina);
	}
	
	@GET
	@Path("/listarmantenimientosporvehiculo/{idVehiculo}")
	public List<DataMantenimientoVehiculo> ListarMantenimientosPorVehiculo(@PathParam("idVehiculo")final String idVehiculo){
		return repo.mantenimientosPorVehiculo(idVehiculo);
	}
	
	
	@GET
	@Path("/getvehiculo/{idVehiculo}")
	public DataVehiculo getVechiulo(@PathParam("idVehiculo")final String idVehiculo){
		return repo.obtenerVehiculo(idVehiculo);
	}
	

}