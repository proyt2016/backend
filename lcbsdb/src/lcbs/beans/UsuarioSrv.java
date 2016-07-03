package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.UsuarioLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Reserva;
import lcbs.models.Terminal;
import lcbs.models.Usuario;
import lcbs.shares.DataEmpleado;
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
    
    public Map<String,DataUsuario> obtenerUsuarios(Integer pagina, Integer elementosPagina){
    	Map<String,DataUsuario> usuarios = new HashMap();
        //obtengo todos los usuarios de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Usuario.class);
    	criteria.add(Restrictions.eq("eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Usuario> listUsu = criteria.list();
        
        listUsu.stream().forEach((usu) -> {
        	usuarios.put(usu.getId(), usu.getDatatype(true));
        });
        return usuarios;
    }
    
    public boolean loginUsuario(String mailUsuario, String clave){
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Usuario.class);
    	criteria.add(Restrictions.eq("email.email", mailUsuario));
        List<Usuario> listUsu = criteria.list();
        if(listUsu.size() == 1){
	    	DataUsuario usuario = listUsu.get(0).getDatatype(true);
	        return usuario.getClave().equals(clave);
        }
        return false;
    }
    
    public void modificarUsuario(DataUsuario usu){
    	Usuario realObj = new Usuario(usu);
        if(em.find(Usuario.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El usuario no existe");
       }
       em.merge(realObj);
    }
    
    public DataUsuario getUsuario(String id){
    	Session session = (Session) em.getDelegate();
    	Usuario realObj = (Usuario) session.get(Usuario.class, id);
		return realObj.getDatatype(true);
    }
    
    public DataUsuario crearUsuario(DataUsuario usu){
    	Usuario realObj = new Usuario(usu);
    	realObj.setEliminado(false);
        //guardo el usuario en bd
        em.persist(realObj);
        return realObj.getDatatype(true);
    }
    
    public void darBajaUsuario(String idUsuario){
    	DataUsuario usu = getUsuario(idUsuario);
        usu.setEliminado(true);
        this.modificarUsuario(usu);
    }
   
}