package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.UsuarioLocalApi;
import lcbs.models.Usuario;
import lcbs.shares.DataUsuario;

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
    
    public Map<String,DataUsuario> obtenerUsuarios(){
    	Map<String,DataUsuario> usuarios = new HashMap();
        //obtengo todos los usuarios de la bd
        Query query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        
        List<Usuario> listUsu = query.getResultList();
        listUsu.stream().forEach((usu) -> {
        	usuarios.put(usu.getId(), usu.getDatatype());
        });
        return usuarios;
    }
    
    public void modificarUsuario(DataUsuario usu){
    	Usuario realObj = new Usuario(usu);
        if(em.find(Usuario.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El usuario no existe");
       }
       em.merge(realObj);
    }
    
    public DataUsuario getUsuario(String id){
        return this.obtenerUsuarios().get(id);
    }
    
    public DataUsuario crearUsuario(DataUsuario usu){
    	Usuario realObj = new Usuario(usu);
        //guardo el usuario en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaUsuario(String idUsuario){
    	DataUsuario usu = getUsuario(idUsuario);
        usu.setEliminado(true);
        this.modificarUsuario(usu);
    }
   
}