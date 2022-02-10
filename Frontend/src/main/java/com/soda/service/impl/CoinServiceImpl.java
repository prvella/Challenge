package com.soda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.soda.constants.ServiceConstant;
import com.soda.exception.ServiceException;
import com.soda.service.ICoinService;
import com.soda.utility.CoinStatusHolder;

@Service
public class CoinServiceImpl implements ICoinService {

  @Autowired
  private CoinStatusHolder coinStatusHolder;

  @Override
  public ResponseEntity<String> insert() {
    if (!coinStatusHolder.getStatus()) {
      coinStatusHolder.setStatus(Boolean.TRUE);
      return new ResponseEntity<>(ServiceConstant.SUCCESSFULLY_INSERTED, HttpStatus.CREATED);
    } else {
      throw new ServiceException(HttpStatus.CONFLICT.value(), ServiceConstant.ALREADY_INSERTED);
    }
  }

  @Override
  public ResponseEntity<String> remove() {
    if (coinStatusHolder.getStatus()) {
      coinStatusHolder.setStatus(Boolean.FALSE);
      return new ResponseEntity<>(ServiceConstant.SUCCESSFULLY_COIN_REMOVED, HttpStatus.CREATED);
    } else {
      throw new ServiceException(HttpStatus.NO_CONTENT.value(), ServiceConstant.COIN_NOT_INSERTED);
    }
  }

}
