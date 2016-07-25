package lcbs.beans;

import javax.ejb.Stateless;
import lcbs.shares.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import lcbs.interfaces.EmpleadoLocalApi;
import lcbs.models.ConfiguracionEmpresa;
import lcbs.models.Cuponera;
import lcbs.models.Empleado;
import lcbs.models.Encomienda;
import lcbs.models.Usuario;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
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
    
    public List<DataEmpleado> obtenerEmpleados(Integer pagina, Integer elementosPagina){
    	List<DataEmpleado> empleados = new ArrayList();
        //obtengo todos los empleados de la bd
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Empleado.class);
    	criteria.add(Restrictions.eq("eliminado", false));
        criteria.setFirstResult((pagina - 1) * elementosPagina);
    	criteria.setMaxResults(elementosPagina);
        List<Empleado> listEmp = new ArrayList<Empleado>(new LinkedHashSet( criteria.list() ));
        
        listEmp.stream().forEach((emp) -> {
        	empleados.add(emp.getDatatype(true));
        });
        return empleados;
    }
    
    public void modificarEmpleado(DataEmpleado emp){
    	Empleado realObj = new Empleado(emp,true);
        if(em.find(Empleado.class, realObj.getId()) == null){
           throw new IllegalArgumentException("El empleado no existe");
       }
       em.merge(realObj);
    }
    
    public DataEmpleado getEmpleado(String id){
    	Session session = (Session) em.getDelegate();
    	Empleado realObj = (Empleado) session.get(Empleado.class, id);
		return realObj.getDatatype(true);
    }
    
    public boolean loginUsuario(String mailEmpleado, String clave){
    	Session session = (Session) em.getDelegate();
    	Criteria criteria = session.createCriteria(Empleado.class);
    	criteria.add(Restrictions.eq("email.email", mailEmpleado));
        List<Empleado> listEmp = new ArrayList<Empleado>(new LinkedHashSet( criteria.list() ));
        if(listEmp.size() == 1){
	    	DataEmpleado empleado = listEmp.get(0).getDatatype(true);
	    	if(empleado.getClave().equals(clave))
	        return true;
        }
        return false;
    }
    
    public DataEmpleado crearEmpleado(DataEmpleado emp){
    	Empleado realObj = new Empleado(emp,true);
    	realObj.setEliminado(false);
        //guardo al empleado en bd
        em.persist(realObj);
        return realObj.getDatatype(true);
    }
    
    public void darBajaEmpleado(String idEmpleado){
    	DataEmpleado emp = getEmpleado(idEmpleado);
    	emp.setEliminado(true);
        this.modificarEmpleado(emp);
    }
}
