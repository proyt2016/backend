package lcbs.controllers;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import interfaces.IEmpresa;
import lcbs.interfaces.*;
import lcbs.shares.*;

import javax.ejb.Stateless;

import org.apache.commons.codec.binary.Base64;




/**
 * Session Bean implementation class EmpresaCtrl
 */
@Stateless
public class EmpresaCtrl implements IEmpresa{
	
	@EJB(lookup="java:app/lcbsdb/ConfiguracionEmpresaSrv!lcbs.interfaces.ConfiguracionEmpresaLocalApi")
	ConfiguracionEmpresaLocalApi srvEmpresa;
	
	@Override
	public void altaConfiguracionEmpresa(DataConfiguracionEmpresa empresa, DataTenant tenant) {
		srvEmpresa.crearConfiguracionEmpresa(empresa, tenant);
	}
	
	@Override
	public DataConfiguracionEmpresa obtenerConfiguracionEmpresa(DataTenant tenant){
		return srvEmpresa.getConfiguracionEmpresa(tenant);
	}
	
	@Override
	public void editarConfiguracionEmpresa(DataConfiguracionEmpresa empresa, DataTenant tenant){
		empresa.setStripePrivateKey(Encriptar(empresa.genStripePrivateKey()));
		srvEmpresa.modificarCuponera(empresa, tenant);
	}
	
	public static String Encriptar(String texto) {

        String secretKey = "ck4VC453FDGDFgdgdf";
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
	}
	
}
