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
import lcbs.interfaces.TerminalLocalApi;
import lcbs.models.Terminal;
import lcbs.shares.DataPuntoRecorrido;
import lcbs.shares.DataTenant;
import lcbs.shares.DataTerminal;

/**
 * Session Bean implementation class TerminalSrv
 */
@Stateless

@Interceptors ({TenantIntercept.class})
public class TerminalSrv implements TerminalLocalApi {
	private static final Log log = LogFactory.getLog(TerminalSrv.class);
	@Inject
	EntityManager em;

	public TerminalSrv() {

	}

	public List<DataTerminal> obtenerTerminals(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataTerminal> terminales = new ArrayList();
		// obtengo todas las terminales de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Terminal.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		criteria.addOrder(Order.asc("nombre"));
		List<Terminal> listTer = new ArrayList<Terminal>(new LinkedHashSet(criteria.list()));
		listTer.stream().forEach((ter) -> {
			terminales.add(ter.getDatatype());
		});
		return terminales;
	}

	public void modificarTerminal(DataTerminal ter, DataTenant tenant) {
		Terminal realObj = new Terminal(ter);
		if (em.find(Terminal.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("La terminal no existe");
		}
		em.merge(realObj);
	}

	public DataTerminal getTerminal(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Terminal realObj = (Terminal) session.get(Terminal.class, id);
		return realObj.getDatatype();
	}

	public DataTerminal crearTerminal(DataTerminal ter, DataTenant tenant) {
		Terminal realObj = new Terminal(ter);
		realObj.setEliminado(false);
		// guardo la Terminal en bd
		em.persist(realObj);
		return realObj.getDatatype();
	}

	public void darBajaTerminal(String idTerminal, DataTenant tenant) {
		DataTerminal ter = getTerminal(idTerminal, tenant);
		ter.setEliminado(true);
		this.modificarTerminal(ter, tenant);
	}

	@Override
	public DataPuntoRecorrido getTerminalPorCoordenada(String coord, DataTenant tenant) {
		List<DataTerminal> terminales = new ArrayList();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Terminal.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.add(Restrictions.eq("ubicacionMapa", coord));
		List<Terminal> listTer = new ArrayList<Terminal>(new LinkedHashSet(criteria.list()));

		listTer.stream().forEach((ter) -> {
			terminales.add(ter.getDatatype());
		});
		return terminales.size() > 0 ? terminales.get(0) : null;
	}

}