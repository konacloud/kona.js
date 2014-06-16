package org.kona.js;

import java.util.HashMap;

public class ParserServiceImp extends HashMap<String, String> implements
		ImgService {

	public String qr(String text) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] asd){
		
		org.apache.commons.codec.language.Soundex encoder = new org.apache.commons.codec.language.Soundex();
		String result = encoder.encode("Información sobre Archivos Históricos");
		
		System.out.println(result);
		
		
		
		result = encoder.encode("Información");
		System.out.println(result);
		
		String f = "Información"; 
		f = f.replace ('á','a'); 
		f = f.replace ('é','e'); 
		f = f.replace ('í','i'); 
		f = f.replace ('ó','o'); 
		f = f.replace ('ú','u'); 
		
		System.out.println(f);
	}

}
