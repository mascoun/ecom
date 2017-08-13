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
package tn.iw.ecom.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import tn.iw.ecom.fulfilmentprocess.constants.EcomFulfilmentProcessConstants;

@SuppressWarnings("PMD")
public class EcomFulfilmentProcessManager extends GeneratedEcomFulfilmentProcessManager
{
	public static final EcomFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (EcomFulfilmentProcessManager) em.getExtension(EcomFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
