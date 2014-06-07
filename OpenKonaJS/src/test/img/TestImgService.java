package test.img;

import org.junit.Test;
import org.kona.js.ImgService;
import org.kona.js.OpenKonaFactory;
import org.kona.js.notifications.NotificationsService;

import com.notnoop.apns.APNS;
import com.notnoop.apns.PayloadBuilder;

public class TestImgService {

	//@Test
	public void testQr() throws Exception {
		
		OpenKonaFactory kona = new OpenKonaFactory();
		ImgService service = kona.img;
		
		String result = service.qr("kona");
		
		assert((result!=null));
		
	}

	@Test
	public void testPush() throws Exception {
		
		OpenKonaFactory kona = new OpenKonaFactory();
		NotificationsService notificationsService =  kona.notifications;

		  PayloadBuilder payload =  APNS.newPayload();
		  payload.alertBody("test body");
		  payload.alertTitle("el titulo");
		  payload.badge(1);
		  payload.alertAction("Si o no?");
		  payload.sound("default");
		  payload.customField("micampo", "valor");
		  //payload.
			
		  String payloadString = payload.build();
		System.out.println(payloadString);
		
		
		String token = "fc85574de6588e5926d286743e1b99f5aa8fd5fa1f600612a4b01376695fcad6";
		
		notificationsService.push(token, payloadString);
		
		assert((token!=null));

	}
	
}
