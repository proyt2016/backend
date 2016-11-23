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
public class UsuarioApi extends BaseApi{																														
	
	@EJB
	UsuarioRepo repo;
										
	@POST
	@Path("/altausuario/")
	public DataUsuario AltaUsuario(DataUsuario usuario){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.AltaUsuario(usuario, tenant);
	}
	
	@POST
	@Path("/editarusuario/")
	public void ModificarUsuario(DataUsuario usuario){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.ModificarUsuario(usuario, tenant);
	}
	
	@POST
	@Path("/bajausuario/")
	public void BajaUsuario(String idUsuario){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.BajaUsuario(idUsuario, tenant);
	}
	
	@POST
	@Path("/altaempleado/")
	public DataEmpleado AltaEmpleado(DataEmpleado empleado){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.AltaEmpleado(empleado, tenant);
	}
	
	@POST
	@Path("/editarempleado/")
	public void ModificarEmpleado(DataEmpleado empleado){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.ModificarEmpleado(empleado, tenant);
	}
	
	@POST
	@Path("/bajaempleado/")
	public void BajaEmpleado(String data){
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.BajaEmpleado(obj.getString("idEmpleado"), tenant);
	}
	
	@POST
	@Path("/cargarcuponera/")
	public void CargarSaldoCuponera(String data) {
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.CargarSaldoCuponera(obj.getString("idUsuario"), Float.parseFloat(obj.getString("saldo")), tenant);
	}
	
	@GET
	@Path("/listanotificaciones/{idUsuario}")
	public List<DataNotificacion> listarNotificaciones(@PathParam("idUsuario") final String idUsuario){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.listarNotificaciones(idUsuario, tenant);
	}
	
	//tested
	@POST
	@Path("/altaperfil/")
	public DataPerfil AltaPerfil(final DataPerfil perfil) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.AltaPerfil(perfil, tenant);
	}
	
	@POST
	@Path("/editarperfil/")
	public void EditarPerfil(final DataPerfil perfil){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.EditarPerfil(perfil, tenant);
	}
	
	@DELETE
	@Path("/eliminarperfil/{idPerfil}")
	public void EliminarPerfil(@PathParam("idPerfil") String idPerfil){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.EliminarPerfil(idPerfil, tenant);
	}
	
	//tested
	@POST
	@Path("/asignarperfil/")
	public void AsignarPerfil(String data) {
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.AsignarPerfil(obj.getString("idEmpleado"), obj.getString("perfil"), tenant);
	}
	
	@POST
	@Path("/loginusuario/")
	public DataUsuario loginUsuario(String data) {
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.loginUsuario(obj.getString("usuario"), obj.getString("clave"), tenant);
	}

	@GET
	@Path("/getusuario/{idUsuario}")
	public DataUsuario getUsuario(@PathParam("idUsuario") final String idUsuario){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getUsuario(idUsuario, tenant);
	}
	
	@POST
	@Path("/loginempleado/")
	public DataEmpleado loginEmpleado(String data) {
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.loginEmpleado(obj.getString("usuario"), obj.getString("clave"), tenant);
	}
	
	@GET
	@Path("/getempleado/{idEmpleado}")
	public DataEmpleado getEmpleado(@PathParam("idEmpleado") final String idEmpleado) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getEmpleado(idEmpleado, tenant);
	}
	
	@GET
	@Path("/listarempleados/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEmpleado> listarEmpleados(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.listarEmpleados(pagina, elementosPagina, tenant);
	}
	
	@GET
	@Path("/listarusuarios/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataUsuario> listarUsuarios(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.listarUsuarios(pagina, elementosPagina, tenant);
	}

	@GET
	@Path("/getperfil/{idPerfil}")
	public DataPerfil getPerfil(@PathParam("idPerfil") final String idPerfil) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getPerfil(idPerfil, tenant);
	}

	@GET
	@Path("/listarperfiles/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataPerfil> listarPerfiles(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.listarPerfiles(pagina, elementosPagina, tenant);
	}
	
	@POST
	@Path("/buscarusuariopormail/")
	public DataUsuario buscarUsuarioPorMail(String mailUsuario){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.buscarUsuarioPorMail(mailUsuario, tenant);
	}
	
	@POST
	@Path("/guardartokenusuario/")
	public void guardarTokenUsuario(String data){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		JSONObject obj = new JSONObject(data);
		repo.guardarTokenUsuario(obj.getString("idUsuario"), obj.getString("token"), Integer.valueOf(obj.getString("ultimosDigitosTarjeta")), tenant);
	}
	
	@POST
	@Path("/cargarsaldocuponerastripe/")
	public void CargarSaldoCuponeraStripe(String data){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		JSONObject obj = new JSONObject(data);
		repo.CargarSaldoCuponeraStripe(obj.getString("idUsuario"), Float.valueOf(obj.getString("saldo")), tenant);
	}
}
