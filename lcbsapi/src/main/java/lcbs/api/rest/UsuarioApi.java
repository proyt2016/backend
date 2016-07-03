package lcbs.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.json.*;

import org.jboss.dmr.JSONParser;

import javax.ws.rs.core.UriBuilder;

import lcbs.shares.*;
import lcbs.api.service.EncomiendaRepo;
import lcbs.api.service.UsuarioRepo;


@RequestScoped
@Path("/usuarios/")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class UsuarioApi {																														
	
	@EJB
	UsuarioRepo repo;
										
	@POST
	@Path("/altausuario/")
	public DataUsuario AltaUsuario(DataUsuario usuario){
		return repo.AltaUsuario(usuario);
	}
	
	@POST
	@Path("/editarusuario/")
	public void ModificarUsuario(DataUsuario usuario){
		repo.ModificarUsuario(usuario);
	}
	
	@POST
	@Path("/bajausuario/")
	public void BajaUsuario(String idUsuario){
		repo.BajaUsuario(idUsuario);
	}
	
	@POST
	@Path("/altaempleado/")
	public DataEmpleado AltaEmpleado(DataEmpleado empleado){
		return repo.AltaEmpleado(empleado);
	}
	
	@POST
	@Path("/editarempleado/")
	public void ModificarEmpleado(DataEmpleado empleado){
		repo.ModificarEmpleado(empleado);
	}
	
	@POST
	@Path("/bajaempleado/")
	public void BajaEmpleado(String idEmpleado){
		repo.BajaEmpleado(idEmpleado);
	}
	
	@POST
	@Path("/cargarcuponera/")
	public void CargarSaldoCuponera(String data) {
		JSONObject obj = new JSONObject(data);
		repo.CargarSaldoCuponera(obj.getString("idUsuario"), Float.parseFloat(obj.getString("saldo")));
	}
	
	@POST
	@Path("/listanotificaciones/")
	public List<DataNotificacion> listarNotificaciones(String idUsuario){
		return repo.listarNotificaciones(idUsuario);
	}
	
	@POST
	@Path("/altaperfil/")
	public void AltaPerfil(final DataPerfil perfil) {
		repo.AltaPerfil(perfil);
	}
	
	@POST
	@Path("/editarperfil/")
	public void EditarPerfil(final DataPerfil perfil){
		repo.EditarPerfil(perfil);
	}
	
	@POST
	@Path("/eliminarperfil/")
	public void EliminarPerfil(String idPerfil){
		repo.EliminarPerfil(idPerfil);
	}
	
	@POST
	@Path("/asignarperfil/")
	public void AsignarPerfil(String data) {
		JSONObject obj = new JSONObject(data);
		repo.AsignarPerfil(obj.getString("idEmpleado"), obj.getString("perfil"));
	}
	
	@POST
	@Path("/loginusuario/")
	public boolean loginUsuario(String data) {
		JSONObject obj = new JSONObject(data);
		return repo.loginUsuario(obj.getString("usuario"), obj.getString("clave"));
	}

	@GET
	@Path("/getusuario/{idUsuario}")
	public DataUsuario getUsuario(@PathParam("idUsuario") final String idUsuario){
		return repo.getUsuario(idUsuario);
	}
	
	@POST
	@Path("/loginempleado/")
	public boolean loginEmpleado(String data) {
		JSONObject obj = new JSONObject(data);
		return repo.loginEmpleado(obj.getString("usuario"), obj.getString("clave"));
	}
	
	@GET
	@Path("/getempleado/{idEmpleado}")
	public DataEmpleado getEmpleado(@PathParam("idEmpleado") final String idEmpleado) {
		return repo.getEmpleado(idEmpleado);
	}

	@GET
	@Path("/getperfil/{idPerfil}")
	public DataPerfil getPerfil(@PathParam("idPerfil") final String idPerfil) {
		return repo.getPerfil(idPerfil);
	}

	@GET
	@Path("/listarperfiles/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public Map<String, DataPerfil> listarPerfiles(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina) {
		return repo.listarPerfiles(pagina, elementosPagina);
	}
}
