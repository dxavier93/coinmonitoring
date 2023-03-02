package com.example.coinmonitoring.repository;

import com.example.coinmonitoring.model.CoinStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinStatusRepository extends JpaRepository<CoinStatus, Long> {
}
