package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.ReservaLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.ReglaCobroEncomienda;
import lcbs.models.Reserva;
import lcbs.models.Viaje;
import lcbs.shares.DataReserva;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class ReservaSrv
 */
@Stateless
public class ReservaSrv implements ReservaLocalApi {
	@Inject
    EntityManager em;
	
    private ReservaSrv(){
        
    }
    
    public Map<String,DataReserva> obtenerReservas(Integer pagina, Integer elementosPagina){
    	Map<String,DataReserva> reservas = new HashMap();
        //obtengo todas las Reservas de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Reserva.class);
    	criteria.add(Restrictions.eq("Eliminada", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Reserva> listRes = criteria.list();
        
        listRes.stream().forEach((rec) -> {
        	reservas.put(rec.getId(), rec.getDatatype());
        });
        return reservas;
    }
    
    public Map<String,DataReserva> listarReservasPorUsuario(String idUsuario){
    	Map<String,DataReserva> reservas = new HashMap();
        //obtengo todas las Reservas de la bd
    	Session session = (Session) em.getDelegate();
        
        Criteria c = session.createCriteria(Reserva.class);
        c.add(Restrictions.eq("usuario.id", idUsuario)); //TODO: Fixear filtro
        List<Reserva> listRes = c.list();
        
        listRes.stream().forEach((rec) -> {
        	reservas.put(rec.getId(), rec.getDatatype());
        });
        return reservas;
    }
    
    public void modificarReserva(DataReserva res){
    	Reserva realObj = new Reserva(res);
        if(em.find(Reserva.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La reserva no existe");
       }
       em.merge(realObj);
    }
    
    public DataReserva getReserva(String id){
    	Session session = (Session) em.getDelegate();
    	Reserva realObj = (Reserva) session.get(Reserva.class, id);
		return realObj.getDatatype();
    }
    
    public DataReserva crearReserva(DataReserva res){
    	Reserva realObj = new Reserva(res);
        //guardo la reserva en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaReserva(String idReserva){
    	DataReserva res = getReserva(idReserva);
        res.setEliminada(true);
        this.modificarReserva(res);
    }
    
    //TODO filtrar reservas por usuario
}