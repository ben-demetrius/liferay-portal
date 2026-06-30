/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import '@testing-library/jest-dom';
import {render, screen} from '@testing-library/react';
import React from 'react';

import ScheduleOptions from '../../src/main/resources/META-INF/resources/js/ScheduleOptions';

const DEFAULT_PROPS = {
	displayDate: null,
	error: '',
	formId: 'formId',
	portletNamespace: 'portletNamespace',
	setDisplayDate: jest.fn(),
	setError: jest.fn(),
	timeZone: {name: 'UTC'},
};

describe('ScheduleOptions', () => {
	it('shows an AM/PM time hint when the locale uses a 12-hour clock', () => {
		render(<ScheduleOptions {...DEFAULT_PROPS} use12Hours />);

		expect(
			screen.getByPlaceholderText('yyyy-mm-dd-hh-mm-am-pm')
		).toBeInTheDocument();
	});

	it('shows a 24-hour time hint when the locale uses a 24-hour clock', () => {
		render(<ScheduleOptions {...DEFAULT_PROPS} use12Hours={false} />);

		expect(
			screen.getByPlaceholderText('yyyy-mm-dd-hh-mm')
		).toBeInTheDocument();
	});

	it('accepts a complete AM/PM date as valid when the clock is 12-hour', () => {
		const setError = jest.fn();

		render(
			<ScheduleOptions
				{...DEFAULT_PROPS}
				displayDate="2024-01-15 02:30 PM"
				setError={setError}
				use12Hours
			/>
		);

		expect(setError).toHaveBeenCalledWith('');
		expect(setError).not.toHaveBeenCalledWith('please-enter-a-valid-date');
	});

	it('accepts a complete 24-hour date as valid when the clock is 24-hour', () => {
		const setError = jest.fn();

		render(
			<ScheduleOptions
				{...DEFAULT_PROPS}
				displayDate="2024-01-15 14:30"
				setError={setError}
				use12Hours={false}
			/>
		);

		expect(setError).toHaveBeenCalledWith('');
	});

	it('flags an incomplete date as invalid', () => {
		const setError = jest.fn();

		render(
			<ScheduleOptions
				{...DEFAULT_PROPS}
				displayDate="2024-01-15"
				setError={setError}
				use12Hours
			/>
		);

		expect(setError).toHaveBeenCalledWith('please-enter-a-valid-date');
	});
});
