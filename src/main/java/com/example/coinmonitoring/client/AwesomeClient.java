package com.example.coinmonitoring.client;

import com.example.coinmonitoring.dto.AwesomeClientDto;
import com.example.coinmonitoring.dto.EURBRL;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AwesomeEconomyClient", url = "https://economia.awesomeapi.com.br")
public interface AwesomeClient {

  @GetMapping("/json/last/{coin}")
  Object findActualCoin(@PathVariable(value = "coin") String coin);
}
