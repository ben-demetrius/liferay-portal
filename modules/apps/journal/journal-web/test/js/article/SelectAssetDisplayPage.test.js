/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

// eslint-disable-next-line @liferay/portal/no-cross-module-deep-import
import {checkAccessibility} from '@liferay/layout-js-components-web/test/__lib__/index';

import '@testing-library/jest-dom';
import {render} from '@testing-library/react';
import React from 'react';

import SelectAssetDisplayPage from '../../../src/main/resources/META-INF/resources/js/article/SelectAssetDisplayPage';

const DEFAULT_PROPS = {
	assetDisplayPageType: 0,
	portletNamespace: 'namespace',
};

const renderComponent = () =>
	render(<SelectAssetDisplayPage {...DEFAULT_PROPS} />);

describe('SelectAssetDisplayPage', () => {
	it('carries the display page state in inputs that are not form controls', () => {
		const {container} = renderComponent();

		['assetDisplayPageId', 'displayPageType', 'layoutUuid'].forEach(
			(name) => {
				expect(
					container.querySelector(
						`input[name="${DEFAULT_PROPS.portletNamespace}${name}"]`
					)
				).toHaveAttribute('type', 'hidden');
			}
		);
	});

	it('has no accessibility violations', async () => {
		const {container} = renderComponent();

		await checkAccessibility({bestPractices: true, context: container});
	});
});
