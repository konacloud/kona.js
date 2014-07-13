package org.kona.js;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jdk.nashorn.internal.objects.NativeDate;
import jdk.nashorn.internal.scripts.JO;

import org.kona.js.exceptions.KonaException;

public class KonaNashormConverter {

	public static KonaNashormConverter getNewInstance() {
		return new KonaNashormConverter();
	}

	public Map parseObjectToKonaDO(Object obj) throws Exception {

		if (obj instanceof HashMap) {
			return (HashMap) obj;
		}

		// ahora nos fijamos si es un objeto de java nashorm

		if (obj instanceof JO) {
			JO javaObj = (JO) obj;

			Map db = new HashMap();

			for (Object key : javaObj.keySet()) {
				db.put(key.toString(), javaObj.get(key));
			}

			return db;
		}
		
		System.out.println(obj);
		System.out.println(obj.getClass());
		
		
		if (obj.getClass().toString().contains("jdk.nashorn.internal.scripts")
				|| obj.getClass()
						.toString()
						.contains(
								"jdk.nashorn.api.scripting.ScriptObjectMirror")) {

			Method m1 = obj.getClass().getMethod("keySet", null);
			

			Set<String> result = (Set<String>) m1.invoke(obj, null);

			Map db = new HashMap();
			Method m2 = obj.getClass().getMethod("get", Object.class);
			for (Object key : result) {
				Object res = m2.invoke(obj, key);

				Object value = res;

				System.out.println(res.getClass().toString());
				if (res.getClass().toString()
						.contains("jdk.nashorn.internal.objects.NativeDate")) {

					jdk.nashorn.internal.objects.NativeDate na = (NativeDate) res;
					Date date = getDateFromNashornNativeDate(na);

					db.put(key.toString(), date);

				} else {
					db.put(key.toString(), value);
				}

			}

			return db;
		}

		return null;

	}

	private Date getDateFromNashornNativeDate(NativeDate na) {

		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(Calendar.YEAR, ((Double) NativeDate.getYear(na)).intValue());
		cal.set(Calendar.MONTH, ((Double) NativeDate.getMonth(na)).intValue());
		cal.set(Calendar.DAY_OF_MONTH,
				((Double) NativeDate.getDay(na)).intValue());
		cal.set(Calendar.HOUR, ((Double) NativeDate.getHours(na)).intValue());
		cal.set(Calendar.MINUTE,
				((Double) NativeDate.getMinutes(na)).intValue());
		cal.set(Calendar.SECOND,
				((Double) NativeDate.getSeconds(na)).intValue());
		return cal.getTime();
	}

	/*
	 * este metodo no puede tirar error nunca
	 */
	public String getStringError(Exception e) {
		String msg = e.getLocalizedMessage();

		try {
			msg = e.getCause().getLocalizedMessage();
			msg = msg.replace("org.kona.js.exceptions.KonaException:", "");
		} catch (Exception e1) {
		}

		return msg;
	}
}
