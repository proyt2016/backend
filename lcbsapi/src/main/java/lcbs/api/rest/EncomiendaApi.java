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
public class EncomiendaApi {

	
	@EJB
	EncomiendaRepo repo;
		
	
	@POST
	@Path("/altaencomienda/")
	public DataEncomiendaConvertor AltaEncomienda(DataEncomiendaConvertor enc){
		DataEncomienda encomienda = enc.getDataEncomienda();
		return repo.AltaEncomienda(encomienda).genConvertor();
	}
	
	@DELETE
	@Path("/borrarestadoencomienda/{idEstadoEncomienda}")
	public void borrarEstadoEncomienda(@PathParam("idEstadoEncomienda") final String idEstadoEncomienda){
		repo.borrarEstadoEncomienda(idEstadoEncomienda);
	}
	
	@DELETE
	@Path("/borrarreglacobroencomienda/{idReglaCobro}")
	public void borrarReglaCobroEncomienda(@PathParam("idReglaCobro") final String idReglaCobro){
		repo.borrarReglaCobroEncomienda(idReglaCobro);
	}
	
	@POST
	@Path("/setestadoencomienda/{idEncomienda}/")
	public void setEstadoEncomienda(@PathParam("idEncomienda")final String idEncomienda,final DataEstadosEncomienda dataEstado){
		repo.setEstadoEncomienda(idEncomienda, dataEstado);
	}
	
	@POST
	@Path("/asignarencomiendavehiculo/")
	public void AsignarEncomiendaVehiculo(String data) {
		JSONObject obj = new JSONObject(data);
		repo.AsignarEncomiendasVehiculo(obj.getString("IdEncomienda"), obj.getString("idViaje"), obj.getString("idCoche"));
	}
	
		
	@POST
	@Path("/altaestadoencomienda/")
	public void AltaEstadoEncomienda(DataEstadosEncomienda dataEstado){
		repo.crearEstadoEncomienda(dataEstado);
	}
	
	@POST
	@Path("/editarestadoencomienda/")
	public void EditarEstadoEncomienda(DataEstadosEncomienda dataEstado){
		repo.EditarEstadoEncomienda(dataEstado);
	}
	
	@POST
	@Path("/editarreglacobroencomienda/")
	public DataReglaCobroEncomienda editarReglaCobroEncomienda(DataReglaCobroEncomienda dataRegla){
		return repo.editarReglaCobroEncomienda(dataRegla);
	}
	
	@GET
	@Path("/getestadoencomienda/{idEstadoEncomienda}")
	public DataEstadosEncomienda getEstadoEncomienda(@PathParam("idEstadoEncomienda") final String idEstadoEncomienda){
		return repo.getEstadoEncomienda(idEstadoEncomienda);
	}
	
	@POST
	@Path("/altareglacobro/")
	public void AltaReglaCobro(DataReglaCobroEncomienda dataRegla){
		repo.AltaReglaCobro(dataRegla);
	}
	
	@POST
	@Path("/editarencomienda/")
	public void editarEncomienda(DataEncomienda encomienda){
		repo.editarEncomienda(encomienda);
	}
	
	@DELETE
	@Path("/borrarencomienda/{idEncomienda}")
	public void borrarEncomienda(@PathParam("idEncomienda") final String idEncomienda){
		repo.borrarEstadoEncomienda(idEncomienda);
	}
	
	@GET
	@Path("/getencomiendasporvehiculo/{idViaje}")
	public List<DataEncomienda> getEncomiendasPorVehiculo(@PathParam("idViaje") final String idViaje){
		return repo.getEncomiendasPorVehiculo(idViaje);
	}
	
	@POST
	@Path("/buscarencomienda/{pagina:[0-9][0-9]*}/{elementosPagina:[0-9][0-9]*}")
	public List<DataEncomiendaConvertor> buscarEncomienda(DataEncomienda filtro, @PathParam("pagina") final Integer pagina,@PathParam("elementosPagina") final Integer ElementosPagina){
		List<DataEncomiendaConvertor> result = new ArrayList<DataEncomiendaConvertor>();
		repo.buscarEncomienda(filtro, pagina, ElementosPagina).forEach((encomienda)->{
			result.add(encomienda.genConvertor());
		});
		return result;
	}
	
	@GET
	@Path("/getestados/{pagina:[0-9][0-9]*}/{elementosPagina:[0-9][0-9]*}")
	public List<DataEstadosEncomienda> getEstados(@PathParam("pagina") final Integer pagina,@PathParam("elementosPagina") final Integer ElementosPagina){
		return repo.getEstados(pagina, ElementosPagina); 
	}
	
	
	@GET
	@Path("/getencomienda/{idEncomienda}")
	public DataEncomienda getEncomienda(@PathParam("idEncomienda") final String idEncomienda){
		return repo.getEncomienda(idEncomienda);
	}
	
	@GET
	@Path("/getreglacobro/{idRegla}")
	public DataReglaCobroEncomienda getReglaCobro(@PathParam("idRegla") final String idEncomieda){
		return repo.getReglaDeCobro(idEncomieda);
	}
	
	@GET
	@Path("/getencomiendas/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEncomiendaConvertor> getEncomiendas(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		return repo.ListarEncomiendas(pagina, elementosPagina);
	}
	
	@GET
	@Path("/getreglascobro/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataReglaCobroEncomienda> getReglasCobro(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		return repo.getReglasDeCobro(pagina, elementosPagina);
	}
	
	@GET
	@Path("/gethistorialestados/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}/{idEncomienda}")
	public List<DataHistorialEstadosEncomienda> getHistorialEstadosEncomienda(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina, @PathParam("idEncomienda") final String idEncomienda){
		return repo.getHistorialEstado(idEncomienda);
	}
	
	@GET
	@Path("/getultimoestado/{idEncomienda}")
	public DataEstadosEncomienda getUltimoEstado(@PathParam("idEncomienda")final String idEncomienda){
		return repo.getUltimoEstado(idEncomienda);
	}
	
	@GET
	@Path("/listarestadosencomienda/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEstadosEncomienda> listarEstadoEncomienda(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		return repo.listarEstadoEncomienda(pagina, elementosPagina);
	}
	@GET
	@Path("/listarencomiendasusuario/{idUsuario}/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	public List<DataEncomienda> listarEncomiendasPorUsuario(@PathParam("idUsuario") final String idUsuario, @PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar")final Integer elementosPagina){
		return repo.listarEncomiendasPorUsuario(idUsuario, pagina, elementosPagina);
	}
	
	@GET
	@Path("/getencomiendaxcodigo/{codigoEnc}")
	public DataEncomienda getEncomiendaXcodigo(@PathParam("codigoEnc") final int codigoEncomienda){
		return repo.getEncomiendaXcodigo(codigoEncomienda);
	}
	
	@GET
	@Path("/getpreciodeencomienda/{codigoReglaCobro}/{monto}")
	public Float getPrecioDeEncomienda(@PathParam("codigoReglaCobro") final String codigoReglaCobro, @PathParam("monto") final Float monto){
		return repo.getPrecioDeEncomienda(codigoReglaCobro, monto);
	}

}
