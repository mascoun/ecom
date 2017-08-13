/**
 *
 */
package tn.iw.ecom.core.service;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;

import java.util.List;


/**
 * @author Loez
 *
 */
public interface EComOrderService extends OrderService
{
	public List<OrderModel> getAllOrders();
}
