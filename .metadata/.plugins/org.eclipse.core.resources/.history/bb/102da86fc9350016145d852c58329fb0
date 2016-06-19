package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.PasajeLocalApi;
import lcbs.models.Pasaje;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class PasajeSrv
 */
@Stateless
public class PasajeSrv implements PasajeLocalApi {
	@Inject
    EntityManager em;
	
    private PasajeSrv(){
        
    }
    
    public Map<String,Pasaje> obtenerPasajes(){
    	Map<String,Pasaje> Pasajes = new HashMap();
        //obtengo todos los Pasajes de la bd
        Query query = em.createQuery("SELECT p FROM Pasaje p", Pasaje.class);
        
        List<Pasaje> listPsj = query.getResultList();
        listPsj.stream().forEach((psj) -> {
        	Pasajes.put(psj.getId(), psj);
        });
        return Pasajes;
    }
    
    public void modificarPasaje(Pasaje psj){
        if(em.find(Pasaje.class, psj.getId()) == null){
           throw new IllegalArgumentException("El Pasaje no existe");
       }
       em.getTransaction().begin();
       em.merge(psj);
       em.getTransaction().commit();
    }
    
    public Pasaje getPasaje(String id){
        return this.obtenerPasajes().get(id);
    }
    
    public void crearPasaje(Pasaje psj){
        //guardo el Pasaje en bd
        em.getTransaction().begin();
        em.persist(psj);
        em.getTransaction().commit();
    }
    
    public void darBajaPasaje(Pasaje psj){
        psj.setEliminado(true);
        this.modificarPasaje(psj);
    }
}
