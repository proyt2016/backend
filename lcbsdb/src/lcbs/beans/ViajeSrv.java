package lcbs.beans;

import javax.ejb.Stateless;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ViajeLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Encomienda;
import lcbs.models.Recorrido;
import lcbs.models.Vehiculo;
import lcbs.models.Viaje;
import lcbs.shares.DataEncomienda;
import lcbs.shares.DataViaje;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

/**
 * Session Bean implementation class ViajeSrv
 */
@Stateless
public class ViajeSrv implements ViajeLocalApi {
	@Inject
    EntityManager em;
	private static final Log log = LogFactory.getLog(ViajeSrv.class);
    private ViajeSrv(){
        
    }
    
    public List<DataViaje> obtenerViajes(Integer pagina, Integer elementosPagina){
    	List<DataViaje> Viajes = new ArrayList();
        //obtengo todos los Viajes de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Viaje.class);
        
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
    	List<Viaje> listTer = new ArrayList<Viaje>(new LinkedHashSet( criteria.list() ));
        
        listTer.stream().forEach((via) -> {
        	Viajes.add(via.getDatatype(true));
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
    
    public List<DataViaje> viajesPorTerminal(String idterminal, Integer pagina, Integer ElementosPagina){
    	List<DataViaje> viajes = new ArrayList();
        Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Viaje.class, "viaje");
    	criteria.createAlias("viaje.recorrido", "puntos");
    	criteria.createCriteria("puntos.puntosDeRecorrido").add(Restrictions.eq("id", idterminal));
    	//criteria.add(Restrictions.eq("puntos.id", idterminal));
    	
    	criteria.setFirstResult((pagina - 1) * ElementosPagina);
    	criteria.setMaxResults(ElementosPagina);
    	
        List<Viaje> listViaje = new ArrayList<Viaje>(new LinkedHashSet( criteria.list() ));
        
        listViaje.stream().forEach((via) -> {
        	viajes.add(via.getDatatype(true));
        });
        return viajes;
    }

	public List<DataViaje> buscarViaje(DataViaje filtro, Integer pagina, Integer ElementosPagina){
		List<DataViaje> viajes = new ArrayList();
		
		try{
		
	        Session session = (Session) em.getDelegate();
	    	Criteria criteria = session.createCriteria(Viaje.class);
	    	if(filtro.getRecorrido() != null)
	    		criteria.add(Restrictions.eq("recorrido.id", filtro.getRecorrido().getId()));
	    	if(filtro.getHorario() != null)
	    		criteria.add(Restrictions.eq("horario.id", filtro.getHorario().getId()));
	    	if(filtro.getFechaSalida() != null){
	    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String myDate = new SimpleDateFormat("yyyy-MM-dd").format(filtro.getFechaSalida());
	    		Date fromDate = df.parse(myDate+" 00:00:00");
	    		Date toDate = df.parse(myDate+" 23:59:59");
	
	    		criteria.add(Restrictions.between("fechaSalida", fromDate, toDate));
	    	}
	    	//if(filtro.getEmpleados() != null)
	    	//	criteria.createCriteria("empleados").add(Restrictions.eq("id", filtro.getEmpleados().getId()));
	    	
	    	//if(filtro.getEncomiendas() != null)
	    	//	criteria.createCriteria("encomiendas").add(Restrictions.eq("id", filtro.getEncomiendas().getId()));
	    	
	    	
	    	DetachedCriteria subquery = DetachedCriteria.forClass(Viaje.class);
	    	if(filtro.getRecorrido() != null)
	    		subquery.add(Restrictions.eq("recorrido.id", filtro.getRecorrido().getId()));
	    	if(filtro.getHorario() != null)
	    		subquery.add(Restrictions.eq("horario.id", filtro.getHorario().getId()));
	    	if(filtro.getFechaSalida() != null){
	    		
	    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String myDate = new SimpleDateFormat("yyyy-MM-dd").format(filtro.getFechaSalida());
	    		Date fromDate = df.parse(myDate+" 00:00:00");
	    		Date toDate = df.parse(myDate+" 23:59:59");
	
	    		subquery.add(Restrictions.between("fechaSalida", fromDate, toDate));
	    	}
	    	if(filtro.genIdOrigen() != null)
	    		subquery.createCriteria("recorrido").createCriteria("puntosDeRecorrido").add(Restrictions.eq("id", filtro.genIdOrigen()));
	    	subquery.setProjection(Projections.property("id"));
	    	if(filtro.genIdDestino() != null)
	    		criteria.createCriteria("recorrido").createCriteria("puntosDeRecorrido").add(Restrictions.eq("id", filtro.genIdDestino()));
	    	
	    	criteria.add(Subqueries.propertyIn("id", subquery));
	    	criteria.setFirstResult((pagina - 1) * ElementosPagina);
	    	criteria.setMaxResults(ElementosPagina);
	    	
	    	List<Viaje> listViaje = new ArrayList<Viaje>(new LinkedHashSet( criteria.list() ));
	        
	        listViaje.stream().forEach((via) -> {
	        	viajes.add(via.getDatatype(true));
	        });
		}catch(Exception e){
			log.info("#################################################################### "+e.getMessage());
		}
        return viajes;
	}
    
    
   
}