package lcbs.utils;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.pusher.rest.Pusher;

import lcbs.interfaces.UsuarioLocalApi;
import lcbs.shares.DataNotificacion;
import lcbs.shares.DataTenant;
import lcbs.shares.DataUsuario;

public class NotificationHandler {
	@EJB(lookup = "java:app/lcbsdb/UsuarioSrv!lcbs.interfaces.UsuarioLocalApi")
	private static UsuarioLocalApi srvUsuario;

	public static String getChannel(DataTenant tenant, String email) {
		String ch = tenant.getName() + email;
		return Base64.getEncoder().encode(ch.getBytes()).toString();
	}

	public static void sendNotification(DataUsuario per, String section, String event, String message,
			DataTenant tenant) {
		Pusher pusher = new Pusher("223932", "e782ddf887a873098d22", "e9a763ead5000f8ae2e0");
		pusher.setEncrypted(true);
		HashMap<String, String> mp = new HashMap<String, String>();
		mp.put("section", section);
		mp.put("message", message);
		pusher.trigger(getChannel(tenant, per.getEmail().getEmail()), event, mp);
		DataNotificacion notificacion = new DataNotificacion();
		notificacion.setFecha(new Date());
		notificacion.setMensaje(message);
		per.getNotificaciones().add(notificacion);
		srvUsuario.modificarUsuario(per, tenant);
		sendEmail(per.getEmail().getEmail(), "no-reply@" + tenant.getName() + ".com", section + event, message);
	}

	private static void sendEmail(String to, String from, String subject, String text) {
		String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		try {
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
}
