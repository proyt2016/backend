package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import lcbs.interfaces.PerfilLocalApi;
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
    
    public Map<String,DataPerfil> obtenerPerfils(){
    	Map<String,DataPerfil> Perfils = new HashMap();
        //obtengo todos los Perfiles de la bd
        Session session = (Session) em.getDelegate();
        List<Perfil> listPds = session.createCriteria(Perfil.class).list();
        
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
        return this.obtenerPerfils().get(id);
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
