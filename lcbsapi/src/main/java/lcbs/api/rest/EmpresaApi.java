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

import lcbs.shares.*;
import lcbs.api.service.EmpresaRepo;

/**
 * @author rodrigo
 *
 */
@RequestScoped
@Path("/empresa")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class EmpresaApi extends BaseApi{

	
	@EJB
	EmpresaRepo repo;
	
	@POST
	@Path("/altaconfiguracion/")
	public void AltaConfiguracionEmpresa(DataConfiguracionEmpresa empresa){
		DataTenant tenant = checkTenant();
		if(tenant != null){

			repo.altaConfiguracionEmpresa(empresa, tenant);
		}else{
			return ;
		}
	
	}
	
	@POST
	@Path("/editarconfiguracion/")
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa empresa){
		DataTenant tenant = checkTenant();
		if(tenant != null){

			repo.editarConfiguracionEmpresa(empresa, tenant);
		}else{
			return;
		}
	
	}
	
	@GET
	@Path("/getconfirguacionempresa/")
	public DataConfiguracionEmpresa obtenerConfiguracion(){
		
		DataTenant tenant = checkTenant();
		if(tenant != null){
			return repo.obtenerConfiguracionEmpresa(tenant);
		}else{
			return null;
		}
	}
	
	
	

}
