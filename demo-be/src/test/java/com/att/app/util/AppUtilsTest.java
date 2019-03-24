package com.att.app.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AppUtilsTest {
	
	@ParameterizedTest(name = "{index} => url={0}, expectedIsValid={1}")
	@MethodSource("urlDataProvider")
	void isValidUrl(final String url, final boolean expectedIsValid) {
		final boolean isValid = AppUtils.isValidUrl(url);
		assertTrue(isValid == expectedIsValid);
	}
	
	@ParameterizedTest(name = "{index} => text={0}, expectedTokenList={1}")
	@MethodSource("luceneTokenizerDataProvider")
	void tokenizeStringWithLucene(final String text, final List<String> expectedTokenList) throws IOException {
		final List<String> tokens = AppUtils.tokenizeStringWithLucene(text);
		assertIterableEquals(expectedTokenList, tokens);
	}
	
	private static Stream<Arguments> urlDataProvider() {
		return Stream.of(
				Arguments.of("http://www.bbc.com", true),
				Arguments.of("https://www.bbc.com", true),
				Arguments.of("http://bbc.com", true),
				Arguments.of("https://bbc.com", true),
				Arguments.of("bbc.com", false),
				Arguments.of("www.bbc.com", false),
				Arguments.of("www.bbc", false),
				Arguments.of("http://bbc.com/somthing", true)
		);
	}
	
	private static Stream<Arguments> luceneTokenizerDataProvider() {
		return Stream.of(
				Arguments.of("here is a simple text", Arrays.asList("here", "is", "a", "simple", "text")),
				Arguments.of("here is a (simple) text...", Arrays.asList("here", "is", "a", "simple", "text")),
				Arguments.of("here is a \"simple\" text", Arrays.asList("here", "is", "a", "simple", "text")),
				Arguments.of("here is a ivan.stefko@mail.com text", Arrays.asList("here", "is", "a", "ivan.stefko", "mail.com", "text"))
		);
	}
}