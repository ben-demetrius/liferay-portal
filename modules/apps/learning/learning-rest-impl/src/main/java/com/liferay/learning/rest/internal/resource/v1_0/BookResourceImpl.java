/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.learning.rest.internal.resource.v1_0;

import com.liferay.learning.rest.dto.v1_0.Book;
import com.liferay.learning.rest.resource.v1_0.BookResource;
import com.liferay.learning.service.BookLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.vulcan.pagination.Page;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Ben Demetrius
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/book.properties",
	scope = ServiceScope.PROTOTYPE, service = BookResource.class
)
public class BookResourceImpl extends BaseBookResourceImpl {

	@Override
	public Book getBook(Long bookId) throws Exception {
		return _toBook(_bookLocalService.getBook(bookId));
	}

	@Override
	public Page<Book> getBooksPage() throws Exception {
		return Page.of(
			transform(
				_bookLocalService.getBooks(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS),
				this::_toBook));
	}

	private Book _toBook(com.liferay.learning.model.Book book) {
		return new Book() {
			{
				author = book.getAuthor();
				id = book.getBookId();
				isbn = book.getIsbn();
				title = book.getTitle();
			}
		};
	}

	@Reference
	private BookLocalService _bookLocalService;

}