/**
 * 
 */
package lcbs.api.rest;

import java.util.ArrayList;
import java.util.List;

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

import lcbs.api.model.Encomienda;
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
	/**
	* @param encomienda
	* @return
	*/
	@POST
	public Response create(final Encomienda encomienda) {
		//TODO: process the given encomienda 
		//here we use Encomienda#getId(), assuming that it provides the identifier to retrieve the created Encomienda resource. 
		return Response
				.created(UriBuilder.fromResource(EncomiendaApi.class).path(String.valueOf(encomienda.getId())).build())
				.build();
	}

	/**
	* @param id
	* @return
	*/
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id) {
		//TODO: retrieve the encomienda 
		Encomienda encomienda = null;
		if (encomienda == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(encomienda).build();
	}

	/**
	* @param startPosition
	* @param maxResult
	* @return
	*/
	@GET
	public List<Encomienda> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the encomiendas 
		final List<Encomienda> encomiendas = new ArrayList<Encomienda>();//[new Encomienda()];
		Encomienda e = new Encomienda();
		e.setEmail("mujica@asd.com"+repo.getSape());
		
		encomiendas.add(e);
		e = new Encomienda();
		e.setEmail("mujic2a@asd.com");
		encomiendas.add(e); 
		return encomiendas;
	}

	/**
	* @param id
	* @param encomienda
	* @return
	*/
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final Encomienda encomienda) {
		//TODO: process the given encomienda 
		return Response.noContent().build();
	}

	/**me 
	* @param id
	* @return
	*/
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") final Long id) {
		//TODO: process the encomienda matching by the given id 
		return Response.noContent().build();
	}

}
