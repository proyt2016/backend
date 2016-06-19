package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.EncomiendaLocalApi;
import lcbs.models.Encomienda;

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
    
    public Map<String,Encomienda> obtenerEncomiendas(){
    	Map<String,Encomienda> encomiendas = new HashMap();
        //obtengo todas las encomiendas de la bd
        Query query = em.createQuery("SELECT e FROM Encomienda e", Encomienda.class);
        
        List<Encomienda> listEnc = query.getResultList();
        listEnc.stream().forEach((enc) -> {
        	encomiendas.put(enc.getId(), enc);
        });
        return encomiendas;
    }
    
    public void modificarEncomienda(Encomienda enc){
        if(em.find(Encomienda.class, enc.getId()) == null){
           throw new IllegalArgumentException("La encomienda no existe");
       }
       em.getTransaction().begin();
       em.merge(enc);
       em.getTransaction().commit();
    }
    
    public Encomienda getEncomienda(String id){
        return this.obtenerEncomiendas().get(id);
    }
    
    public void crearEncomienda(Encomienda enc){
        //guardo la encomienda en bd
        em.persist(enc);
    }
    
    //TODO: Hacer busqueda con filtros
}
