package lcbs.api.rest;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import lcbs.api.service.TenantRepo;
public class BaseApi {	
	@Context
	HttpServletRequest request;
	@EJB
	TenantRepo repo;
	 
	public BaseApi (){}
}
