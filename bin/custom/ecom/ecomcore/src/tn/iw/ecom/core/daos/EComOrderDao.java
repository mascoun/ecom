/**
 *
 */
package tn.iw.ecom.core.daos;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.daos.OrderDao;

import java.util.List;


/**
 * @author Loez
 *
 */
public interface EComOrderDao extends OrderDao
{
	public List<OrderModel> findAllOrders();
}
