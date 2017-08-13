/**
 *
 */
package tn.iw.ecom.core.impl;


import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.impl.DefaultOrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import tn.iw.ecom.core.daos.EComOrderDao;
import tn.iw.ecom.core.service.EComOrderService;



/**
 * @author Loez
 *
 */
public class DefaultEComOrderService extends DefaultOrderService implements EComOrderService
{
	private EComOrderDao eComOrderDao;

	@Override
	public List<OrderModel> getAllOrders()
	{
		return eComOrderDao.findAllOrders();
	}

	public EComOrderDao geteComOrderDao()
	{
		return eComOrderDao;
	}

	@Required
	public void seteComOrderDao(final EComOrderDao eComOrderDao)
	{
		this.eComOrderDao = eComOrderDao;
	}







}
