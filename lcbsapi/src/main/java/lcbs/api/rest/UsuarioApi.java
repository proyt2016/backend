package lcbs.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;

import org.json.JSONObject;

import lcbs.api.service.UsuarioRepo;
import lcbs.exceptions.UserException;
import lcbs.shares.DataEmpleado;
import lcbs.shares.DataNotificacion;
import lcbs.shares.DataPerfil;
import lcbs.shares.DataTenant;
import lcbs.shares.DataUsuario;

@RequestScoped
@Path("/usuarios/")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class UsuarioApi extends BaseApi {

	@EJB
	UsuarioRepo repo;

	private void handleError(UserException e) {
		switch (e.code) {
		case 0:
		case 1:
			throw new BadRequestException(e.getMessage());
		case 2:
		case 3:
			throw new NotAuthorizedException(e.getMessage());
		case 4:
			throw new ServiceUnavailableException(e.getMessage());
		}

	}

	@POST
	@Path("/altausuario/")
	public DataUsuario AltaUsuario(DataUsuario usuario) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		try {
			return repo.AltaUsuario(usuario, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
	}

	@POST
	@Path("/editarusuario/")
	public void ModificarUsuario(DataUsuario usuario) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.ModificarUsuario(usuario, tenant);
		} catch (UserException e) {
			handleError(e);
		}
	}

	@POST
	@Path("/bajausuario/")
	public void BajaUsuario(String idUsuario) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.BajaUsuario(idUsuario, tenant);
		} catch (UserException e) {
			handleError(e);
		}
	}

	@POST
	@Path("/altaempleado/")
	public DataEmpleado AltaEmpleado(DataEmpleado empleado) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.AltaEmpleado(empleado, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
	}

	@POST
	@Path("/editarempleado/")
	public void ModificarEmpleado(DataEmpleado empleado) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.ModificarEmpleado(empleado, tenant);
		} catch (UserException e) {
			handleError(e);
		}
	}

	@POST
	@Path("/bajaempleado/")
	public void BajaEmpleado(String data) {
		try {
			JSONObject obj = new JSONObject(data);
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.BajaEmpleado(obj.getString("idEmpleado"), tenant);
		} catch (UserException e) {
			handleError(e);
		}
	}

	@POST
	@Path("/cargarcuponera/")
	public void CargarSaldoCuponera(String data) {
		try {
			JSONObject obj = new JSONObject(data);
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.CargarSaldoCuponera(obj.getString("idUsuario"), Float.parseFloat(obj.getString("saldo")), tenant);
		} catch (UserException e) {
			handleError(e);
		}

	}

	@GET
	@Path("/listanotificaciones/{idUsuario}")
	public List<DataNotificacion> listarNotificaciones(@PathParam("idUsuario") final String idUsuario) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.listarNotificaciones(idUsuario, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;

	}

	// tested
	@POST
	@Path("/altaperfil/")
	public DataPerfil AltaPerfil(final DataPerfil perfil) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.AltaPerfil(perfil, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
	}

	@POST
	@Path("/editarperfil/")
	public void EditarPerfil(final DataPerfil perfil) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.EditarPerfil(perfil, tenant);
		} catch (UserException e) {
			handleError(e);
		}
	}

	@DELETE
	@Path("/eliminarperfil/{idPerfil}")
	public void EliminarPerfil(@PathParam("idPerfil") String idPerfil) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.EliminarPerfil(idPerfil, tenant);
		} catch (UserException e) {
			handleError(e);
		}
	}

	// tested
	@POST
	@Path("/asignarperfil/")
	public void AsignarPerfil(String data) {
		try {
			JSONObject obj = new JSONObject(data);
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			repo.AsignarPerfil(obj.getString("idEmpleado"), obj.getString("perfil"), tenant);
		} catch (UserException e) {
			handleError(e);
		}
	}

	@POST
	@Path("/loginusuario/")
	public DataUsuario loginUsuario(String data) {
		try {
			JSONObject obj = new JSONObject(data);
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.loginUsuario(obj.getString("usuario"), obj.getString("clave"), tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
	}

	@GET
	@Path("/getusuario/{idUsuario}")
	public DataUsuario getUsuario(@PathParam("idUsuario") final String idUsuario) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.getUsuario(idUsuario, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
	}

	@POST
	@Path("/loginempleado/")
	public DataEmpleado loginEmpleado(String data) {
		try {
			JSONObject obj = new JSONObject(data);
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.loginEmpleado(obj.getString("usuario"), obj.getString("clave"), tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
	}

	@GET
	@Path("/getempleado/{idEmpleado}")
	public DataEmpleado getEmpleado(@PathParam("idEmpleado") final String idEmpleado) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.getEmpleado(idEmpleado, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
	}

	@GET
	@Path("/listarempleados/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEmpleado> listarEmpleados(@PathParam("pagina") final Integer pagina,
			@PathParam("elementosAMostrar") final Integer elementosPagina) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.listarEmpleados(pagina, elementosPagina, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
	}

	@GET
	@Path("/listarusuarios/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataUsuario> listarUsuarios(@PathParam("pagina") final Integer pagina,
			@PathParam("elementosAMostrar") final Integer elementosPagina) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.listarUsuarios(pagina, elementosPagina, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
		
	}

	@GET
	@Path("/getperfil/{idPerfil}")
	public DataPerfil getPerfil(@PathParam("idPerfil") final String idPerfil) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.getPerfil(idPerfil, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;
		
	}

	@GET
	@Path("/listarperfiles/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataPerfil> listarPerfiles(@PathParam("pagina") final Integer pagina,
			@PathParam("elementosAMostrar") final Integer elementosPagina) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.listarPerfiles(pagina, elementosPagina, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;	
	}

	@POST
	@Path("/buscarusuariopormail/")
	public DataUsuario buscarUsuarioPorMail(String mailUsuario) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			return repo.buscarUsuarioPorMail(mailUsuario, tenant);
		} catch (UserException e) {
			handleError(e);
		}
		return null;	
	}

	@POST
	@Path("/guardartokenusuario/")
	public void guardarTokenUsuario(String data) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			JSONObject obj = new JSONObject(data);
			repo.guardarTokenUsuario(obj.getString("idUsuario"), obj.getString("token"),
					Integer.valueOf(obj.getString("ultimosDigitosTarjeta")), tenant);
		} catch (UserException e) {
			handleError(e);
		}
	}

	@POST
	@Path("/cargartarjeta/")
	public void cargarTarjeta(String data) {
		try {
			DataTenant tenant = (DataTenant) request.getAttribute("tenant");
			JSONObject obj = new JSONObject(data);
			repo.cargarTarjeta(obj.getString("idUsuario"), Float.valueOf(obj.getString("monto")), tenant);
		} catch (UserException e) {
			handleError(e);
		}
	}
}
