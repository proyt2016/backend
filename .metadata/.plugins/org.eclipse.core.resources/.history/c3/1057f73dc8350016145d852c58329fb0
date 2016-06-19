package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.EstadosEncomiendaLocalApi;
import lcbs.models.EstadosEncomienda;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class EstadosEncomiendaSrv
 */
@Stateless
public class EstadosEncomiendaSrv implements EstadosEncomiendaLocalApi {
	@Inject
    EntityManager em;
	
    private EstadosEncomiendaSrv(){
        
    }
    
    public Map<String,EstadosEncomienda> obtenerEstadosEncomienda(){
    	Map<String,EstadosEncomienda> estados = new HashMap();
        //obtengo todas los estados de encomienda de la bd
        Query query = em.createQuery("SELECT e FROM EstadosEncomienda e", EstadosEncomienda.class);
        
        List<EstadosEncomienda> listEstEnc = query.getResultList();
        listEstEnc.stream().forEach((est) -> {
        	estados.put(est.getId(), est);
        });
        return estados;
    }
    
    public void modificarEstadosEncomienda(EstadosEncomienda estEnc){
        if(em.find(EstadosEncomienda.class, estEnc.getId()) == null){
           throw new IllegalArgumentException("El estado no existe");
       }
       em.getTransaction().begin();
       em.merge(estEnc);
       em.getTransaction().commit();
    }
    
    public EstadosEncomienda getEstadosEncomienda(String id){
        return this.obtenerEstadosEncomienda().get(id);
    }
    
    public void crearEstadosEncomienda(EstadosEncomienda est){
        //guardo el estado de encomienda en bd
        em.getTransaction().begin();
        em.persist(est);
        em.getTransaction().commit();
    }
    
    public void borrarEstadosEncomienda(EstadosEncomienda emp){
        em.remove(emp);
    }
}
