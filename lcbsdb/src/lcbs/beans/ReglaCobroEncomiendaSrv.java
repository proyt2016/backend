package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ReglaCobroEncomiendaLocalApi;
import lcbs.models.ReglaCobroEncomienda;
import lcbs.shares.DataReglaCobroEncomienda;

import java.util.Map;
import java.util.HashMap;
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
    
    public Map<String,DataReglaCobroEncomienda> obtenerReglaCobroEncomiendas(){
    	Map<String,DataReglaCobroEncomienda> reglas = new HashMap();
        //obtengo todas las reglas de cobro de encomiendas de la bd
        Query query = em.createQuery("SELECT r FROM ReglaCobroEncomienda r", ReglaCobroEncomienda.class);
        
        List<ReglaCobroEncomienda> listRce = query.getResultList();
        listRce.stream().forEach((rce) -> {
        	reglas.put(rce.getId(), rce.getDatatype());
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
        return this.obtenerReglaCobroEncomiendas().get(id);
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
