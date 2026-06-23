<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
List<Book> books = (List<Book>)request.getAttribute("books");
%>

<div class="container-fluid container-fluid-max-xl">

	<% if (books.isEmpty()) { %>

		<div class="alert alert-info">
			There are no books to display.
		</div>

	<% } else { %>

		<table class="table table-autofit table-list table-responsive">
			<thead>
				<tr>
					<th>Book ID</th>
					<th>Title</th>
					<th>Author</th>
					<th>ISBN</th>
				</tr>
			</thead>
			<tbody>

				<% for (Book book : books) { %>

					<tr>
						<td><%= book.getBookId() %></td>
						<td><%= HtmlUtil.escape(book.getTitle()) %></td>
						<td><%= HtmlUtil.escape(book.getAuthor()) %></td>
						<td><%= HtmlUtil.escape(book.getIsbn()) %></td>
					</tr>

				<% } %>

			</tbody>
		</table>

	<% } %>

</div>