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
public class EmpresaApi {

	
	@EJB
	EmpresaRepo repo;
	
	
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
