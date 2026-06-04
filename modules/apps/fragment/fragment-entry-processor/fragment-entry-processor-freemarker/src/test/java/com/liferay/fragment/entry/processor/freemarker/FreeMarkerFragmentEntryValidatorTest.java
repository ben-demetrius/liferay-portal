/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.entry.processor.freemarker;

import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.fragment.entry.processor.freemarker.internal.configuration.FreeMarkerFragmentEntryProcessorConfiguration;
import com.liferay.fragment.util.configuration.FragmentEntryConfigurationParser;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Ben Demetrius
 */
public class FreeMarkerFragmentEntryValidatorTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_freeMarkerFragmentEntryValidator =
			new FreeMarkerFragmentEntryValidator();

		ConfigurationProvider configurationProvider = Mockito.mock(
			ConfigurationProvider.class);

		FreeMarkerFragmentEntryProcessorConfiguration
			freeMarkerFragmentEntryProcessorConfiguration = Mockito.mock(
				FreeMarkerFragmentEntryProcessorConfiguration.class);

		Mockito.when(
			freeMarkerFragmentEntryProcessorConfiguration.enable()
		).thenReturn(
			true
		);

		Mockito.when(
			configurationProvider.getCompanyConfiguration(
				Mockito.eq(FreeMarkerFragmentEntryProcessorConfiguration.class),
				Mockito.anyLong())
		).thenReturn(
			freeMarkerFragmentEntryProcessorConfiguration
		);

		_fragmentEntryConfigurationParser = Mockito.mock(
			FragmentEntryConfigurationParser.class);

		ReflectionTestUtil.setFieldValue(
			_freeMarkerFragmentEntryValidator, "_configurationProvider",
			configurationProvider);
		ReflectionTestUtil.setFieldValue(
			_freeMarkerFragmentEntryValidator,
			"_fragmentEntryConfigurationParser",
			_fragmentEntryConfigurationParser);
		ReflectionTestUtil.setFieldValue(
			_freeMarkerFragmentEntryValidator, "_language",
			Mockito.mock(Language.class));
	}

	@After
	public void tearDown() {
		ExportImportThreadLocal.setLayoutImportInProcess(false);

		ServiceContextThreadLocal.popServiceContext();
	}

	@Test
	public void testValidateFragmentEntryHTMLSkipsValidationDuringImport()
		throws Exception {

		// A theme display is present, so only the import guard skips this

		_pushServiceContextWithThemeDisplay();

		ExportImportThreadLocal.setLayoutImportInProcess(true);

		_freeMarkerFragmentEntryValidator.validateFragmentEntryHTML(
			_DYNAMIC_DATA_HTML, Mockito.mock(JSONObject.class), LocaleUtil.US);

		// The validator returned before building the template context

		Mockito.verify(
			_fragmentEntryConfigurationParser, Mockito.never()
		).getConfigurationDefaultValuesJSONObject(
			Mockito.any()
		);
	}

	private void _pushServiceContextWithThemeDisplay() {
		ServiceContext serviceContext = Mockito.mock(ServiceContext.class);

		HttpServletRequest httpServletRequest = Mockito.mock(
			HttpServletRequest.class);

		Mockito.when(
			serviceContext.getRequest()
		).thenReturn(
			httpServletRequest
		);

		Mockito.when(
			httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY)
		).thenReturn(
			new Object()
		);

		ServiceContextThreadLocal.pushServiceContext(serviceContext);
	}

	private static final String _DYNAMIC_DATA_HTML =
		"[#assign restGetSample = restClient.get(\"/headless-delivery\")]" +
			"[#if (restGetSample.taxonomyCategoryBriefs)??]ERROR[/#if]";

	private FragmentEntryConfigurationParser _fragmentEntryConfigurationParser;
	private FreeMarkerFragmentEntryValidator _freeMarkerFragmentEntryValidator;

}