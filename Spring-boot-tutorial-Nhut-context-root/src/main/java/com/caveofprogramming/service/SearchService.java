package com.caveofprogramming.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.caveofprogramming.model.dto.SearchResult;
import com.caveofprogramming.model.entity.Profile;
import com.caveofprogramming.model.repository.ProfileDao;

@Service
public class SearchService {
	@Value("${results.pagesize}")
	private int pageSize;
	
	@Autowired
	private ProfileDao profileDao;
	
//	public List<SearchResult> search(String text, int pageNumber) {
	public Page<SearchResult> search(String text, int pageNumber) {
//		List<Profile> listProfiles = profileDao.findByInterestsName(text);
//		listProfiles
//			.stream().forEach(System.out::println);
		PageRequest request = new PageRequest(pageNumber-1, pageSize);
//		return profileDao.findByInterestsNameContainingIgnoreCase(text, request)
//			.stream().map(SearchResult::new).collect(Collectors.toList());
		Page<Profile> results = profileDao.findDistinctByInterestsNameContainingIgnoreCase(text, request);
		Converter<Profile, SearchResult> converter = new Converter<Profile, SearchResult>() {
			@Override
			public SearchResult convert(Profile profile) {
				return new SearchResult(profile);
			}			
		};				
		return results.map(converter);
	}
}
