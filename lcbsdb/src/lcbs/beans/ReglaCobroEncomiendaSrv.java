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
import lcbs.interfaces.ReglaCobroEncomiendaLocalApi;
import lcbs.models.ReglaCobroEncomienda;
import lcbs.shares.DataReglaCobroEncomienda;
import lcbs.shares.DataReglaCobroEncomiendaCriteria;
import lcbs.shares.DataTenant;

/**
 * Session Bean implementation class ReglaCobroEncomiendaSrv
 */
@Stateless

@Interceptors ({TenantIntercept.class})
public class ReglaCobroEncomiendaSrv implements ReglaCobroEncomiendaLocalApi {
	@Inject
	EntityManager em;

	public ReglaCobroEncomiendaSrv() {

	}

	public List<DataReglaCobroEncomienda> obtenerReglaCobroEncomiendas(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataReglaCobroEncomienda> reglas = new ArrayList();
		// obtengo todas las reglas de cobro de encomiendas de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(ReglaCobroEncomienda.class);

		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<ReglaCobroEncomienda> listRce = new ArrayList<ReglaCobroEncomienda>(new LinkedHashSet(criteria.list()));

		listRce.stream().forEach((rce) -> {
			reglas.add(rce.getDatatype());
		});
		return reglas;
	}

	public DataReglaCobroEncomienda modificarReglaCobroEncomienda(DataReglaCobroEncomienda rce, DataTenant tenant) {
		ReglaCobroEncomienda realObj = new ReglaCobroEncomienda(rce);
		if (em.find(ReglaCobroEncomienda.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("La regla de cobro de encomiendas no existe");
		}
		em.merge(realObj);
		return realObj.getDatatype();
	}

	public DataReglaCobroEncomienda getReglaCobroEncomienda(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		ReglaCobroEncomienda realObj = (ReglaCobroEncomienda) session.get(ReglaCobroEncomienda.class, id);
		return realObj.getDatatype();
	}

	public DataReglaCobroEncomienda crearReglaCobroEncomienda(DataReglaCobroEncomienda rce, DataTenant tenant) {
		ReglaCobroEncomienda realObj = new ReglaCobroEncomienda(rce);
		// guardo la regla de cobro de encomiendas en bd
		em.persist(realObj);
		return realObj.getDatatype();
	}

	public void borrarReglaCobroEncomienda(String idRce, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		ReglaCobroEncomienda realObj = (ReglaCobroEncomienda) session.get(ReglaCobroEncomienda.class, idRce);
		em.remove(realObj);
	}

	public Float getPrecioDeEncomienda(String codigoReglaCobro, Float monto, DataTenant tenant) {
		Float result = null;
		DataReglaCobroEncomienda regla = getReglaCobroEncomienda(codigoReglaCobro, tenant);
		if (regla != null && regla.getCriterias().size() > 0) {
			for (DataReglaCobroEncomiendaCriteria crit : regla.getCriterias()) {
				if (crit.getOperador().equals("<=")) {
					if (Float.valueOf(monto) <= Float.valueOf(crit.getValor())) {
						if (regla.getPrecioExactoOCalculo()) {
							return crit.getPrecio();
						} else {
							return crit.getPrecio() * monto;
						}
					}
				} else {
					if (Float.valueOf(monto) > Float.valueOf(crit.getValor())) {
						if (regla.getPrecioExactoOCalculo()) {
							return crit.getPrecio();
						} else {
							return crit.getPrecio() * monto;
						}
					}
				}
			}
		}
		return result;
	}
}
