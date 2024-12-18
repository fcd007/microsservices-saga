package br.com.microservices.orchestrated.core.document;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {

  private String id;
  private List<OrderProducts> products;
  private LocalDateTime createdAt;
  private String transactionId;
  private double totalAmount;
  private int totalItems;
}
