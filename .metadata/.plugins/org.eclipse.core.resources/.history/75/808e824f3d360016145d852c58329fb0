package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.TerminalLocalApi;
import lcbs.models.Terminal;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class TerminalSrv
 */
@Stateless
public class TerminalSrv implements TerminalLocalApi {
	@Inject
    EntityManager em;
	
    private TerminalSrv(){
        
    }
    
    public Map<String,Terminal> obtenerTerminals(){
    	Map<String,Terminal> terminales = new HashMap();
        //obtengo todas las terminales de la bd
        Query query = em.createQuery("SELECT t FROM Terminal t", Terminal.class);
        
        List<Terminal> listTer = query.getResultList();
        listTer.stream().forEach((ter) -> {
        	terminales.put(ter.getId(), ter);
        });
        return terminales;
    }
    
    public void modificarTerminal(Terminal ter){
        if(em.find(Terminal.class, ter.getId()) == null){
           throw new IllegalArgumentException("La terminal no existe");
       }
       em.getTransaction().begin();
       em.merge(ter);
       em.getTransaction().commit();
    }
    
    public Terminal getTerminal(String id){
        return this.obtenerTerminals().get(id);
    }
    
    public void crearTerminal(Terminal ter){
        //guardo la Terminal en bd
        em.getTransaction().begin();
        em.persist(ter);
        em.getTransaction().commit();
    }
    
    public void darBajaTerminal(Terminal ter){
        ter.setEliminado(true);
        this.modificarTerminal(ter);
    }
   
}