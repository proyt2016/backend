package lcbs.api.rest;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import lcbs.api.service.TenantRepo;
import lcbs.shares.DataTenant;

public class BaseApi {
	@Context 
	HttpHeaders headers;
	
	@EJB
	TenantRepo repo;
	
	public DataTenant checkTenant(){
		 String host = headers.getHeaderString("host");
		 DataTenant filter = new DataTenant();
		 filter.setDomain(host); 
		 return repo.get(filter);
	}
	 
	
}
