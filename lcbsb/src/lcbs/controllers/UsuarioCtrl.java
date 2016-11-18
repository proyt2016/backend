package lcbs.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class UsuarioCtrl implements IUsuario {

	@EJB(lookup = "java:app/lcbsdb/UsuarioSrv!lcbs.interfaces.UsuarioLocalApi")
	UsuarioLocalApi srvUsuario;

	@EJB(lookup = "java:app/lcbsdb/EmpleadoSrv!lcbs.interfaces.EmpleadoLocalApi")
	EmpleadoLocalApi srvEmpleado;

	@EJB(lookup = "java:app/lcbsdb/CuponeraSrv!lcbs.interfaces.CuponeraLocalApi")
	CuponeraLocalApi srvCuponera;

	@EJB(lookup = "java:app/lcbsdb/PerfilSrv!lcbs.interfaces.PerfilLocalApi")
	PerfilLocalApi srvPerfil;
	
	@EJB(lookup = "java:app/lcbsdb/ConfiguracionEmpresaSrv!lcbs.interfaces.ConfiguracionEmpresaLocalApi")
	ConfiguracionEmpresaLocalApi srvConfiguracionEmpresa;

	@Override
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) {
		DataCuponera cuponera = new DataCuponera();
		cuponera.setSaldo(0.0f);
		usuario.setCuponera(cuponera);
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(usuario.getClave().getBytes());

			byte byteData[] = md.digest();
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		usuario.setClave(sb.toString());
		return srvUsuario.crearUsuario(usuario, tenant);
	}

	@Override
	public void ModificarUsuario(DataUsuario usuario, DataTenant tenant) {
		srvUsuario.modificarUsuario(usuario, tenant);
	}

	@Override
	public void CargarSaldoCuponera(String idUsuario, Float saldo, DataTenant tenant) {
		DataUsuario usuario = srvUsuario.getUsuario(idUsuario, tenant);
		DataCuponera cuponera = usuario.getCuponera();
		cuponera.setSaldo(cuponera.getSaldo() + saldo);
		srvCuponera.modificarCuponera(cuponera, tenant);
	}

	@Override
	public List<DataNotificacion> listarNotificaciones(String idUsuario, DataTenant tenant) {
		DataUsuario usuario = srvUsuario.getUsuario(idUsuario, tenant);
		return usuario.getNotificaciones();
	}

	@Override
	public void BajaUsuario(String idUsuario, DataTenant tenant) {
		srvUsuario.darBajaUsuario(idUsuario, tenant);
	}

	@Override
	public DataEmpleado AltaEmpleado(DataEmpleado empleado, DataTenant tenant) {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(empleado.getClave().getBytes());

			byte byteData[] = md.digest();
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		empleado.setClave(sb.toString());
		return srvEmpleado.crearEmpleado(empleado, tenant);
	}

	@Override
	public void ModificarEmpleado(DataEmpleado empleado, DataTenant tenant) {
		srvEmpleado.modificarEmpleado(empleado, tenant);

	}

	@Override
	public void BajaEmpleado(String idEmpleado, DataTenant tenant) {
		srvEmpleado.darBajaEmpleado(idEmpleado, tenant);
	}

	@Override
	public DataPerfil AltaPerfil(DataPerfil perfil, DataTenant tenant) {
		return srvPerfil.crearPerfil(perfil, tenant);
	}

	@Override
	public void EditarPerfil(DataPerfil perfil, DataTenant tenant) {
		srvPerfil.modificarPerfil(perfil, tenant);
	}

	@Override
	public void EliminarPerfil(String idPerfil, DataTenant tenant) {
		srvPerfil.borrarPerfil(idPerfil, tenant);
	}

	@Override
	public void AsignarPerfil(String idEmpleado, String perfil, DataTenant tenant) {
		DataEmpleado empleado = srvEmpleado.getEmpleado(idEmpleado, tenant);
		DataPerfil perf = srvPerfil.getPerfil(perfil, tenant);
		empleado.setPerfil(perf);
		srvEmpleado.modificarEmpleado(empleado, tenant);
		List<DataEmpleado> empList = perf.getEmpleados();
		empList.add(empList.size(), empleado);
		perf.setEmpleados(empList);
		srvPerfil.modificarPerfil(perf, tenant);
	}

	@Override
	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant) {
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(clave.getBytes());

			byte byteData[] = md.digest();
			sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		clave = sb.toString();
		return srvUsuario.loginUsuario(usuario, clave, tenant);
	}

	@Override
	public DataUsuario getUsuario(String idUsuario, DataTenant tenant) {
		return srvUsuario.getUsuario(idUsuario, tenant);
	}

	@Override
	public DataEmpleado loginEmpleado(String mail, String clave, DataTenant tenant) {
		DataEmpleado result = new DataEmpleado();
		DataConfiguracionEmpresa conf = srvConfiguracionEmpresa.getConfiguracionEmpresa(tenant);
		if(conf.getUrlLdap() != null){
			result= srvEmpleado.empleadoPorIdLdap(mail, tenant);
			if(result == null)
				result = srvEmpleado.loginUsuario(mail, clave, tenant);
		}else{
			MessageDigest md;
			StringBuffer sb = new StringBuffer();
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(clave.getBytes());

				byte byteData[] = md.digest();
				sb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			clave = sb.toString();
			result = srvEmpleado.loginUsuario(mail, clave, tenant);
		}
		if(conf.getUrlLdap() != null && !ldapconnection.validarCredenciales(conf.getUrlLdap(), conf.getBaseLdap(), result.getIdEmpleadoLdap(), result.getClave())){
			if(result.getIdEmpleadoLdap() != null)
				return null;
		}
		return result;
	}

	@Override
	public DataEmpleado getEmpleado(String idEmpleado, DataTenant tenant) {
		return srvEmpleado.getEmpleado(idEmpleado, tenant);
	}

	@Override
	public List<DataEmpleado> listarEmpleados(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvEmpleado.obtenerEmpleados(pagina, elementosPagina, tenant);
	}

	@Override
	public DataPerfil getPerfil(String idPerfil, DataTenant tenant) {
		return srvPerfil.getPerfil(idPerfil, tenant);
	}

	@Override
	public List<DataPerfil> listarPerfiles(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvPerfil.obtenerPerfils(pagina, elementosPagina, tenant);
	}

	@Override
	public List<DataUsuario> listarUsuarios(Integer pagina, Integer elementosPagina, DataTenant tenant) {
		return srvUsuario.obtenerUsuarios(pagina, elementosPagina, tenant);
	}

	@Override
	public DataUsuario buscarUsuarioPorMail(String mailUsuario, DataTenant tenant) {
		return srvUsuario.buscarUsuarioPorMail(mailUsuario, tenant);
	}

}
