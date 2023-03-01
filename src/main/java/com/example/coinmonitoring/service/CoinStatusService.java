package com.example.coinmonitoring.service;

import com.example.coinmonitoring.client.AwesomeClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoinStatusService {
  private static final String CURR = "bid";
  private static final String EURO = "EUR-BRL";
  private final ObjectMapper objectMapper;

  private final AwesomeClient client;

  public BigDecimal findAll() {
    try{
      log.info("Consulting awesome api client.");
      Object response = client.findActualCoin(EURO);
      String json = objectMapper.writeValueAsString(response);

      int index = json.indexOf(CURR) + CURR.length() + 3;
      String euro = json.substring(index, index + 6);
      BigDecimal bdEuro = BigDecimal.valueOf(Double.parseDouble(euro));

      return bdEuro;
    }catch (Exception ex){
      log.error("Error");
      return null;
    }
  }
}
