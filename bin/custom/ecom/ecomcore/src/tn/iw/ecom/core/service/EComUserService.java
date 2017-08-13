/**
 *
 */
package tn.iw.ecom.core.service;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;


/**
 * @author Loez
 *
 */
public interface EComUserService extends UserService
{
	public List<UserModel> getAllUsers();
}
