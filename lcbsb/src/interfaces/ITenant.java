package interfaces;

import java.util.List;

import javax.ejb.Local;

import lcbs.exceptions.TenantException;
import lcbs.shares.DataTenant;
@Local
public interface ITenant {
	
	public List<DataTenant> list() throws TenantException;	
	public List<DataTenant> list(DataTenant filter) throws TenantException;

	public DataTenant create(DataTenant tenant) throws TenantException, Exception; 
	public boolean delete(DataTenant tenant) throws TenantException; 
	public boolean deactivate(DataTenant tenant) throws TenantException;  
	public Boolean activate(DataTenant tenant) throws TenantException;
	public DataTenant get(DataTenant tenant) throws TenantException;
}
