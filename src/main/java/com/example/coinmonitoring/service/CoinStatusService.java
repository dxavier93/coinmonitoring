package com.example.coinmonitoring.service;

import com.example.coinmonitoring.client.AwesomeClient;
import com.example.coinmonitoring.enums.CurrencyEnum;
import com.example.coinmonitoring.model.CoinStatus;
import com.example.coinmonitoring.repository.CoinStatusRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoinStatusService {
  private static final String CURR = "bid";
  private static final String EURO = "EUR-BRL";
  private static final Double EURO_OFFSET = 5.6;
  private static final String EMAIL = "deorgenes.xavier@gmail.com";
  private final ObjectMapper objectMapper;
  private final AwesomeClient client;
  private final EmailService emailService;
  private final CoinStatusRepository repository;

  public void updateCoinStatus(LocalDateTime localDateTime) {
    Optional<CoinStatus> maxValue = repository.findAll()
        .stream()
        .max(Comparator.comparing(CoinStatus::getCoinValue));

    repository.deleteAll();

    maxValue.ifPresent(
        (coinStatus) -> {
          repository.save(coinStatus);
          String message = String.format("Updating DB. Max euro value is [%s] at [%s]",
              coinStatus.getCoinValue(),
              localDateTime);
          emailService.sendMail(EMAIL, message);
        }
    );

  }

  public Optional<Double> verifyEuroValue(LocalDateTime localDateTime) {
    Optional<Double> optEuro = getEuroFromClient();

    optEuro.ifPresent((euro) -> {
      repository.save(CoinStatus.builder()
          .currency(CurrencyEnum.EURO)
          .coinValue(euro)
          .createdAt(localDateTime)
          .build());

      if (euro.compareTo(EURO_OFFSET) > 0) {
        String message = String.format("Euro value is above [%s]. Its value is: [%s] at [%s]", EURO_OFFSET, euro, localDateTime);
        emailService.sendMail(EMAIL, message);
      }
    });

    return optEuro;
  }

  public List<CoinStatus> findAll() {
    emailService.sendMail("deorgenes.junior@gmail.com", "Consulting euro values");
    return repository.findAll();
  }

  private Optional<Double> getEuroFromClient() {
    return getCoinFromClient(EURO);
  }

  private Optional<Double> getCoinFromClient(String coin) {
    try {
      log.info("Consulting awesome api client.");
      Object response = client.findActualCoin(coin);
      String json = objectMapper.writeValueAsString(response);

      int index = json.indexOf(CURR) + CURR.length() + 3;
      String euro = json.substring(index, index + 6);
      Double bdEuro = Double.parseDouble(euro);

      return Optional.of(bdEuro);
    } catch (Exception ex) {
      log.error("Error");
      return Optional.empty();
    }
  }
}
