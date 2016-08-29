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
import lcbs.interfaces.PerfilLocalApi;
import lcbs.models.Perfil;
import lcbs.shares.DataPerfil;
import lcbs.shares.DataTenant;

/**
 * Session Bean implementation class PerfilSrv
 */
@Stateless

@Interceptors ({TenantIntercept.class})
public class PerfilSrv implements PerfilLocalApi {
	@Inject
	EntityManager em;

	public PerfilSrv() {

	}

	public List<DataPerfil> obtenerPerfils(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataPerfil> Perfils = new ArrayList();
		// obtengo todos los Perfiles de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Perfil.class);

		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<Perfil> listPds = new ArrayList<Perfil>(new LinkedHashSet(criteria.list()));
		listPds.stream().forEach((prf) -> {
			Perfils.add(prf.getDatatype(true));
		});
		return Perfils;
	}

	public void modificarPerfil(DataPerfil prf, DataTenant tenant) {
		Perfil realObj = new Perfil(prf, true);
		if (em.find(Perfil.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El perfil no existe");
		}
		em.merge(realObj);
	}

	public DataPerfil getPerfil(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		Perfil realObj = (Perfil) session.get(Perfil.class, id);
		return realObj.getDatatype(true);
	}

	public DataPerfil crearPerfil(DataPerfil prf, DataTenant tenant) {
		Perfil realObj = new Perfil(prf, true);
		// guardo la Perfil en bd
		em.persist(realObj);
		return realObj.getDatatype(true);
	}

	public void borrarPerfil(String idPerfil, DataTenant tenant) {
		DataPerfil prf = getPerfil(idPerfil, tenant);
		Perfil realObj = new Perfil(prf, true);
		realObj = em.merge(realObj);
		em.remove(realObj);
	}
}
