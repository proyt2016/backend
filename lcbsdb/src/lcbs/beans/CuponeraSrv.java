package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import lcbs.interceptors.TenantIntercept;
import lcbs.interfaces.CuponeraLocalApi;
import lcbs.models.Cuponera;
import lcbs.shares.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
public class CuponeraSrv implements CuponeraLocalApi {
	@Inject
	EntityManager em;

	public CuponeraSrv() {

	}

	public List<DataCuponera> obtenerCuponera(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataCuponera> cuponeras = new ArrayList();
		// obtengo todas las cuponera de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Cuponera.class);

		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Cuponera> listCup = new ArrayList<Cuponera>(new LinkedHashSet(criteria.list()));

		listCup.stream().forEach((cup) -> {
			cuponeras.add(cup.getDatatype());
		});
		return cuponeras;
	}

	public void modificarCuponera(DataCuponera cup, DataTenant tenant) {
		Cuponera realObj = new Cuponera(cup);
		if (em.find(Cuponera.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("La cuponera no existe");
		}
		em.merge(realObj);
	}

	public DataCuponera getCuponera(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Cuponera realObj = (Cuponera) session.get(Cuponera.class, id);
		return realObj.getDatatype();
	}

	public DataCuponera crearCuponera(DataCuponera cup, DataTenant tenant) {
		Cuponera realObj = new Cuponera(cup);
		// guardo la cuponera en bd
		em.persist(realObj);
		return realObj.getDatatype();
	}
}
