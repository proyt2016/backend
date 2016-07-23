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
import lcbs.models.Terminal;
import lcbs.models.Viaje;
import lcbs.shares.DataReserva;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
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
    
    public List<DataReserva> obtenerReservas(Integer pagina, Integer elementosPagina){
    	List<DataReserva> reservas = new ArrayList();
        //obtengo todas las Reservas de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Reserva.class);
    	criteria.add(Restrictions.eq("eliminada", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Reserva> listRes = new ArrayList<Reserva>(new LinkedHashSet( criteria.list() ));
        
        listRes.stream().forEach((rec) -> {
        	reservas.add(rec.getDatatype());
        });
        return reservas;
    }
    
    public List<DataReserva> listarReservasPorUsuario(String idUsuario){
    	List<DataReserva> reservas = new ArrayList();
        //obtengo todas las Reservas de la bd
    	Session session = (Session) em.getDelegate();
        
        Criteria c = session.createCriteria(Reserva.class);
        c.add(Restrictions.eq("usuarioReserva.id", idUsuario)); //TODO: Fixear filtro
        List<Reserva> listRes = new ArrayList<Reserva>(new LinkedHashSet( c.list() ));
        
        listRes.stream().forEach((rec) -> {
        	reservas.add(rec.getDatatype());
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
    	realObj.setEliminada(false);
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