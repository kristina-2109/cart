package com.demo.cart.controller;

import com.demo.cart.service.statistic.StatisticsService;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stats")
@AllArgsConstructor
public class StatisticsController {

  private final StatisticsService statisticsService;

  @PreAuthorize("hasAnyRole('ADMIN')")
  @GetMapping("/{itemId}")
  public ResponseEntity<Integer> getCarts(@PathVariable Long itemId,
      @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return ResponseEntity.ok().body(statisticsService.getNumSoldByDate(itemId, date));
  }

}
