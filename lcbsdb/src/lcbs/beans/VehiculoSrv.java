package lcbs.beans;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interceptors.TenantIntercept;
import lcbs.interfaces.VehiculoLocalApi;
import lcbs.models.MantenimientoVehiculo;
import lcbs.models.Vehiculo;
import lcbs.shares.DataMantenimientoVehiculo;
import lcbs.shares.DataTenant;
import lcbs.shares.DataVehiculo;

/**
 * Session Bean implementation class VehiculoSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
public class VehiculoSrv implements VehiculoLocalApi {
	@Inject
	EntityManager em;

	public VehiculoSrv() {

	}

	public List<DataVehiculo> obtenerVehiculos(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataVehiculo> vehiculos = new ArrayList();
		// obtengo todos los vehiculos de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Vehiculo.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Vehiculo> listVeh = new ArrayList<Vehiculo>(new LinkedHashSet(criteria.list()));

		listVeh.stream().forEach((veh) -> {
			vehiculos.add(veh.getDatatype(true));
		});
		return vehiculos;
	}
	
	public List<DataVehiculo> obtenerVehiculosEnMantenimiento(Integer pagina, Integer elementosPagina, DataTenant tenant){
		List<DataVehiculo> vehiculosEnMante = new ArrayList();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Vehiculo.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.add(Restrictions.eq("enMantenimiento", true));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Vehiculo> listVeh = new ArrayList<Vehiculo>(new LinkedHashSet(criteria.list()));

		listVeh.stream().forEach((veh) -> {
			vehiculosEnMante.add(veh.getDatatype(true));
		});
		return vehiculosEnMante;
	}

	public void modificarVehiculo(DataVehiculo veh, DataTenant tenant) {
		Vehiculo realObj = new Vehiculo(veh);
		if (em.find(Vehiculo.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("La Vehiculo no existe");
		}
		em.merge(realObj);
	}
	

	public Integer getMenorCantAsientos(DataTenant tenant){
		Integer menor = 0;
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Vehiculo.class);
		criteria.add(Restrictions.eq("eliminado", false));
		List<Vehiculo> listVeh = new ArrayList<Vehiculo>(new LinkedHashSet(criteria.list()));
		for(Integer i=0; i < listVeh.size(); i++){
			if(i==0){
				menor = listVeh.get(i).getCantidadAsientos();
			}
			if(listVeh.get(i).getCantidadAsientos() < menor){
				menor = listVeh.get(i).getCantidadAsientos();
			}
		}
		return menor;
	}

	public DataVehiculo getVehiculo(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Vehiculo realObj = (Vehiculo) session.get(Vehiculo.class, id);
		return realObj.getDatatype(true);
	}
	
	public DataVehiculo getVehiculoPorNumero(String numeroVehiculo, DataTenant tenant) {
		List<DataVehiculo> vehiculos = new ArrayList();
		// obtengo todos los vehiculos de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Vehiculo.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.add(Restrictions.eq("numeroVehiculo", numeroVehiculo));
		List<Vehiculo> listVeh = new ArrayList<Vehiculo>(new LinkedHashSet(criteria.list()));

		listVeh.stream().forEach((veh) -> {
			vehiculos.add(veh.getDatatype(true));
		});
		return vehiculos.get(0);
	}

	public DataVehiculo crearVehiculo(DataVehiculo veh, DataTenant tenant) {
		Vehiculo realObj = new Vehiculo(veh);
		realObj.setEliminado(false);
		realObj.setEnMantenimiento(false);
		// guardo el vehiculo en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	public void darBajaVehiculo(String idVehiculo, DataTenant tenant) {
		DataVehiculo veh = getVehiculo(idVehiculo, tenant);
		veh.setEliminado(true);
		this.modificarVehiculo(veh, tenant);
	}

}