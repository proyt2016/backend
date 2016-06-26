package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import lcbs.interfaces.RecorridoLocalApi;
import lcbs.models.Perfil;
import lcbs.models.Recorrido;
import lcbs.shares.DataRecorrido;

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
    
    public Map<String,DataRecorrido> obtenerRecorridos(){
    	Map<String,DataRecorrido> Recorridos = new HashMap();
        //obtengo todos los Recorridos de la bd
        Session session = (Session) em.getDelegate();
        List<Recorrido> listRec = session.createCriteria(Recorrido.class).list();
        
        listRec.stream().forEach((rec) -> {
        	Recorridos.put(rec.getId(), rec.getDatatype());
        });
        return Recorridos;
    }
    
    public void modificarRecorrido(DataRecorrido rec){
    	Recorrido realObj = new Recorrido(rec);
        if(em.find(Recorrido.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El recorrido no existe");
       }
       em.merge(realObj);
    }
    
    public DataRecorrido getRecorrido(String id){
        return this.obtenerRecorridos().get(id);
    }
    
    public DataRecorrido crearRecorrido(DataRecorrido rec){
    	Recorrido realObj = new Recorrido(rec);
        //guardo el Recorrido en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaRecorrido(String idRecorrido){
    	DataRecorrido rec = getRecorrido(idRecorrido);
    	rec.setEliminado(true);
        this.modificarRecorrido(rec);
    }
}