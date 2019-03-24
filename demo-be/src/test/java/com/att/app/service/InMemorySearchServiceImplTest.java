package com.att.app.service;

import com.att.app.dto.CharOccurenceEntry;
import com.att.app.dto.TokenMaxLengthEntry;
import com.att.app.dto.TokenOccurrenceEntry;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemorySearchServiceImplTest {
	InMemorySearchServiceImpl service;
	
	@BeforeEach
	void setUp() {
		this.service = new InMemorySearchServiceImpl();
	}
	
	@Test
	void getMaxOccurrencingChars() {
		final List<String> tokens = Arrays.asList("This", "iS", "a", "teSt", "t");
		
		final LinkedList<CharOccurenceEntry> charsOccurrence = this.service.getCharsOccurrence(tokens);

		assertEquals('t', charsOccurrence.get(0).getToken());
		assertEquals(4, charsOccurrence.get(0).getOccurrence().intValue());
		
		assertEquals('s', charsOccurrence.get(1).getToken());
		assertEquals(3, charsOccurrence.get(1).getOccurrence().intValue());
		
		assertEquals('i', charsOccurrence.get(2).getToken());
		assertEquals(2, charsOccurrence.get(2).getOccurrence().intValue());
		
		final char[] array = { 'e', 'h', 'a' };
		
		charsOccurrence.stream()
				.filter(entry -> ArrayUtils.contains(array, entry.getToken()))
				.forEach(item -> {
					assertEquals(1, item.getOccurrence().intValue());
				});
		
	}
	
	@Test
	void getMaxOccurrencingChars_Empty() {
		final LinkedList<CharOccurenceEntry> charsOccurrence  = this.service.getCharsOccurrence(new ArrayList<>());
		assertTrue(charsOccurrence.size() == 0);
	}


	@Test
	void getMaxLengthToken() {
		final List<String> tokens = Arrays.asList("The", "bigggEstWord", "in", "the", "worlD");
		final TokenMaxLengthEntry maxLengthToken = this.service.getMaxLengthToken(tokens);
		
		assertEquals("bigggEstWord", maxLengthToken.getToken());
		assertNotEquals("bigggestword", maxLengthToken);
		assertEquals(12, maxLengthToken.getLength().intValue());
	}

	@Test
	void getMaxLengthToken_Empty() {
		final Executable closureWithEmpty = () -> this.service.getMaxLengthToken(new ArrayList());
		assertThrows(IllegalArgumentException.class, closureWithEmpty, "Any tokens presents!");
	}

	@Test
	void getTokensOccurrence() {
		final List<String> tokens = Arrays.asList("Here", "I", "am", "here", "and", "there", "here", "there");
		final LinkedList<TokenOccurrenceEntry> tokensOccurrence = this.service.getTokensOccurrence(tokens);
		
		assertEquals("here", tokensOccurrence.get(0).getToken());
		assertEquals(3, tokensOccurrence.get(0).getOccurrence().intValue());

		assertEquals("there", tokensOccurrence.get(1).getToken());
		assertEquals(2, tokensOccurrence.get(1).getOccurrence().intValue());
		
		final List<String> stringList = Arrays.asList("and", "i");
		
		tokensOccurrence.stream()
				.filter(entry -> stringList.contains(entry.getToken()))
				.forEach(item -> {
					assertEquals(1, item.getOccurrence().intValue());
				});
	}
	
	@Test
	void getTokensOccurrence_Empty() {
		final Executable closureWithEmpty = () -> this.service.getTokensOccurrence(new ArrayList());
		assertThrows(IllegalArgumentException.class, closureWithEmpty, "Any tokens presents!");
	}
	
}