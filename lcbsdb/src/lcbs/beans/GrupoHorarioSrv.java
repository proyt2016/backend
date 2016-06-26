package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import lcbs.interfaces.GrupoHorarioLocalApi;
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
    
    public Map<String,DataGrupoHorario> obtenerGrupoHorario(){
    	Map<String,DataGrupoHorario> grupos = new HashMap();
        //obtengo todos los grupos de horarios de la bd
        Session session = (Session) em.getDelegate();
        List<GrupoHorario> listGrupHor = session.createCriteria(GrupoHorario.class).list();
        
        listGrupHor.stream().forEach((grp) -> {
        	grupos.put(grp.getId(), grp.getDatatype());
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
        return this.obtenerGrupoHorario().get(id);
    }
    
    public DataGrupoHorario crearGrupoHorario(DataGrupoHorario grp){
    	GrupoHorario realObj = new GrupoHorario(grp);
        //guardo el grupo de horarios en bd
        em.persist(grp);
        return realObj.getDatatype();
    }
    
    public void borrarGrupoHorario(String idGrp){
    	DataGrupoHorario grp = getGrupoHorario(idGrp);
    	GrupoHorario realObj = new GrupoHorario(grp);
        em.remove(realObj);
    }
}
