package lcbs.controllers;

import javax.ejb.Local;
import javax.enterprise.context.RequestScoped;

@Local
public interface LocalApi{
	@RequestScoped
	public String getSape();
}
