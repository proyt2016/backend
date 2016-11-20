package lcbs.controllers;


import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ldapconnection {
	private static final Log log = LogFactory.getLog(ldapconnection.class);
    
    public static Boolean validarCredenciales(String ldapAdServer, String ldapDomain, String ldapUsr, String ldapPassword){
    	
    	//final String ldapAdServer = "ldap://127.0.0.1:389";
        //final String ldapSearchBase = "cn=pepe, dc=ldap,dc=lacbus,dc=com";
        //final String ldapPassword = "p455w0rd";
        
    	String ldapSearchBase = "cn="+ldapUsr;
    	for (String retval: ldapDomain.split("\\.")) {
    		ldapSearchBase += ", dc=" + retval;
        }
    	Hashtable<String,String> env = new Hashtable<String,String>();
    	env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    	env.put(Context.PROVIDER_URL, ldapAdServer);
    	env.put(Context.SECURITY_AUTHENTICATION, "simple");
    	env.put(Context.SECURITY_PRINCIPAL, ldapSearchBase);
    	env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
    	 
    	LdapContext ctxGC = null;
    	 
    	try {
    	     
    	    // This is the actual Authentication piece. Will throw javax.naming.AuthenticationException
    	    // if the users password is not correct. Other exceptions may include IO (server not found) etc.
    	    ctxGC = new InitialLdapContext(env, null);
    	    return true;
    	}catch(Exception e){
    		log.info("################################newEM#################################### "+e.getMessage());
    		log.info("################################newEM#################################### "+ldapAdServer +' '+ldapSearchBase+' '+ldapPassword+' '+ldapSearchBase+ ' '+ldapDomain);
    		return false;
    	}
    }
}