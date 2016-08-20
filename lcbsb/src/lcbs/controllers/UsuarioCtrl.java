package lcbs.controllers;



import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import interfaces.IUsuario;
import lcbs.interfaces.*;
import lcbs.shares.*;

import javax.ejb.Stateless;




/**
 * Session Bean implementation class UsuarioSrv
 */
@Stateless
public class UsuarioCtrl implements IUsuario{

	@EJB(lookup="java:app/lcbsdb/UsuarioSrv!lcbs.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;
	
	@EJB(lookup="java:app/lcbsdb/EmpleadoSrv!lcbs.interfaces.EmpleadoLocalApi")
	EmpleadoLocalApi srvEmpleado;
	
	@EJB(lookup="java:app/lcbsdb/CuponeraSrv!lcbs.interfaces.CuponeraLocalApi")
	CuponeraLocalApi srvCuponera;
	
	@EJB(lookup="java:app/lcbsdb/PerfilSrv!lcbs.interfaces.PerfilLocalApi")
	PerfilLocalApi srvPerfil;

	@Override
	public DataUsuario AltaUsuario(DataUsuario usuario) {
		DataCuponera cuponera = new DataCuponera();
		cuponera.setSaldo(0);
		usuario.setCuponera(cuponera);
		return srvUsuario.crearUsuario(usuario);
	}

	@Override
	public void ModificarUsuario(DataUsuario usuario) {
		srvUsuario.modificarUsuario(usuario);
	}
	
	@Override
	public void CargarSaldoCuponera(String idUsuario, Float saldo){
		DataUsuario usuario = srvUsuario.getUsuario(idUsuario);
		DataCuponera cuponera = usuario.getCuponera();
		cuponera.setSaldo(cuponera.getSaldo() + saldo);
		srvCuponera.modificarCuponera(cuponera);
	}
	
	@Override
	public List<DataNotificacion> listarNotificaciones(String idUsuario){
		DataUsuario usuario = srvUsuario.getUsuario(idUsuario);
		return usuario.getNotificaciones();
	}

	@Override
	public void BajaUsuario(String idUsuario) {
		srvUsuario.darBajaUsuario(idUsuario);
	}

	@Override
	public DataEmpleado AltaEmpleado(DataEmpleado empleado) {
		return srvEmpleado.crearEmpleado(empleado);
	}

	@Override
	public void ModificarEmpleado(DataEmpleado empleado) {
		srvEmpleado.modificarEmpleado(empleado);
		
	}

	@Override
	public void BajaEmpleado(String idEmpleado) {
		srvEmpleado.darBajaEmpleado(idEmpleado);
	}
	
	@Override
	public DataPerfil AltaPerfil(DataPerfil perfil){
		return srvPerfil.crearPerfil(perfil);
	}
	
	@Override
	public void EditarPerfil(DataPerfil perfil){
		srvPerfil.modificarPerfil(perfil);
	}
	
	@Override
	public void EliminarPerfil(String idPerfil){
		srvPerfil.borrarPerfil(idPerfil);
	}
	
	@Override
	public void AsignarPerfil(String idEmpleado, String perfil){
		DataEmpleado empleado = srvEmpleado.getEmpleado(idEmpleado);
		DataPerfil perf = srvPerfil.getPerfil(perfil);
		empleado.setPerfil(perf);
		srvEmpleado.modificarEmpleado(empleado);
		List<DataEmpleado> empList = perf.getEmpleados();
		empList.add(empList.size(), empleado);
		perf.setEmpleados(empList);
		srvPerfil.modificarPerfil(perf);
	}

	@Override
	public DataUsuario loginUsuario(String usuario, String clave) {
		return srvUsuario.loginUsuario(usuario, clave);
	}

	@Override
	public DataUsuario getUsuario(String idUsuario) {
		return srvUsuario.getUsuario(idUsuario);
	}

	@Override
	public DataEmpleado loginEmpleado(String mail, String clave) {
		return srvEmpleado.loginUsuario(mail, clave);
	}

	@Override
	public DataEmpleado getEmpleado(String idEmpleado) {
		return srvEmpleado.getEmpleado(idEmpleado);
	}
	
	@Override
	public List<DataEmpleado> listarEmpleados(Integer pagina, Integer elementosPagina) {
		return srvEmpleado.obtenerEmpleados(pagina, elementosPagina);
	}

	@Override
	public DataPerfil getPerfil(String idPerfil) {
		return srvPerfil.getPerfil(idPerfil);
	}

	@Override
	public List<DataPerfil> listarPerfiles(Integer pagina, Integer elementosPagina) {
		return srvPerfil.obtenerPerfils(pagina, elementosPagina);
	}

	@Override
	public List<DataUsuario> listarUsuarios(Integer pagina, Integer elementosPagina) {
		return srvUsuario.obtenerUsuarios(pagina, elementosPagina);
	}

	@Override
	public DataUsuario buscarUsuarioPorMail(String mailUsuario) {
		return srvUsuario.buscarUsuarioPorMail(mailUsuario);
	}
	
	
}
