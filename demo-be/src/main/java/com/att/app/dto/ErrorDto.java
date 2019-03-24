package com.att.app.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class {@link ErrorDto} is used like container to pass information about
 * error.
 * 
 * @author Ivan Stefko
 */
@Data
@NoArgsConstructor
public class ErrorDto implements Serializable {
	
	/** The status. */
	private HttpStatus statusee;

	/** The code. */
	private int code;

	/** The message. */
	private String message;

	/** The stack trace. */
	private String stackTrace;

}
