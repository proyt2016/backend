package lcbs.controllers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import interfaces.ITenant;
import lcbs.exceptions.SchemaException;
import lcbs.exceptions.TenantException;
import lcbs.interfaces.ISchemaHandler;
import lcbs.interfaces.TenantLocalApi;
import lcbs.shares.DataTenant;


/**
 * Session Bean implementation class EncomiendaSrv
 */
@Stateless
public class TenantCtrl implements ITenant{
	@EJB(lookup="java:app/lcbsdb/TenantSrv!lcbs.interfaces.TenantLocalApi")
	TenantLocalApi srvTenant;
	@EJB(lookup="java:app/lcbsdb/SchemaHandler!lcbs.interfaces.ISchemaHandler")
	ISchemaHandler srvSchemaHandler;
	public List<DataTenant> list() {  
		return srvTenant.list();
	} 
	public DataTenant get(DataTenant tenant) { 
		return srvTenant.get(tenant);
	}
	public DataTenant create(DataTenant tenant) throws TenantException, Exception { 
		DataTenant dt = srvTenant.get(tenant);
		try{
			if(dt == null){
				srvSchemaHandler.createSchema(tenant.getName());
				DataTenant result = srvTenant.create(tenant); 
				//TODO: Execute command here.
				//result.getId();
				//result.getFbId();
				//resutl.getName();
				//result.getFbSecret();
				//result.getTipoVertical();
				//Runtime rt = Runtime.getRuntime();
				//Process pr = rt.exec(" COMANDO");
				return result;
			}else{
				throw new TenantException("Tenant Already Exist");
			}
		}catch(SchemaException e){
			throw e;//new TenantException("Something went wrong");
		}
		
	}

	public boolean delete(DataTenant tenant) { 
		return srvTenant.delete(tenant);
	}

	public boolean deactivate(DataTenant tenant) { 
		return srvTenant.deactivate(tenant);
	}
	@Override
	public Boolean activate(DataTenant tenant) {
		return srvTenant.activate(tenant);
	}
	@Override
	public List<DataTenant> list(DataTenant filter) {
		return srvTenant.list(filter);
	}
}
