package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;

import lcbs.interfaces.CuponeraLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Cuponera;
import lcbs.models.Perfil;
import lcbs.shares.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless
public class CuponeraSrv implements CuponeraLocalApi {
	@Inject
    EntityManager em;
	
    private CuponeraSrv(){
        
    }
    
    public Map<String,DataCuponera> obtenerCuponera(Integer pagina, Integer elementosPagina){
    	Map<String,DataCuponera> cuponeras = new HashMap();
        //obtengo todas las cuponera de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Cuponera.class);
        
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Cuponera> listCup = criteria.list();
        
        listCup.stream().forEach((cup) -> {
        	cuponeras.put(cup.getId(), cup.getDatatype());
        });
        return cuponeras;
    }
    
    public void modificarCuponera(DataCuponera cup){
    	Cuponera realObj = new Cuponera(cup);
        if(em.find(Cuponera.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La cuponera no existe");
       }
       em.merge(realObj);
    }
    
    public DataCuponera getCuponera(String id){
    	Session session = (Session) em.getDelegate();
		Cuponera realObj = (Cuponera) session.get(Cuponera.class, id);
		return realObj.getDatatype();
    }
    
    public DataCuponera crearCuponera(DataCuponera cup){
    	Cuponera realObj = new Cuponera(cup);
        //guardo la cuponera en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
}
