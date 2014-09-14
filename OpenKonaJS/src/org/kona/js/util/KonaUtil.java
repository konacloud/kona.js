package org.kona.js.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jdk.nashorn.internal.objects.NativeArray;
import jdk.nashorn.internal.objects.NativeDate;
import jdk.nashorn.internal.runtime.ConsString;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class KonaUtil {

	public static KonaUtil getNewInstance() {
		return new KonaUtil();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object parseObjectToKonaDO(Object obj) throws Exception {

		System.out.println("a" + obj.getClass().toString());
		System.out.println("a" + obj.getClass());
		
		try {
			
			
			boolean isArray = false;
			if (obj instanceof jdk.nashorn.api.scripting.ScriptObjectMirror) {
				isArray = ((jdk.nashorn.api.scripting.ScriptObjectMirror) obj)
						.isArray();
			}

			if (obj.getClass().toString()
					.contains("jdk.nashorn.internal.scripts.JO")
					|| (obj.getClass()
							.toString()
							.contains(
									"jdk.nashorn.api.scripting.ScriptObjectMirror") && !isArray)) {

				Method m1 = obj.getClass().getMethod("keySet", null);

				Set<String> result = (Set<String>) m1.invoke(obj, null);

				DBObject db = new BasicDBObject();
				Method m2 = obj.getClass().getMethod("get", Object.class);
				for (Object key : result) {
					Object res = m2.invoke(obj, key);

					Object value = res;

					if (res.getClass()!=null && res.getClass()
							.toString()
							.contains("jdk.nashorn.internal.objects.NativeDate")) {

						jdk.nashorn.internal.objects.NativeDate na = (NativeDate) res;
						Date date = getDateFromNashornNativeDate(na);

						db.put(key.toString(), date);

					} else if (res.getClass()!=null && res.getClass().toString()
							.contains("jdk.nashorn.internal.scripts.JO")
							|| res.getClass()
									.toString()
									.contains(
											"jdk.nashorn.api.scripting.ScriptObjectMirror")) {
						db.put(key.toString(), parseObjectToKonaDO(res));
					} else {
						db.put(key.toString(), parseObjectToKonaDO(res));
					}

				}

				return db;
			} else if (obj.getClass().toString()
					.contains("jdk.nashorn.internal.objects.NativeArray")
					|| isArray) {

				if (isArray) {
					Collection<Object> values = ((jdk.nashorn.api.scripting.ScriptObjectMirror) obj)
							.values();

					List list = new ArrayList();
					for (Object objA : values) {
						list.add(parseObjectToKonaDO(objA));
					}
					return list;

				}
				jdk.nashorn.internal.objects.NativeArray arr = (NativeArray) obj;

				BasicDBList list = new BasicDBList();

				Object[] array = arr.getArray().asObjectArray();
				for (Object o : array) {
					System.out.println(o);
					list.add(o);
				}
				return array;
			} else if (obj.getClass().toString()
					.contains("jdk.nashorn.internal.runtime.ConsString")) {
				jdk.nashorn.internal.runtime.ConsString nash = (ConsString) obj;
				return nash.toString();
			} else {
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return obj.toString();
		}

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
