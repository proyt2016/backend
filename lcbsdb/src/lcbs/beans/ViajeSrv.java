package lcbs.beans;

import javax.ejb.Stateless;
import org.hibernate.Session;

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
        Session session = (Session) em.getDelegate();
        List<Viaje> listTer = session.createCriteria(Viaje.class).list();
        
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
    
    public DataViaje crearViaje(DataViaje via){
    	Viaje realObj = new Viaje(via);
        //guardo el viaje en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void borrarViaje(DataViaje via){
    	Viaje realObj = new Viaje(via);
        em.remove(realObj);
    }

	@Override
	public Map<String, DataViaje> buscarViaje(DataViaje filtro) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
   
}