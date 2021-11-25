package com.care.root.service;

import java.util.ArrayList;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.care.root.dto.SearchMovieDto;
import com.care.root.movieURL.movieURL;

@Service
public class SearchService implements movieURL{ //변수를 쓰기 위해 
	
	RestTemplate movieSearch = new RestTemplate();
	/*
	public ArrayList<SearchMovieDto> getMovieView() {
		String test = movieSearch.getForObject(movieViewUrl+"/getMovies", String.class);	//movieViewUrl : "10001/search/"까지만
		System.out.println("view 결과 확인 : " + test);
		
		ArrayList<SearchMovieDto> list = new ArrayList<SearchMovieDto>();
		String movieURL = "http://localhost:10000/search/movieinfo/";	//제목 클릭 했을 때 이동하기 위한 경로, 상세보기
		for (int i = 1; i < 6; i++) {	//임시 서버
			SearchMovieDto dto = new SearchMovieDto();
			dto.setMovieId(i + "");
			dto.setMovieTitle("화성을 지켜라" + i);
			dto.setMovieDate("2200-05-1" + i);
			Link link = Link.of(movieURL + i);			//상세보기 링크 (=search/movieinfo/i)
			dto.add(link.withRel(dto.getMovieTitle()));	// 경로이름
			list.add(dto);
		}
		return list;
	}
	*/
	
	public SearchMovieDto[] getMovieView() {
		return movieSearch.getForObject(movieViewUrl+"/getMovies", SearchMovieDto[].class);	//배열 형태
	}
}
