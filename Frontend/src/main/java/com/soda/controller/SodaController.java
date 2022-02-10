package com.soda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.soda.response.SodaResponse;
import com.soda.service.ISodaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(maxAge=3600)
@RequestMapping("/soda/")
public class SodaController {

  @Autowired
  private ISodaService sodaService;

  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Request successfully completed", response = String.class),
      @ApiResponse(code = 406, message = "No coin inserted please insert the coin",
          responseContainer = ""),
      @ApiResponse(code = 204, message = "No content available", response = String.class)})
  @PostMapping("/despence")
  public ResponseEntity<String> despence() {
    return sodaService.despence();
  }

  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Request successfully fetched",
          response = SodaResponse.class),
      @ApiResponse(code = 204, message = "No content available", responseContainer = "")})
  @GetMapping("/status")
  public ResponseEntity<SodaResponse> status() {
    return sodaService.status();
  }

}
