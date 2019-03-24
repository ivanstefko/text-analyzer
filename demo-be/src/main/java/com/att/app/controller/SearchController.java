package com.att.app.controller;

import com.att.app.dto.CharOccurenceEntry;
import com.att.app.dto.SearchResultStatsDto;
import com.att.app.dto.TokenMaxLengthEntry;
import com.att.app.dto.TokenOccurrenceEntry;
import com.att.app.service.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/")
public class SearchController {

	@Autowired
	@Qualifier("InMemorySearch")
	SearchService searchService;

	@GetMapping()
	@ApiOperation(value = "Index page. Gets basic information about REST API.")
	public Map<String, String> index() {
		final Map<String, String> toReturn = new LinkedHashMap<>();
		toReturn.put("appName", "AT&T demo application");
		toReturn.put("version", "1.0");
		return toReturn;
	}
	
	@GetMapping("/searchAnalyze")
	@ApiOperation(value = "Gets the result statistics.")
	public ResponseEntity<SearchResultStatsDto> resultStats(
			@RequestParam(value = "url") final String url) {
		
		return new ResponseEntity<>(searchService.getSearchResultStats(url), HttpStatus.OK);
	}
	
	@GetMapping("/charsOccurence")
	@ApiOperation(value = "Gets the max occurrence of chars.")
	public LinkedList<CharOccurenceEntry> maxOccurrencingChars(
			@RequestParam(value = "url") final String url) {
		
		return searchService.getCharsOccurrence(url);
	}
	
	@GetMapping("/maxLengthToken")
	@ApiOperation(value = "Gets the max length of tokens.")
	public TokenMaxLengthEntry maxLengthToken(
			@RequestParam(value = "url") final String url) {
		
		return searchService.getMaxLengthToken(url);
	}
	
	@GetMapping("/tokensOccurrence")
	@ApiOperation(value = "Gets the tokens occurrences.")
	public List<TokenOccurrenceEntry> tokensOccurrence(
			@RequestParam(value = "url") final String url) {
		
		return searchService.getTokensOccurrence(url);
	}
	
}
