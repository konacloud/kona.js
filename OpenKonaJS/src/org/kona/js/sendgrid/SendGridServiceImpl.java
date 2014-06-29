package org.kona.js.sendgrid;

import java.util.Map;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import com.sendgrid.SendGrid.Email;

public class SendGridServiceImpl implements SendGridService {

	public void send(Map map) throws SendGridException {
		
		SendGrid sendgrid = new SendGrid("user", "password");
		
		Email email = new Email();
	
		/*
		var email = {
				to: ["test@test.com", "test@konacloud.io"],
				from: "test@konacloud.io",
				fromName: "Pepe Pepa",
				subject: "hello world",
				text: "",
				html: ""
			
			};
		
		var sendgrid = kona.sendgrid("dcibils","Hawaii2014");
		sendgrid.send(email);
		*/
	/*	var email = {
			to: ["dcibils@gmail.com", "santiago@konacloud.io"],
			from: "diego@konacloud.io",
			fromName: "Pepe Pepa",
			subject: "hello world",
			text: "",
			html: ""
		
		}
*/
		email.addTo("dcibils@tet.com");
		//email.add("Example Guy");
		email.setFrom("diego@test.io");
		email.setFromName("Other Dude");
		
		email.setSubject("Hello World");
		email.setText("My first email through SendGrid");
		//email.setHtml("<html>SALAME</html>");
		
			sendgrid.send(email);
		
	}

}
