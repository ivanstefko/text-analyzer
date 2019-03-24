package com.att.app.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class AppUtils {
	
	private static final String URL_PATTERN = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
	
	private AppUtils() {
		// prevent instantiation
	}
	
	public static boolean isValidUrl(final String url) {
		boolean toReturn = false;
		
		if (StringUtils.isNotBlank(url)) {
			toReturn = Pattern.compile(URL_PATTERN).matcher(url).matches();
		}
		return toReturn;
	}
	
	
	public static List<String> tokenizeStringWithLucene(final String text) throws IOException {
		Validate.notEmpty(text, "Text to Lucene analyse can not be null or empty");
		final Analyzer analyzer = new StandardAnalyzer();
		final List<String> result = new ArrayList<>();
		
		final TokenStream stream = analyzer.tokenStream(null, new StringReader(text));
		stream.reset();
		while (stream.incrementToken()) {
			result.add(stream.getAttribute(CharTermAttribute.class).toString());
		}
		
		return result;
	}
}
