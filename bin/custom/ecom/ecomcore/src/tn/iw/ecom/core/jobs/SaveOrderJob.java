/**
 *
 */
package tn.iw.ecom.core.jobs;


import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import tn.iw.ecom.core.impl.DefaultEComOrderService;


/**
 * @author Loez
 *
 */
public class SaveOrderJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(SaveOrderJob.class);

	@Autowired
	private DefaultEComOrderService eComOrderService;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		LOG.info("Orders");
		final List<OrderModel> orders = eComOrderService.getAllOrders();

		if (saveResult(orders))
		{
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
		else
		{
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
		}
	}

	public boolean saveResult(final List<OrderModel> orders)
	{
		try
		{
			final String seperator = ";\t";
			final File f = new File(
					System.getProperty("user.dir") + "/../../../../data/orders_" + System.currentTimeMillis() + ".csv");
			final FileWriter fw = new FileWriter(f);
			fw.write("Code" + seperator + "User" + seperator + "Mail" + seperator + "Delivery Adress" + seperator + "Payment Adress"
					+ seperator + "Products" + seperator + "Modified Time" + seperator + "Last Login Time" + seperator + "Date"
					+ seperator + "Price\r\n");
			for (final OrderModel order : orders)
			{
				final AddressModel deliveryAdress = order.getDeliveryAddress();
				final AddressModel paymentAdress = order.getPaymentAddress();
				final String str1 = deliveryAdress.getLine1() + " " + deliveryAdress.getStreetname() + " "
						+ deliveryAdress.getCountry().getName() + " " + deliveryAdress.getPhone1();
				final String str2 = paymentAdress.getLine1() + " " + paymentAdress.getStreetname() + " "
						+ paymentAdress.getCountry().getName() + " " + paymentAdress.getPhone1();
				final List<AbstractOrderEntryModel> p = order.getEntries();
				String str3 = "[";
				for (final AbstractOrderEntryModel m : p)
				{
					str3 += m.getProduct().getCode() + ",";
				}
				str3 += "]";
				str3.replace(",]", "]");
				final String str = order.getCode() + seperator + order.getUser().getDisplayName() + seperator
						+ order.getUser().getUid() + seperator + str1 + seperator + str2 + seperator + str3 + seperator
						+ order.getModifiedtime() + seperator + order.getUser().getLastLogin() + seperator + order.getDate() + seperator
						+ order.getTotalPrice() + "\r\n";
				fw.write(str);
			}
			fw.close();
			return true;
		}
		catch (final IOException e)
		{
			LOG.error(e);
			return false;
		}
	}
}