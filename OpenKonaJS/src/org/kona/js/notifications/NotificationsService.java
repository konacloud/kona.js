package org.kona.js.notifications;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class NotificationsService implements INotificationsService {

	private String user;
	private String appId;

	public NotificationsService(String user, String appId) {
		this.user = user;
		this.appId = appId;
	}
	
	private static Map<String, ApnsService> mapServices = new HashMap<String, ApnsService>();
	
	public void push(String token, String payload) {
		ApnsService service;
		String mapId = user + "@" + appId;
		if (mapServices.get(mapId) == null) {
			 service =
				    APNS.newService()
				    .withCert("/Users/diego/Development/ios.konacloud.ios.pushtest.p12", "password")
				    .withSandboxDestination()
				    .build();
			 mapServices.put(mapId, service);
		} else {
			service = mapServices.get(mapId);	
		}
		service.push(token, payload);
	}
}
