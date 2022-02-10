package com.soda.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.soda.constants.ServiceConstant;
import com.soda.entity.SodaDetailsEntity;
import com.soda.entity.TransactionDetailsEntity;
import com.soda.exception.ServiceException;
import com.soda.repository.SodaDetailsRepository;
import com.soda.repository.TransactionDetailsRepository;
import com.soda.response.DespenceResponse;
import com.soda.response.SodaResponse;
import com.soda.service.ISodaService;
import com.soda.utility.CoinStatusHolder;

@Service
public class SodaServiceImpl implements ISodaService {

  @Autowired
  private CoinStatusHolder coinStatusHolder;


  @Autowired
  private TransactionDetailsRepository transactionDetailsRepository;

  @Autowired
  private SodaDetailsRepository sodaDetailsRepository;

  @Override
  @Transactional
  public ResponseEntity<String> despence() {
    if (coinStatusHolder.getStatus()) {
      List<SodaDetailsEntity> detailsEntities = sodaDetailsRepository.findAll();
      if (CollectionUtils.isNotEmpty(detailsEntities)) {
        SodaDetailsEntity detailsEntity = detailsEntities.get(0);
        transactionDetailsRepository
            .save(generateTransactionDetailsEntity.apply(sodaDetailsRepository.save(
                detailsEntity.setQuantity(Math.subtractExact(detailsEntity.getQuantity(), 1)))));
        coinStatusHolder.setStatus(Boolean.FALSE);
        return new ResponseEntity<>(ServiceConstant.SUCCESSFULLY_COMPLETED, HttpStatus.CREATED);
      } else {
        throw new ServiceException(HttpStatus.NO_CONTENT.value(),
            HttpStatus.NO_CONTENT.getReasonPhrase());
      }
    } else {
      throw new ServiceException(HttpStatus.NOT_ACCEPTABLE.value(),
          ServiceConstant.NO_COIN_INSERTED);
    }
  }

  @Override
  public ResponseEntity<SodaResponse> status() {
    List<SodaDetailsEntity> detailsEntities = sodaDetailsRepository.findAll();
    if (CollectionUtils.isNotEmpty(detailsEntities)) {
      SodaDetailsEntity detailsEntity = detailsEntities.get(0);
      return new ResponseEntity<>(generateSodaDetailsResponse.apply(detailsEntity), HttpStatus.OK);
    } else {
      throw new ServiceException(HttpStatus.NO_CONTENT.value(),
          HttpStatus.NO_CONTENT.getReasonPhrase());
    }
  }

  private static final Function<LocalDateTime, String> convertDateTimeIntoString =
      date -> getDateTimeFormat().format(date);

  private static final DateTimeFormatter getDateTimeFormat() {
    return DateTimeFormatter.ofPattern("EE, MMM dd, yyyy | hh:mm:ss");
  }

  private static final Function<Set<TransactionDetailsEntity>, List<DespenceResponse>> generateDespenceResponse =
      list -> {
        return list.stream()
            .map(despence -> new DespenceResponse(despence.getId(),
                convertDateTimeIntoString.apply(despence.getDespenceDate())))
            .collect(Collectors.toList());
      };


  private final Function<SodaDetailsEntity, TransactionDetailsEntity> generateTransactionDetailsEntity =
      detailsEntity -> new TransactionDetailsEntity(detailsEntity, LocalDateTime.now());

  private final Function<SodaDetailsEntity, SodaResponse> generateSodaDetailsResponse =
      detailsEntity -> new SodaResponse(detailsEntity.getName(), detailsEntity.getQuantity(),
          generateDespenceResponse.apply(detailsEntity.getTransactionDetailsEntities()));



}
