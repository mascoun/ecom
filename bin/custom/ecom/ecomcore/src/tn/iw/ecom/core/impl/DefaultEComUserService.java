/**
 *
 */
package tn.iw.ecom.core.impl;


import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.impl.DefaultUserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import tn.iw.ecom.core.daos.EComUserDao;
import tn.iw.ecom.core.service.EComUserService;



/**
 * @author Loez
 *
 */
public class DefaultEComUserService extends DefaultUserService implements EComUserService
{
	private EComUserDao eComUserDao;

	@Override
	public List<UserModel> getAllUsers()
	{
		return eComUserDao.findAllUsers();
	}

	public EComUserDao geteComUserDao()
	{
		return eComUserDao;
	}

	@Required
	public void seteComUserDao(final EComUserDao eComUserDao)
	{
		this.eComUserDao = eComUserDao;
	}







}
