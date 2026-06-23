/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.learning.rest.client.dto.v1_0;

import com.liferay.learning.rest.client.function.UnsafeSupplier;
import com.liferay.learning.rest.client.serdes.v1_0.BookSerDes;

import jakarta.annotation.Generated;

import java.io.Serializable;

import java.util.Objects;

/**
 * @author Ben Demetrius
 * @generated
 */
@Generated("")
public class Book implements Cloneable, Serializable {

	public static Book toDTO(String json) {
		return BookSerDes.toDTO(json);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setAuthor(
		UnsafeSupplier<String, Exception> authorUnsafeSupplier) {

		try {
			author = authorUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setIsbn(UnsafeSupplier<String, Exception> isbnUnsafeSupplier) {
		try {
			isbn = isbnUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String isbn;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTitle(
		UnsafeSupplier<String, Exception> titleUnsafeSupplier) {

		try {
			title = titleUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String title;

	@Override
	public Book clone() throws CloneNotSupportedException {
		return (Book)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Book)) {
			return false;
		}

		Book book = (Book)object;

		return Objects.equals(toString(), book.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return BookSerDes.toJSON(this);
	}

}
// LIFERAY-REST-BUILDER-HASH:690317299