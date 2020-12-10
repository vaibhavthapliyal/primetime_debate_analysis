package com.primetime.debate.analysis.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse implements Serializable {
	private static final long serialVersionUID = -7030058223962386687L;
	private String message;
	private boolean status;
	private int code = HttpStatus.OK.value();

}