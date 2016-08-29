package lcbs.api.rest;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import lcbs.api.interceptor.TenantChecked;
import lcbs.api.service.TenantRepo;
@TenantChecked
public class BaseApi {	
	@Context
	HttpServletRequest request;
	@EJB
	TenantRepo repo;
	 
	
}
