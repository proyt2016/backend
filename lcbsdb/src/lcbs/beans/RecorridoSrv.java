package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.RecorridoLocalApi;
import lcbs.models.Recorrido;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class RecorridoSrv
 */
@Stateless
public class RecorridoSrv implements RecorridoLocalApi {
	@Inject
    EntityManager em;
	
    private RecorridoSrv(){
        
    }
    
    public Map<String,Recorrido> obtenerRecorridos(){
    	Map<String,Recorrido> Recorridos = new HashMap();
        //obtengo todos los Recorridos de la bd
        Query query = em.createQuery("SELECT r FROM Recorrido r", Recorrido.class);
        
        List<Recorrido> listRec = query.getResultList();
        listRec.stream().forEach((rec) -> {
        	Recorridos.put(rec.getId(), rec);
        });
        return Recorridos;
    }
    
    public void modificarRecorrido(Recorrido rec){
        if(em.find(Recorrido.class, rec.getId()) == null){
           throw new IllegalArgumentException("El recorrido no existe");
       }
       em.getTransaction().begin();
       em.merge(rec);
       em.getTransaction().commit();
    }
    
    public Recorrido getRecorrido(String id){
        return this.obtenerRecorridos().get(id);
    }
    
    public void crearRecorrido(Recorrido rec){
        //guardo el Recorrido en bd
        em.getTransaction().begin();
        em.persist(rec);
        em.getTransaction().commit();
    }
    
    public void darBajaRecorrido(Recorrido rec){
        rec.setEliminado(true);
        this.modificarRecorrido(rec);
    }
}