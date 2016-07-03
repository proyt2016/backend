package lcbs.beans;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ViajeLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Encomienda;
import lcbs.models.Vehiculo;
import lcbs.models.Viaje;
import lcbs.shares.DataEncomienda;
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
        	Viajes.put(via.getId(), via.getDatatype(true));
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
		return realObj.getDatatype(true);
    }
    
    public DataViaje crearViaje(DataViaje via){
    	Viaje realObj = new Viaje(via);
        //guardo el viaje en bd
        em.persist(realObj);
        return realObj.getDatatype(true);
    }
    
    public void borrarViaje(String idViaje){
    	Viaje realObj = new Viaje(this.getViaje(idViaje));
        em.remove(realObj);
    }

	@Override
	public Map<String, DataViaje> buscarViaje(DataViaje filtro, Integer pagina, Integer ElementosPagina) {
		Map<String, DataViaje> viajes = new HashMap();
        Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Encomienda.class);
    	if(filtro.getRecorrido() != null)
    		criteria.add(Restrictions.eq("recorrido.id", filtro.getRecorrido().getId()));
    	if(filtro.getHorario() != null)
    		criteria.add(Restrictions.eq("horario.id", filtro.getHorario().getId()));
    	if(filtro.getFechaSalida() != null)
    		criteria.add(Restrictions.eq("fechaSalida", filtro.getFechaSalida()));
    	//if(filtro.getEmpleados() != null)
    	//	criteria.createCriteria("empleados").add(Restrictions.eq("id", filtro.getEmpleados().getId()));
    	if(filtro.getCoche() != null)
    		criteria.add(Restrictions.eq("coche", filtro.getCoche().getId()));
    	//if(filtro.getEncomiendas() != null)
    	//	criteria.createCriteria("encomiendas").add(Restrictions.eq("id", filtro.getEncomiendas().getId()));
    	
    	//Asi se obtiene el numero de resultados
    	/*Criteria criteriaCount = criteria;
    	criteriaCount.setProjection(Projections.rowCount());
    	Long count = (Long) criteriaCount.uniqueResult();*/
    	
    	criteria.setFirstResult((pagina - 1) * ElementosPagina);
    	criteria.setMaxResults(ElementosPagina);
    	
        List<Viaje> listViaje = criteria.list();
        
        listViaje.stream().forEach((via) -> {
        	viajes.put(via.getId(), via.getDatatype(true));
        });
        return viajes;
	}
    
    
   
}