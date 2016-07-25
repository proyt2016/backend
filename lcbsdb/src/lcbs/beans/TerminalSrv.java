package lcbs.beans;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.TerminalLocalApi;
import lcbs.models.Terminal;
import lcbs.models.Usuario;
import lcbs.shares.DataPuntoRecorrido;
import lcbs.shares.DataTerminal;

import java.util.ArrayList;

/**
 * Session Bean implementation class TerminalSrv
 */
@Stateless
public class TerminalSrv implements TerminalLocalApi {
	private static final Log log = LogFactory.getLog(TerminalSrv.class);
	@Inject
    EntityManager em;
	
    private TerminalSrv(){
        
    }
    
    public List<DataTerminal> obtenerTerminals(Integer pagina, Integer elementosPagina){
    	List<DataTerminal> terminales = new ArrayList();   
        //obtengo todas las terminales de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Terminal.class);
    	criteria.add(Restrictions.eq("eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Terminal> listTer = new ArrayList<Terminal>(new LinkedHashSet( criteria.list() ));
        listTer.stream().forEach((ter) -> {
        	terminales.add(ter.getDatatype());
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
    	realObj.setEliminado(false);
        //guardo la Terminal en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaTerminal(String idTerminal){
    	DataTerminal ter = getTerminal(idTerminal);
        ter.setEliminado(true);
        this.modificarTerminal(ter);
    }
	@Override
	public DataPuntoRecorrido getTerminalPorCoordenada(String coord) {
		List<DataTerminal> terminales = new ArrayList(); 
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Terminal.class);
    	criteria.add(Restrictions.eq("eliminado", false));
    	criteria.add(Restrictions.eq("ubicacionMapa", coord));
        List<Terminal> listTer = new ArrayList<Terminal>(new LinkedHashSet( criteria.list() ));
        
        listTer.stream().forEach((ter) -> {
        	terminales.add(ter.getDatatype());
        });
        return terminales.size() > 0 ? terminales.get(0) : null;
	}
   
}