package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.ParadaLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.GrupoHorario;
import lcbs.models.Parada;
import lcbs.models.Perfil;
import lcbs.shares.DataParada;
import lcbs.shares.DataPuntoRecorrido;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Session Bean implementation class ParadaSrv
 */
@Stateless
public class ParadaSrv implements ParadaLocalApi {
	@Inject
    EntityManager em;
	
    private ParadaSrv(){
        
    }
    
    public List<DataParada> obtenerParadas(Integer pagina, Integer elementosPagina){
    	List<DataParada> paradas = new ArrayList();
        //obtengo todas las paradas de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Parada.class);
    	criteria.add(Restrictions.eq("eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Parada> listPds = new ArrayList<Parada>(new LinkedHashSet( criteria.list() ));
        
        listPds.stream().forEach((prd) -> {
        	paradas.add(prd.getDatatype());
        });
        return paradas;
    }
    
    public void modificarParada(DataParada prd){
    	Parada realObj = new Parada(prd);
        if(em.find(Parada.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La parada no existe");
       }
       em.merge(realObj);
    }
    
    public DataParada getParada(String id){
    	Session session = (Session) em.getDelegate();
    	Parada realObj = (Parada) session.get(Parada.class, id);
		return realObj.getDatatype();
    }
    
    public DataParada crearParada(DataParada prd){
    	Parada realObj = new Parada(prd);
    	realObj.setEliminado(false);
        //guardo la parada en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void borrarParada(String idParada){
    	DataParada prd = getParada(idParada);
    	Parada realOb = new Parada(prd);
        em.remove(realOb);
    }

	@Override
	public DataPuntoRecorrido getParadaPorCoordenada(String coord) {
		List<DataParada> paradas = new ArrayList();
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Parada.class);
    	criteria.add(Restrictions.eq("eliminado", false));
    	criteria.add(Restrictions.eq("ubicacionMapa", coord));
        List<Parada> listPds = new ArrayList<Parada>(new LinkedHashSet( criteria.list() ));
        
        listPds.stream().forEach((prd) -> {
        	paradas.add(prd.getDatatype());
        });
        return paradas.size() > 0 ? paradas.get(0) : null;
	}
}
