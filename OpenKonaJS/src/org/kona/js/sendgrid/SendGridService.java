package org.kona.js.sendgrid;

import com.sendgrid.SendGridException;

public interface SendGridService {

	SendGridService open(Object conf) throws Exception;

	void send(Object map) throws SendGridException, Exception;
}
