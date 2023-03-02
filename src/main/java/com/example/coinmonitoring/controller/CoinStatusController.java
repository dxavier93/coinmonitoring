package com.example.coinmonitoring.controller;

import com.example.coinmonitoring.model.CoinStatus;
import com.example.coinmonitoring.service.CoinStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController

public class CoinStatusController {
  private final CoinStatusService service;

  @GetMapping
  @RequestMapping("/")
  public String home(){
    return "Hello AWS";
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping("/coin-status")
  public List<CoinStatus> findAll() {
    return service.findAll();
  }
}
