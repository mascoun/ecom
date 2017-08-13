/**
 *
 */
package tn.iw.ecom.core.impl;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.daos.impl.DefaultUserDao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import tn.iw.ecom.core.daos.EComUserDao;



/**
 * @author Loez
 *
 */
public class DefaultEComUserDao extends DefaultUserDao implements EComUserDao
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(DefaultEComOrderDao.class);

	@Autowired
	private FlexibleSearchService flexibleSearchService;

	@Override
	public List<UserModel> findAllUsers()
	{

		// Build a query for the flexible search.
		final String queryString = //
				"SELECT {p:" + UserModel.PK + "} "//
						+ "FROM {" + UserModel._TYPECODE + " AS p} ";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString);

		// Note that we could specify paginating logic by providing a start and count variable (commented out below)
		// This can provide a safeguard against returning very large amounts of data, or hogging the database when there are
		// for example millions of items being returned.
		// As we know that there are only a few persisted stadiums in this use case we do not need to provide this.

		//query.setStart(start);
		//query.setCount(count);

		// Return the list of StadiumModels.
		return flexibleSearchService.<UserModel> search(query).getResult();
	}

}
