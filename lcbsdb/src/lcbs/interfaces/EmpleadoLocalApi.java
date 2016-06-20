package lcbs.interfaces;

import java.util.Map;

import javax.ejb.Local;

import lcbs.shares.DataEmpleado;

@Local
public interface EmpleadoLocalApi {
	public Map<String,DataEmpleado> obtenerEmpleados();
	public void modificarEmpleado(DataEmpleado emp);
	public DataEmpleado getEmpleado(String id);
	public DataEmpleado crearCuponera(DataEmpleado emp);
	public void darBajaEmpleado(DataEmpleado emp);
}
