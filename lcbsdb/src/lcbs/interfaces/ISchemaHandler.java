package lcbs.interfaces;

import javax.ejb.Local;

import lcbs.exceptions.SchemaException;

@Local
public interface ISchemaHandler {
	
	public void createSchema(String name) throws SchemaException;
}
