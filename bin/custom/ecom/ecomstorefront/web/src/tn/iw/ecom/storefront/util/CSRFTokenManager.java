/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package tn.iw.ecom.storefront.util;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



/**
 * A manager for the CSRF token for a given session. The {@link #getTokenForSession(HttpSession)} should used to obtain
 * the token value for the current session (and this should be the only way to obtain the token value).
 *
 */
public final class CSRFTokenManager
{

	/**
	 * The token parameter name
	 */
	public static final String CSRF_PARAM_NAME = "CSRFToken";

	/**
	 * The location on the session which stores the token
	 */
	private static final String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getName() + ".tokenval";

	private CSRFTokenManager()
	{
	}

	public static String getTokenForSession(final HttpSession session)
	{
		String token = null;
		// I cannot allow more than one token on a session - in the case of two requests trying to
		// init the token concurrently
		synchronized (session)
		{
			token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
			if (null == token)
			{
				token = UUID.randomUUID().toString();
				session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
			}
		}
		return token;
	}

	/**
	 * Extracts the token value from the request
	 *
	 * @param request
	 * @return the token
	 */
	public static String getTokenFromRequest(final HttpServletRequest request)
	{
		final String parameter = request.getParameter(CSRF_PARAM_NAME);
		return parameter != null ? parameter : request.getHeader(CSRF_PARAM_NAME);
	}
}
