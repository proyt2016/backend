package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.models.Empleado;

@Local
public interface EmpleadoLocalApi {
	public Map<String,Empleado> obtenerEmpleados();
	public void modificarEmpleado(Empleado emp);
	public Empleado getEmpleado(String id);
	public void crearCuponera(Empleado emp);
	public void darBajaEmpleado(Empleado emp);
}
