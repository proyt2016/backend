package lcbs.beans;

import javax.ejb.Stateless;
import lcbs.shares.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Column;

import lcbs.interfaces.EncomiendaLocalApi;
import lcbs.models.Encomienda;
import lcbs.models.Viaje;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless
public class EncomiendaSrv implements EncomiendaLocalApi {
	@Inject
    EntityManager em;
	
    private EncomiendaSrv(){
        
    }
    
    public Map<String,DataEncomienda> obtenerEncomiendas(){
    	Map<String,DataEncomienda> encomiendas = new HashMap();
        //obtengo todas las encomiendas de la bd
        Session session = (Session) em.getDelegate();
        List<Encomienda> listEnc = session.createCriteria(Encomienda.class).list();
        
        listEnc.stream().forEach((enc) -> {
        	encomiendas.put(enc.getId(), enc.getDatatype());
        });
        return encomiendas;
    }
    
    public void modificarEncomienda(DataEncomienda enc){
    	Encomienda realObj = new Encomienda(enc);
        if(em.find(Encomienda.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La encomienda no existe");
       }
       em.merge(realObj);
    }
    
    public DataEncomienda getEncomienda(String id){
		Session session = (Session) em.getDelegate();
		Encomienda realObj = (Encomienda) session.get(Encomienda.class, id);
		return realObj.getDatatype();
    }
    
    public DataEncomienda crearEncomienda(DataEncomienda enc){
    	Encomienda realObj = new Encomienda(enc);
        //guardo la encomienda en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaEncomienda(String idEncomienda){
    	DataEncomienda enc = getEncomienda(idEncomienda);
        enc.setEliminada(true);
        this.modificarEncomienda(enc);
    }
    
    public Map<String, DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina){
    	Map<String,DataEncomienda> encomiendas = new HashMap();
        Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Encomienda.class);
    	if(filtro.getOrigen() != null)
    		criteria.createCriteria("origen").add(Restrictions.eq("id", filtro.getOrigen().getId()));
    	if(filtro.getDestino() != null)
    		criteria.createCriteria("destino").add(Restrictions.eq("id", filtro.getDestino().getId()));
    	if(filtro.getEmisor() != null)
    		criteria.createCriteria("emisor").add(Restrictions.eq("id", filtro.getEmisor().getId()));
    	if(filtro.getReceptor() != null)
    		criteria.createCriteria("receptor").add(Restrictions.eq("id", filtro.getReceptor().getId()));
    	if(filtro.getEstadoActual() != null)
    		criteria.createCriteria("estadoActual").add(Restrictions.eq("id", filtro.getEstadoActual().getId()));
    	if(filtro.getCiEmisor() != null)
    		criteria.add(Restrictions.eq("ciEmisor", filtro.getCiEmisor()));
    	if(filtro.getCiReceptor() != null)
    		criteria.add(Restrictions.eq("ciReceptor", filtro.getCiReceptor()));
    	if(filtro.getFechaIngreso() != null)
    		criteria.add(Restrictions.eq("fechaIngreso", filtro.getFechaIngreso()));
    	if(filtro.getFechaEntrega() != null)
    		criteria.add(Restrictions.eq("fechaEntrega", filtro.getFechaEntrega()));
    	criteria.add(Restrictions.eq("retiraEnSucursal", filtro.getRetiraEnSucursal()));
    	
    	//Asi se obtiene el numero de resultados
    	/*Criteria criteriaCount = criteria;
    	criteriaCount.setProjection(Projections.rowCount());
    	Long count = (Long) criteriaCount.uniqueResult();*/
    	
    	criteria.setFirstResult((pagina - 1) * ElementosPagina);
    	criteria.setMaxResults(ElementosPagina);
    	
        List<Encomienda> listEnc = criteria.list();
        
        listEnc.stream().forEach((enc) -> {
        	encomiendas.put(enc.getId(), enc.getDatatype());
        });
        return encomiendas;
    }
    
    //TODO: Hacer busqueda con filtros
}
