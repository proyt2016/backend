package lcbs.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.codec.binary.Base64;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

import interfaces.IUsuario;
import lcbs.interfaces.ConfiguracionEmpresaLocalApi;
import lcbs.interfaces.CuponeraLocalApi;
import lcbs.interfaces.EmpleadoLocalApi;
import lcbs.interfaces.PerfilLocalApi;
import lcbs.interfaces.UsuarioLocalApi;
import lcbs.shares.DataConfiguracionEmpresa;
import lcbs.shares.DataCuponera;
import lcbs.shares.DataEmpleado;
import lcbs.shares.DataNotificacion;
import lcbs.shares.DataPerfil;
import lcbs.shares.DataTenant;
import lcbs.shares.DataUsuario;
import lcbs.utils.NotificationHandler;

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

	@EJB
	NotificationHandler nHandler;
	@Override
	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) {
		DataCuponera cuponera = new DataCuponera();
		cuponera.setSaldo(0.0f);
		usuario.setCuponera(cuponera);
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(usuario.genClave().getBytes());

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
		nHandler.sendNotification(usuario, "Cuponera", "recarga-saldo", "Se acredito su recarga de: " + saldo,
				tenant);
	}
	
	@Override
	public void CargarSaldoCuponeraStripe(String idUsuario, Float saldo, DataTenant tenant) {
		DataUsuario usuario = srvUsuario.getUsuario(idUsuario, tenant);
		DataCuponera cuponera = usuario.getCuponera();
		cargarTarjeta(idUsuario, saldo, tenant);
		cuponera.setSaldo(cuponera.getSaldo() + saldo);
		srvCuponera.modificarCuponera(cuponera, tenant);
		nHandler.sendNotification(usuario, "Cuponera", "recarga-saldo", "Se acredito su recarga de: " + saldo,
				tenant);
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
			md.update(empleado.genClave().getBytes());

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
			if(result == null){
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
		if(conf.getUrlLdap() != null && !ldapconnection.validarCredenciales(conf.getUrlLdap(), conf.getBaseLdap(), result.getIdEmpleadoLdap(), result.genClave())){
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
	
	@Override
	public void guardarTokenUsuario(String idUsuario, String token, Integer ultimosDigitosTarjeta, DataTenant tenant) {

		DataUsuario usu = getUsuario(idUsuario, tenant);
		usu.setUltimosCuatroDigitos(ultimosDigitosTarjeta);
		DataConfiguracionEmpresa conf = srvConfiguracionEmpresa.getConfiguracionEmpresa(tenant);
		try {
			Stripe.apiKey = Desencriptar(conf.genStripePrivateKey());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String cusId = "";
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("description", "Customer for "+usu.getEmail().getEmail());
		customerParams.put("source", token);

		Customer cus = new Customer();
		try {
			cus = Customer.create(customerParams);
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | CardException
				| APIException e) {
			e.printStackTrace();
		}
		cusId = cus.getId();
		usu.setStripeCustomerId(cusId);
		srvUsuario.modificarUsuario(usu, tenant);
	}
	
	@Override
	public void cargarTarjeta(String idUsuario, Float cargo, DataTenant tenant) {
		DataConfiguracionEmpresa conf = srvConfiguracionEmpresa.getConfiguracionEmpresa(tenant);
		try {
			Stripe.apiKey = Desencriptar(conf.genStripePrivateKey());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// Get the credit card details submitted by the form
		// String token = request.getParameter("stripeToken");
		DataUsuario usu = getUsuario(idUsuario, tenant);
		String cusId = usu.getStripeCustomerId();
		String emailUsuario = usu.getEmail().getEmail();

		// Create a charge: this will charge the user's card
		try {
			Map<String, Object> chargeParams = new HashMap<String, Object>();
			chargeParams.put("amount", Math.round(cargo * 100)); // Amount in
																	// cents
			chargeParams.put("currency", "usd");
			chargeParams.put("customer", cusId);
			chargeParams.put("description", "Cargo para: " + emailUsuario);

			// Charge charge = Charge.create(chargeParams);
			Charge.create(chargeParams);

		} catch (Exception e) {
			
		}

	}
	
	public static String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "ck4VC453FDGDFgdgdf";
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
	}

}
