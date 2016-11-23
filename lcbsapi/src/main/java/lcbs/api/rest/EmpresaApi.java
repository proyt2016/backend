/**
 * 
 */
package lcbs.api.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import lcbs.api.service.EmpresaRepo;
import lcbs.shares.DataConfiguracionEmpresa;
import lcbs.shares.DataTenant;

/**
 * @author rodrigo
 *
 */
@RequestScoped
@Path("/empresa")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class EmpresaApi extends BaseApi {
	@EJB
	EmpresaRepo repo;

	@POST
	@Path("/altaconfiguracion/")

	public void AltaConfiguracionEmpresa(DataConfiguracionEmpresa empresa) {
		try{

			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.altaConfiguracionEmpresa(empresa, tenant);
		}catch(Exception e){
			throw new WebApplicationException(Response.Status.SERVICE_UNAVAILABLE);
		}

	}

	@POST
	@Path("/editarconfiguracion/")
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa empresa) {
		try{
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.editarConfiguracionEmpresa(empresa, tenant);
		}catch(IllegalArgumentException e){
			throw new NotFoundException();
		}
	}

	@GET
	@Path("/getconfirguacionempresa/")
	public DataConfiguracionEmpresa obtenerConfiguracion() {
		try{
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.obtenerConfiguracionEmpresa(tenant);
		}catch(Exception e){
			throw new WebApplicationException(Response.Status.SERVICE_UNAVAILABLE);
		}
	}

}
