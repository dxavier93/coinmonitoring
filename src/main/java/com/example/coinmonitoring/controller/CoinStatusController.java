package com.example.coinmonitoring.controller;

import com.example.coinmonitoring.service.CoinStatusService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coin-status")
public class CoinStatusController {
  private final CoinStatusService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public BigDecimal findAll() {
    return service.findAll();
  }
}
