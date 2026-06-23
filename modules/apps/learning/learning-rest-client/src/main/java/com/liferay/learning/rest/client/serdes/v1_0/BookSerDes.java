/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.learning.rest.client.serdes.v1_0;

import com.liferay.learning.rest.client.dto.v1_0.Book;
import com.liferay.learning.rest.client.json.BaseJSONParser;

import jakarta.annotation.Generated;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Ben Demetrius
 * @generated
 */
@Generated("")
public class BookSerDes {

	public static Book toDTO(String json) {
		BookJSONParser bookJSONParser = new BookJSONParser();

		return bookJSONParser.parseToDTO(json);
	}

	public static Book[] toDTOs(String json) {
		BookJSONParser bookJSONParser = new BookJSONParser();

		return bookJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Book book) {
		if (book == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (book.getAuthor() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"author\": ");

			sb.append("\"");

			sb.append(_escape(book.getAuthor()));

			sb.append("\"");
		}

		if (book.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(book.getId());
		}

		if (book.getIsbn() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"isbn\": ");

			sb.append("\"");

			sb.append(_escape(book.getIsbn()));

			sb.append("\"");
		}

		if (book.getTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"title\": ");

			sb.append("\"");

			sb.append(_escape(book.getTitle()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		BookJSONParser bookJSONParser = new BookJSONParser();

		return bookJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Book book) {
		if (book == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (book.getAuthor() == null) {
			map.put("author", null);
		}
		else {
			map.put("author", String.valueOf(book.getAuthor()));
		}

		if (book.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(book.getId()));
		}

		if (book.getIsbn() == null) {
			map.put("isbn", null);
		}
		else {
			map.put("isbn", String.valueOf(book.getIsbn()));
		}

		if (book.getTitle() == null) {
			map.put("title", null);
		}
		else {
			map.put("title", String.valueOf(book.getTitle()));
		}

		return map;
	}

	public static class BookJSONParser extends BaseJSONParser<Book> {

		@Override
		protected Book createDTO() {
			return new Book();
		}

		@Override
		protected Book[] createDTOArray(int size) {
			return new Book[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "author")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "isbn")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			Book book, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "author")) {
				if (jsonParserFieldValue != null) {
					book.setAuthor((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					book.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "isbn")) {
				if (jsonParserFieldValue != null) {
					book.setIsbn((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				if (jsonParserFieldValue != null) {
					book.setTitle((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			sb.append(_toJSON(value));

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _toJSON(Object value) {
		if (value == null) {
			return "null";
		}

		if (value instanceof Map) {
			return _toJSON((Map)value);
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			StringBuilder sb = new StringBuilder("[");

			Object[] values = (Object[])value;

			for (int i = 0; i < values.length; i++) {
				sb.append(_toJSON(values[i]));

				if ((i + 1) < values.length) {
					sb.append(", ");
				}
			}

			sb.append("]");

			return sb.toString();
		}

		if (value instanceof String) {
			return "\"" + _escape(value) + "\"";
		}

		return String.valueOf(value);
	}

}
// LIFERAY-REST-BUILDER-HASH:-332488667