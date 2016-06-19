package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ParadaLocalApi;
import lcbs.models.Parada;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class ParadaSrv
 */
@Stateless
public class ParadaSrv implements ParadaLocalApi {
	@Inject
    EntityManager em;
	
    private ParadaSrv(){
        
    }
    
    public Map<String,Parada> obtenerParadas(){
    	Map<String,Parada> paradas = new HashMap();
        //obtengo todas las paradas de la bd
        Query query = em.createQuery("SELECT p FROM Parada p", Parada.class);
        
        List<Parada> listPds = query.getResultList();
        listPds.stream().forEach((prd) -> {
        	paradas.put(prd.getId(), prd);
        });
        return paradas;
    }
    
    public void modificarParada(Parada prd){
        if(em.find(Parada.class, prd.getId()) == null){
           throw new IllegalArgumentException("La parada no existe");
       }
       em.getTransaction().begin();
       em.merge(prd);
       em.getTransaction().commit();
    }
    
    public Parada getParada(String id){
        return this.obtenerParadas().get(id);
    }
    
    public void crearParada(Parada prd){
        //guardo la parada en bd
        em.getTransaction().begin();
        em.persist(prd);
        em.getTransaction().commit();
    }
    
    public void borrarParada(Parada prd){
        em.remove(prd);
    }
}
