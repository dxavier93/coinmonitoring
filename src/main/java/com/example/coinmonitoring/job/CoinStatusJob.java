package com.example.coinmonitoring.job;

import com.example.coinmonitoring.service.CoinStatusService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Data
@Slf4j
public class CoinStatusJob {
  private final CoinStatusService service;
  private static final long SECONDS = 1000;
  private static final long MINUTE = 60 * SECONDS;

  private static final long FIVE_MINUTES = 5 * MINUTE;

  private static final long ONE_HOUR = 60 * MINUTE;

  //@Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Lisbon")
  @Scheduled(fixedDelay = FIVE_MINUTES)
  private void jobCoinStatus() {
    LocalDateTime localDateTime = LocalDateTime.now();
    Optional<Double> optEuro = service.verifyEuroValue(localDateTime);

    optEuro.ifPresentOrElse(
        (euro) -> {
          log.info(String.format("Consulting euro value: [%s] at [%s]", euro, localDateTime));
        },
        () -> {
          log.warn(String.format("Error at consulting euro value at [%s]", localDateTime));
        }
    );
  }

  @Scheduled(fixedDelay = ONE_HOUR)
  private void jobUpdateCoinStatus() {
    LocalDateTime localDateTime = LocalDateTime.now();

    log.info(String.format("Updating database at [%s]", localDateTime));

    service.updateCoinStatus(localDateTime);
  }
}
