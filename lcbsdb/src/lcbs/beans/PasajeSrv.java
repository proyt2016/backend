package lcbs.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.hibernate.criterion.Restrictions;

import lcbs.interceptors.TenantIntercept;
import lcbs.interfaces.PasajeLocalApi;
import lcbs.models.Pasaje;
import lcbs.shares.DataPasaje;
import lcbs.shares.DataPasajeConvertor;
import lcbs.shares.DataTenant;

/**
 * Session Bean implementation class PasajeSrv
 */
@Stateless

@Interceptors ({TenantIntercept.class})
public class PasajeSrv implements PasajeLocalApi {
	@Inject
	EntityManager em;
	private static final Log log = LogFactory.getLog(EntityManagerProducer.class);

	public PasajeSrv() {

	}

	public List<DataPasajeConvertor> obtenerPasajes(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataPasajeConvertor> Pasajes = new ArrayList();
		// obtengo todos los Pasajes de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Pasaje.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);

		List<Pasaje> listPsj = new ArrayList<Pasaje>(new LinkedHashSet(criteria.list()));

		listPsj.stream().forEach((psj) -> {
			Pasajes.add(psj.getDatatype().genConvertor());
		});
		return Pasajes;
	}

	public void modificarPasaje(DataPasaje psj, DataTenant tenant) {
		Pasaje realObj = new Pasaje(psj);
		if (getPasaje(realObj.getId(), tenant) == null) {
			throw new IllegalArgumentException("El Pasaje no existe");
		}
		em.merge(realObj);
	}

	public DataPasaje getPasaje(String id, DataTenant tenant) {
		List<DataPasaje> Pasajes = new ArrayList();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Pasaje.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.add(Restrictions.eq("id", id));
		List<Pasaje> listPsj = new ArrayList<Pasaje>(new LinkedHashSet(criteria.list()));
		listPsj.stream().forEach((psj) -> {
			Pasajes.add(psj.getDatatype());
		});
		return Pasajes.get(0);
	}

	public DataPasaje getpasajeXcodigo(Integer codigoPasaje, DataTenant tenant) {
		List<DataPasaje> Pasajes = new ArrayList();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Pasaje.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.add(Restrictions.eq("codigoPasaje", codigoPasaje));
		List<Pasaje> listPsj = new ArrayList<Pasaje>(new LinkedHashSet(criteria.list()));
		listPsj.stream().forEach((psj) -> {
			Pasajes.add(psj.getDatatype());
		});
		return Pasajes.get(0);
	}

	public DataPasaje crearPasaje(DataPasaje psj, DataTenant tenant) {
		Pasaje realObj = new Pasaje(psj);
		realObj.setEliminado(false);
		// guardo el Pasaje en bd
		em.persist(realObj);
		return realObj.getDatatype();
	}

	public void darBajaPasaje(String idPasaje, DataTenant tenant) {
		DataPasaje psj = getPasaje(idPasaje, tenant);
		psj.setEliminado(true);
		this.modificarPasaje(psj, tenant);
	}

	public List<DataPasajeConvertor> obtenerPasajesPorPersona(String idUsuario, Integer pagina, Integer elementosPagina,
			DataTenant tenant) {
		List<DataPasajeConvertor> Pasajes = new ArrayList();
		// obtengo todos los Pasajes de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Pasaje.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.add(Restrictions.eq("comprador.id", idUsuario));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Pasaje> listPsj = new ArrayList<Pasaje>(new LinkedHashSet(criteria.list()));

		listPsj.stream().forEach((psj) -> {
			Pasajes.add(psj.getDatatype().genConvertor());
		});
		return Pasajes;
	}
	
	public List<DataPasajeConvertor> obtenerPasajesPorViaje(String idViaje, DataTenant tenant) {
		List<DataPasajeConvertor> Pasajes = new ArrayList();
		// obtengo todos los Pasajes de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Pasaje.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.add(Restrictions.eq("viaje.id", idViaje));
		List<Pasaje> listPsj = new ArrayList<Pasaje>(new LinkedHashSet(criteria.list()));

		listPsj.stream().forEach((psj) -> {
			Pasajes.add(psj.getDatatype().genConvertor());
		});
		return Pasajes;
	}
	
	public List<DataPasajeConvertor> obtenerTotalPasajesVendidos(String fecha, Integer pagina, Integer elementosPagina, DataTenant tenant){
		List<DataPasajeConvertor> pasajesVendidos = new ArrayList();
		
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Pasaje.class);
		criteria.add(Restrictions.eq("eliminado", false));
		criteria.add(Restrictions.eq("pago", true));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fromDate = new Date();
		Date toDate = new Date();
		try {
			fromDate = df.parse(fecha + " 00:00:00");
			 toDate = df.parse(fecha + " 23:59:59");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		criteria.add(Restrictions.between("fechaCompra", fromDate, toDate));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Pasaje> listPsj = new ArrayList<Pasaje>(new LinkedHashSet(criteria.list()));
		
		listPsj.stream().forEach((psj) -> {
			pasajesVendidos.add(psj.getDatatype().genConvertor());
		});
		return pasajesVendidos;
		
	}
}
