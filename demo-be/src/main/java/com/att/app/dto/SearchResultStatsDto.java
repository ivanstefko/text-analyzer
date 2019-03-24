package com.att.app.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.LinkedList;

@Builder
@Value
public class SearchResultStatsDto implements Serializable {
	
	private TokenMaxLengthEntry maxToken;
	private LinkedList<TokenOccurrenceEntry> tokensOccurrence;
	private LinkedList<CharOccurenceEntry> charsOccurrence;
	
}
