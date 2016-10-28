/**
 * 
 */
package lcbs.api.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;


import lcbs.shares.*;
import lcbs.api.service.EncomiendaRepo;

/**
 * @author rodrigo
 *
 */
@RequestScoped
@Path("/encomiendas")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class EncomiendaApi extends BaseApi{

	
	@EJB
	EncomiendaRepo repo;
		
	
	@POST
	@Path("/altaencomienda/")
	public DataEncomiendaConvertor AltaEncomienda(DataEncomiendaConvertor enc){
		DataEncomienda encomienda = enc.getDataEncomienda();
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.AltaEncomienda(encomienda, tenant).genConvertor();
	}
	
	@DELETE
	@Path("/borrarestadoencomienda/{idEstadoEncomienda}")
	public void borrarEstadoEncomienda(@PathParam("idEstadoEncomienda") final String idEstadoEncomienda){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.borrarEstadoEncomienda(idEstadoEncomienda, tenant);
	}
	
	@DELETE
	@Path("/borrarreglacobroencomienda/{idReglaCobro}")
	public void borrarReglaCobroEncomienda(@PathParam("idReglaCobro") final String idReglaCobro){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.borrarReglaCobroEncomienda(idReglaCobro, tenant);
	}
	
	@POST
	@Path("/setestadoencomienda/{idEncomienda}/")
	public void setEstadoEncomienda(@PathParam("idEncomienda")final String idEncomienda,final DataEstadosEncomienda dataEstado){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.setEstadoEncomienda(idEncomienda, dataEstado, tenant);
	}
	
	@POST
	@Path("/asignarencomiendavehiculo/")
	public void AsignarEncomiendaVehiculo(String data) {
		JSONObject obj = new JSONObject(data);
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.AsignarEncomiendasVehiculo(obj.getString("IdEncomienda"), obj.getString("idViaje"), obj.getString("idCoche"), tenant);
	}
	
		
	@POST
	@Path("/altaestadoencomienda/")
	public void AltaEstadoEncomienda(DataEstadosEncomienda dataEstado){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.crearEstadoEncomienda(dataEstado, tenant);
	}
	
	@POST
	@Path("/editarestadoencomienda/")
	public void EditarEstadoEncomienda(DataEstadosEncomienda dataEstado){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.EditarEstadoEncomienda(dataEstado, tenant);
	}
	
	@POST
	@Path("/editarreglacobroencomienda/")
	public DataReglaCobroEncomienda editarReglaCobroEncomienda(DataReglaCobroEncomienda dataRegla){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.editarReglaCobroEncomienda(dataRegla, tenant);
	}
	
	@GET
	@Path("/getestadoencomienda/{idEstadoEncomienda}")
	public DataEstadosEncomienda getEstadoEncomienda(@PathParam("idEstadoEncomienda") final String idEstadoEncomienda){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getEstadoEncomienda(idEstadoEncomienda, tenant);
	}
	
	@POST
	@Path("/altareglacobro/")
	public void AltaReglaCobro(DataReglaCobroEncomienda dataRegla){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.AltaReglaCobro(dataRegla, tenant);
	}
	
	@POST
	@Path("/editarencomienda/")
	public void editarEncomienda(DataEncomienda encomienda){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.editarEncomienda(encomienda, tenant);
	}
	
	@DELETE
	@Path("/borrarencomienda/{idEncomienda}")
	public void borrarEncomienda(@PathParam("idEncomienda") final String idEncomienda){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		repo.borrarEstadoEncomienda(idEncomienda, tenant);
	}
	
	@GET
	@Path("/getencomiendasporvehiculo/{idVehiculo}")
	public List<DataEncomienda> getEncomiendasPorVehiculo(@PathParam("idVehiculo") final String idVehiculo){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getEncomiendasPorVehiculo(idVehiculo, tenant);
	}
	
	@POST
	@Path("/buscarencomienda/{pagina:[0-9][0-9]*}/{elementosPagina:[0-9][0-9]*}")
	public List<DataEncomiendaConvertor> buscarEncomienda(DataEncomiendaConvertor filtro, @PathParam("pagina") final Integer pagina,@PathParam("elementosPagina") final Integer ElementosPagina){
		List<DataEncomiendaConvertor> result = new ArrayList<DataEncomiendaConvertor>();
		DataEncomienda enc = filtro.getDataEncomienda();
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		
		repo.buscarEncomienda(enc, pagina, ElementosPagina, tenant).forEach((encomienda)->{
			result.add(encomienda.genConvertor());
		});
		return result;
	}
	
	@GET
	@Path("/getestados/{pagina:[0-9][0-9]*}/{elementosPagina:[0-9][0-9]*}")
	public List<DataEstadosEncomienda> getEstados(@PathParam("pagina") final Integer pagina,@PathParam("elementosPagina") final Integer ElementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getEstados(pagina, ElementosPagina, tenant); 
	}
	
	
	@GET
	@Path("/getencomienda/{idEncomienda}")
	public DataEncomienda getEncomienda(@PathParam("idEncomienda") final String idEncomienda){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getEncomienda(idEncomienda, tenant);
	}
	
	@GET
	@Path("/getreglacobro/{idRegla}")
	public DataReglaCobroEncomienda getReglaCobro(@PathParam("idRegla") final String idEncomieda){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getReglaDeCobro(idEncomieda, tenant);
	}
	
	@GET
	@Path("/getencomiendas/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEncomiendaConvertor> getEncomiendas(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.ListarEncomiendas(pagina, elementosPagina, tenant);
	}
	
	@GET
	@Path("/getreglascobro/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataReglaCobroEncomienda> getReglasCobro(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getReglasDeCobro(pagina, elementosPagina, tenant);
	}
	
	@GET
	@Path("/gethistorialestados/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}/{idEncomienda}")
	public List<DataHistorialEstadosEncomienda> getHistorialEstadosEncomienda(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina, @PathParam("idEncomienda") final String idEncomienda){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getHistorialEstado(idEncomienda, tenant);
	}
	
	@GET
	@Path("/getultimoestado/{idEncomienda}")
	public DataEstadosEncomienda getUltimoEstado(@PathParam("idEncomienda")final String idEncomienda){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getUltimoEstado(idEncomienda, tenant);
	}
	
	@GET
	@Path("/listarestadosencomienda/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEstadosEncomienda> listarEstadoEncomienda(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.listarEstadoEncomienda(pagina, elementosPagina, tenant);
	}
	@GET
	@Path("/listarencomiendasusuario/{idUsuario}/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEncomienda> listarEncomiendasPorUsuario(@PathParam("idUsuario") final String idUsuario, @PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.listarEncomiendasPorUsuario(idUsuario, pagina, elementosPagina, tenant);
	}
	
	@GET
	@Path("/getencomiendaxcodigo/{codigoEnc}")
	public DataEncomienda getEncomiendaXcodigo(@PathParam("codigoEnc") final Integer codigoEncomienda){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getEncomiendaXcodigo(codigoEncomienda, tenant);
	}
	
	@GET
	@Path("/getpreciodeencomienda/{codigoReglaCobro}/{monto}")
	public Float getPrecioDeEncomienda(@PathParam("codigoReglaCobro") final String codigoReglaCobro, @PathParam("monto") final Float monto){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getPrecioDeEncomienda(codigoReglaCobro, monto, tenant);
	}
	@GET
	@Path("/getencomiendaspagas/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}/{fecha}")
	public List<DataEncomiendaConvertor> getEncomiendasEntregadas(@PathParam("pagina") final Integer pagina,@PathParam("pagina") final Integer elementosAMostrar, @PathParam("fecha") final String fecha){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.getEncomiendasPagas(pagina, elementosAMostrar, fecha, tenant);
	}
}
