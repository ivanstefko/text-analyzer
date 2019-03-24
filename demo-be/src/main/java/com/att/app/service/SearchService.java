package com.att.app.service;

import com.att.app.dto.CharOccurenceEntry;
import com.att.app.dto.SearchResultStatsDto;
import com.att.app.dto.TokenMaxLengthEntry;
import com.att.app.dto.TokenOccurrenceEntry;

import java.util.LinkedList;

public interface SearchService {

	SearchResultStatsDto getSearchResultStats(String url);
	
	LinkedList<CharOccurenceEntry> getCharsOccurrence(String url);
	
	TokenMaxLengthEntry getMaxLengthToken(String url);
	
	LinkedList<TokenOccurrenceEntry> getTokensOccurrence(String url);
	

}
