/**
 * 
 */
package lcbs.api.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.altaConfiguracionEmpresa(empresa, tenant);

	}

	@POST
	@Path("/editarconfiguracion/")
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa empresa) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.editarConfiguracionEmpresa(empresa, tenant);

	}

	@GET
	@Path("/getconfirguacionempresa/")
	public DataConfiguracionEmpresa obtenerConfiguracion() {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerConfiguracionEmpresa(tenant);
	}

}
