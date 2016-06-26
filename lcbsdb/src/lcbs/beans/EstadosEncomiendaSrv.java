package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.EstadosEncomiendaLocalApi;
import lcbs.shares.DataEstadosEncomienda;
import lcbs.models.EstadosEncomienda;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class EstadosEncomiendaSrv
 */
@Stateless
public class EstadosEncomiendaSrv implements EstadosEncomiendaLocalApi {
	@Inject
    EntityManager em;
	
    private EstadosEncomiendaSrv(){
        
    }
    
    public Map<String,DataEstadosEncomienda> obtenerEstadosEncomienda(){
    	Map<String,DataEstadosEncomienda> estados = new HashMap();
        //obtengo todas los estados de encomienda de la bd
        Query query = em.createQuery("SELECT e FROM EstadosEncomienda e", EstadosEncomienda.class);
        
        List<EstadosEncomienda> listEstEnc = query.getResultList();
        listEstEnc.stream().forEach((est) -> {
        	estados.put(est.getId(), est.getDatatype());
        });
        return estados;
    }
    
    public void modificarEstadosEncomienda(DataEstadosEncomienda estEnc){
        EstadosEncomienda realObj = new EstadosEncomienda(estEnc);
    	if(em.find(EstadosEncomienda.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El estado no existe");
       }
       em.merge(realObj);
    }
    
    public DataEstadosEncomienda getEstadosEncomienda(String id){
        return this.obtenerEstadosEncomienda().get(id);
    }
    
    public DataEstadosEncomienda crearEstadosEncomienda(DataEstadosEncomienda est){
    	EstadosEncomienda realObj = new EstadosEncomienda(est);
        //guardo el estado de encomienda en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void borrarEstadosEncomienda(String idEst){
    	DataEstadosEncomienda est = getEstadosEncomienda(idEst);
    	EstadosEncomienda realObj = new EstadosEncomienda(est);
        em.remove(realObj);
    }
}
