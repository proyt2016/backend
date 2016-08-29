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
import lcbs.interfaces.ParadaLocalApi;
import lcbs.models.Parada;
import lcbs.shares.DataParada;
import lcbs.shares.DataPuntoRecorrido;
import lcbs.shares.DataTenant;

/**
 * Session Bean implementation class ParadaSrv
 */
@Stateless

@Interceptors ({TenantIntercept.class})
public class ParadaSrv implements ParadaLocalApi {
	@Inject
	EntityManager em;

	public ParadaSrv() {

	}

	public List<DataParada> obtenerParadas(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataParada> paradas = new ArrayList();
		// obtengo todas las paradas de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Parada.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Parada> listPds = new ArrayList<Parada>(new LinkedHashSet(criteria.list()));

		listPds.stream().forEach((prd) -> {
			paradas.add(prd.getDatatype());
		});
		return paradas;
	}

	public void modificarParada(DataParada prd, DataTenant tenant) {
		Parada realObj = new Parada(prd);
		if (em.find(Parada.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("La parada no existe");
		}
		em.merge(realObj);
	}

	public DataParada getParada(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Parada realObj = (Parada) session.get(Parada.class, id);
		return realObj.getDatatype();
	}

	public DataParada crearParada(DataParada prd, DataTenant tenant) {
		Parada realObj = new Parada(prd);
		realObj.setEliminado(false);
		// guardo la parada en bd
		em.persist(realObj); 
		return realObj.getDatatype();
	}

	public void borrarParada(String idParada, DataTenant tenant) {
		DataParada prd = getParada(idParada, tenant);
		Parada realOb = new Parada(prd);
		em.remove(realOb);
	}

	@Override
	public DataPuntoRecorrido getParadaPorCoordenada(String coord, DataTenant tenant) {
		List<DataParada> paradas = new ArrayList();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Parada.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.add(Restrictions.eq("ubicacionMapa", coord));
		List<Parada> listPds = new ArrayList<Parada>(new LinkedHashSet(criteria.list()));

		listPds.stream().forEach((prd) -> {
			paradas.add(prd.getDatatype());
		});
		return paradas.size() > 0 ? paradas.get(0) : null;
	}
}
