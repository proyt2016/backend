 package lcbs.schema;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lcbs.exceptions.SchemaException;
import lcbs.interfaces.ISchemaHandler;
@Stateless
public class SchemaHandler implements ISchemaHandler{

	private static final Log log = LogFactory.getLog(SchemaHandler.class);
	private ArrayList<String> createSQL = new ArrayList<String>();
	private static final String delimiter = ";";
	@Inject
	EntityManager em;
	
	@RequestScoped
	public void createSchema(String name) throws SchemaException {
		try {
			if (em.isJoinedToTransaction()) {
				em.joinTransaction();

			}
			log.info("Create schema if not exist");
			em.createNativeQuery("CREATE SCHEMA " + name).executeUpdate();
		} catch (Exception e) {
			 throw new SchemaException("Schema Already Exist");
		}
		log.info("Changing current Schema to: "+ name);
		em.createNativeQuery("SET SCHEMA '" + name+"'").executeUpdate();
		try {  
			File file =  new File("META-INF/schema.ddl");
			
			log.info(file.getAbsoluteFile());
			log.info("=====");
			log.info(file.getAbsolutePath());
			log.info("====="); 
			Scanner scanner = new Scanner(file).useDelimiter(delimiter);
		    while(scanner.hasNext()) {
		    	String sql = scanner.next();
		    	log.info(sql);
		        createSQL.add(sql + delimiter);
		    }
		
			create(em);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		em.createNativeQuery("SET SCHEMA 'public'").executeUpdate();
	}

	private void create(EntityManager em) throws IOException {
		String[] createSQsL = this.createSQL.toArray(new String[0]);
		for (int j = 0; j < createSQsL.length; j++) {
			try {
				 log.info(createSQsL[j]);;
				em.createNativeQuery(createSQsL[j]).executeUpdate();
			} catch (Exception e) {
				// exceptions.add( e );
				log.error("Unsuccessful: " + createSQsL[j]);
				log.error(e.getMessage());
			}
		}
	}
}
