package lcbs.utils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pusher.rest.Pusher;

import lcbs.interfaces.UsuarioLocalApi;
import lcbs.shares.DataEmpleado;
import lcbs.shares.DataNotificacion;
import lcbs.shares.DataTenant;
import lcbs.shares.DataUsuario;

@Stateless
public class NotificationHandler {

	private static final Log log = LogFactory.getLog(NotificationHandler.class);
	@EJB(lookup = "java:app/lcbsdb/UsuarioSrv!lcbs.interfaces.UsuarioLocalApi")
	private UsuarioLocalApi srvUsuario;

	public void sendNotification(String email, String section, String event, String message, DataTenant tenant) {

		// Pusher stuff
		Pusher pusher = new Pusher("223932", "e782ddf887a873098d22", "e9a763ead5000f8ae2e0");
		pusher.setEncrypted(true);
		HashMap<String, String> mp = new HashMap<String, String>();
		mp.put("section", section);
		mp.put("message", message);
		pusher.trigger(section, event, mp);

		// Send emails
		sendEmail(email, "no-reply@" + tenant.getName() + ".com", section + " " + event, message);

	}

	public void sendNotification(DataUsuario per, String section, String event, String message, DataTenant tenant) {

		if (per != null) {

			// Pusher stuff
			Pusher pusher = new Pusher("223932", "e782ddf887a873098d22", "e9a763ead5000f8ae2e0");
			pusher.setEncrypted(true);
			HashMap<String, String> mp = new HashMap<String, String>();
			mp.put("section", section);
			mp.put("message", message);
			String email = per.getEmail().getEmail();
			String channel = getChannel(tenant, email);
			pusher.trigger(channel, event, mp);

			/// Save notifications
			DataUsuario usr = srvUsuario.getUsuario(per.getId(), tenant);
			DataNotificacion notificacion = new DataNotificacion();
			notificacion.setFecha(new Date());
			notificacion.setMensaje(message);
			if (usr.getNotificaciones() == null || per.getNotificaciones().equals(null)) {
				usr.setNotificaciones(new ArrayList<DataNotificacion>());
			}
			usr.getNotificaciones().add(notificacion);
			srvUsuario.modificarUsuario(usr, tenant);

			// Send emails
			sendEmail(per.getEmail().getEmail(), "no-reply@" + tenant.getName() + ".com", section + " " + event,
					message);
		}

	}

	private String getChannel(DataTenant tenant, String email) {
		String ch = tenant.getName() + email;
		return new String(Base64.getEncoder().encode(ch.getBytes()));
	}

	private void sendEmail(String to, String from, String subject, String text) {
		final String username = "lcbs.email.srv@gmail.com";
		final String password = "soylajuliapuntocom";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.user", username);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", true);

		try {
			Session session = Session.getInstance(properties, new SmtpAuthenticator(username, password));
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(text);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	class SmtpAuthenticator extends Authenticator {
		String username;
		String password;

		public SmtpAuthenticator(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			if ((username != null) && (username.length() > 0) && (password != null) && (password.length() > 0)) {
				return new PasswordAuthentication(username, password);
			}

			return null;
		}
	}
}
