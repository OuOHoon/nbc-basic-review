package me.ouohoon.basicreview.global.exception;

import me.ouohoon.basicreview.global.dto.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<String>> baseExceptionHandler(BaseException e) {
        return ResponseEntity.badRequest().body(BaseResponse.failure(e.getErrorCode().getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse<String>> internalServerErrorHandler(RuntimeException e) {
        return ResponseEntity.internalServerError().body(BaseResponse.failure(e.getMessage()));
    }
}
