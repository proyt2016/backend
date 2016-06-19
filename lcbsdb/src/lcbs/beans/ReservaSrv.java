package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ReservaLocalApi;
import lcbs.models.Reserva;
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
    
    public Map<String,DataReserva> obtenerReservas(){
    	Map<String,DataReserva> reservas = new HashMap();
        //obtengo todas las Reservas de la bd
        Query query = em.createQuery("SELECT r FROM Reserva r", Reserva.class);
        
        List<Reserva> listRes = query.getResultList();
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
        return this.obtenerReservas().get(id);
    }
    
    public void crearReserva(DataReserva res){
    	Reserva realObj = new Reserva(res);
        //guardo la reserva en bd
        em.persist(realObj);
    }
    
    public void darBajaReserva(DataReserva res){
        res.setEliminada(true);
        this.modificarReserva(res);
    }
    
    //TODO filtrar reservas por usuario
}