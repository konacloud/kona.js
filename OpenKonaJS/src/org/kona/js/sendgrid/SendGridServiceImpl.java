package org.kona.js.sendgrid;

import java.util.Map;

import org.kona.js.util.KonaUtil;

import com.mongodb.DBObject;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.Email;

public class SendGridServiceImpl implements SendGridService {

	private SendGrid sendgrid;

	public void send(Object obj) throws Exception {
		
		DBObject map = (DBObject) KonaUtil.getNewInstance().parseObjectToKonaDO(obj);
		Email email = new Email();

		/*
		 * var email = { to: ["test@test.com", "test@konacloud.io"], from:
		 * "test@konacloud.io", fromName: "Pepe Pepa", subject: "hello world",
		 * text: "", html: ""
		 * 
		 * };
		 * 
		 * var sendgrid = kona.sendgrid("dcibils","Hawaii2014");
		 * sendgrid.send(email);
		 */
		/*
		 * var email = { to: ["dcibils@gmail.com", "santiago@konacloud.io"],
		 * from: "diego@konacloud.io", fromName: "Pepe Pepa", subject:
		 * "hello world", text: "", html: ""
		 * 
		 * }
		 * 
		 * 
		 */

		
		String to = (String) map.get("to");
		String from = (String) map.get("from");

		String fromName = (String) map.get("fromName");
		String subject = (String) map.get("subject");
		String text = (String) map.get("text");

		email.addTo(to);
		// email.add("Example Guy");
		email.setFrom(from);
		email.setFromName(fromName);

		email.setSubject(subject);
		// email.setText("My first email through SendGrid");
		email.setHtml(text);

		sendgrid.send(email);

	}

	public SendGridService open(Object obj) throws Exception {

		DBObject conf = (DBObject) KonaUtil.getNewInstance().parseObjectToKonaDO(obj);
		
		sendgrid = new SendGrid((String) conf.get("user"),
				(String) conf.get("password"));
		return this;
	}

}
