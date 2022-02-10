package com.soda.exception;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Umesh Kumar
 *
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceErrorMessage {

  private Integer statusCode;
  private String error;
  private String path;
  private String methodName;
  private String ControllerName;
  private String timeStamp;



}
