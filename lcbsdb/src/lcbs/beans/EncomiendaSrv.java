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
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Encomienda;
import lcbs.models.EstadosEncomienda;
import lcbs.models.Viaje;

import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.Date;

/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless
public class EncomiendaSrv implements EncomiendaLocalApi {
	@Inject
    EntityManager em;
	
    private EncomiendaSrv(){
        
    }
    
    public List<DataEncomiendaConvertor> obtenerEncomiendas(Integer pagina, Integer elementosPagina){
    	List<DataEncomiendaConvertor> encomiendas = new ArrayList();
        //obtengo todas las encomiendas de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Encomienda.class);
    	criteria.add(Restrictions.eq("eliminada", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Encomienda> listEnc = new ArrayList<Encomienda>(new LinkedHashSet( criteria.list() ));
        
        listEnc.stream().forEach((enc) -> {
        	encomiendas.add(enc.getDatatype(true).genConvertor());
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
		return realObj.getDatatype(true);
    }
    
    public DataEncomienda crearEncomienda(DataEncomienda enc){
    	Encomienda realObj = new Encomienda(enc);
    	realObj.setEliminada(false);
    	realObj.setFechaIngreso(new Date());
        //guardo la encomienda en bd
        em.persist(realObj);
        return realObj.getDatatype(true);
    }
    
    public void darBajaEncomienda(String idEncomienda){
    	DataEncomienda enc = getEncomienda(idEncomienda);
        enc.setEliminada(true);
        this.modificarEncomienda(enc);
    }
    
    public List<DataEncomienda> buscarEncomienda(DataEncomienda filtro, Integer pagina, Integer ElementosPagina){
    	List<DataEncomienda> encomiendas = new ArrayList();
    	try{
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
	    	if(filtro.getFechaIngreso() != null){
	    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String myDate = new SimpleDateFormat("yyyy-MM-dd").format(filtro.getFechaIngreso());
	    		Date fromDate = df.parse(myDate+" 00:00:00");
	    		Date toDate = df.parse(myDate+" 23:59:59");
	
	    		criteria.add(Restrictions.between("fechaIngreso", fromDate, toDate));
	    	}
	    	if(filtro.getFechaEntrega() != null){
	    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		String myDate = new SimpleDateFormat("yyyy-MM-dd").format(filtro.getFechaEntrega());
	    		Date fromDate = df.parse(myDate+" 00:00:00");
	    		Date toDate = df.parse(myDate+" 23:59:59");
	
	    		criteria.add(Restrictions.between("fechaEntrega", fromDate, toDate));
	    	}
	    	criteria.add(Restrictions.eq("retiraEnSucursal", filtro.getRetiraEnSucursal()));
	    	
	    	//Asi se obtiene el numero de resultados
	    	/*Criteria criteriaCount = criteria;
	    	criteriaCount.setProjection(Projections.rowCount());
	    	Long count = (Long) criteriaCount.uniqueResult();*/
	    	
	    	criteria.setFirstResult((pagina - 1) * ElementosPagina);
	    	criteria.setMaxResults(ElementosPagina);
	    	
	        List<Encomienda> listEnc = new ArrayList<Encomienda>(new LinkedHashSet( criteria.list() ));
	        
	        listEnc.stream().forEach((enc) -> {
	        	encomiendas.add(enc.getDatatype(true));
	        });
    	}catch(Exception e){
			//log.info("#################################################################### "+e.getMessage());
		}
        return encomiendas;
    }

	@Override
	public List<DataEncomienda> listarEncomiendasPorUsuario(String idUsuario, Integer pagina, Integer elementosPagina) {
		List<DataEncomienda> encomiendas = new ArrayList();
        //obtengo todas las encomiendas de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Encomienda.class);
    	criteria.add(Restrictions.eq("eliminada", false));
    	criteria.add(Restrictions.or(Restrictions.eq("emisor.id", idUsuario),Restrictions.eq("receptor.id", idUsuario)));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Encomienda> listEnc = new ArrayList<Encomienda>(new LinkedHashSet( criteria.list() ));
        
        listEnc.stream().forEach((enc) -> {
        	encomiendas.add(enc.getDatatype(true));
        });
        return encomiendas;
	}
    
}
