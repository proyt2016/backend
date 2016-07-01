package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.TerminalLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Reserva;
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
    
    public Map<String,DataTerminal> obtenerTerminals(Integer pagina, Integer elementosPagina){
    	Map<String,DataTerminal> terminales = new HashMap();
        //obtengo todas las terminales de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Terminal.class);
    	criteria.add(Restrictions.eq("Eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Terminal> listTer = criteria.list();
        
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
    	Session session = (Session) em.getDelegate();
    	Terminal realObj = (Terminal) session.get(Terminal.class, id);
		return realObj.getDatatype();
    }
    
    public DataTerminal crearTerminal(DataTerminal ter){
    	Terminal realObj = new Terminal(ter);
        //guardo la Terminal en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaTerminal(String idTerminal){
    	DataTerminal ter = getTerminal(idTerminal);
        ter.setEliminado(true);
        this.modificarTerminal(ter);
    }
   
}