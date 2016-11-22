package lcbs.beans;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import lcbs.interceptors.TenantIntercept;
import lcbs.interfaces.RecorridoLocalApi;
import lcbs.models.Recorrido;
import lcbs.shares.DataRecorrido;
import lcbs.shares.DataTenant;

/**
 * Session Bean implementation class RecorridoSrv
 */
@Stateless

@Interceptors ({TenantIntercept.class})
public class RecorridoSrv implements RecorridoLocalApi {
	private static final Log log = LogFactory.getLog(RecorridoSrv.class);
	@Inject
	EntityManager em;

	public static void log(String s) {

		log.info("------->" + s);
	}

	public RecorridoSrv() {

	}

	public List<DataRecorrido> obtenerRecorridos(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataRecorrido> Recorridos = new ArrayList();
		// obtengo todos los Recorridos de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Recorrido.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		criteria.addOrder(Order.asc("nombre"));
		List<Recorrido> listRec = new ArrayList<Recorrido>(new LinkedHashSet(criteria.list()));
		listRec.stream().forEach((rec) -> {
			Recorridos.add(rec.getDatatype(true));
		});
		return Recorridos;
	}

	public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, Integer pagina, Integer ElementosPagina, DataTenant tenant) {
		List<DataRecorrido> recorridos = new ArrayList();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Recorrido.class);
		if (filtro.getNombre() != null)
			criteria.add(Restrictions.eq("nombre", filtro.getNombre()));
		if (filtro.genIdOrigen() != null && filtro.genIdDestino() != null)
			criteria.createCriteria("puntosDeRecorrido").add(Restrictions
					.and(Restrictions.eq("id", filtro.genIdOrigen()), Restrictions.eq("id", filtro.genIdDestino())));
		if (filtro.genTipoHorario() != null)
			criteria.add(Restrictions.eq("horario.tipo", filtro.genTipoHorario()));

		criteria.setFirstResult((pagina - 1) * ElementosPagina);
		criteria.setMaxResults(ElementosPagina);

		List<Recorrido> listRec = new ArrayList<Recorrido>(new LinkedHashSet(criteria.list()));
		listRec.stream().forEach((rec) -> {
			recorridos.add(rec.getDatatype(true));
		});
		return recorridos;
	}

	public void modificarRecorrido(DataRecorrido rec, DataTenant tenant) {
		Recorrido realObj = new Recorrido(rec);
		if (em.find(Recorrido.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El recorrido no existe");
		}
		em.merge(realObj);
	}

	public DataRecorrido getRecorrido(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Recorrido realObj = (Recorrido) session.get(Recorrido.class, id);
		return realObj.getDatatype(true);
	}

	public DataRecorrido crearRecorrido(DataRecorrido rec, DataTenant tenant) {
		Recorrido realObj = new Recorrido(rec);
		realObj.setEliminado(false);
		// guardo el Recorrido en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	public void darBajaRecorrido(String idRecorrido, DataTenant tenant) {
		DataRecorrido rec = getRecorrido(idRecorrido, tenant);
		rec.setEliminado(true);
		this.modificarRecorrido(rec, tenant);
	}
}