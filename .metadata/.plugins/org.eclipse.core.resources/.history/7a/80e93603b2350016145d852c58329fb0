package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.CuponeraLocalApi;
import lcbs.models.Cuponera;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless
public class CuponeraSrv implements CuponeraLocalApi {
	@Inject
    EntityManager em;
	
    private CuponeraSrv(){
        
    }
    
    public Map<String,Cuponera> obtenerCuponera(){
    	Map<String,Cuponera> cuponeras = new HashMap();
        //obtengo todas las cuponera de la bd
        Query query = em.createQuery("SELECT c FROM Cuponera c", Cuponera.class);
        
        List<Cuponera> listCup = query.getResultList();
        listCup.stream().forEach((cup) -> {
        	cuponeras.put(cup.getId(), cup);
        });
        return cuponeras;
    }
    
    public void modificarCuponera(Cuponera cup){
        if(em.find(Cuponera.class, cup.getId()) == null){
           throw new IllegalArgumentException("La cuponera no existe");
       }
       em.getTransaction().begin();
       em.merge(cup);
       em.getTransaction().commit();
    }
    
    public Cuponera getCuponera(String id){
        return this.obtenerCuponera().get(id);
    }
    
    public void crearCuponera(Cuponera cup){
        //guardo la cuponera en bd
        em.getTransaction().begin();
        em.persist(cup);
        em.getTransaction().commit();
    }
}
