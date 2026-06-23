/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.learning.web.internal.portlet;

import com.liferay.learning.service.BookLocalService;
import com.liferay.learning.web.internal.constants.LearningPortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import jakarta.portlet.Portlet;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;

import java.io.IOException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ben Demetrius
 */
@Component(
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=learning-web",
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"jakarta.portlet.display-name=Learning Books",
		"jakarta.portlet.init-param.template-path=/META-INF/resources/",
		"jakarta.portlet.init-param.view-template=/view.jsp",
		"jakarta.portlet.name=" + LearningPortletKeys.LEARNING,
		"jakarta.portlet.resource-bundle=content.Language",
		"jakarta.portlet.security-role-ref=guest,power-user,user",
		"jakarta.portlet.version=4.0"
	},
	service = Portlet.class
)
public class LearningPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			"books",
			_bookLocalService.getBooks(QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private BookLocalService _bookLocalService;

}