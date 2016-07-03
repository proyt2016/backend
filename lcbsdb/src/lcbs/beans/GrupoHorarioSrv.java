package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;

import lcbs.interfaces.GrupoHorarioLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Empleado;
import lcbs.models.EstadosEncomienda;
import lcbs.models.GrupoHorario;
import lcbs.shares.DataGrupoHorario;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class GrupoHorarioSrv
 */
@Stateless
public class GrupoHorarioSrv implements GrupoHorarioLocalApi {
	@Inject
    EntityManager em;
	
    private GrupoHorarioSrv(){
        
    }
    
    public Map<String,DataGrupoHorario> obtenerGrupoHorario(Integer pagina, Integer elementosPagina){
    	Map<String,DataGrupoHorario> grupos = new HashMap();
        //obtengo todos los grupos de horarios de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(GrupoHorario.class);
        
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<GrupoHorario> listGrupHor = criteria.list();
        
        listGrupHor.stream().forEach((grp) -> {
        	grupos.put(grp.getId(), grp.getDatatype(true));
        });
        return grupos;
    }
    
    public void modificarGrupoHorario(DataGrupoHorario grHor){
        GrupoHorario realObj = new GrupoHorario(grHor);
    	if(em.find(GrupoHorario.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El grupo horario no existe");
       }
       em.merge(realObj);
    }
    
    public DataGrupoHorario getGrupoHorario(String id){
    	Session session = (Session) em.getDelegate();
    	GrupoHorario realObj = (GrupoHorario) session.get(GrupoHorario.class, id);
		return realObj.getDatatype(true);
    }
    
    public DataGrupoHorario crearGrupoHorario(DataGrupoHorario grp){
    	GrupoHorario realObj = new GrupoHorario(grp);
        //guardo el grupo de horarios en bd
        em.persist(grp);
        return realObj.getDatatype(true);
    }
    
    public void borrarGrupoHorario(String idGrp){
    	DataGrupoHorario grp = getGrupoHorario(idGrp);
    	GrupoHorario realObj = new GrupoHorario(grp);
        em.remove(realObj);
    }
}
