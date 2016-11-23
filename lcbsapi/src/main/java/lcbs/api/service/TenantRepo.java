package lcbs.api.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import interfaces.ITenant;
import lcbs.exceptions.TenantException;
import lcbs.shares.DataTenant;

@Stateless
@Remote
public class TenantRepo {
	@EJB(lookup = "java:app/lcbsb/TenantCtrl!interfaces.ITenant")
	ITenant ctrTenant;

	public List<DataTenant> list() throws TenantException {
		return ctrTenant.list();
	}

	public DataTenant create(DataTenant tenant) throws TenantException, Exception {
		return ctrTenant.create(tenant);
	}

	public boolean delete(DataTenant tenant) throws TenantException{
		return ctrTenant.delete(tenant);

	}

	public boolean deactivate(DataTenant tenant) throws TenantException{
		return ctrTenant.deactivate(tenant);

	}

	public boolean activate(DataTenant tenant) throws TenantException{
		return ctrTenant.activate(tenant);

	}

	public DataTenant get(DataTenant tenant) throws TenantException{
		return ctrTenant.get(tenant);

	}

}
