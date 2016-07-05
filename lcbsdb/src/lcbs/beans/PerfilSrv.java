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
import java.util.ArrayList;
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
    
    public List<DataPerfil> obtenerPerfils(Integer pagina, Integer elementosPagina){
    	List<DataPerfil> Perfils = new ArrayList();
        //obtengo todos los Perfiles de la bd
        Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Perfil.class);
        
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
    	List<Perfil> listPds = criteria.list();
        listPds.stream().forEach((prf) -> {
        	Perfils.add(prf.getDatatype(true));
        });
        return Perfils;
    }
    
    public void modificarPerfil(DataPerfil prf){
    	Perfil realObj = new Perfil(prf,true);
        if(em.find(Perfil.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El perfil no existe");
       }
       em.merge(realObj);
    }
    
    public DataPerfil getPerfil(String id){
    	Session session = (Session) em.getDelegate();
		Perfil realObj = (Perfil) session.get(Perfil.class, id);
		return realObj.getDatatype(true);
    }
    
    public DataPerfil crearPerfil(DataPerfil prf){
    	Perfil realObj = new Perfil(prf,true);
        //guardo la Perfil en bd
        em.persist(realObj);
        return realObj.getDatatype(true);
    }
    
    public void borrarPerfil(String idPerfil){
    	DataPerfil prf = getPerfil(idPerfil);
    	Perfil realObj = new Perfil(prf,true);
    	realObj = em.merge(realObj);
        em.remove(realObj);
    }
}
