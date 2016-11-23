package lcbs.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import interfaces.ITenant;
import interfaces.IUsuario;
import lcbs.exceptions.SchemaException;
import lcbs.exceptions.TenantException;
import lcbs.exceptions.UserException;
import lcbs.interfaces.ISchemaHandler;
import lcbs.interfaces.PerfilLocalApi;
import lcbs.interfaces.TenantLocalApi;
import lcbs.schema.SchemaHandler;
import lcbs.shares.DataEmpleado;
import lcbs.shares.DataPerfil;
import lcbs.shares.DataTenant;
import lcbs.utils.NotificationHandler;

/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
@TransactionManagement(value = TransactionManagementType.BEAN)
public class TenantCtrl implements ITenant {

	private static final Log log = LogFactory.getLog(TenantCtrl.class);
	private final static String EMAIL_SUFIX = "@lcbs.org";
	private static final String ROLE_SUFIX = "admin";
	@EJB(lookup = "java:app/lcbsdb/TenantSrv!lcbs.interfaces.TenantLocalApi")
	TenantLocalApi srvTenant;
	@EJB(lookup = "java:app/lcbsdb/SchemaHandler!lcbs.interfaces.ISchemaHandler")
	ISchemaHandler srvSchemaHandler;
	@EJB
	NotificationHandler nHandler;
	@EJB
	IUsuario usrCtrl;

	@Inject
	UserTransaction ut;

	public List<DataTenant> list() throws TenantException {
		try {
			return srvTenant.list();
		} catch (Exception e) {
			throw new TenantException("No se encuentra este tenant", 3);
		}
	}

	public DataTenant get(DataTenant tenant) throws TenantException {
		try {
			return srvTenant.get(tenant);
		} catch (Exception e) {
			throw new TenantException("Tenant no existe", 3);
		}
	}

	public DataTenant create(DataTenant tenant) throws TenantException, Exception {
		DataTenant dt = srvTenant.get(tenant);
		try {
			if (dt == null) {
				ut.begin();
				log.info("//////////////////////new tenant Insert///////////////");
				DataTenant res = srvTenant.create(tenant);
				ut.commit();
				srvSchemaHandler.createSchema(tenant.getName());

				insertConfigs(tenant);
				return res;
			} else {
				throw new TenantException("Tenant ya existe", 0);
			}
		} catch (SchemaException e) {
			ut.begin();
			srvTenant.delete(tenant);
			ut.commit();
			throw new TenantException("Algo marcho mal", 1);
		}

	}

	private void insertConfigs(DataTenant tenant)
			throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException, SystemException, NotSupportedException, TenantException {
		DataEmpleado usr = new DataEmpleado();
		String psw = ROLE_SUFIX + tenant.getName();
		usr.setEliminado(false);
		usr.setEmail(tenant.getEmail());
		usr.setNombrePila(ROLE_SUFIX);
		usr.setClave(psw);

		DataPerfil profile = new DataPerfil();
		profile.setNombrePerfil("Admin");
		profile.setConfiguracionEmpresa(true);
		profile.setEmpleados(null);
		profile.setGestionEncomiendas(true);
		profile.setGestionPasajes(true);
		profile.setGestionReportes(true);
		profile.setMantenimientoFlota(true);
		ut.begin();
		try {
			usr = usrCtrl.AltaEmpleado(usr, tenant);
			ArrayList<DataEmpleado> da = new ArrayList<DataEmpleado>();
			da.add(usr);
			profile.setEmpleados(da);
			profile = usrCtrl.AltaPerfil(profile, tenant);
			usr.setPerfil(profile);
			usrCtrl.ModificarEmpleado(usr, tenant);
		} catch (UserException e) {
			throw new TenantException("Algo marcho mal creando perfil administrador", 1);
		}
		ut.commit();
		ut.begin();
		nHandler.sendNotification(usr.getEmail().getEmail(), "sadmin", "creado", "Servicio disponible: "
				+ tenant.getDomain() + " Usuario: " + usr.getEmail().getEmail() + " Contraseña: " + psw, tenant);
		ut.commit();
	}

	public boolean delete(DataTenant tenant) throws TenantException {
		try {
			ut.begin();
			Boolean l = srvTenant.delete(tenant);
			ut.commit();
			return l;
		} catch (Exception e) {
			throw new TenantException("Imposible ejecutar accion en este momento", 2);
		}
	}

	public boolean deactivate(DataTenant tenant) throws TenantException {
		try {
			ut.begin();
			Boolean l = srvTenant.deactivate(tenant);
			ut.commit();
			return l;
		} catch (Exception e) {
			throw new TenantException("Imposible ejecutar accion en este momento", 2);
		}
	}

	public Boolean activate(DataTenant tenant) throws TenantException {
		try {
			ut.begin();
			Boolean l = srvTenant.activate(tenant);
			ut.commit();
			return l;
		} catch (Exception e) {
			throw new TenantException("Imposible ejecutar accion en este momento", 2);
		}
	}

	public List<DataTenant> list(DataTenant filter) throws TenantException {
		try {
			ut.begin();
			List<DataTenant> l = srvTenant.list(filter);
			ut.commit();
			return l;
		} catch (Exception e) {
			throw new TenantException("No se encuentra este tenant", 3);
		}

	}
}
