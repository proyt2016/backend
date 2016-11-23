/**
 * 
 */
package lcbs.api.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import lcbs.api.interceptor.TenantChecked;
import lcbs.api.service.VehiculoRepo;
import lcbs.shares.DataMantenimientoVehiculo;
import lcbs.shares.DataTenant;
import lcbs.shares.DataVehiculo;

/**
 * @author rodrigo
 *
 */
@RequestScoped
@Path("/vehiculos")
@Produces({ "application/json" })
@Consumes({ "application/json" })
@TenantChecked public class VehiculoApi extends BaseApi{

	
	@EJB
	VehiculoRepo repo;
	
	//tested
	@POST
	@Path("/altavehiculo/")
	@TenantChecked public DataVehiculo AltaVehiculo(DataVehiculo dataVehiculo){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.altaVehiculo(dataVehiculo, tenant );
	}
	
	//tested
	@POST
	@Path("/editarvehiculo/")
	@TenantChecked public void editarVehiculo(DataVehiculo vehiculo){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		 repo.editarVehiculo(vehiculo, tenant );
	}
	
	//tested
	@POST
	@Path("/bajavehiculo/")
	@TenantChecked public void bajaVehiculo(String data){
		 JSONObject obj = new JSONObject(data);
		 DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		 repo.bajaVehiculo(obj.getString("idVehiculo"), tenant );
	}
	@POST
	@Path("/altamantenimiento/{idVehiculo}")
	@TenantChecked public void altaMantenimiento(DataMantenimientoVehiculo mant,@PathParam("idVehiculo")final String id){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
	    repo.altaMantenimiento(mant,id, tenant);
	}
	
	@GET
	@Path("/listarvehiculos/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	@TenantChecked public List<DataVehiculo> listarVehiculos(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar") final Integer elementosPagina) {
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.listarVehiculos(pagina, elementosPagina, tenant );
	}
	
	@GET
	@Path("/listarmantenimientosporvehiculo/{idVehiculo}")
	@TenantChecked public List<DataMantenimientoVehiculo> ListarMantenimientosPorVehiculo(@PathParam("idVehiculo")final String idVehiculo){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.mantenimientosPorVehiculo(idVehiculo, tenant );
	}
	
	@GET
	@Path("/getvehiculo/{idVehiculo}")
	@TenantChecked public DataVehiculo getVechiulo(@PathParam("idVehiculo")final String idVehiculo){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerVehiculo(idVehiculo, tenant);
	}
	
	@GET
	@Path("/obtenervehiculopornumero/{nroVehiculo}")
	@TenantChecked public DataVehiculo obtenerVehiculoPorNumero(@PathParam("nroVehiculo")final String nroVehiculo){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerVehiculoPorNumero(nroVehiculo, tenant);
	}
	
	@GET
	@Path("/vehiculosenmantenimiento/{pagina:[0-9][0-9]*}/{elementosAMostrar:[0-9][0-9]*}")
	@TenantChecked public List<DataVehiculo> obtenerVehiculosEnMantenimiento(@PathParam("pagina") final Integer pagina, @PathParam("elementosAMostrar") final Integer elementosPagina){
		DataTenant tenant = (DataTenant) request.getAttribute("tenant");
		return repo.obtenerVehiculosEnMantenimiento(pagina, elementosPagina, tenant);
	}
	

}
