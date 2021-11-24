package com.care.root.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlDto {

	private String title;
	private String url;
	private UrlDto(String title, String url) {
		
	}
}
