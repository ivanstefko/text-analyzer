package com.att.app.exception.builder;

import com.att.app.dto.ErrorDto;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


/**
 * The Class {@link ExceptionBuilder} provides basic build method to build error
 * message in required form.
 *
 * @author Ivan Stefko
 *
 */
@Component
public class ExceptionBuilder {

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";
	
	/**
	 * Builds the.
	 *
	 * @param status the status
	 * @param errorMessage the error message
	 * @return the error dto
	 */
	public ErrorDto build(final HttpStatus status, final String errorMessage) {
		return build(status, errorMessage, null); 
	}
	
	/**
	 * Builds the exception.
	 *
	 * @param status
	 *            the status
	 * @param errorMessage
	 *            the error message
	 * @param throwable
	 *            the throwable
	 * @return the error dto
	 */
	public ErrorDto build(final HttpStatus status, final String errorMessage, final Throwable throwable) {
		final ErrorDto toReturn = new ErrorDto();

		toReturn.setStatusee(status);
		toReturn.setCode(status.value());
		toReturn.setMessage(errorMessage);
		
		toReturn.setStackTrace((throwable != null) ? ExceptionUtils.getStackTrace(throwable) : EMPTY_STRING);

		return toReturn;
	}
	
	public ErrorDto build(final HttpStatus status, final int customStatusCode, final String errorMessage, final Throwable throwable) {
		final ErrorDto toReturn = new ErrorDto();
		
		toReturn.setStatusee(status);
		toReturn.setCode(customStatusCode);
		toReturn.setMessage(errorMessage);
		
		toReturn.setStackTrace((throwable != null) ? ExceptionUtils.getStackTrace(throwable) : EMPTY_STRING);
		
		return toReturn;
	}

}
