/**
 *
 */
package tn.iw.ecom.core.jobs;


import de.hybris.platform.core.model.user.UserModel;
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

import tn.iw.ecom.core.impl.DefaultEComUserService;


/**
 * @author Loez
 *
 */
public class SaveUserJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(SaveOrderJob.class);

	@Autowired
	private DefaultEComUserService eComuserService;

	@Override
	public PerformResult perform(final CronJobModel cronJob)
	{
		LOG.info("User");
		final List<UserModel> users = eComuserService.getAllUsers();

		if (saveResult(users))
		{
			return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
		}
		else
		{
			return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
		}
	}

	public boolean saveResult(final List<UserModel> users)
	{
		try
		{
			final File f = new File(
					System.getProperty("user.dir") + "/../../../../data/users_" + System.currentTimeMillis() + ".csv");
			final FileWriter fw = new FileWriter(f);
			fw.write("Display Name;\tName\tEncoded Password\tLast Login\r\n");
			for (final UserModel user : users)
			{
				final String seperator = "\t;";
				final String str = user.getDisplayName() + seperator + user.getName() + seperator + user.getEncodedPassword()
						+ seperator + user.getLastLogin();
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