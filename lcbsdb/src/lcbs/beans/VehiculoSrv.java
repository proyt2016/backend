package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.VehiculoLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Terminal;
import lcbs.models.Usuario;
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
    
    public Map<String,DataVehiculo> obtenerVehiculos(Integer pagina, Integer elementosPagina){
    	Map<String,DataVehiculo> vehiculos = new HashMap();
        //obtengo todos los vehiculos de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Vehiculo.class);
    	criteria.add(Restrictions.eq("Eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Vehiculo> listVeh = criteria.list();
        
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
    	Session session = (Session) em.getDelegate();
    	Vehiculo realObj = (Vehiculo) session.get(Vehiculo.class, id);
		return realObj.getDatatype();
    }
    
    public DataVehiculo crearVehiculo(DataVehiculo veh){
    	Vehiculo realObj = new Vehiculo(veh);
        //guardo el vehiculo en bd
        em.persist(realObj);
        return realObj.getDatatype();
    }
    
    public void darBajaVehiculo(String idVehiculo){
    	DataVehiculo veh = getVehiculo(idVehiculo);
        veh.setEliminado(true);
        this.modificarVehiculo(veh);
    }
   
}