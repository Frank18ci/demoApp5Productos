package org.carpio.com.demoapp5.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorResponse {
	private int status; // 200, 400, 404, etc.
    private String message; // error message
    private String error; // error type
    private String time; // timestamp
}
