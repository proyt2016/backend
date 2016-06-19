package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.TerminalLocalApi;
import lcbs.models.Terminal;
import lcbs.shares.DataTerminal;

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
    
    public Map<String,DataTerminal> obtenerTerminals(){
    	Map<String,DataTerminal> terminales = new HashMap();
        //obtengo todas las terminales de la bd
        Query query = em.createQuery("SELECT t FROM Terminal t", Terminal.class);
        
        List<Terminal> listTer = query.getResultList();
        listTer.stream().forEach((ter) -> {
        	terminales.put(ter.getId(), ter.getDatatype());
        });
        return terminales;
    }
    
    public void modificarTerminal(DataTerminal ter){
    	Terminal realObj = new Terminal(ter);
        if(em.find(Terminal.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La terminal no existe");
       }
       em.merge(realObj);
    }
    
    public DataTerminal getTerminal(String id){
        return this.obtenerTerminals().get(id);
    }
    
    public void crearTerminal(DataTerminal ter){
    	Terminal realObj = new Terminal(ter);
        //guardo la Terminal en bd
        em.persist(realObj);
    }
    
    public void darBajaTerminal(DataTerminal ter){
        ter.setEliminado(true);
        this.modificarTerminal(ter);
    }
   
}