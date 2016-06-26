package lcbs.beans;

import javax.ejb.Stateless;
import lcbs.shares.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import lcbs.interfaces.EmpleadoLocalApi;
import lcbs.models.Cuponera;
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
        Session session = (Session) em.getDelegate();
        List<Empleado> listEmp = session.createCriteria(Empleado.class).list();
        
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
    
    public void darBajaEmpleado(String idEmpleado){
    	DataEmpleado emp = getEmpleado(idEmpleado);
    	emp.setEliminado(true);
        this.modificarEmpleado(emp);
    }
}
