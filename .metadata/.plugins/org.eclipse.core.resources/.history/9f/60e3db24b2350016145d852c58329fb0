package lcbs.beans;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import lcbs.interfaces.EmpleadoLocalApi;
import lcbs.models.Empleado;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Session Bean implementation class CuponeraSrv
 */
@Stateless
public class EmpleadoSrv implements EmpleadoLocalApi {
	@Inject
    EntityManager em;
	
    private EmpleadoSrv(){
        
    }
    
    public Map<String,Empleado> obtenerEmpleados(){
    	Map<String,Empleado> empleados = new HashMap();
        //obtengo todos los empleados de la bd
        Query query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
        
        List<Empleado> listEmp = query.getResultList();
        listEmp.stream().forEach((emp) -> {
        	empleados.put(emp.getId(), emp);
        });
        return empleados;
    }
    
    public void modificarEmpleado(Empleado emp){
        if(em.find(Empleado.class, emp.getId()) == null){
           throw new IllegalArgumentException("El empleado no existe");
       }
       em.getTransaction().begin();
       em.merge(emp);
       em.getTransaction().commit();
    }
    
    public Empleado getEmpleado(String id){
        return this.obtenerEmpleados().get(id);
    }
    
    public void crearCuponera(Empleado emp){
        //guardo al empleado en bd
        em.getTransaction().begin();
        em.persist(emp);
        em.getTransaction().commit();
    }
    
    public void darBajaEmpleado(Empleado emp){
        emp.setEliminado(true);
        this.modificarEmpleado(emp);
    }
}
