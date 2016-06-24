package lcbs.controllers;



import java.util.List;

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
	public void CargarSaldoCuponera(DataUsuario usuario, Float saldo){
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
	public void BajaUsuario(DataUsuario usuario) {
		srvUsuario.darBajaUsuario(usuario);
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
	public void BajaEmpleado(DataEmpleado empleado) {
		srvEmpleado.darBajaEmpleado(empleado);
	}
	
	@Override
	public void AltaPerfil(DataPerfil perfil){
		srvPerfil.crearPerfil(perfil);
	}
	
	@Override
	public void EditarPerfil(DataPerfil perfil){
		srvPerfil.modificarPerfil(perfil);
	}
	
	@Override
	public void EliminarPerfil(DataPerfil perfil){
		srvPerfil.borrarPerfil(perfil);
	}
	
	@Override
	public void AsignarPerfil(String idEmpleado, DataPerfil perfil){
		DataEmpleado empleado = srvEmpleado.getEmpleado(idEmpleado);
		empleado.setPerfil(perfil);
		srvEmpleado.modificarEmpleado(empleado);
	}
	
	
}
