package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ViajeLocalApi;
import lcbs.models.Perfil;
import lcbs.models.Viaje;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class ViajeSrv
 */
@Stateless
public class ViajeSrv implements ViajeLocalApi {
	@Inject
    EntityManager em;
	
    private ViajeSrv(){
        
    }
    
    public Map<String,Viaje> obtenerViajes(){
    	Map<String,Viaje> Viajes = new HashMap();
        //obtengo todos los Viajes de la bd
        Query query = em.createQuery("SELECT v FROM Viaje v", Viaje.class);
        
        List<Viaje> listTer = query.getResultList();
        listTer.stream().forEach((via) -> {
        	Viajes.put(via.getId(), via);
        });
        return Viajes;
    }
    
    public void modificarViaje(Viaje via){
        if(em.find(Viaje.class, via.getId()) == null){
           throw new IllegalArgumentException("El viaje no existe");
       }
       em.getTransaction().begin();
       em.merge(via);
       em.getTransaction().commit();
    }
    
    public Viaje getViaje(String id){
        return this.obtenerViajes().get(id);
    }
    
    public void crearViaje(Viaje via){
        //guardo el viaje en bd
        em.getTransaction().begin();
        em.persist(via);
        em.getTransaction().commit();
    }
    
    public void borrarViaje(Viaje via){
        em.remove(via);
    }
    
    //TODO Busqueda de viaje por atrubutos
    
   
}