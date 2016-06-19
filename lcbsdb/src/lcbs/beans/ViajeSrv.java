package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ViajeLocalApi;
import lcbs.models.Viaje;
import lcbs.shares.DataViaje;

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
    
    public Map<String,DataViaje> obtenerViajes(){
    	Map<String,DataViaje> Viajes = new HashMap();
        //obtengo todos los Viajes de la bd
        Query query = em.createQuery("SELECT v FROM Viaje v", Viaje.class);
        
        List<Viaje> listTer = query.getResultList();
        listTer.stream().forEach((via) -> {
        	Viajes.put(via.getId(), via.getDatatype());
        });
        return Viajes;
    }
    
    public void modificarViaje(DataViaje via){
    	Viaje realObj = new Viaje(via);
        if(em.find(Viaje.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El viaje no existe");
       }
       em.merge(realObj);
    }
    
    public DataViaje getViaje(String id){
        return this.obtenerViajes().get(id);
    }
    
    public void crearViaje(DataViaje via){
    	Viaje realObj = new Viaje(via);
        //guardo el viaje en bd
        em.persist(realObj);
    }
    
    public void borrarViaje(DataViaje via){
    	Viaje realObj = new Viaje(via);
        em.remove(realObj);
    }
   
}