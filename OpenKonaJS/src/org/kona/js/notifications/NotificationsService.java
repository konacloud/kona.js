package org.kona.js.notifications;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;

public class NotificationsService implements INotificationsService {

	public NotificationsService(String user, String appId) {
		
	}
	
	public void push(String token, String payload) {

		ApnsService service =
			    APNS.newService()
			    .withCert("/Users/diego/Development/ios.konacloud.ios.pushtest.p12", "fisica")
			    .withSandboxDestination()
			    .build();

			service.push(token, payload);

	}
	
	

}
