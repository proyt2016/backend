package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.PerfilLocalApi;
import lcbs.models.Perfil;

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
    
    public Map<String,Perfil> obtenerPerfils(){
    	Map<String,Perfil> Perfils = new HashMap();
        //obtengo todos los Perfiles de la bd
        Query query = em.createQuery("SELECT p FROM Perfil p", Perfil.class);
        
        List<Perfil> listPds = query.getResultList();
        listPds.stream().forEach((prf) -> {
        	Perfils.put(prf.getId(), prf);
        });
        return Perfils;
    }
    
    public void modificarPerfil(Perfil prf){
        if(em.find(Perfil.class, prf.getId()) == null){
           throw new IllegalArgumentException("El perfil no existe");
       }
       em.getTransaction().begin();
       em.merge(prf);
       em.getTransaction().commit();
    }
    
    public Perfil getPerfil(String id){
        return this.obtenerPerfils().get(id);
    }
    
    public void crearPerfil(Perfil prf){
        //guardo la Perfil en bd
        em.getTransaction().begin();
        em.persist(prf);
        em.getTransaction().commit();
    }
    
    public void borrarPerfil(Perfil prf){
        em.remove(prf);
    }
}
