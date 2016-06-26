package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import lcbs.interfaces.ParadaLocalApi;
import lcbs.models.GrupoHorario;
import lcbs.models.Parada;
import lcbs.shares.DataParada;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class ParadaSrv
 */
@Stateless
public class ParadaSrv implements ParadaLocalApi {
	@Inject
    EntityManager em;
	
    private ParadaSrv(){
        
    }
    
    public Map<String,DataParada> obtenerParadas(){
    	Map<String,DataParada> paradas = new HashMap();
        //obtengo todas las paradas de la bd
        Session session = (Session) em.getDelegate();
        List<Parada> listPds = session.createCriteria(Parada.class).list();
        
        listPds.stream().forEach((prd) -> {
        	paradas.put(prd.getId(), prd.getDatatype());
        });
        return paradas;
    }
    
    public void modificarParada(DataParada prd){
    	Parada realObj = new Parada(prd);
        if(em.find(Parada.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La parada no existe");
       }
       em.merge(realObj);
    }
    
    public DataParada getParada(String id){
        return this.obtenerParadas().get(id);
    }
    
    public DataParada crearParada(DataParada prd){
    	Parada realObj = new Parada(prd);
        //guardo la parada en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void borrarParada(String idParada){
    	DataParada prd = getParada(idParada);
    	Parada realOb = new Parada(prd);
        em.remove(realOb);
    }
}
