package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;

import lcbs.interfaces.ReglaCobroEncomiendaLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Recorrido;
import lcbs.models.ReglaCobroEncomienda;
import lcbs.models.Reserva;
import lcbs.shares.DataReglaCobroEncomienda;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Session Bean implementation class ReglaCobroEncomiendaSrv
 */
@Stateless
public class ReglaCobroEncomiendaSrv implements ReglaCobroEncomiendaLocalApi {
	@Inject
    EntityManager em;
	
    private ReglaCobroEncomiendaSrv(){
        
    }
    
    public List<DataReglaCobroEncomienda> obtenerReglaCobroEncomiendas(Integer pagina, Integer elementosPagina){
    	List<DataReglaCobroEncomienda> reglas = new ArrayList();
        //obtengo todas las reglas de cobro de encomiendas de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(ReglaCobroEncomienda.class);
        
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
    	List<ReglaCobroEncomienda> listRce = new ArrayList<ReglaCobroEncomienda>(new LinkedHashSet( criteria.list() ));
        
        listRce.stream().forEach((rce) -> {
        	reglas.add(rce.getDatatype());
        });
        return reglas;
    }
    
    public void modificarReglaCobroEncomienda(DataReglaCobroEncomienda rce){
    	ReglaCobroEncomienda realObj = new ReglaCobroEncomienda(rce);
        if(em.find(ReglaCobroEncomienda.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La regla de cobro de encomiendas no existe");
       }
       em.merge(realObj);
    }
    
    public DataReglaCobroEncomienda getReglaCobroEncomienda(String id){
    	Session session = (Session) em.getDelegate();
    	ReglaCobroEncomienda realObj = (ReglaCobroEncomienda) session.get(ReglaCobroEncomienda.class, id);
		return realObj.getDatatype();
    }
    
    public DataReglaCobroEncomienda crearReglaCobroEncomienda(DataReglaCobroEncomienda rce){
    	ReglaCobroEncomienda realObj = new ReglaCobroEncomienda(rce);
        //guardo la regla de cobro de encomiendas en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void borrarReglaCobroEncomienda(String idRce){
    	ReglaCobroEncomienda realObj = new ReglaCobroEncomienda(getReglaCobroEncomienda(idRce));
        em.remove(realObj);
    }
}
