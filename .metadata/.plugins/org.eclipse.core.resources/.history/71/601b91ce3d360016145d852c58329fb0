package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.VehiculoLocalApi;
import lcbs.models.Vehiculo;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class VehiculoSrv
 */
@Stateless
public class VehiculoSrv implements VehiculoLocalApi {
	@Inject
    EntityManager em;
	
    private VehiculoSrv(){
        
    }
    
    public Map<String,Vehiculo> obtenerVehiculos(){
    	Map<String,Vehiculo> vehiculos = new HashMap();
        //obtengo todos los vehiculos de la bd
        Query query = em.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class);
        
        List<Vehiculo> listVeh = query.getResultList();
        listVeh.stream().forEach((veh) -> {
        	vehiculos.put(veh.getId(), veh);
        });
        return vehiculos;
    }
    
    public void modificarVehiculo(Vehiculo veh){
        if(em.find(Vehiculo.class, veh.getId()) == null){
           throw new IllegalArgumentException("La Vehiculo no existe");
       }
       em.getTransaction().begin();
       em.merge(veh);
       em.getTransaction().commit();
    }
    
    public Vehiculo getVehiculo(String id){
        return this.obtenerVehiculos().get(id);
    }
    
    public void crearVehiculo(Vehiculo veh){
        //guardo el vehiculo en bd
        em.getTransaction().begin();
        em.persist(veh);
        em.getTransaction().commit();
    }
    
    public void darBajaVehiculo(Vehiculo veh){
        veh.setEliminado(true);
        this.modificarVehiculo(veh);
    }
   
}