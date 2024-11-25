package br.com.microservices.orchestrated.orchestratorservice;

import lombok.*;

@AllArgsConstructor
@Getter
public enum TopicsEnum {

  START_SAGA("start-saga"),
  BASE_ORCHESTRATOR("orchestrator"),
  FINISH_SUCCESS("finish-success"),
  FINISH_FAIL("finish-fail"),
  PRODUCT_VALIDATION_SUCCESS("product-validation-success"),
  PRODUCT_VALIDATION_FAIL("product-validation-fail"),
  INVENTORY_SUCCESS("inventory-success"),
  INVENTORY_FAIL("inventory-fail"),
  PAYMENT_SUCCESS("payment-success"),
  PAYMENT_FAIL("payment-fail");

  private String topic;
}
