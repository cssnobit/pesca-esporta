package com.pescaria.api_rest.api.exceptionhandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final LocalDateTime localDateTime;

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException (IllegalArgumentException ex, 
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.INVALID_ARGUMENT;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(ex.getMessage())
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus statusCode, WebRequest request) {
		
		if(body == null) {
			body = Problem.builder()
					.title(statusCode.getReasonPhrase())
					.status(statusCode.value())
					.build();
		} else if(body instanceof String) {
			body = Problem.builder()
					.title((String) body)
					.status(statusCode.value())
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, 
			String detail) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime timestamp = localDateTime.now();
		
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.timestamp(localDateTime.parse(timestamp.format(formatter), formatter))
				.detail(detail);
	}
}
