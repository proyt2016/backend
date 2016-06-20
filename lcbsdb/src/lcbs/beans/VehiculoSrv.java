package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.VehiculoLocalApi;
import lcbs.models.Vehiculo;
import lcbs.shares.DataVehiculo;

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
    
    public Map<String,DataVehiculo> obtenerVehiculos(){
    	Map<String,DataVehiculo> vehiculos = new HashMap();
        //obtengo todos los vehiculos de la bd
        Query query = em.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class);
        
        List<Vehiculo> listVeh = query.getResultList();
        listVeh.stream().forEach((veh) -> {
        	vehiculos.put(veh.getId(), veh.getDatatype());
        });
        return vehiculos;
    }
    
    public void modificarVehiculo(DataVehiculo veh){
    	Vehiculo realObj = new Vehiculo(veh); 
        if(em.find(Vehiculo.class, realObj.getId()) == null){
           throw new IllegalArgumentException("La Vehiculo no existe");
       }
       em.merge(realObj);
    }
    
    public DataVehiculo getVehiculo(String id){
        return this.obtenerVehiculos().get(id);
    }
    
    public DataVehiculo crearVehiculo(DataVehiculo veh){
    	Vehiculo realObj = new Vehiculo(veh);
        //guardo el vehiculo en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaVehiculo(DataVehiculo veh){
        veh.setEliminado(true);
        this.modificarVehiculo(veh);
    }
   
}