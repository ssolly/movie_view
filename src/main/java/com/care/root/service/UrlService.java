package com.care.root.service;

import java.util.ArrayList;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.care.root.dto.UrlDto;

@Service
public class UrlService {
	
	//서버단에서 경로 설정
	public ArrayList<UrlDto> getUrl() {
		ArrayList<UrlDto> urlList = new ArrayList<UrlDto>();
		
		String[] url= {"/home","/searchMovie"};
		String[] name= {"홈","영화검색"};
		String defaultURL = "http://localhost:10000";
		
		for(int i=0; i<url.length;i++) {
			UrlDto dto = new UrlDto(name[i], url[i]);
			Link link = Link.of(defaultURL+url[i]);		//boot문법, 기본 경로 
			dto.add(link.withRel(dto.getUrl()));		// getUrl : "/home", "/searchMovie"
			urlList.add(dto);
		}
		return urlList;
	}
}
