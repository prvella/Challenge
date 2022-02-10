package com.soda.service;

import org.springframework.http.ResponseEntity;
import com.soda.response.SodaResponse;

public interface ISodaService {

  ResponseEntity<String> despence();

  ResponseEntity<SodaResponse> status();

}
