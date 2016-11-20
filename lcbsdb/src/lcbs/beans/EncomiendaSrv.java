package lcbs.beans;

import javax.ejb.Stateless;
import lcbs.shares.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Column;

import lcbs.interceptors.TenantIntercept;
import lcbs.interfaces.EncomiendaLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Encomienda;
import lcbs.models.EstadosEncomienda;
import lcbs.models.Pasaje;
import lcbs.models.Viaje;

import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.Date;

/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless

@Interceptors ({TenantIntercept.class})
public class EncomiendaSrv implements EncomiendaLocalApi {
	@Inject
	EntityManager em;
	private static final Log log = LogFactory.getLog(EncomiendaSrv.class);

	public EncomiendaSrv() {

	}

	public List<DataEncomiendaConvertor> obtenerEncomiendas(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataEncomiendaConvertor> encomiendas = new ArrayList();
		// obtengo todas las encomiendas de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Encomienda.class);
		criteria.add(Restrictions.eq("eliminada", false));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Encomienda> listEnc = new ArrayList<Encomienda>(new LinkedHashSet(criteria.list()));

		listEnc.stream().forEach((enc) -> {
			encomiendas.add(enc.getDatatype(true).genConvertor());
		});
		return encomiendas;
	}
	
	public List<DataEncomiendaConvertor> getEncomiendasPagas(String fecha, Integer pagina, Integer elementosPagina ,DataTenant tenant) {
		List<DataEncomiendaConvertor> encomiendas = new ArrayList();
		// obtengo todas las encomiendas de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Encomienda.class);
		criteria.add(Restrictions.eq("eliminada", false));
		criteria.add(Restrictions.eq("paga", true));
		
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
		criteria.add(Restrictions.between("fechaEntrega", fromDate, toDate));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Encomienda> listEnc = new ArrayList<Encomienda>(new LinkedHashSet(criteria.list()));

		listEnc.stream().forEach((enc) -> {
			encomiendas.add(enc.getDatatype(true).genConvertor());
			log.info("-------------------------------------->"+enc.getCodigoEncomienda());
		});
		return encomiendas;
	}

	public void modificarEncomienda(DataEncomienda enc, DataTenant tenant) {
		Encomienda realObj = new Encomienda(enc, true);
		em.merge(realObj);
	}

	public DataEncomienda getEncomienda(String id, DataTenant tenant) {
		List<DataEncomienda> encomiendas = new ArrayList();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Encomienda.class);
		criteria.add(Restrictions.eq("eliminada", false));
		criteria.add(Restrictions.eq("id", id));
		List<Encomienda> listEnc = new ArrayList<Encomienda>(new LinkedHashSet(criteria.list()));
		listEnc.stream().forEach((enc) -> {
			encomiendas.add(enc.getDatatype(true));
		});
		return encomiendas.get(0);
	}

	public DataEncomienda getEncomiendaXcodigo(Integer codigoEncomienda, DataTenant tenant) {
		List<DataEncomienda> encomiendas = new ArrayList();
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Encomienda.class);
		criteria.add(Restrictions.eq("eliminada", false));
		criteria.add(Restrictions.eq("codigoEncomienda", codigoEncomienda));
		List<Encomienda> listEnc = new ArrayList<Encomienda>(new LinkedHashSet(criteria.list()));
		listEnc.stream().forEach((enc) -> {
			encomiendas.add(enc.getDatatype(true));
		});
		return encomiendas.get(0);
	}

	public DataEncomienda crearEncomienda(DataEncomienda enc, DataTenant tenant) {
		Encomienda realObj = new Encomienda(enc, true);
		realObj.setEliminada(false);
		realObj.setFechaIngreso(new Date());
		// guardo la encomienda en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	public void darBajaEncomienda(String idEncomienda, DataTenant tenant) {
		DataEncomienda enc = getEncomienda(idEncomienda, tenant);
		enc.setEliminada(true);
		this.modificarEncomienda(enc, tenant);
	}

	public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina, DataTenant tenant) {
		List<DataEncomienda> encomiendas = new ArrayList();
		try {
			Session session = (Session) em.getDelegate();
			Criteria criteria = session.createCriteria(Encomienda.class);
			if (filtro.getOrigen() != null)
				criteria.createCriteria("origen").add(Restrictions.eq("id", filtro.getOrigen().getId()));
			if (filtro.getDestino() != null)
				criteria.createCriteria("destino").add(Restrictions.eq("id", filtro.getDestino().getId()));
			if (filtro.getEmisor() != null)
				criteria.createCriteria("emisor").add(Restrictions.eq("id", filtro.getEmisor().getId()));
			if (filtro.getReceptor() != null)
				criteria.createCriteria("receptor").add(Restrictions.eq("id", filtro.getReceptor().getId()));
			if (filtro.getEstadoActual() != null)
				criteria.createCriteria("estadoActual").add(Restrictions.eq("id", filtro.getEstadoActual().getId()));
			if (filtro.getCiEmisor() != null)
				criteria.add(Restrictions.eq("ciEmisor", filtro.getCiEmisor()));
			if (filtro.getCiReceptor() != null)
				criteria.add(Restrictions.eq("ciReceptor", filtro.getCiReceptor()));
			if (filtro.getFechaIngreso() != null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String myDate = new SimpleDateFormat("yyyy-MM-dd").format(filtro.getFechaIngreso());
				Date fromDate = df.parse(myDate + " 00:00:00");
				Date toDate = df.parse(myDate + " 23:59:59");

				criteria.add(Restrictions.between("fechaIngreso", fromDate, toDate));
			}
			if (filtro.getFechaEntrega() != null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String myDate = new SimpleDateFormat("yyyy-MM-dd").format(filtro.getFechaEntrega());
				Date fromDate = df.parse(myDate + " 00:00:00");
				Date toDate = df.parse(myDate + " 23:59:59");

				criteria.add(Restrictions.between("fechaEntrega", fromDate, toDate));
			}
			if (filtro.getRetiraEnSucursal() != null)
				criteria.add(Restrictions.eq("retiraEnSucursal", filtro.getRetiraEnSucursal()));

			// Asi se obtiene el numero de resultados
			/*
			 * Criteria criteriaCount = criteria;
			 * criteriaCount.setProjection(Projections.rowCount()); Long count =
			 * (Long) criteriaCount.uniqueResult();
			 */

			criteria.setFirstResult((pagina - 1) * ElementosPagina);
			criteria.setMaxResults(ElementosPagina);

			List<Encomienda> listEnc = new ArrayList<Encomienda>(new LinkedHashSet(criteria.list()));
			listEnc.stream().forEach((enc) -> {
				encomiendas.add(enc.getDatatype(true));
			});
		} catch (Exception e) {
			 log.info("#################################################################### "+e.getMessage());
		}
		return encomiendas;
	}

	@Override
	public List<DataEncomienda> listarEncomiendasPorUsuario(String idUsuario, Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataEncomienda> encomiendas = new ArrayList();
		// obtengo todas las encomiendas de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Encomienda.class);
		criteria.add(Restrictions.eq("eliminada", false));
		criteria.add(
				Restrictions.or(Restrictions.eq("emisor.id", idUsuario), Restrictions.eq("receptor.id", idUsuario)));
		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Encomienda> listEnc = new ArrayList<Encomienda>(new LinkedHashSet(criteria.list()));

		listEnc.stream().forEach((enc) -> {
			encomiendas.add(enc.getDatatype(true));
		});
		return encomiendas;
	}

}
