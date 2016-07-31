package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.RecorridoLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Pasaje;
import lcbs.models.Perfil;
import lcbs.models.Recorrido;
import lcbs.models.Viaje;
import lcbs.shares.DataRecorrido;
import lcbs.shares.DataViaje;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Session Bean implementation class RecorridoSrv
 */
@Stateless
public class RecorridoSrv implements RecorridoLocalApi {
	private static final Log log = LogFactory.getLog(TerminalSrv.class);
	@Inject
    EntityManager em;
	
	
	public static void log(String s){

		log.info("------->"+s);		
	}
    private RecorridoSrv(){
        
    }
    
    public List<DataRecorrido> obtenerRecorridos(Integer pagina, Integer elementosPagina){
    	List<DataRecorrido> Recorridos = new ArrayList();
        //obtengo todos los Recorridos de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Recorrido.class);
    	criteria.add(Restrictions.eq("eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
    	criteria.addOrder(Order.asc("nombre"));
        List<Recorrido> listRec = new ArrayList<Recorrido>(new LinkedHashSet( criteria.list() ));
        listRec.stream().forEach((rec) -> {
        	Recorridos.add(rec.getDatatype(true));
        });
        return Recorridos;
    }
    
    public List<DataRecorrido> BuscarRecorrido(DataRecorrido filtro, Integer pagina, Integer ElementosPagina) {
		List<DataRecorrido> recorridos = new ArrayList();
        Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Recorrido.class);
    	if(filtro.getNombre() != null)
    		criteria.add(Restrictions.eq("nombre", filtro.getNombre()));
    	if(filtro.genIdOrigen() != null && filtro.genIdDestino() != null)
    		criteria.createCriteria("puntosDeRecorrido").add(Restrictions.and(Restrictions.eq("id", filtro.genIdOrigen()),Restrictions.eq("id", filtro.genIdDestino())));
    	if(filtro.genTipoHorario() != null)
    		criteria.add(Restrictions.eq("horario.tipo", filtro.genTipoHorario()));
    	
    	criteria.setFirstResult((pagina - 1) * ElementosPagina);
    	criteria.setMaxResults(ElementosPagina);
    	
    	List<Recorrido> listRec = new ArrayList<Recorrido>(new LinkedHashSet( criteria.list() ));
    	listRec.stream().forEach((rec) -> {
    		recorridos.add(rec.getDatatype(true));
        });
        return recorridos;
	}
    
    public void modificarRecorrido(DataRecorrido rec){
    	Recorrido realObj = new Recorrido(rec);
        if(em.find(Recorrido.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El recorrido no existe");
       }
       em.merge(realObj);
    }
    
    public DataRecorrido getRecorrido(String id){
    	Session session = (Session) em.getDelegate();
    	Recorrido realObj = (Recorrido) session.get(Recorrido.class, id);
		return realObj.getDatatype(true);
    }
    
    public DataRecorrido crearRecorrido(DataRecorrido rec){
    	Recorrido realObj = new Recorrido(rec);
    	realObj.setEliminado(false);
        //guardo el Recorrido en bd
        em.persist(realObj);
        return realObj.getDatatype(true);
    }
    
    public void darBajaRecorrido(String idRecorrido){
    	DataRecorrido rec = getRecorrido(idRecorrido);
    	rec.setEliminado(true);
        this.modificarRecorrido(rec);
    }
}