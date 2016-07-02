package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;

import lcbs.interfaces.PerfilLocalApi;
import lcbs.models.Encomienda;
import lcbs.models.Pasaje;
import lcbs.models.Perfil;
import lcbs.shares.DataPerfil;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class PerfilSrv
 */
@Stateless
public class PerfilSrv implements PerfilLocalApi {
	@Inject
    EntityManager em;
	
    private PerfilSrv(){
        
    }
    
    public Map<String,DataPerfil> obtenerPerfils(Integer pagina, Integer elementosPagina){
    	Map<String,DataPerfil> Perfils = new HashMap();
        //obtengo todos los Perfiles de la bd
        Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Perfil.class);
        
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
    	List<Perfil> listPds = criteria.list();
        listPds.stream().forEach((prf) -> {
        	Perfils.put(prf.getId(), prf.getDatatype());
        });
        return Perfils;
    }
    
    public void modificarPerfil(DataPerfil prf){
    	Perfil realObj = new Perfil(prf);
        if(em.find(Perfil.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El perfil no existe");
       }
       em.merge(realObj);
    }
    
    public DataPerfil getPerfil(String id){
    	Session session = (Session) em.getDelegate();
		Perfil realObj = (Perfil) session.get(Perfil.class, id);
		return realObj.getDatatype();
    }
    
    public DataPerfil crearPerfil(DataPerfil prf){
    	Perfil realObj = new Perfil(prf);
        //guardo la Perfil en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void borrarPerfil(String idPerfil){
    	DataPerfil prf = getPerfil(idPerfil);
    	Perfil realObj = new Perfil(prf);
        em.remove(realObj);
    }
}
