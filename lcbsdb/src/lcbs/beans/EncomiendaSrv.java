package lcbs.beans;

import javax.ejb.Stateless;
import lcbs.shares.*;
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
    
    public Map<String,DataEncomienda> obtenerEncomiendas(){
    	Map<String,DataEncomienda> encomiendas = new HashMap();
        //obtengo todas las encomiendas de la bd
        Query query = em.createQuery("SELECT e FROM Encomienda e", Encomienda.class);
        
        List<Encomienda> listEnc = query.getResultList();
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
        return this.obtenerEncomiendas().get(id);
    }
    
    public void crearEncomienda(DataEncomienda enc){
    	Encomienda realObj = new Encomienda(enc);
        //guardo la encomienda en bd
        em.persist(realObj);
        System.out.println("#################################################################################### "+realObj.getId());
    }
    
    //TODO: Hacer busqueda con filtros
}
