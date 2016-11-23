package lcbs.api.interceptor;

import javax.ejb.EJB;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;

import lcbs.api.service.TenantRepo;
import lcbs.exceptions.TenantException;
import lcbs.shares.DataTenant;
@Provider
@TenantChecked
public class TenantCheckInterceptor implements javax.ws.rs.container.ContainerRequestFilter {
	
	@EJB
	TenantRepo repo; 
    @Override
    public void filter(ContainerRequestContext requestContext){
    	 String id = requestContext.getHeaderString("lcbs-tenant");
    	 String host =  requestContext.getHeaderString("host");
		 DataTenant filter = new DataTenant();
		 if(id != null && !id.isEmpty()){
			 filter.setId(id);
		 }else{
			 filter.setDomain(host); 	 
		 }
		 filter.setIsActive(true);
		 filter.setIsDelete(false);
		 try{
			 DataTenant tenant = repo.get(filter);

			 if(tenant == null){
				 throw new ForbiddenException();
			 }else{
				 requestContext.setProperty("tenant", tenant);
			 }

			 throw new ForbiddenException();
		 }catch(TenantException e){
			 throw new ForbiddenException();
		 } 
    } 

}
