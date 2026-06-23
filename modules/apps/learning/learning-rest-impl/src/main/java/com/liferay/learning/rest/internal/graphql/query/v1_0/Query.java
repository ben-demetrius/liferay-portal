/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.learning.rest.internal.graphql.query.v1_0;

import com.liferay.learning.rest.dto.v1_0.Book;
import com.liferay.learning.rest.resource.v1_0.BookResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import jakarta.annotation.Generated;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ws.rs.core.UriInfo;

import java.util.Map;
import java.util.function.BiFunction;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Ben Demetrius
 * @generated
 */
@Generated("")
public class Query {

	public static void setBookResourceComponentServiceObjects(
		ComponentServiceObjects<BookResource>
			bookResourceComponentServiceObjects) {

		_bookResourceComponentServiceObjects =
			bookResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {book(bookId: ___){author, id, isbn, title}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(description = "Retrieves the book via its ID.")
	public Book book(@GraphQLName("bookId") Long bookId) throws Exception {
		return _applyComponentServiceObjects(
			_bookResourceComponentServiceObjects,
			this::_populateResourceContext,
			bookResource -> bookResource.getBook(bookId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {books{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField(
		description = "Retrieves the books. Results can be paginated."
	)
	public BookPage books() throws Exception {
		return _applyComponentServiceObjects(
			_bookResourceComponentServiceObjects,
			this::_populateResourceContext,
			bookResource -> new BookPage(bookResource.getBooksPage()));
	}

	@GraphQLName("BookPage")
	public class BookPage {

		public BookPage(Page bookPage) {
			actions = bookPage.getActions();

			items = bookPage.getItems();
			lastPage = bookPage.getLastPage();
			page = bookPage.getPage();
			pageSize = bookPage.getPageSize();
			totalCount = bookPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Book> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(BookResource bookResource)
		throws Exception {

		bookResource.setContextAcceptLanguage(_acceptLanguage);
		bookResource.setContextCompany(_company);
		bookResource.setContextHttpServletRequest(_httpServletRequest);
		bookResource.setContextHttpServletResponse(_httpServletResponse);
		bookResource.setContextUriInfo(_uriInfo);
		bookResource.setContextUser(_user);
		bookResource.setGroupLocalService(_groupLocalService);
		bookResource.setResourceActionLocalService(_resourceActionLocalService);
		bookResource.setResourcePermissionLocalService(
			_resourcePermissionLocalService);
		bookResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<BookResource>
		_bookResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction
		<Object, String, com.liferay.portal.kernel.search.filter.Filter>
			_filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private ResourceActionLocalService _resourceActionLocalService;
	private ResourcePermissionLocalService _resourcePermissionLocalService;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, com.liferay.portal.kernel.search.Sort[]>
		_sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}
// LIFERAY-REST-BUILDER-HASH:387099050