package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ReglaCobroEncomiendaLocalApi;
import lcbs.models.ReglaCobroEncomienda;

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
    
    public Map<String,ReglaCobroEncomienda> obtenerReglaCobroEncomiendas(){
    	Map<String,ReglaCobroEncomienda> reglas = new HashMap();
        //obtengo todas las reglas de cobro de encomiendas de la bd
        Query query = em.createQuery("SELECT r FROM ReglaCobroEncomienda r", ReglaCobroEncomienda.class);
        
        List<ReglaCobroEncomienda> listRce = query.getResultList();
        listRce.stream().forEach((rce) -> {
        	reglas.put(rce.getId(), rce);
        });
        return reglas;
    }
    
    public void modificarReglaCobroEncomienda(ReglaCobroEncomienda rce){
        if(em.find(ReglaCobroEncomienda.class, rce.getId()) == null){
           throw new IllegalArgumentException("La regla de cobro de encomiendas no existe");
       }
       em.getTransaction().begin();
       em.merge(rce);
       em.getTransaction().commit();
    }
    
    public ReglaCobroEncomienda getReglaCobroEncomienda(String id){
        return this.obtenerReglaCobroEncomiendas().get(id);
    }
    
    public void crearReglaCobroEncomienda(ReglaCobroEncomienda rce){
        //guardo la regla de cobro de encomiendas en bd
        em.getTransaction().begin();
        em.persist(rce);
        em.getTransaction().commit();
    }
    
    public void borrarReglaCobroEncomienda(ReglaCobroEncomienda rce){
        em.remove(rce);
    }
}
