package lcbs.beans;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ViajeLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Vehiculo;
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
    
    public Map<String,DataViaje> obtenerViajes(Integer pagina, Integer elementosPagina){
    	Map<String,DataViaje> Viajes = new HashMap();
        //obtengo todos los Viajes de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Viaje.class);
        
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Viaje> listTer = criteria.list();
        
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
    	Session session = (Session) em.getDelegate();
    	Viaje realObj = (Viaje) session.get(Viaje.class, id);
		return realObj.getDatatype();
    }
    
    public DataViaje crearViaje(DataViaje via){
    	Viaje realObj = new Viaje(via);
        //guardo el viaje en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void borrarViaje(String idViaje){
    	Viaje realObj = new Viaje(this.getViaje(idViaje));
        em.remove(realObj);
    }

	@Override
	public Map<String, DataViaje> buscarViaje(DataViaje filtro) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
   
}