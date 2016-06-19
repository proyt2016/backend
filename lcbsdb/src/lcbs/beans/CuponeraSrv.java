package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.CuponeraLocalApi;
import lcbs.models.Cuponera;
import lcbs.shares.*;

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
    
    public Map<String,DataCuponera> obtenerCuponera(){
    	Map<String,DataCuponera> cuponeras = new HashMap();
        //obtengo todas las cuponera de la bd
        Query query = em.createQuery("SELECT c FROM Cuponera c", Cuponera.class);
        
        List<Cuponera> listCup = query.getResultList();
        listCup.stream().forEach((cup) -> {
        	cuponeras.put(cup.getId(), cup.getDatatype());
        });
        return cuponeras;
    }
    
    public void modificarCuponera(DataCuponera cup){
    	Cuponera realObj = new Cuponera(cup);
        if(em.find(Cuponera.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La cuponera no existe");
       }
       em.merge(realObj);
    }
    
    public DataCuponera getCuponera(String id){
        return this.obtenerCuponera().get(id);
    }
    
    public void crearCuponera(DataCuponera cup){
    	Cuponera realObj = new Cuponera(cup);
        //guardo la cuponera en bd
        em.persist(realObj);
    }
}
