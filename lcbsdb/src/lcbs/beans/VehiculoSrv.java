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
import lcbs.models.Vehiculo;
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

	public void modificarVehiculo(DataVehiculo veh, DataTenant tenant) {
		Vehiculo realObj = new Vehiculo(veh);
		if (em.find(Vehiculo.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("La Vehiculo no existe");
		}
		em.merge(realObj);
	}

	public DataVehiculo getVehiculo(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Vehiculo realObj = (Vehiculo) session.get(Vehiculo.class, id);
		return realObj.getDatatype(true);
	}

	public DataVehiculo crearVehiculo(DataVehiculo veh, DataTenant tenant) {
		Vehiculo realObj = new Vehiculo(veh);
		realObj.setEliminado(false);
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