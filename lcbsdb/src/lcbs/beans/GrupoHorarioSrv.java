package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.GrupoHorarioLocalApi;
import lcbs.models.GrupoHorario;

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
    
    public Map<String,GrupoHorario> obtenerGrupoHorario(){
    	Map<String,GrupoHorario> grupos = new HashMap();
        //obtengo todos los grupos de horarios de la bd
        Query query = em.createQuery("SELECT g FROM GrupoHorario g", GrupoHorario.class);
        
        List<GrupoHorario> listGrupHor = query.getResultList();
        listGrupHor.stream().forEach((grp) -> {
        	grupos.put(grp.getId(), grp);
        });
        return grupos;
    }
    
    public void modificarGrupoHorario(GrupoHorario grHor){
        if(em.find(GrupoHorario.class, grHor.getId()) == null){
           throw new IllegalArgumentException("El grupo horario no existe");
       }
       em.getTransaction().begin();
       em.merge(grHor);
       em.getTransaction().commit();
    }
    
    public GrupoHorario getGrupoHorario(String id){
        return this.obtenerGrupoHorario().get(id);
    }
    
    public void crearGrupoHorario(GrupoHorario grp){
        //guardo el grupo de horarios en bd
        em.getTransaction().begin();
        em.persist(grp);
        em.getTransaction().commit();
    }
    
    public void borrarGrupoHorario(GrupoHorario grp){
        em.remove(grp);
    }
}
