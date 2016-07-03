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
	@EJB(lookup =  "java:app/lcbsb/TenantCtrl!interfaces.ITenant")
	ITenant ctrTenant;
	//TODO: Handle errors propertly 
	public List<DataTenant> list() { 
		try{
			return ctrTenant.list();	
		}catch(Exception e){
			
			throw e;
		}
	}

	public DataTenant create(DataTenant tenant) throws TenantException {
		try {
			return ctrTenant.create(tenant);
		} catch (TenantException e) {
			 throw e;
		}
	}

	public boolean delete(DataTenant tenant) {
		try{
			return ctrTenant.delete(tenant);
		}catch(Exception e){
			throw e;
		} 
	}

	public boolean deactivate(DataTenant tenant) { 
		try{
			return ctrTenant.deactivate(tenant);
		}catch(Exception e){
			throw e;
		}  
	}
	public boolean activate(DataTenant tenant) { 
		try{
			return ctrTenant.activate(tenant);
		}catch(Exception e){
			throw e;
		}  
	}
	public DataTenant get(DataTenant tenant) { 
		try{
			return ctrTenant.get(tenant);
		}catch(Exception e){
			throw e;
		} 
	}

}
