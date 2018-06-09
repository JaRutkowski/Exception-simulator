package com.javafee.excsim.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import lombok.Getter;
import lombok.Setter;

public final class SystemProperties {

	@Getter
	@Setter
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(Constans.PL_LANGUAGE_RESOURCE_BUNDLE,
			new Locale(Constans.APPLICATION_LANGUAGE));

	public static void setResourceBundleLanguage(String language) {
		resourceBundle = ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE, new Locale(language));
	}

	public static void setResourceBundleLanguage(Locale locale) {
		resourceBundle = ResourceBundle.getBundle(Constans.LANGUAGE_RESOURCE_BUNDLE, locale);
	}

}
