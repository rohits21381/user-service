package com.user.app.util;


import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	
    private String errorMessage;
    private HttpStatus errorCode;
    private String timestamp;
    
}

