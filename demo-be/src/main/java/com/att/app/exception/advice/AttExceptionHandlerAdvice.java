package com.att.app.exception.advice;

import com.att.app.dto.ErrorDto;
import com.att.app.exception.builder.ExceptionBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import java.net.UnknownHostException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Log4j2
public class AttExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	
	
	/** The servlet request. */
	HttpServletRequest servletRequest;
	
	/** The exception builder. */
	ExceptionBuilder exceptionBuilder;
	

	public AttExceptionHandlerAdvice(final HttpServletRequest servletRequest, final ExceptionBuilder exceptionBuilder) {
		super();
		this.servletRequest = servletRequest;
		this.exceptionBuilder = exceptionBuilder;
	}
	
	/**
	 * Return custom status code 522 if UnknownHostException is propagated
	 *
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ UnknownHostException.class })
	public ResponseEntity<Object> handleTBExceptions(final UnknownHostException ex, final WebRequest request) {
		log.error(ex);
		final ErrorDto errorDto = exceptionBuilder.build(INTERNAL_SERVER_ERROR, 522, ex.getMessage(), ex);
		return handleExceptionInternal(ex, errorDto, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
	}
	
}
