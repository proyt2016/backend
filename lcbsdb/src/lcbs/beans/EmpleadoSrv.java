package lcbs.beans;

import javax.ejb.Stateless;
import lcbs.shares.*;
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
    
    public Map<String,DataEmpleado> obtenerEmpleados(){
    	Map<String,DataEmpleado> empleados = new HashMap();
        //obtengo todos los empleados de la bd
        Query query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
        
        List<Empleado> listEmp = query.getResultList();
        listEmp.stream().forEach((emp) -> {
        	empleados.put(emp.getId(), emp.getDatatype());
        });
        return empleados;
    }
    
    public void modificarEmpleado(DataEmpleado emp){
    	Empleado realObj = new Empleado(emp);
        if(em.find(Empleado.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El empleado no existe");
       }
       em.merge(realObj);
    }
    
    public DataEmpleado getEmpleado(String id){
        return this.obtenerEmpleados().get(id);
    }
    
    public DataEmpleado crearEmpleado(DataEmpleado emp){
    	Empleado realObj = new Empleado(emp);
        //guardo al empleado en bd
        em.persist(emp);
        return realObj.getDatatype();
    }
    
    public void darBajaEmpleado(DataEmpleado emp){
    	emp.setEliminado(true);
        this.modificarEmpleado(emp);
    }
}
