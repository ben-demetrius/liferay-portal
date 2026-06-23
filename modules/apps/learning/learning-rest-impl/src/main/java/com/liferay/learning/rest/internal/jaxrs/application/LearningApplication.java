/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.learning.rest.internal.jaxrs.application;

import jakarta.annotation.Generated;

import jakarta.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ben Demetrius
 * @generated
 */
@Component(
	property = {
		"liferay.jackson=false", "osgi.jaxrs.application.base=/learning",
		"osgi.jaxrs.extension.select=(osgi.jaxrs.name=Liferay.Vulcan)",
		"osgi.jaxrs.name=Liferay.Learning.REST"
	},
	service = Application.class
)
@Generated("")
public class LearningApplication extends Application {
}
// LIFERAY-REST-BUILDER-HASH:-627745735