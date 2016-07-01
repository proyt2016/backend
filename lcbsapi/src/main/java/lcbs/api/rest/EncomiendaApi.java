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
	/**
	* @param encomienda
	* @return
	*/
	public Response create(final DataEncomienda encomienda) {
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
		DataEncomienda encomienda = null;
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
	@Path("/sape/")
	public List<DataEncomienda> listAll(@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult) {
		//TODO: retrieve the encomiendas 
		DataEstadosEncomienda estado = new DataEstadosEncomienda(); 
		estado.setNombre("rota");
		DataEstadosEncomienda estados = new DataEstadosEncomienda(); 
		estado.setNombre("sana");
		
		estado = repo.crearEstadoEncomienda(estado);
		estados = repo.crearEstadoEncomienda(estados);
		DataEncomienda aInsertar = new DataEncomienda();
		aInsertar.setCiEmisor("111111");
		repo.AltaEncomienda(aInsertar);
		aInsertar = new DataEncomienda();
		aInsertar.setCiEmisor("222222");
		repo.AltaEncomienda(aInsertar);
		aInsertar = new DataEncomienda();
		aInsertar.setCiEmisor("333333");
		repo.AltaEncomienda(aInsertar);
		aInsertar = new DataEncomienda();
		aInsertar.setCiEmisor("444444");
		repo.AltaEncomienda(aInsertar);
		aInsertar = new DataEncomienda();
		aInsertar.setCiEmisor("555555");
		aInsertar.setEstadoActual(estado);
		repo.AltaEncomienda(aInsertar);
		aInsertar = new DataEncomienda();
		aInsertar.setCiEmisor("666666");
		repo.AltaEncomienda(aInsertar);
		DataEncomienda aBuscar = new DataEncomienda();
		aBuscar.setCiEmisor("555555");
		aBuscar.setEstadoActual(estado);
		List<DataEncomienda> todas = new ArrayList(repo.buscarEncomienda(aBuscar, 1, 50).values());
		String recienInsertada = todas.get(0).getId();
		
		final List<DataEncomienda> encomiendas = new ArrayList<DataEncomienda>();//[new Encomienda()];
		encomiendas.add(repo.getEncomienda(recienInsertada));
		return encomiendas;
	}

	/**
	* @param id
	* @param encomienda
	* @return
	*/
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	public Response update(@PathParam("id") Long id, final DataEncomienda encomienda) {
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
