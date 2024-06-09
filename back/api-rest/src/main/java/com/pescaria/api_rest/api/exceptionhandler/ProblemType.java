package com.pescaria.api_rest.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	INVALID_ARGUMENT("Invalid argument", "/invalid-argument");
	
	private String title;
	private String uri;
	
	ProblemType(String title, String path) {
		this.title = title;
		this.uri = "https://help.casanti.com.br" + path;
	}
}
