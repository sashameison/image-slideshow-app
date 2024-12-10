package com.narozhnyi.slideshow.handler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.narozhnyi.slideshow.exception.SlideshowNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(SlideshowNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleSlideshowNotFoundException(
      SlideshowNotFoundException ex, HttpServletRequest request) {
    var errorResponse = new ErrorResponse(
        ex.getMessage(),
        NOT_FOUND.value(),
        request.getRequestURI());
    return new ResponseEntity<>(errorResponse, NOT_FOUND);
  }
}
