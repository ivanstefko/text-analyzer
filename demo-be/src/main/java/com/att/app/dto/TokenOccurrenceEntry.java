package com.att.app.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
public class TokenOccurrenceEntry implements Serializable {
	private String token;
	private Integer occurrence;
}
