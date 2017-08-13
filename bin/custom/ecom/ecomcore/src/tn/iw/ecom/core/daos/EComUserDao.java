/**
 *
 */
package tn.iw.ecom.core.daos;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.daos.UserDao;

import java.util.List;


/**
 * @author Loez
 *
 */
public interface EComUserDao extends UserDao
{
	public List<UserModel> findAllUsers();
}
