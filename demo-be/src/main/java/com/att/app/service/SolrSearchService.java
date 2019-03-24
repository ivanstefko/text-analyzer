package com.att.app.service;

import com.att.app.dto.CharOccurenceEntry;
import com.att.app.dto.SearchResultStatsDto;
import com.att.app.dto.TokenMaxLengthEntry;
import com.att.app.dto.TokenOccurrenceEntry;
import com.att.app.mapper.SearchResultStatsMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;

@Service
@Qualifier("SolrSearch")
// TODO needs to be implemented
public class SolrSearchService implements SearchService {
	
	// I don't want to use @Autowired annotation here because of is not
	// good practice for test compose pattern
	// Constructor dependency injection is much better
	
	SearchResultStatsMapper statsMapper;
	
	public SolrSearchService(final SearchResultStatsMapper statsMapper) {
		this.statsMapper = statsMapper;
	}
	
	
	@Override
	public SearchResultStatsDto getSearchResultStats(final String url) {
		return null;
	}
	
	@Override
	public LinkedList<CharOccurenceEntry> getCharsOccurrence(final String url) {
		return null;
	}
	
	@Override
	public TokenMaxLengthEntry getMaxLengthToken(final String url) {
		return null;
	}
	
	@Override
	public LinkedList<TokenOccurrenceEntry> getTokensOccurrence(final String url) {
		return null;
	}
}
