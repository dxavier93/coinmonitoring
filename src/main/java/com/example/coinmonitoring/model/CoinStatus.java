package com.example.coinmonitoring.model;

import com.example.coinmonitoring.enums.CurrencyEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "TABLE_COIN_STATUS")
@NoArgsConstructor
@AllArgsConstructor
public class CoinStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private CurrencyEnum currency;
  private Double coinValue;
  private LocalDateTime createdAt;
}

