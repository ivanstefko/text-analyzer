package com.att.app.service;

import com.att.app.dto.CharOccurenceEntry;
import com.att.app.dto.SearchResultStatsDto;
import com.att.app.dto.TokenMaxLengthEntry;
import com.att.app.dto.TokenOccurrenceEntry;
import com.att.app.util.AppUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


@Service
@Qualifier("InMemorySearch")
@Log4j2
public class InMemorySearchServiceImpl implements SearchService {

	@Override
	public LinkedList<CharOccurenceEntry> getCharsOccurrence(final String url) {
		final List<String> tokens = getWebPageTokens(url);
		return getCharsOccurrence(tokens);
	}

	protected LinkedList<CharOccurenceEntry> getCharsOccurrence(final List<String> tokens) {
		final Map<Character, Integer> map = new HashMap<>();

		if (CollectionUtils.isNotEmpty(tokens)) {

			tokens.forEach(token -> {
				for (int i = 0; i < token.length(); i++) {
					final char ch = Character.toLowerCase(token.charAt(i));

					if (map.containsKey(ch)) {
						map.put(ch, map.get(ch) + 1);
					} else {
						map.put(ch, 1);
					}
				}
			});

		}
		
		// reorder according to occurrences
		final LinkedList<CharOccurenceEntry> orderedOccurrences = map.entrySet()
				.stream()
				.sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
				.map(e -> CharOccurenceEntry.builder().token(e.getKey()).occurrence(e.getValue()).build())
				.collect(Collectors.toCollection(LinkedList::new));

		return orderedOccurrences;
	}

	@Override
	public SearchResultStatsDto getSearchResultStats(final String url) {
		final List<String> tokens = getWebPageTokens(url);

		final SearchResultStatsDto dto = SearchResultStatsDto.builder()
				.maxToken(getMaxLengthToken(tokens))
				.charsOccurrence(getCharsOccurrence(tokens))
				.tokensOccurrence(getTokensOccurrence(tokens))
				.build();

		return dto;
	}

	@Override
	public TokenMaxLengthEntry getMaxLengthToken(final String url) {
		final List<String> tokens = getWebPageTokens(url);
		return getMaxLengthToken(tokens);
	}

	protected TokenMaxLengthEntry getMaxLengthToken(final List<String> tokens) {
		Validate.notEmpty(tokens, "Any tokens presents!");
		final String maxToken = tokens.stream().max(Comparator.comparingInt(String::length)).get();
		
		return TokenMaxLengthEntry.builder().token(maxToken).length(maxToken.length()).build();
	}

	@Override
	public LinkedList<TokenOccurrenceEntry> getTokensOccurrence(final String url) {
		final List<String> tokens = getWebPageTokens(url);
		return getTokensOccurrence(tokens);
	}

	protected LinkedList<TokenOccurrenceEntry> getTokensOccurrence(final List<String> tokens) {
		Validate.notEmpty(tokens, "Any tokens presents!");

		// not ordered
		final Map<String, Integer> frequencyMap = tokens.stream()
				.collect(toMap(
						s -> s.toLowerCase(),
						s -> 1,    // init value is 1
						Integer::sum)
				); // count aggregation

		// let's order frequencyMap according to occurrence
		final LinkedList<TokenOccurrenceEntry> ordered = frequencyMap.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.map(e -> TokenOccurrenceEntry.builder().token(e.getKey()).occurrence(e.getValue()).build())
				.collect(Collectors.toCollection(LinkedList::new));
		
		return ordered;
	}

	private List<String> getWebPageTokens(final String url) {
		Validate.isTrue(AppUtils.isValidUrl(url), "The given url is not valid!");

		final List<String> toReturn;

		try {
			final String text = Jsoup.connect(url).get().text();
			toReturn = AppUtils.tokenizeStringWithLucene(text);

		} catch (final IOException e) {
			log.error("Not able to create Lucene analyzer", e);
			throw new RuntimeException(e);
		}

		return toReturn;
	}
	
	public static void main(final String[] args) {
		final InMemorySearchServiceImpl service = new InMemorySearchServiceImpl();
		service.getWebPageTokens("http://www.sfasfasf.sk");
		System.out.println(".>>>> ");
	}

//	public void createLuceneIndex() throws IOException, ParseException {
////		Analyzer analyzer = new StandardAnalyzer();
//		IndexWriterConfig writerConfig = new IndexWriterConfig();
//		Directory index = new MMapDirectory(Paths.get("indexDirectory"));
//
//		IndexWriter indexWriter = new IndexWriter(index, writerConfig);
//		Document document = new Document();
//
//		FieldType fieldType = new FieldType();
//		fieldType.setStored(true);
//
//		document.add(new TextField("title", "my new title", Field.Store.YES));
//		document.add(new TextField("body", "here is my test, what do you think do do?", Field.Store.YES));
//		document.add(new SortedDocValuesField("title", new BytesRef("my new title")));
//
//		indexWriter.addDocument(document);
//		indexWriter.close();
//
//		System.out.println(">>>> ");
//
//		Directory directory = MMapDirectory.open(Paths.get("indexDirectory"));
//		IndexReader reader = DirectoryReader.open(directory);
//
//		String[] strings = directory.listAll();
//
//		IndexSearcher indexSearcher = new IndexSearcher(reader);
//
//		//Create search query
//		QueryParser qp = new QueryParser("body", new StandardAnalyzer());
//		Query query = qp.parse("do");
//		TopDocs search = indexSearcher.search(query, 10);
//
//		for (ScoreDoc sd : search.scoreDocs) {
//			Document d = indexSearcher.doc(sd.doc);
//			System.out.println("body : " + d.get("body") + ", Score : " + sd.score);
//		}
//
//
//		System.out.println(">>>>");
//	}

}
