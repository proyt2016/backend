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

	public List<DataTenant> list() {
		return srvTenant.list();
	}

	public DataTenant get(DataTenant tenant) {
		return srvTenant.get(tenant);
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
				throw new TenantException("Tenant Already Exist");
			}
		} catch (SchemaException e) {
			ut.begin();
			srvTenant.delete(tenant);
			ut.commit();
			throw e;// new TenantException("Something went wrong");
		}

	}

	private void insertConfigs(DataTenant tenant) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
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
		usrCtrl.AltaPerfil(profile, tenant);
		ut.commit();
		ut.begin();
		ArrayList<DataEmpleado> da = new ArrayList<DataEmpleado>();
		da.add(usr);
		profile.setEmpleados(da);
		usrCtrl.AltaEmpleado(usr, tenant);
		ut.commit();
		ut.begin();
		nHandler.sendNotification(usr.getEmail().getEmail(), "sadmin", "creado",
				"Servicio disponible: " + tenant.getDomain() + " Usuario: " + usr.getEmail().getEmail() + " Contraseña: " + psw,
				tenant);
		ut.commit();
	}

	public boolean delete(DataTenant tenant) {
		try {
			ut.begin();
			Boolean l = srvTenant.delete(tenant);
			ut.commit();
			return l;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deactivate(DataTenant tenant) {
		try {
			ut.begin();
			Boolean l = srvTenant.deactivate(tenant);
			ut.commit();
			return l;
		} catch (Exception e) {
			return false;
		}
	}

	public Boolean activate(DataTenant tenant) {
		try {
			ut.begin();
			Boolean l = srvTenant.activate(tenant);
			ut.commit();
			return l;
		} catch (Exception e) {
			return false;
		}
	}

	public List<DataTenant> list(DataTenant filter) {
		try {
			ut.begin();
			List<DataTenant> l = srvTenant.list(filter);
			ut.commit();
			return l;
		} catch (Exception e) {
			return null;
		}

	}
}
