/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.learning.service.persistence.impl;

import com.liferay.learning.exception.NoSuchBookException;
import com.liferay.learning.model.Book;
import com.liferay.learning.model.BookTable;
import com.liferay.learning.model.impl.BookImpl;
import com.liferay.learning.model.impl.BookModelImpl;
import com.liferay.learning.service.persistence.BookPersistence;
import com.liferay.learning.service.persistence.BookUtil;
import com.liferay.learning.service.persistence.impl.constants.LearningPersistenceConstants;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.CollectionPersistenceFinder;
import com.liferay.portal.kernel.service.persistence.impl.FinderColumn;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the book service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = BookPersistence.class)
public class BookPersistenceImpl
	extends BasePersistenceImpl<Book, NoSuchBookException>
	implements BookPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>BookUtil</code> to access the book persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		BookImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private CollectionPersistenceFinder<Book, NoSuchBookException>
		_collectionPersistenceFinderByGroupId;

	/**
	 * Returns an ordered range of all the books where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BookModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of books
	 * @param end the upper bound of the range of books (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching books
	 */
	@Override
	public List<Book> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Book> orderByComparator, boolean useFinderCache) {

		return _collectionPersistenceFinderByGroupId.find(
			finderCache, new Object[] {groupId}, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first book in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching book
	 * @throws NoSuchBookException if a matching book could not be found
	 */
	@Override
	public Book findByGroupId_First(
			long groupId, OrderByComparator<Book> orderByComparator)
		throws NoSuchBookException {

		return _collectionPersistenceFinderByGroupId.findFirst(
			finderCache, new Object[] {groupId}, orderByComparator);
	}

	/**
	 * Returns the first book in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching book, or <code>null</code> if a matching book could not be found
	 */
	@Override
	public Book fetchByGroupId_First(
		long groupId, OrderByComparator<Book> orderByComparator) {

		return _collectionPersistenceFinderByGroupId.fetchFirst(
			finderCache, new Object[] {groupId}, orderByComparator);
	}

	/**
	 * Removes all the books where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		_collectionPersistenceFinderByGroupId.remove(
			finderCache, new Object[] {groupId});
	}

	/**
	 * Returns the number of books where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching books
	 */
	@Override
	public int countByGroupId(long groupId) {
		return _collectionPersistenceFinderByGroupId.count(
			finderCache, new Object[] {groupId});
	}

	private CollectionPersistenceFinder<Book, NoSuchBookException>
		_collectionPersistenceFinderByG_T;

	/**
	 * Returns an ordered range of all the books where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BookModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of books
	 * @param end the upper bound of the range of books (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching books
	 */
	@Override
	public List<Book> findByG_T(
		long groupId, String title, int start, int end,
		OrderByComparator<Book> orderByComparator, boolean useFinderCache) {

		return _collectionPersistenceFinderByG_T.find(
			finderCache, new Object[] {groupId, title}, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first book in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching book
	 * @throws NoSuchBookException if a matching book could not be found
	 */
	@Override
	public Book findByG_T_First(
			long groupId, String title,
			OrderByComparator<Book> orderByComparator)
		throws NoSuchBookException {

		return _collectionPersistenceFinderByG_T.findFirst(
			finderCache, new Object[] {groupId, title}, orderByComparator);
	}

	/**
	 * Returns the first book in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching book, or <code>null</code> if a matching book could not be found
	 */
	@Override
	public Book fetchByG_T_First(
		long groupId, String title, OrderByComparator<Book> orderByComparator) {

		return _collectionPersistenceFinderByG_T.fetchFirst(
			finderCache, new Object[] {groupId, title}, orderByComparator);
	}

	/**
	 * Removes all the books where groupId = &#63; and title = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 */
	@Override
	public void removeByG_T(long groupId, String title) {
		_collectionPersistenceFinderByG_T.remove(
			finderCache, new Object[] {groupId, title});
	}

	/**
	 * Returns the number of books where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the number of matching books
	 */
	@Override
	public int countByG_T(long groupId, String title) {
		return _collectionPersistenceFinderByG_T.count(
			finderCache, new Object[] {groupId, title});
	}

	public BookPersistenceImpl() {
		setModelClass(Book.class);

		setModelImplClass(BookImpl.class);
		setModelPKClass(long.class);

		setTable(BookTable.INSTANCE);
	}

	/**
	 * Creates a new book with the primary key. Does not add the book to the database.
	 *
	 * @param bookId the primary key for the new book
	 * @return the new book
	 */
	@Override
	public Book create(long bookId) {
		Book book = new BookImpl();

		book.setNew(true);
		book.setPrimaryKey(bookId);

		book.setCompanyId(CompanyThreadLocal.getCompanyId());

		return book;
	}

	/**
	 * Removes the book with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bookId the primary key of the book
	 * @return the book that was removed
	 * @throws NoSuchBookException if a book with the primary key could not be found
	 */
	@Override
	public Book remove(long bookId) throws NoSuchBookException {
		return remove((Serializable)bookId);
	}

	@Override
	protected Book removeImpl(Book book) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(book)) {
				book = (Book)session.get(
					BookImpl.class, book.getPrimaryKeyObj());
			}

			if (book != null) {
				session.delete(book);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (book != null) {
			clearCache(book);
		}

		return book;
	}

	@Override
	public Book updateImpl(Book book) {
		boolean isNew = book.isNew();

		if (!(book instanceof BookModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(book.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(book);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in book proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Book implementation " +
					book.getClass());
		}

		BookModelImpl bookModelImpl = (BookModelImpl)book;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (book.getCreateDate() == null)) {
			if (serviceContext == null) {
				book.setCreateDate(date);
			}
			else {
				book.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!bookModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				book.setModifiedDate(date);
			}
			else {
				book.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(book);
			}
			else {
				book = (Book)session.merge(book);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		cacheUniqueFindersResult(book, false);

		if (isNew) {
			book.setNew(false);
		}

		book.resetOriginalValues();

		return book;
	}

	/**
	 * Returns the book with the primary key or throws a <code>NoSuchBookException</code> if it could not be found.
	 *
	 * @param bookId the primary key of the book
	 * @return the book
	 * @throws NoSuchBookException if a book with the primary key could not be found
	 */
	@Override
	public Book findByPrimaryKey(long bookId) throws NoSuchBookException {
		return findByPrimaryKey((Serializable)bookId);
	}

	/**
	 * Returns the book with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param bookId the primary key of the book
	 * @return the book, or <code>null</code> if a book with the primary key could not be found
	 */
	@Override
	public Book fetchByPrimaryKey(long bookId) {
		return fetchByPrimaryKey((Serializable)bookId);
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "bookId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_BOOK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return BookModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the book persistence.
	 */
	@Activate
	public void activate() {
		_collectionPersistenceFinderByGroupId =
			new CollectionPersistenceFinder<>(
				this,
				new FinderPath(
					FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
					new String[] {
						Long.class.getName(), Integer.class.getName(),
						Integer.class.getName(),
						OrderByComparator.class.getName()
					},
					new String[] {"groupId"}, true),
				new FinderPath(
					FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
					new String[] {Long.class.getName()},
					new String[] {"groupId"}, true),
				new FinderPath(
					FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
					new String[] {Long.class.getName()},
					new String[] {"groupId"}, false),
				_SQL_SELECT_BOOK_WHERE, _SQL_COUNT_BOOK_WHERE,
				BookModelImpl.ORDER_BY_JPQL, _ENTITY_ALIAS_PREFIX, "",
				new FinderColumn<>(
					"book.", "groupId", FinderColumn.Type.LONG, "=", true, true,
					Book::getGroupId));

		_collectionPersistenceFinderByG_T = new CollectionPersistenceFinder<>(
			this,
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_T",
				new String[] {
					Long.class.getName(), String.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				},
				new String[] {"groupId", "title"}, true),
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_T",
				new String[] {Long.class.getName(), String.class.getName()},
				new String[] {"groupId", "title"}, 0, 2, true, null),
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_T",
				new String[] {Long.class.getName(), String.class.getName()},
				new String[] {"groupId", "title"}, 0, 2, false, null),
			_SQL_SELECT_BOOK_WHERE, _SQL_COUNT_BOOK_WHERE,
			BookModelImpl.ORDER_BY_JPQL, _ENTITY_ALIAS_PREFIX, "",
			new FinderColumn<>(
				"book.", "groupId", FinderColumn.Type.LONG, "=", true, true,
				Book::getGroupId),
			new FinderColumn<>(
				"book.", "title", FinderColumn.Type.STRING, "=", true, true,
				Book::getTitle));

		BookUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		BookUtil.setPersistence(null);

		entityCache.removeCache(BookImpl.class.getName());
	}

	@Override
	@Reference(
		target = LearningPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = LearningPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = LearningPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _ENTITY_ALIAS_PREFIX =
		BookModelImpl.ENTITY_ALIAS + ".";

	private static final String _SQL_SELECT_BOOK = "SELECT book FROM Book book";

	private static final String _SQL_SELECT_BOOK_WHERE =
		"SELECT book FROM Book book WHERE ";

	private static final String _SQL_COUNT_BOOK_WHERE =
		"SELECT COUNT(book) FROM Book book WHERE ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Book exists with the key {";

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-717323480