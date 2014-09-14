package test.img;

import org.kona.js.ImgService;
import org.kona.js.OpenKonaFactory;

public class TestImgService {

	//@Test
	public void testQr() throws Exception {
		
		OpenKonaFactory kona = new OpenKonaFactory();
		ImgService service = kona.img;
		
		String result = service.qr("kona");
		
		assert((result!=null));
		
	}

	
}
