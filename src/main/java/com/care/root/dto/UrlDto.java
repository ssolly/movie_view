package com.care.root.dto;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlDto extends RepresentationModel<UrlDto> {	//상속:부모가 가지고 있는 기능을 사용가능

	private String title;
	private String url;
	public UrlDto(String title, String url) {
		this.title=title;
		this.url=url;
	}
}
