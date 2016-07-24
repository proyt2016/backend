package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.PasajeLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.GrupoHorario;
import lcbs.models.Parada;
import lcbs.models.Pasaje;
import lcbs.models.Viaje;
import lcbs.shares.DataPasaje;
import lcbs.shares.DataPasajeConvertor;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Session Bean implementation class PasajeSrv
 */
@Stateless
public class PasajeSrv implements PasajeLocalApi {
	@Inject
    EntityManager em;
	
    private PasajeSrv(){
        
    }
    
    public List<DataPasajeConvertor> obtenerPasajes(Integer pagina, Integer elementosPagina){
    	List<DataPasajeConvertor> Pasajes = new ArrayList();
        //obtengo todos los Pasajes de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Pasaje.class);
    	criteria.add(Restrictions.eq("eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
    	
    	List<Pasaje> listPsj = new ArrayList<Pasaje>(new LinkedHashSet( criteria.list() ));
        
        listPsj.stream().forEach((psj) -> {
        	Pasajes.add(psj.getDatatype().genConvertor());
        });
        return Pasajes;
    }
    
    public void modificarPasaje(DataPasaje psj){
        Pasaje realObj = new Pasaje(psj);
    	if(em.find(Pasaje.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El Pasaje no existe");
       }
       em.merge(realObj);
    }
    
    public DataPasaje getPasaje(String id){
    	Session session = (Session) em.getDelegate();
    	Pasaje realObj = (Pasaje) session.get(Pasaje.class, id);
		return realObj.getDatatype();
    }
    
    public DataPasaje getpasajeXcodigo(int codigoPasaje) {
    	List<DataPasaje> Pasajes = new ArrayList();
        //obtengo todos los Pasajes de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Pasaje.class);
    	criteria.add(Restrictions.eq("eliminado", false));
    	criteria.add(Restrictions.eq("codigoPasaje", codigoPasaje));
    	List<Pasaje> listPsj = new ArrayList<Pasaje>(new LinkedHashSet( criteria.list() ));
        listPsj.stream().forEach((psj) -> {
        	Pasajes.add(psj.getDatatype());
        });
        return Pasajes.get(0);
	}
    
    public DataPasaje crearPasaje(DataPasaje psj){
    	Pasaje realObj = new Pasaje(psj);
    	realObj.setEliminado(false);
        //guardo el Pasaje en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaPasaje(String idPasaje){
    	DataPasaje psj = getPasaje(idPasaje);
        psj.setEliminado(true);
        this.modificarPasaje(psj);
    }

	public List<DataPasaje> obtenerPasajesPorPersona(String idUsuario, Integer pagina, Integer elementosPagina) {
		List<DataPasaje> Pasajes = new ArrayList();
        //obtengo todos los Pasajes de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Pasaje.class);
    	criteria.add(Restrictions.eq("eliminado", false));
    	criteria.add(Restrictions.eq("comprador.id", idUsuario));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
    	List<Pasaje> listPsj = new ArrayList<Pasaje>(new LinkedHashSet( criteria.list() ));
        
        listPsj.stream().forEach((psj) -> {
        	Pasajes.add(psj.getDatatype());
        });
        return Pasajes;
	}
}
