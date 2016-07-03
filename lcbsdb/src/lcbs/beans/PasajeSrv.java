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
import lcbs.shares.DataPasaje;

import java.util.Map;
import java.util.HashMap;
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
    
    public Map<String,DataPasaje> obtenerPasajes(Integer pagina, Integer elementosPagina){
    	Map<String,DataPasaje> Pasajes = new HashMap();
        //obtengo todos los Pasajes de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Pasaje.class);
    	criteria.add(Restrictions.eq("eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Pasaje> listPsj = criteria.list();
        
        listPsj.stream().forEach((psj) -> {
        	Pasajes.put(psj.getId(), psj.getDatatype());
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

	@Override
	public Map<String, DataPasaje> obtenerPasajesPorPersona(String idUsuario) {
		Map<String,DataPasaje> Pasajes = new HashMap();
        //obtengo todos los Pasajes de la bd
        Query query = em.createQuery("SELECT p FROM Pasaje p", Pasaje.class);
        //TODO: Listar por usuario
        
        List<Pasaje> listPsj = query.getResultList();
        listPsj.stream().forEach((psj) -> {
        	Pasajes.put(psj.getId(), psj.getDatatype());
        });
        return Pasajes;
	}
}
