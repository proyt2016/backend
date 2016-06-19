package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.ReservaLocalApi;
import lcbs.models.Reserva;

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
    
    public Map<String,Reserva> obtenerReservas(){
    	Map<String,Reserva> reservas = new HashMap();
        //obtengo todas las Reservas de la bd
        Query query = em.createQuery("SELECT r FROM Reserva r", Reserva.class);
        
        List<Reserva> listRes = query.getResultList();
        listRes.stream().forEach((rec) -> {
        	reservas.put(rec.getId(), rec);
        });
        return reservas;
    }
    
    public void modificarReserva(Reserva res){
        if(em.find(Reserva.class, res.getId()) == null){
           throw new IllegalArgumentException("La reserva no existe");
       }
       em.getTransaction().begin();
       em.merge(res);
       em.getTransaction().commit();
    }
    
    public Reserva getReserva(String id){
        return this.obtenerReservas().get(id);
    }
    
    public void crearReserva(Reserva res){
        //guardo la reserva en bd
        em.getTransaction().begin();
        em.persist(res);
        em.getTransaction().commit();
    }
    
    public void darBajaReserva(Reserva res){
        res.setEliminada(true);
        this.modificarReserva(res);
    }
    
    //TODO filtrar reservas por usuario
}