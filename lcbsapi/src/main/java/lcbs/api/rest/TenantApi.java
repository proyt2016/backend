package lcbs.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import lcbs.api.interceptor.TenantChecked;
import lcbs.api.service.EmpresaRepo;
import lcbs.api.service.TenantRepo;
import lcbs.api.service.UsuarioRepo;
import lcbs.exceptions.TenantException;
import lcbs.exceptions.UserException;
import lcbs.shares.DataConfiguracionEmpresa;
import lcbs.shares.DataTenant;
import lcbs.shares.DataUsuario;

@RequestScoped
@Path("/tenant")
@Produces("application/json")
@Consumes("application/json")
public class TenantApi {

	@EJB
	TenantRepo repo;
	@EJB
	UsuarioRepo usrRepo;
	
	@EJB
	EmpresaRepo empRepo;
	private void errorHandler(TenantException e) {
		switch (e.code) {
		case 0:
			throw new NotFoundException(e);

		case 1:
			throw new InternalServerErrorException(e);

		case 2:
			throw new ServiceUnavailableException(e.getMessage());

		case 3:
			throw new NotFoundException(e);

		}
	}
	@POST
	@Path("/healt/")
	 public DataConfiguracionEmpresa healt(DataTenant tenant) {
		try{ 
			return empRepo.obtenerConfiguracionEmpresa(tenant);
		}catch(Exception e){
			throw new WebApplicationException(Response.Status.SERVICE_UNAVAILABLE);
		}
	}
	@POST
	@Path("/loginusuario/")
	public DataUsuario loginUsuario(String data) throws UserException {
		try {
			JSONObject obj = new JSONObject(data);
			return usrRepo.loginUsuario(obj.getString("usuario"), obj.getString("clave"), null);
		} catch (UserException e) {
			throw e;
		}
	}
 
	@GET
	@Path("/list/")
	public List<DataTenant> list() {
		try {
			return repo.list();
		} catch (TenantException e) {
			errorHandler(e);
		}
		return null;
	}

	@GET
	@Path("/find/")
	public DataTenant get(DataTenant tenant) {
		try {
			return repo.get(tenant);
		} catch (TenantException e) {
			errorHandler(e);
		}
		return null;
	}

	@POST
	@Path("/create/")
	public DataTenant create(DataTenant tenant) throws TenantException, Exception {
		try {
			return repo.create(tenant);
		} catch (TenantException e) {
			errorHandler(e);
		}
		return null; 
	}

	@POST
	@Path("/delete/")
	public boolean delete(DataTenant tenant) {
		try {
			return repo.delete(tenant);
		} catch (TenantException e) {
			errorHandler(e);
		}
		return false; 
	}

	@POST
	@Path("/deactivate/")
	public boolean deactivate(DataTenant tenant) {
		try {
			return repo.deactivate(tenant);
		} catch (TenantException e) {
			errorHandler(e);
		}
		return false; 
	}

	@POST
	@Path("/activate/")
	public boolean activate(DataTenant tenant) {
		try {	
			return repo.activate(tenant);
		} catch (TenantException e) {
			errorHandler(e);
		}
		return false; 
	
	}
}
