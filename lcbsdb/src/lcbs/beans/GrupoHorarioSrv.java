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
import lcbs.interfaces.GrupoHorarioLocalApi;
import lcbs.models.GrupoHorario;
import lcbs.shares.DataGrupoHorario;
import lcbs.shares.DataTenant;

/**
 * Session Bean implementation class GrupoHorarioSrv
 */
@Stateless
@Interceptors ({TenantIntercept.class})
public class GrupoHorarioSrv implements GrupoHorarioLocalApi {
	@Inject
	EntityManager em;

	public GrupoHorarioSrv() {

	}

	public List<DataGrupoHorario> obtenerGrupoHorario(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		List<DataGrupoHorario> grupos = new ArrayList();
		// obtengo todos los grupos de horarios de la bd
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(GrupoHorario.class);

		criteria.setFirstResult((pagina - 1) * elementosPagina);
		criteria.setMaxResults(elementosPagina);
		List<GrupoHorario> listGrupHor = new ArrayList<GrupoHorario>(new LinkedHashSet(criteria.list()));

		listGrupHor.stream().forEach((grp) -> {
			grupos.add(grp.getDatatype(true));
		});
		return grupos;
	}

	public void modificarGrupoHorario(DataGrupoHorario grHor, DataTenant tenant) {
		GrupoHorario realObj = new GrupoHorario(grHor);
		if (em.find(GrupoHorario.class, realObj.getId()) == null) {
			throw new IllegalArgumentException("El grupo horario no existe");
		}
		em.merge(realObj);
	}

	public DataGrupoHorario getGrupoHorario(String id, DataTenant tenant) {
		Session session = (Session) em.getDelegate();
		GrupoHorario realObj = (GrupoHorario) session.get(GrupoHorario.class, id);
		return realObj.getDatatype(true);
	}

	public DataGrupoHorario crearGrupoHorario(DataGrupoHorario grp, DataTenant tenant) {
		GrupoHorario realObj = new GrupoHorario(grp);
		// guardo el grupo de horarios en bd
		em.persist(grp);
		return realObj.getDatatype(true);
	}

	public void borrarGrupoHorario(String idGrp, DataTenant tenant) {
		DataGrupoHorario grp = getGrupoHorario(idGrp, tenant);
		GrupoHorario realObj = new GrupoHorario(grp);
		em.remove(realObj);
	}
}
