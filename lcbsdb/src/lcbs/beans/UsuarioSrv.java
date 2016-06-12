package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.UsuarioLocalApi;
import lcbs.models.Usuario;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class UsuarioSrv
 */
@Stateless
public class UsuarioSrv implements UsuarioLocalApi {
	@Inject
    EntityManager em;
	
    private UsuarioSrv(){
        
    }
    
    public Map<String,Usuario> obtenerUsuarios(){
    	Map<String,Usuario> usuarios = new HashMap();
        //obtengo todos los usuarios de la bd
        Query query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        
        List<Usuario> listUsu = query.getResultList();
        listUsu.stream().forEach((usu) -> {
        	usuarios.put(usu.getId(), usu);
        });
        return usuarios;
    }
    
    public void modificarUsuario(Usuario usu){
        if(em.find(Usuario.class, usu.getId()) == null){
           throw new IllegalArgumentException("El usuario no existe");
       }
       em.getTransaction().begin();
       em.merge(usu);
       em.getTransaction().commit();
    }
    
    public Usuario getUsuario(String id){
        return this.obtenerUsuarios().get(id);
    }
    
    public void crearUsuario(Usuario usu){
        //guardo el usuario en bd
        em.getTransaction().begin();
        em.persist(usu);
        em.getTransaction().commit();
    }
    
    public void darBajaUsuario(Usuario usu){
        usu.setEliminado(true);
        this.modificarUsuario(usu);
    }
   
}