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

import lcbs.interceptors.TenantIntercept;
import lcbs.interfaces.EstadosEncomiendaLocalApi;
import lcbs.models.EstadosEncomienda;
import lcbs.shares.DataEstadosEncomienda;
import lcbs.shares.DataTenant;

/**
 * Session Bean implementation class EstadosEncomiendaSrv
 */
@Stateless

@Interceptors ({TenantIntercept.class})
public class EstadosEncomiendaSrv implements EstadosEncomiendaLocalApi {
	@Inject
	EntityManager em;

	public EstadosEncomiendaSrv() {

	}

	public List<DataEstadosEncomienda> obtenerEstadosEncomienda(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataEstadosEncomienda> estados = new ArrayList();
		// obtengo todas los estados de encomienda de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(EstadosEncomienda.class);

		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<EstadosEncomienda> listEstEnc = new ArrayList<EstadosEncomienda>(new LinkedHashSet(criteria.list()));

		listEstEnc.stream().forEach((est) -> {
			estados.add(est.getDatatype());
		});
		return estados;
	}

	public void modificarEstadosEncomienda(DataEstadosEncomienda estEnc, DataTenant tenant) {
		EstadosEncomienda realObj = new EstadosEncomienda(estEnc);
		if (em.find(EstadosEncomienda.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El estado no existe");
		}
		em.merge(realObj);
	}

	public DataEstadosEncomienda getEstadosEncomienda(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		EstadosEncomienda realObj = (EstadosEncomienda) session.get(EstadosEncomienda.class, id);
		return realObj.getDatatype();
	}

	public DataEstadosEncomienda crearEstadosEncomienda(DataEstadosEncomienda est, DataTenant tenant) {
		EstadosEncomienda realObj = new EstadosEncomienda(est);
		// guardo el estado de encomienda en bd
		em.persist(realObj);
		return realObj.getDatatype();
	}

	public void borrarEstadosEncomienda(String idEst, DataTenant tenant) {
		DataEstadosEncomienda est = getEstadosEncomienda(idEst, tenant);
		EstadosEncomienda realObj = new EstadosEncomienda(est);
		em.remove(realObj);
	}
}
