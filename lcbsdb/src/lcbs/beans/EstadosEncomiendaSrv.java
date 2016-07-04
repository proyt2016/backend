package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;

import lcbs.interfaces.EstadosEncomiendaLocalApi;
import lcbs.shares.DataEstadosEncomienda;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Cuponera;
import lcbs.models.Empleado;
import lcbs.models.EstadosEncomienda;

import java.util.Map;
import java.util.ArrayList;
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
    
    public List<DataEstadosEncomienda> obtenerEstadosEncomienda(Integer pagina, Integer elementosPagina){
    	List<DataEstadosEncomienda> estados = new ArrayList();
        //obtengo todas los estados de encomienda de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(EstadosEncomienda.class);
        
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<EstadosEncomienda> listEstEnc = criteria.list();
        
        listEstEnc.stream().forEach((est) -> {
        	estados.add(est.getDatatype());
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
    	Session session = (Session) em.getDelegate();
    	EstadosEncomienda realObj = (EstadosEncomienda) session.get(EstadosEncomienda.class, id);
		return realObj.getDatatype();
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
