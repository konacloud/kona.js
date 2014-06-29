package org.kona.js.sendgrid;

import java.util.Map;

import com.sendgrid.SendGridException;

public interface SendGridService {

	void send(Map map) throws SendGridException;
}
