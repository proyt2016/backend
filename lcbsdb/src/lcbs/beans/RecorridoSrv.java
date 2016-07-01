package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.RecorridoLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Pasaje;
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
    
    public Map<String,DataRecorrido> obtenerRecorridos(Integer pagina, Integer elementosPagina){
    	Map<String,DataRecorrido> Recorridos = new HashMap();
        //obtengo todos los Recorridos de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Recorrido.class);
    	criteria.add(Restrictions.eq("Eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Recorrido> listRec = criteria.list();
        
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
    	Session session = (Session) em.getDelegate();
    	Recorrido realObj = (Recorrido) session.get(Recorrido.class, id);
		return realObj.getDatatype();
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