package com.nimap.nimapinfotech.utils;

import org.springframework.http.ResponseEntity;
import com.nimap.nimapinfotech.entities.*;

public class CommonMethods {
    public static ResponseEntity<?> handleResponse(String msg, int statusCode, Object res ){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(msg);
        apiResponse.setStatusCode(statusCode);
        apiResponse.setResult(res);
        return ResponseEntity.status(statusCode).body(apiResponse);
    }
    public static ResponseEntity<?> handleResponse(String msg, int statusCode ){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(msg);
        apiResponse.setStatusCode(statusCode);
        return ResponseEntity.status(statusCode).body(apiResponse);
    }
}
