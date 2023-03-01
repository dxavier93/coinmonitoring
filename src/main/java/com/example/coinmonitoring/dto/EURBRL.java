package com.example.coinmonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EURBRL implements Serializable {
  String code;
  String codein;
  String name;
  String high;
  String low;
  String varBid;
  String pctChange;
  String bid;
  String ask;
  String timestamp;
  String create_date;
}
