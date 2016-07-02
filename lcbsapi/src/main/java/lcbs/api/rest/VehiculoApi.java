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
import javax.ws.rs.core.UriBuilder;

import lcbs.shares.*;
import lcbs.api.service.VehiculoRepo;
import lcbs.controllers.VehiculoCtrl;

/**
 * @author rodrigo
 *
 */
@RequestScoped
@Path("/encomiendas")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class VehiculoApi {

	
	@EJB
	VehiculoRepo repo;
	
	@POST
	@Path("/altavehiculo/")
	public DataVehiculo AltaVehiculo(DataVehiculo dataVehiculo){
		return repo.altaVehiculo(dataVehiculo);
	}
	
	@POST
	@Path("/editarvehiculo/")
	public void editarVehiculo(DataVehiculo vehiculo){
		 repo.editarVehiculo(vehiculo);
	}
	
	@POST
	@Path("/bajavehiculo/")
	public void bajaVehiculo(String idVehiculo){
		 repo.bajaVehiculo(idVehiculo);
	}
	
	@GET
	@Path("/listarmantenimientosporvehiculo/{idVehiculo}")
	public List<DataMantenimientoVehiculo> ListarMantenimientosPorVehiculo(@PathParam("idVehiculo")final String idVehiculo){
		return repo.mantenimientosPorVehiculo(idVehiculo);
	}
	
	
	
	
		
	
	
	
	
	
	

}
