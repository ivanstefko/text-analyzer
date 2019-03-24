package com.att.app.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder
@Value
public class CharOccurenceEntry implements Serializable {
	private char token;
	private Integer occurrence;
}
