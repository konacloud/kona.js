package org.kona.js;

import java.util.HashMap;

import org.kona.js.exceptions.KonaException;

public class OpenKonaFactory extends HashMap<String,String> implements IOpenKonaFactory {

	private static final long serialVersionUID = -8247540165161495010L;

	public OpenKonaFactory() {
	}

	public ImgService img = new ImgServiceImp();

	public void error(String msg) throws KonaException {		
		KonaException e = new KonaException(msg);
		throw e;
	}


}
