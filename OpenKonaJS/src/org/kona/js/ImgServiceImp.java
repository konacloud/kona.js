package org.kona.js;

import java.util.HashMap;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class ImgServiceImp extends HashMap<String, String> implements
		ImgService {

	public static void main(String[] arg) throws Exception {

		ImgServiceImp img = new ImgServiceImp();
		System.out.println(img.qr("a"));

	}

	public String qr(String text) throws Exception {

		HttpResponse<JsonNode> request = Unirest
				.get("https://mutationevent-qr-code-generator.p.mashape.com/generate.php?content="
						+ text
						+ "&quality=%3Cquality%3E&size=4&type=%3Ctype%3E")
				.header("X-Mashape-Authorization",
						"7GmJkzjelE65U91UATosJYTTRXLvvAH7").asJson();

		String str = request.getBody().toString();

		return str;

	}

}
