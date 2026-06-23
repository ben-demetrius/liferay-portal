/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.learning.rest.internal.graphql.servlet.v1_0;

import com.liferay.learning.rest.internal.graphql.mutation.v1_0.Mutation;
import com.liferay.learning.rest.internal.graphql.query.v1_0.Query;
import com.liferay.learning.rest.internal.resource.v1_0.BookResourceImpl;
import com.liferay.learning.rest.resource.v1_0.BookResource;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import jakarta.annotation.Generated;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Ben Demetrius
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setBookResourceComponentServiceObjects(
			_bookResourceComponentServiceObjects);

		Query.setBookResourceComponentServiceObjects(
			_bookResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "Liferay.Learning.REST";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/learning-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#createBooksPageExportBatch",
						new ObjectValuePair<>(
							BookResourceImpl.class,
							"postBooksPageExportBatch"));

					put(
						"query#book",
						new ObjectValuePair<>(
							BookResourceImpl.class, "getBook"));
					put(
						"query#books",
						new ObjectValuePair<>(
							BookResourceImpl.class, "getBooksPage"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<BookResource>
		_bookResourceComponentServiceObjects;

}
// LIFERAY-REST-BUILDER-HASH:-1598917479