package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import lcbs.interfaces.CuponeraLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Cuponera;
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
    
    public Map<String,DataCuponera> obtenerCuponera(){
    	Map<String,DataCuponera> cuponeras = new HashMap();
        //obtengo todas las cuponera de la bd
        Session session = (Session) em.getDelegate();
        List<Cuponera> listCup = session.createCriteria(Cuponera.class).list();
        
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
        return this.obtenerCuponera().get(id);
    }
    
    public DataCuponera crearCuponera(DataCuponera cup){
    	Cuponera realObj = new Cuponera(cup);
        //guardo la cuponera en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
}
