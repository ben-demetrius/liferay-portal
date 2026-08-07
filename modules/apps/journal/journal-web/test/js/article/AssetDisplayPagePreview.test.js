/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom';
import {render, screen} from '@testing-library/react';
import React from 'react';

import AssetDisplayPagePreview from '../../../src/main/resources/META-INF/resources/js/article/AssetDisplayPagePreview';

const DEFAULT_PROPS = {
	portletNamespace: 'namespace',
	sites: [],
};

const renderComponent = () =>
	render(<AssetDisplayPagePreview {...DEFAULT_PROPS} />, {
		baseElement: document.body,
	});

describe('AssetDisplayPagePreview', () => {
	beforeEach(() => {

		// The edit article form renders this field, and PreviewButton reads it.

		const articleIdInput = document.createElement('input');

		articleIdInput.id = `${DEFAULT_PROPS.portletNamespace}articleId`;

		document.body.appendChild(articleIdInput);

		Liferay.componentReady = jest.fn(() => Promise.resolve({}));
	});

	afterEach(() => {
		document
			.getElementById(`${DEFAULT_PROPS.portletNamespace}articleId`)
			?.remove();

		delete Liferay.componentReady;
	});

	it('gives the site selector an accessible name', () => {
		renderComponent();

		expect(
			screen.getByLabelText('site', {selector: 'button'})
		).toBeInTheDocument();
	});

	it('names the site list after the site label', () => {
		const {baseElement} = renderComponent();

		const siteLabel = screen.getByText('site');

		expect(
			baseElement.querySelectorAll(`[aria-labelledby~="${siteLabel.id}"]`)
		).not.toHaveLength(0);
	});
});
