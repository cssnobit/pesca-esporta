package com.pescaria.api_rest.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	INVALID_ARGUMENT("Invalid argument", "/invalid-argument"),
	MODEL_ERROR("Model Error", "/model-error");
	
	private String title;
	private String uri;
	
	ProblemType(String title, String path) {
		this.title = title;
		this.uri = "https://help.casanti.com.br" + path;
	}
}
