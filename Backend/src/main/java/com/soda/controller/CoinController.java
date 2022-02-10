package com.soda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.soda.service.ICoinService;
import com.soda.utility.CoinStatusHolder;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/coin/")
public class CoinController {

  @Autowired
  private ICoinService coinService;

  @Autowired
  private CoinStatusHolder coinStatusHolder;

  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Coin successfully inserted", response = String.class),
      @ApiResponse(code = 409, message = "Coin already inserted please remove coin first",
          responseContainer = "")})
  @GetMapping("/insert")
  public ResponseEntity<String> insert() {
    return coinService.insert();
  }

  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Coin successfully removed", response = String.class),
      @ApiResponse(code = 204, message = "Please insert coin first", responseContainer = "")})
  @GetMapping("/remove")
  public ResponseEntity<String> remove() {
    return coinService.remove();
  }

  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully fetched", response = Boolean.class)})
  @GetMapping("/status")
  public ResponseEntity<Boolean> status() {
    return new ResponseEntity<>(coinStatusHolder.getStatus(), HttpStatus.OK);
  }

}
