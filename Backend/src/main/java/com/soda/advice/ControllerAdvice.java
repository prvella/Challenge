package com.soda.advice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import com.soda.exception.ResourceNotFoundException;
import com.soda.exception.ServiceErrorMessage;
import com.soda.exception.ServiceException;


@org.springframework.web.bind.annotation.ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvice {

  public static final DateFormat getDateTimeFormat() {
    return new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
  }

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ServiceErrorMessage> handleEmptyInputException(ServiceException ex,
      HandlerMethod handlerMethod, HttpServletRequest req) {
    ServiceErrorMessage error = new ServiceErrorMessage();
    error.setStatusCode(ex.getStatusCode());
    error.setError(ex.getLocalizedMessage());
    error.setControllerName(String.valueOf(handlerMethod.getMethod().getDeclaringClass()));
    error.setMethodName(handlerMethod.getMethod().getName());
    error.setTimeStamp(getDateTimeFormat().format(System.currentTimeMillis()));
    error.setPath(req.getRequestURL().toString());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ServiceErrorMessage> handleNullPointerException(HttpServletRequest req,
      NullPointerException ex, HandlerMethod handlerMethod) {
    ServiceErrorMessage error = new ServiceErrorMessage();
    error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    error.setError(ex.getLocalizedMessage());
    error.setControllerName(String.valueOf(handlerMethod.getMethod().getDeclaringClass()));
    error.setMethodName(handlerMethod.getMethod().getName());
    error.setTimeStamp(getDateTimeFormat().format(System.currentTimeMillis()));
    error.setPath(req.getRequestURL().toString());

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ServiceErrorMessage> resourceNotFoundException(ResourceNotFoundException ex,
      HttpServletRequest req, HandlerMethod handlerMethod) {
    ServiceErrorMessage error = new ServiceErrorMessage();
    error.setStatusCode(HttpStatus.NOT_FOUND.value());
    error.setError(ex.getLocalizedMessage());
    error.setControllerName(String.valueOf(handlerMethod.getMethod().getDeclaringClass()));
    error.setMethodName(handlerMethod.getMethod().getName());
    error.setTimeStamp(getDateTimeFormat().format(System.currentTimeMillis()));
    error.setPath(req.getRequestURL().toString());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  protected ResponseEntity<ServiceErrorMessage> handleConflict(IllegalArgumentException ex,
      HttpServletRequest req, HandlerMethod handlerMethod) {
    ServiceErrorMessage error = new ServiceErrorMessage();
    error.setStatusCode(HttpStatus.CONFLICT.value());
    error.setError(ex.getLocalizedMessage());
    error.setControllerName(String.valueOf(handlerMethod.getMethod().getDeclaringClass()));
    error.setMethodName(handlerMethod.getMethod().getName());
    error.setTimeStamp(getDateTimeFormat().format(System.currentTimeMillis()));
    error.setPath(req.getRequestURL().toString());
    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
  }
}
