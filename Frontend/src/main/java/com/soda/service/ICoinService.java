package com.soda.service;

import org.springframework.http.ResponseEntity;

public interface ICoinService {

  ResponseEntity<String> insert();

  ResponseEntity<String> remove();

}
