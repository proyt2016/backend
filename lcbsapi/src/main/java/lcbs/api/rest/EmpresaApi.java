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
import lcbs.api.service.EmpresaRepo;
import lcbs.controllers.EmpresaCtrl;

/**
 * @author rodrigo
 *
 */
@RequestScoped
@Path("/empresa")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class EmpresaApi {

	
	@EJB
	EmpresaRepo repo;
	
	@POST
	@Path("/altaconfiguracion/")
	public void AltaConfiguracionEmpresa(DataConfiguracionEmpresa empresa){
		repo.altaConfiguracionEmpresa(empresa);
	
	}
	
	@POST
	@Path("/editarconfiguracion/")
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa empresa){
		repo.editarConfiguracionEmpresa(empresa);
	
	}
	
	@GET
	@Path("/getconfirguacionempresa/")
	public DataConfiguracionEmpresa obtenerConfiguracion(){
		return repo.obtenerConfiguracionEmpresa();
	
	}
	
	
	

}
