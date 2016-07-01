package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;

import lcbs.interfaces.ParadaLocalApi;
import lcbs.models.ConfiguracionEmpresa;
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
    
    public Map<String,DataParada> obtenerParadas(Integer pagina, Integer elementosPagina){
    	Map<String,DataParada> paradas = new HashMap();
        //obtengo todas las paradas de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Parada.class);
        
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Parada> listPds = criteria.list();
        
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
    	Session session = (Session) em.getDelegate();
    	Parada realObj = (Parada) session.get(Parada.class, id);
		return realObj.getDatatype();
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
