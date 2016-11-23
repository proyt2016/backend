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
import lcbs.interfaces.ReservaLocalApi;
import lcbs.models.Reserva;
import lcbs.shares.DataReserva;
import lcbs.shares.DataTenant;

/**
 * Session Bean implementation class ReservaSrv
 */
@Stateless

@Interceptors ({TenantIntercept.class})
public class ReservaSrv implements ReservaLocalApi {
	@Inject
	EntityManager em;

	public ReservaSrv() {

	}

	public List<DataReserva> obtenerReservas(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataReserva> reservas = new ArrayList();
		// obtengo todas las Reservas de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("eliminada", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Reserva> listRes = new ArrayList<Reserva>(new LinkedHashSet(criteria.list()));

		listRes.stream().forEach((rec) -> {
			reservas.add(rec.getDatatype());
		});
		return reservas;
	}

	public List<DataReserva> listarReservasPorUsuario(String idUsuario, DataTenant tenant) {
		List<DataReserva> reservas = new ArrayList();
		// obtengo todas las Reservas de la bd
		Session session = (Session) em.getDelegate();

		Criteria c = session.createCriteria(Reserva.class);
		c.add(Restrictions.eq("usuarioReserva.id", idUsuario)); 
		List<Reserva> listRes = new ArrayList<Reserva>(new LinkedHashSet(c.list()));

		listRes.stream().forEach((rec) -> {
			reservas.add(rec.getDatatype());
		});
		return reservas;
	}

	public void modificarReserva(DataReserva res, DataTenant tenant) {
		Reserva realObj = new Reserva(res);
		if (em.find(Reserva.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("La reserva no existe");
		}
		em.merge(realObj);
	}

	public DataReserva getReserva(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Reserva realObj = (Reserva) session.get(Reserva.class, id);
		return realObj.getDatatype();
	}

	public DataReserva crearReserva(DataReserva res, DataTenant tenant) {
		Reserva realObj = new Reserva(res);
		realObj.setEliminada(false);
		// guardo la reserva en bd
		em.persist(realObj);
		return realObj.getDatatype();
	}

	public void darBajaReserva(String idReserva, DataTenant tenant) {
		DataReserva res = getReserva(idReserva, tenant);
		res.setEliminada(true);
		this.modificarReserva(res, tenant);
	}

	@Override
	public DataReserva getReservaPorCi(String ciUsuario, DataTenant tenant) {
		List<DataReserva> reservas = new ArrayList();
		// obtengo todas las Reservas de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Reserva.class);
		criteria.add(Restrictions.eq("eliminada", false));
		criteria.add(Restrictions.eq("ciPersona", ciUsuario));
		List<Reserva> listRes = new ArrayList<Reserva>(new LinkedHashSet(criteria.list()));

		listRes.stream().forEach((rec) -> {
			reservas.add(rec.getDatatype());
		});
		return reservas.get(0);
	}

}