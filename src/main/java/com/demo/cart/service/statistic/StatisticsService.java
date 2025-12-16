package com.demo.cart.service.statistic;

import com.demo.cart.model.cart.Action;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class StatisticsService {

  private final MongoTemplate mongoTemplate;

  public Integer getNumSoldByDate(Long itemId, LocalDate date) {
    Instant startOfDay = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
    Instant endOfDay = date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();

    log.info("Creating statistics");

    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.match(Criteria.where("itemId").is(itemId)
            .and("action").is("ADD")
            .and("time").gte(Date.from(startOfDay)).lt(Date.from(endOfDay))),
        Aggregation.group("itemId").sum("quantity").as("totalQuantity")
    );

    AggregationResults<TotalQuantityResult> result = mongoTemplate.aggregate(
        aggregation,
        "statistics",
        TotalQuantityResult.class
    );

    TotalQuantityResult total = result.getUniqueMappedResult();
    log.info(String.valueOf(total));
    return total != null ? total.getTotalQuantity() : 0;
  }

  @Data
  public static class TotalQuantityResult {
    private Integer totalQuantity;
  }
}
